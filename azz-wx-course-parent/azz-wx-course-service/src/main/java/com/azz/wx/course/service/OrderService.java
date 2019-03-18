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
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.azz.util.SystemSeqUtils;
import com.azz.wx.course.mapper.ClientUserMapper;
import com.azz.wx.course.mapper.WxCourseEvaluationMapper;
import com.azz.wx.course.mapper.WxCourseOrderItemMapper;
import com.azz.wx.course.mapper.WxCourseOrderMapper;
import com.azz.wx.course.mapper.WxCourseOrderStatusMapper;
import com.azz.wx.course.mapper.WxCourseStartClasRecordMapper;
import com.azz.wx.course.pojo.ClientUser;
import com.azz.wx.course.pojo.WxCourseEvaluation;
import com.azz.wx.course.pojo.WxCourseOrder;
import com.azz.wx.course.pojo.WxCourseOrderItem;
import com.azz.wx.course.pojo.WxCourseOrderStatus;
import com.azz.wx.course.pojo.bo.CallBackParam;
import com.azz.wx.course.pojo.bo.ChangeOrderStatusParam;
import com.azz.wx.course.pojo.bo.EvaluateCourseParam;
import com.azz.wx.course.pojo.bo.PayOrderParam;
import com.azz.wx.course.pojo.bo.SearchCourseOrderParam;
import com.azz.wx.course.pojo.bo.SearchPersonalOrderParam;
import com.azz.wx.course.pojo.vo.CourseOrderDetail;
import com.azz.wx.course.pojo.vo.CourseOrderInfo;
import com.azz.wx.course.pojo.vo.PayOrderInfo;
import com.azz.wx.course.pojo.vo.PlatformCourseOrderDetail;
import com.azz.wx.course.pojo.vo.PlatformCourseOrderInfo;
import com.azz.wx.course.pojo.vo.StartClassRecord;
import com.azz.wx.course.pojo.vo.WxUserInfo;
import com.github.pagehelper.PageHelper;

