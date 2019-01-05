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
import com.azz.wx.course.pojo.bo.AddCourseParam;
import com.azz.wx.course.pojo.bo.EditCourseParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelCourseParam;
import com.azz.wx.course.pojo.bo.SearchCourseInfoParam;
import com.azz.wx.course.pojo.vo.CourseDetail;
import com.azz.wx.course.pojo.vo.CourseInfo;
import com.azz.wx.course.service.CourseService;

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
@RequestMapping("/azz/api/platform/course")
public class CourseController {

	@Autowired
	CourseService courseService;

	/**
	 * 
	 * <p>
	 * 查询课程列表
	 * </p>
	 * 
	 * @param param
	 * @return
	 * @author 黄智聪 2019年1月4日 上午11:33:03
	 */
	@RequestMapping(value = "getCourseInfos", method = RequestMethod.POST)
	public JsonResult<Pagination<CourseInfo>> getCourseInfos(@RequestBody SearchCourseInfoParam param) {
		return courseService.getCourseInfos(param);
	}

	/**
	 * 
	 * <p>
	 * 查询课程详情
	 * </p>
	 * 
	 * @param courseCode
	 * @return
	 * @author 黄智聪 2019年1月4日 下午3:20:59
	 */
	@RequestMapping(value = "getCourseDetail", method = RequestMethod.POST)
	public JsonResult<CourseDetail> getCourseDetail(@RequestParam("courseCode") String courseCode) {
		return courseService.getCourseDetail(courseCode);
	}

	/**
	 * 
	 * <p>
	 * 新增课程
	 * </p>
	 * 
	 * @param param
	 * @return
	 * @author 黄智聪 2019年1月4日 上午11:50:51
	 */
	@RequestMapping(value = "addCourse", method = RequestMethod.POST)
	public JsonResult<String> addCourse(@RequestBody AddCourseParam param) {
		return courseService.addCourse(param);
	}

	/**
	 * 
	 * <p>
	 * 修改课程
	 * </p>
	 * 
	 * @param param
	 * @return
	 * @author 黄智聪 2019年1月4日 上午11:50:51
	 */
	@RequestMapping(value = "editCourse", method = RequestMethod.POST)
	public JsonResult<String> editCourse(@RequestBody EditCourseParam param) {
		return courseService.editCourse(param);
	}

	/**
	 * 
	 * <p>
	 * 上架、下架或删除课程
	 * </p>
	 * 
	 * @param param
	 * @return
	 * @author 黄智聪 2019年1月4日 下午2:51:18
	 */
	@RequestMapping(value = "putOnOrPutOffOrDelCourse", method = RequestMethod.POST)
	public JsonResult<String> putOnOrPutOffOrDelCourse(@RequestBody PutOnOrPutOffOrDelCourseParam param) {
		return courseService.putOnOrPutOffOrDelCourse(param);
	}

}
