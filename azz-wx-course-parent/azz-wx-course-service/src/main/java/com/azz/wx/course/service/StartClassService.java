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
import com.azz.core.common.page.Pagination;
import com.azz.util.JSR303ValidateUtils;
import com.azz.wx.course.mapper.WxCourseStartClasRecordMapper;
import com.azz.wx.course.pojo.WxCourseStartClasRecord;
import com.azz.wx.course.pojo.bo.AddStartClassRecordParam;
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
	private WxCourseStartClasRecordMapper wxCourseStartClasRecordMapper;
	
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
	 * <p>新增开课信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:57:17
	 */
	public JsonResult<String> addStartClassRecord(@RequestBody AddStartClassRecordParam param){
		JSR303ValidateUtils.validate(param);
		String startClassCode = System.currentTimeMillis() + "";
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
				.startClassTime(param.getStartClassTime())
				.build();
		wxCourseStartClasRecordMapper.insertSelective(startClassRecord);
		return JsonResult.successJsonResult();
	}

}

