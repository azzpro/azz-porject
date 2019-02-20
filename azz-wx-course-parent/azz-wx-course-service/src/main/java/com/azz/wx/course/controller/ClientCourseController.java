/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.wx.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.wx.course.pojo.bo.SearchCourseInfoParam;
import com.azz.wx.course.pojo.bo.SearchEvaluationInfoParam;
import com.azz.wx.course.pojo.bo.SearchStartClassRecordParam;
import com.azz.wx.course.pojo.vo.CourseDetail;
import com.azz.wx.course.pojo.vo.CourseInfo;
import com.azz.wx.course.pojo.vo.EvaluationInfo;
import com.azz.wx.course.pojo.vo.StartClassRecord;
import com.azz.wx.course.service.CourseService;
import com.azz.wx.course.service.StartClassService;

/**
 * 
 * <P>
 * 课程相关控制器
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月17日 下午1:42:55
 */
@RestController
@RequestMapping("/azz/api/client/course")
public class ClientCourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StartClassService startClassService;

	/*******************************  微信课程首页接口   start ********************************/
	
	/**
	 * 
	 * <p>查询首页课程列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月21日 下午4:25:49
	 */
	@RequestMapping(value = "getIndexCourseInfos", method = RequestMethod.POST)
	public JsonResult<Pagination<CourseInfo>> getIndexCourseInfos(@RequestBody SearchCourseInfoParam param){
		return courseService.getIndexCourseInfos(param);
	}
	
	/**
	 * 
	 * <p>查询首页课程详情</p>
	 * @param courseCode
	 * @return
	 * @author 黄智聪  2019年1月4日 下午3:20:59
	 */
	@RequestMapping(value = "getIndexCourseDetail", method = RequestMethod.POST)
	public JsonResult<CourseDetail> getIndexCourseDetail(@RequestParam("courseCode")String courseCode){
		return courseService.getIndexCourseDetail(courseCode);
	}
	
	/**
	 * 
	 * <p>查询课程评价</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月21日 下午7:26:26
	 */
	@RequestMapping(value = "getEvaluationInfos", method = RequestMethod.POST)
	public JsonResult<Pagination<EvaluationInfo>> getEvaluationInfos(@RequestBody SearchEvaluationInfoParam param){
		return courseService.getEvaluationInfos(param);
	}
	
	/**
	 * 
	 * <p>查询首页开课信息列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:37:39
	 */
	@RequestMapping(value = "getIndexStartClassRecords", method = RequestMethod.POST)
	public JsonResult<Pagination<StartClassRecord>> getIndexStartClassRecords(@RequestBody SearchStartClassRecordParam param){
		return startClassService.getIndexStartClassRecords(param);
	}
	
	/*******************************  微信课程首页接口   end ********************************/
	
}
