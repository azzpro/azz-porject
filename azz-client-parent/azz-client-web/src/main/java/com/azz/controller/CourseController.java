/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月24日 下午1:35:52
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.client.pojo.vo.LoginClientUserInfo;
import com.azz.client.user.api.ClientService;
import com.azz.controller.utils.WebUtils;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.ClientConstants;
import com.azz.core.constants.WxCourseConstants;
import com.azz.core.exception.ShiroAuthException;
import com.azz.core.exception.SuppressedException;
import com.azz.wx.course.api.CourseService;
import com.azz.wx.course.pojo.bo.SearchCourseInfoParam;
import com.azz.wx.course.pojo.bo.SearchEvaluationInfoParam;
import com.azz.wx.course.pojo.bo.SearchStartClassRecordParam;
import com.azz.wx.course.pojo.vo.CourseDetail;
import com.azz.wx.course.pojo.vo.CourseInfo;
import com.azz.wx.course.pojo.vo.EvaluationInfo;
import com.azz.wx.course.pojo.vo.StartClassRecord;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月24日 下午1:35:52
 */
@RestController
@RequestMapping("/azz/api/client/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	ClientService clientService;
	
	/**
	 * 
	 * <p>微信课程登录</p>
	 * @param openid
	 * @return
	 * @author 黄智聪  2019年1月25日 下午1:03:08
	 */
    @RequestMapping(value = "/login")
    public JsonResult<LoginClientUserInfo> login(String openid) {
    	// 从SecurityUtils里边创建一个 subject
    	Subject subject = SecurityUtils.getSubject();
    	// 在认证提交前准备 token（令牌）
    	UsernamePasswordToken token = new UsernamePasswordToken(WxCourseConstants.WX_COURSE_LOGIN_USER_NAME_PREFIX + openid, "noUse");
    	try {
    		// 执行认证登陆
    		subject.login(token);
    	} catch (AuthenticationException e) {
    		Throwable[] throwables = e.getSuppressed();
    		if(throwables != null && throwables.length != 0) {
    			int code = ((SuppressedException) throwables[0]).getCode();
    			String msg = ((SuppressedException) throwables[0]).getMessage();
    			JsonResult<LoginClientUserInfo> jr = new JsonResult<>();
    			jr.setCode(code);
    			jr.setMsg(msg);
    			return jr;
    		}
    		throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "登录失败,请重试");
    	}
    	JsonResult<LoginClientUserInfo> jr = clientService.getLoginClientUserInfoByOpenid(openid);
    	LoginClientUserInfo loginClientUser = jr.getData();
    	loginClientUser.setSessionId(subject.getSession().getId());
    	WebUtils.setShiroSessionAttr(ClientConstants.LOGIN_CLIENT_USER, loginClientUser);
    	return jr;
    }
	
	/**
	 * 
	 * <p>查询首页课程列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月21日 下午4:25:49
	 */
	@RequestMapping(value = "/getIndexCourseInfos", method = RequestMethod.POST)
	public JsonResult<Pagination<CourseInfo>> getIndexCourseInfos(SearchCourseInfoParam param){
		return courseService.getIndexCourseInfos(param);
	}
	
	/**
	 * 
	 * <p>查询首页课程详情</p>
	 * @param courseCode
	 * @return
	 * @author 黄智聪  2019年1月4日 下午3:20:59
	 */
	@RequestMapping(value = "/getIndexCourseDetail", method = RequestMethod.POST)
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
	@RequestMapping(value = "/getEvaluationInfos", method = RequestMethod.POST)
	public JsonResult<Pagination<EvaluationInfo>> getEvaluationInfos(SearchEvaluationInfoParam param){
		return courseService.getEvaluationInfos(param);
	}
	
	/**
	 * 
	 * <p>查询首页开课信息列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:37:39
	 */
	@RequestMapping(value = "/getIndexStartClassRecords", method = RequestMethod.POST)
	public JsonResult<Pagination<StartClassRecord>> getIndexStartClassRecords(SearchStartClassRecordParam param){
		return courseService.getIndexStartClassRecords(param);
	}
	

}

