/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 上午10:48:02
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
import com.azz.core.constants.WxCourseConstants.StartClassRecordStatus;
import com.azz.exception.JSR303ValidationException;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.azz.util.SystemSeqUtils;
import com.azz.wx.course.mapper.WxCourseMapper;
import com.azz.wx.course.mapper.WxCourseStartClasRecordMapper;
import com.azz.wx.course.pojo.WxCourse;
import com.azz.wx.course.pojo.WxCourseStartClasRecord;
import com.azz.wx.course.pojo.bo.AddStartClassRecordParam;
import com.azz.wx.course.pojo.bo.EditStartClassRecordParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelStartClassRecordParam;
import com.azz.wx.course.pojo.bo.SearchStartClassRecordParam;
import com.azz.wx.course.pojo.vo.StartClassRecord;
import com.github.pagehelper.PageHelper;

/**
 * <P>开课业务</P>
 * @version 1.0
 * @author 黄智聪  2019年1月4日 上午10:48:02
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class StartClassService {
	
	@Autowired
	private WxCourseMapper wxCourseMapper;
	
	@Autowired
	private WxCourseStartClasRecordMapper wxCourseStartClasRecordMapper;
	
	@Autowired
    private DbSequenceService dbSequenceService; 
	
	/**
	 * 
	 * <p>查询开课信息列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:37:39
	 */
	public JsonResult<Pagination<StartClassRecord>> getStartClassRecords(@RequestBody SearchStartClassRecordParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<StartClassRecord> infos = wxCourseStartClasRecordMapper.getStartClassRecords(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询开课信息详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:37:39
	 */
	public JsonResult<StartClassRecord> getStartClassRecordDetail(String startClassCode){
		if(StringUtils.isBlank(startClassCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "开课编码不允许为空");
		}
		StartClassRecord detail = wxCourseStartClasRecordMapper.getStartClassRecordDetail(startClassCode);
		if(detail == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "开课信息不存在");
		}
		return JsonResult.successJsonResult(detail);
	}
	
	/**
	 * 
	 * <p>新增开课信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:57:17
	 */
	public JsonResult<String> addStartClassRecord(@RequestBody AddStartClassRecordParam param){
		JSR303ValidateUtils.validate(param);
		WxCourse course = wxCourseMapper.selectByCourseCode(param.getCourseCode());
		if(course == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "所属课程不存在");
		}
		String startClassCode = SystemSeqUtils.getSeq(dbSequenceService.getWxClassBeginNumber());
		Date nowDate = new Date();
		WxCourseStartClasRecord startClassRecord = WxCourseStartClasRecord.builder()
				.courseCode(param.getCourseCode())
				.createTime(nowDate)
				.creator(param.getCreator())
				.hours(param.getHours())
				.latitude(param.getLatitude())
				.location(param.getLocation())
				.longitude(param.getLongitude())
				.peopleNumber(param.getPeopleNumber())
				.price(param.getPrice())
				.room(param.getRoom())
				.startClassCode(startClassCode)
				.startClassName(param.getStartClassName())
				.startClassTime(param.getStartClassTime())
				.build();
		wxCourseStartClasRecordMapper.insertSelective(startClassRecord);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>修改开课信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:57:17
	 */
	public JsonResult<String> editStartClassRecord(@RequestBody EditStartClassRecordParam param){
		JSR303ValidateUtils.validate(param);
		WxCourseStartClasRecord classRecord = wxCourseStartClasRecordMapper.selectByStartClassCode(param.getStartClassCode());
		if(classRecord == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "开课记录不存在");
		}
		WxCourse course = wxCourseMapper.selectByCourseCode(param.getCourseCode());
		if(course == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "所属课程不存在");
		}
		Date nowDate = new Date();
		WxCourseStartClasRecord startClassRecord = WxCourseStartClasRecord.builder()
				.id(classRecord.getId())
				.courseCode(param.getCourseCode())
				.hours(param.getHours())
				.latitude(param.getLatitude())
				.location(param.getLocation())
				.longitude(param.getLongitude())
				.peopleNumber(param.getPeopleNumber())
				.price(param.getPrice())
				.room(param.getRoom())
				.startClassTime(param.getStartClassTime())
				.modifier(param.getModifier())
				.modifyTime(nowDate)
				.build();
		wxCourseStartClasRecordMapper.updateByPrimaryKeySelective(startClassRecord);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>上架、下架或删除开课信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	public JsonResult<String> putOnOrPutOffOrDelStartClassRecord(@RequestBody PutOnOrPutOffOrDelStartClassRecordParam param){
		// 参数校验
		JSR303ValidateUtils.validate(param);
		String startClassCode = param.getStartClassCode();
		WxCourseStartClasRecord record = wxCourseStartClasRecordMapper.selectByStartClassCode(startClassCode);
		if(record == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "开课信息不存在");
		}
		Byte status = param.getStatus();
		boolean exist = StartClassRecordStatus.checkStatusExist(status);
		if(!exist) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "开课信息状态不存在");
		}
		
		// 等订单模块出来，需要判断是否有订单绑定了该开课信息记录 TODO
		if(status.intValue() == StartClassRecordStatus.INVALID.getValue()) {
			int count = 0;
			if(count > 0) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程已被购买，请先处理完订单后在进行删除");
			}
		}
		
		WxCourseStartClasRecord startClassRecord = WxCourseStartClasRecord.builder()
				.id(record.getId())
				.status(status)
				.modifier(param.getModifier())
				.modifyTime(new Date())
				.build();
		wxCourseStartClasRecordMapper.updateByPrimaryKeySelective(startClassRecord);
		
		return JsonResult.successJsonResult();
	}
	
}

