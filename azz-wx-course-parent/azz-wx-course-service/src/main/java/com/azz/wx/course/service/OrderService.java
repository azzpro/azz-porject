/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月22日 下午5:10:02
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.ClientConstants.PayMethod;
import com.azz.core.constants.ClientConstants.PayStatus;
import com.azz.core.constants.WxCourseConstants.CourseOrderStatus;
import com.azz.exception.JSR303ValidationException;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.azz.wx.course.mapper.ClientUserMapper;
import com.azz.wx.course.mapper.WxCourseOrderItemMapper;
import com.azz.wx.course.mapper.WxCourseOrderMapper;
import com.azz.wx.course.mapper.WxCourseStartClasRecordMapper;
import com.azz.wx.course.pojo.ClientUser;
import com.azz.wx.course.pojo.WxCourseOrder;
import com.azz.wx.course.pojo.WxCourseOrderItem;
import com.azz.wx.course.pojo.bo.PayOrderParam;
import com.azz.wx.course.pojo.bo.SearchCourseOrderParam;
import com.azz.wx.course.pojo.vo.CourseOrderInfo;
import com.azz.wx.course.pojo.vo.PayOrderInfo;
import com.azz.wx.course.pojo.vo.StartClassRecord;
import com.github.pagehelper.PageHelper;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月22日 下午5:10:02
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class OrderService {
	
	@Autowired
	private WxCourseStartClasRecordMapper wxCourseStartClasRecordMapper;

	@Autowired
	private WxCourseOrderMapper wxCourseOrderMapper;
	
	@Autowired
	private WxCourseOrderItemMapper wxCourseOrderItemMapper;
	
	@Autowired
	private ClientUserMapper clientUserMapper;
	
	/*******************************  微信课程首页接口   start ********************************/
	
	/**
	 * 
	 * <p>生成去付款的课程订单信息</p>
	 * @return
	 * @author 黄智聪  2018年11月24日 上午10:58:45
	 */
	public JsonResult<PayOrderInfo> generatePayOrderInfo(@RequestBody PayOrderParam param){
		JSR303ValidateUtils.validate(param);
		StartClassRecord startClassRecord = wxCourseStartClasRecordMapper.getStartClassRecordDetail(param.getStartClassCode());
		if(startClassRecord == null){
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "开课信息不存在");
		}
		String orderCode = System.currentTimeMillis() + ""; // TOTO
		ClientUser user = clientUserMapper.getClientUserByClientUserCode(param.getClientUserCode());
		// 下单人：若姓名为空，取手机号
		String orderCreator = StringUtils.isBlank(user.getClientUserName()) ? user.getPhoneNumber() : user.getClientUserName();
		Date nowDate = new Date();
		// 插入课程订单记录
		WxCourseOrder orderRecord = WxCourseOrder.builder()
				.createTime(nowDate)
				.creator(param.getClientUserCode())
				.applyInfoCode(param.getApplyInfoCode())
				.grandTotal(startClassRecord.getPrice())
				.orderCode(orderCode)
				.orderCreator(orderCreator)
				.orderStatusId(CourseOrderStatus.NOT_PAID.getValue())
				.paymentMethod(PayMethod.ONLINE.getValue())
				.paymentStatus(PayStatus.NOT_PAID.getValue())
				.build();
		wxCourseOrderMapper.insertSelective(orderRecord);
		
		// 插入订单细项记录
		WxCourseOrderItem orderItemRecord = WxCourseOrderItem.builder()
				.brandName(startClassRecord.getBrandName())
				.classificationName(startClassRecord.getClassificationName())
				.courseCode(startClassRecord.getCourseCode())
				.courseName(startClassRecord.getCourseName())
				.courseParamsName(startClassRecord.getCourseParamsName())
				.createTime(nowDate)
				.creator(param.getClientUserCode())
				.location(startClassRecord.getLocation())
				.latitude(startClassRecord.getLatitude())
				.longitude(startClassRecord.getLongitude())
				.hours(startClassRecord.getHours())
				.orderCode(orderCode)
				.peopleNumber(startClassRecord.getPeopleNumber())
				.price(startClassRecord.getPrice())
				.quantity(1)
				.startClassCode(startClassRecord.getStartClassCode())
				.startClassName(startClassRecord.getStartClassName())
				.startClassTime(startClassRecord.getStartClassTime())
				.build();
		wxCourseOrderItemMapper.insertSelective(orderItemRecord);
		PayOrderInfo info = PayOrderInfo.builder()
				.courseName(startClassRecord.getCourseName())
				.orderCode(orderCode)
				.orderStatus(CourseOrderStatus.NOT_PAID.getValue())
				.price(startClassRecord.getPrice())
				.startClassCode(startClassRecord.getStartClassCode())
				.startClassName(startClassRecord.getStartClassName())
				.startClassTime(startClassRecord.getStartClassTime())
				.build();
		return JsonResult.successJsonResult(info);
	}
	
	/**
	 * 
	 * <p>查询待支付的课程订单信息</p>
	 * @return
	 * @author 黄智聪  2018年11月24日 上午10:58:45
	 */
	public JsonResult<PayOrderInfo> getPayOrderInfo(String orderCode){
		if(StringUtils.isBlank(orderCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "请选择课程订单");
		}
		PayOrderInfo info = wxCourseOrderMapper.getPayOrderInfo(orderCode);
		if(info == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程订单不存在");
		}
		// 判断订单是否处于待支付状态
		if(info.getOrderStatus() != CourseOrderStatus.NOT_PAID.getValue()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程订单状态异常");
		}
		return JsonResult.successJsonResult(info);
	}
	
	/**
	 * 
	 * <p>查询个人课程订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:15:33
	 */
	public JsonResult<Pagination<CourseOrderInfo>> getCourseOrders(@RequestBody SearchCourseOrderParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		if(param.getOrderStatusId() != null) {
			if(!CourseOrderStatus.checkStatusExist(param.getOrderStatusId())) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "查询的订单状态不存在");
			}
		}
		List<CourseOrderInfo> infos = wxCourseOrderMapper.getCourseOrders(param);
		return JsonResult.successJsonResult(new Pagination<CourseOrderInfo>(infos));
	}
	
	/**
	 * 
	 * <p>TODO</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:34:28
	 */
	public JsonResult<Pagination<CourseOrderInfo>> getCourseOrderDetail(@RequestBody SearchCourseOrderParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		if(param.getOrderStatusId() != null) {
			if(!CourseOrderStatus.checkStatusExist(param.getOrderStatusId())) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "查询的订单状态不存在");
			}
		}
		List<CourseOrderInfo> infos = wxCourseOrderMapper.getCourseOrders(param);
		return JsonResult.successJsonResult(new Pagination<CourseOrderInfo>(infos));
	} 
	
	
	
	
	/*******************************  微信课程首页接口     end  ********************************/

}