/**
 * <P>微信订单</P>
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
	private WxCourseOrderStatusMapper wxCourseOrderStatusMapper;
	
	@Autowired
	private WxCourseOrderItemMapper wxCourseOrderItemMapper;
	
	@Autowired
	private ClientUserMapper clientUserMapper;
	
	@Autowired
	private WxCourseEvaluationMapper wxCourseEvaluationMapper;
	
	@Autowired
	private DbSequenceService dbSequenceService;
	
	
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
		String orderCode = SystemSeqUtils.getSeq(dbSequenceService.getWxCourseOrderSequenceNumber());
		// 新增一个待确认的商户订单
		ClientUser user = clientUserMapper.getClientUserByClientUserCode(param.getClientUserCode());
		WxUserInfo wxUserInfo = clientUserMapper.getWxUserInfo(param.getClientUserCode());
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
				.openid(wxUserInfo.getOpenid())
				.nickName(wxUserInfo.getNickName())
				.wxBindingPhone(user.getPhoneNumber())
				.build();
		wxCourseOrderMapper.insertSelective(orderRecord);
		
		// 插入课程订单状态变化记录
		WxCourseOrderStatus record = WxCourseOrderStatus.builder()
				.createTime(nowDate)
				.creator(param.getClientUserCode())
				.orderCode(orderCode)
				.orderStatusId(CourseOrderStatus.NOT_PAID.getValue())
				.build();
		wxCourseOrderStatusMapper.insertSelective(record);
		
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
	 * <p>查询课程订单详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:34:28
	 */
	public JsonResult<CourseOrderDetail> getCourseOrderDetail(String orderCode){
		if(StringUtils.isBlank(orderCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "请选择课程订单");
		}
		CourseOrderDetail order = wxCourseOrderMapper.getCourseOrderDetail(orderCode);
		if(order == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程订单不存在");
		}
		return JsonResult.successJsonResult(order);
	} 
	
	/**
	 * 
	 * <p>评价课程</p>
	 * @return
	 * @author 黄智聪  2019年1月23日 上午10:48:25
	 */
	public JsonResult<String> evaluateCourse(@RequestBody EvaluateCourseParam param){
		JSR303ValidateUtils.validate(param);
		CourseOrderDetail order = wxCourseOrderMapper.getCourseOrderDetail(param.getOrderCode());
		if(order == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程订单不存在");
		}
		int count = wxCourseEvaluationMapper.countOrderEvaluation(param.getOrderCode());
		if(count > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "此订单已评价");
		}
		Date nowDate = new Date();
		// 新增评论记录
		WxCourseEvaluation evaluationRecord = WxCourseEvaluation.builder()
				.courseCode(order.getCourseCode())
				.createTime(nowDate)
				.creator(param.getClientUserCode())
				.evaluationCode("E" + System.currentTimeMillis())
				.evaluationContent(param.getEvaluationContent())
				.grade(param.getGrade())
				.orderCode(param.getOrderCode())
				.startClassCode(order.getStartClassCode())
				.userCode(param.getClientUserCode())
				.build();
		wxCourseEvaluationMapper.insertSelective(evaluationRecord);
		
		// 将订单状态改为已完成且已评论
		WxCourseOrder orderRecord = WxCourseOrder.builder()
				.orderCode(param.getOrderCode())
				.orderStatusId(CourseOrderStatus.FINISHED_EVALUATED.getValue())
				.modifier(param.getClientUserCode())
				.modifyTime(nowDate)
				.build();
		wxCourseOrderMapper.updateByOrderCode(orderRecord);
		
		// 插入课程订单状态变化记录
		WxCourseOrderStatus record = WxCourseOrderStatus.builder()
				.createTime(nowDate)
				.creator(param.getClientUserCode())
				.orderCode(param.getOrderCode())
				.orderStatusId(CourseOrderStatus.FINISHED_EVALUATED.getValue())
				.build();
		wxCourseOrderStatusMapper.insertSelective(record);
		
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>取消课程订单</p>
	 * @return
	 * @author 黄智聪  2019年1月23日 上午10:48:25
	 */
	public JsonResult<String> cancelCourseOrder(@RequestBody ChangeOrderStatusParam param){
		JSR303ValidateUtils.validate(param);
		PayOrderInfo info = wxCourseOrderMapper.getPayOrderInfo(param.getOrderCode());
		if(info == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程订单不存在");
		}
		// 判断订单是否处于待支付状态
		if(info.getOrderStatus() != CourseOrderStatus.NOT_PAID.getValue()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程订单状态异常");
		}
		
		Date nowDate = new Date();
		// 将订单状态改为已关闭
		WxCourseOrder orderRecord = WxCourseOrder.builder()
				.orderCode(param.getOrderCode())
				.orderStatusId(CourseOrderStatus.CLOSED.getValue())
				.modifier(param.getClientUserCode())
				.modifyTime(nowDate)
				.build();
		wxCourseOrderMapper.updateByOrderCode(orderRecord);
		
		// 插入课程订单状态变化记录
		WxCourseOrderStatus record = WxCourseOrderStatus.builder()
				.createTime(nowDate)
				.creator(param.getClientUserCode())
				.orderCode(param.getOrderCode())
				.orderStatusId(CourseOrderStatus.CLOSED.getValue())
				.build();
		wxCourseOrderStatusMapper.insertSelective(record);
		
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>确认课程订单</p>
	 * @return
	 * @author 黄智聪  2019年1月23日 上午10:48:25
	 */
	public JsonResult<String> confirmCourseOrder(@RequestBody ChangeOrderStatusParam param){
		JSR303ValidateUtils.validate(param);
		PayOrderInfo info = wxCourseOrderMapper.getPayOrderInfo(param.getOrderCode());
		if(info == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程订单不存在");
		}
		// 判断订单是否处于待确认状态
		if(info.getOrderStatus() != CourseOrderStatus.NOT_CONFIRMED.getValue()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程订单状态异常");
		}
		
		Date nowDate = new Date();
		// 将订单状态改为已完成但未评价
		WxCourseOrder orderRecord = WxCourseOrder.builder()
				.orderCode(param.getOrderCode())
				.orderStatusId(CourseOrderStatus.FINISHED_NOT_EVALUATED.getValue())
				.modifier(param.getClientUserCode())
				.modifyTime(nowDate)
				.build();
		wxCourseOrderMapper.updateByOrderCode(orderRecord);
		
		// 插入课程订单状态变化记录
		WxCourseOrderStatus record = WxCourseOrderStatus.builder()
				.createTime(nowDate)
				.creator(param.getClientUserCode())
				.orderCode(param.getOrderCode())
				.orderStatusId(CourseOrderStatus.FINISHED_NOT_EVALUATED.getValue())
				.build();
		wxCourseOrderStatusMapper.insertSelective(record);
		
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>平台端确认课程订单，将待处理状态改为待确认状态</p>
	 * @return
	 * @author 黄智聪  2019年1月23日 上午10:48:25
	 */
	public JsonResult<String> platformConfirmCourseOrder(@RequestBody ChangeOrderStatusParam param){
		JSR303ValidateUtils.validate(param);
		PayOrderInfo info = wxCourseOrderMapper.getPayOrderInfo(param.getOrderCode());
		if(info == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程订单不存在");
		}
		// 判断订单是否处于待确认状态
		if(info.getOrderStatus() != CourseOrderStatus.PENDING.getValue()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程订单状态异常");
		}
		
		Date nowDate = new Date();
		// 将订单状态改为已完成但未评价
		WxCourseOrder orderRecord = WxCourseOrder.builder()
				.orderCode(param.getOrderCode())
				.orderStatusId(CourseOrderStatus.NOT_CONFIRMED.getValue())
				.modifier(param.getClientUserCode())
				.modifyTime(nowDate)
				.build();
		wxCourseOrderMapper.updateByOrderCode(orderRecord);
		
		// 插入课程订单状态变化记录
		WxCourseOrderStatus record = WxCourseOrderStatus.builder()
				.createTime(nowDate)
				.creator(param.getClientUserCode())
				.orderCode(param.getOrderCode())
				.orderStatusId(CourseOrderStatus.NOT_CONFIRMED.getValue())
				.build();
		wxCourseOrderStatusMapper.insertSelective(record);
		
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>平台端查询课程订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:15:33
	 */
	public JsonResult<Pagination<PlatformCourseOrderInfo>> getPlatformCourseOrders(@RequestBody SearchCourseOrderParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		if(param.getOrderStatusId() != null) {
			if(!CourseOrderStatus.checkStatusExist(param.getOrderStatusId())) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "查询的订单状态不存在");
			}
		}
		List<PlatformCourseOrderInfo> infos = wxCourseOrderMapper.getPlatformCourseOrders(param);
		return JsonResult.successJsonResult(new Pagination<PlatformCourseOrderInfo>(infos));
	}
	
	/**
	 * 
	 * <p>平台端查询课程订单详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:34:28
	 */
	public JsonResult<PlatformCourseOrderDetail> getPlatformCourseOrderDetail(String orderCode){
		if(StringUtils.isBlank(orderCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "请选择课程订单");
		}
		PlatformCourseOrderDetail order = wxCourseOrderMapper.getPlatformCourseOrderDetail(orderCode);
		if(order == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程订单不存在");
		}
		return JsonResult.successJsonResult(order);
	} 
	
	/**
	 * 
	 * <p>关闭订单--6小时未支付的待支付订单，状态改为已关闭</p>
	 * @return
	 * @author 黄智聪  2018年11月15日 上午10:38:19
	 */
	public JsonResult<String> closeCourseOrders(){
		// 查询6小时未支付的客户订单id集合
		List<String> orderCodes = wxCourseOrderMapper.getSixHoursNotPaidCourseOrderCodes(CourseOrderStatus.NOT_PAID.getValue());
		Date nowDate = new Date();
		for (String orderCode : orderCodes) {
			// 将订单状态改为已完成但未评价
			WxCourseOrder orderRecord = WxCourseOrder.builder()
					.orderCode(orderCode)
					.orderStatusId(CourseOrderStatus.CLOSED.getValue())
					.modifyTime(nowDate)
					.remark("6小时未支付，订单状态改为已关闭")
					.build();
			wxCourseOrderMapper.updateByOrderCode(orderRecord);
			
			// 插入课程订单状态变化记录
			WxCourseOrderStatus record = WxCourseOrderStatus.builder()
					.createTime(nowDate)
					.orderCode(orderCode)
					.orderStatusId(CourseOrderStatus.CLOSED.getValue())
					.remark("6小时未支付，订单状态改为已关闭")
					.build();
			wxCourseOrderStatusMapper.insertSelective(record);
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>查询个人报名课程信息列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月21日 下午7:26:26
	 */
	public JsonResult<Pagination<CourseOrderInfo>> getPersonalCourseOrders(@RequestBody SearchPersonalOrderParam param){
		JSR303ValidateUtils.validate(param);
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<CourseOrderInfo> infos = wxCourseOrderMapper.getPersonalCourseOrders(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>微信订单支付成功后的操作</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月26日 下午3:41:55
	 */
	public JsonResult<String> courseOrderPaySuccessOpt(@RequestBody CallBackParam param){
		JSR303ValidateUtils.validate(param);
		String orderCode = param.getOrderCode();
		PayOrderInfo info = wxCourseOrderMapper.getPayOrderInfo(param.getOrderCode());
		if(info == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程订单不存在");
		}
		// 判断订单是否处于待支付状态
		if(info.getOrderStatus() != CourseOrderStatus.NOT_PAID.getValue()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程订单状态异常");
		}
		Date nowDate = new Date();
		// 修改订单
		WxCourseOrder orderRecord = WxCourseOrder.builder()
				.orderCode(orderCode)
				.orderStatusId(CourseOrderStatus.PENDING.getValue())
				.paymentMethod(param.getPayMethod())
				.paymentType(param.getOrderType())
				.paymentStatus(PayStatus.PAY_SUCCESS.getValue())
				.modifyTime(nowDate)
				.build();
		wxCourseOrderMapper.updateByOrderCode(orderRecord);
		
		// 插入课程订单状态变化记录
		WxCourseOrderStatus record = WxCourseOrderStatus.builder()
				.createTime(nowDate)
				.orderCode(orderCode)
				.orderStatusId(CourseOrderStatus.PENDING.getValue())
				.remark("订单支付成功，状态变更为待处理")
				.build();
		wxCourseOrderStatusMapper.insertSelective(record);
		
		return JsonResult.successJsonResult();
	}
	
	/*******************************  微信课程首页接口     end  ********************************/

}

