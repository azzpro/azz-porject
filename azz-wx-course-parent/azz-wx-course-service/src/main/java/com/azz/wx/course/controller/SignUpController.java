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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.wx.course.pojo.bo.AddActivityParam;
import com.azz.wx.course.pojo.bo.EditActivityParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelActivityParam;
import com.azz.wx.course.pojo.bo.SearchActivityInfoParam;
import com.azz.wx.course.pojo.bo.SignUpParam;
import com.azz.wx.course.pojo.vo.ActivityInfo;
import com.azz.wx.course.pojo.vo.ClientSignUpInfo;
import com.azz.wx.course.pojo.vo.SignUpInfo;
import com.azz.wx.course.pojo.vo.WxUserInfo;
import com.azz.wx.course.service.SignUpService;

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
@RequestMapping("/azz/api")
public class SignUpController {
	
	@Autowired
	private SignUpService signUpService;
	
	/**
	 * 
	 * <p>获取微信access_token</p>
	 * @return
	 * @author 黄智聪  2019年4月17日 下午4:39:59
	 */
	@RequestMapping("/client/activity/getAccesstoken")
	public JsonResult<String> getAccesstoken(){
		return signUpService.getAccesstoken();
	}
	
	/**
	 * 
	 * <p>获取微信用户信息</p>
	 * @param code
	 * @return
	 * @author 黄智聪  2019年4月19日 下午6:03:31
	 */
	@RequestMapping("/client/activity/getWxUserInfoByCode")
	public JsonResult<WxUserInfo> getWxUserInfoByCode(@RequestParam("code")String code) {
		return signUpService.getWxUserInfoByCode(code);
	}
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @return
	 * @author 黄智聪  2019年4月16日 下午5:58:40
	 */
	@RequestMapping("/client/activity/getActivityInfos")
	public JsonResult<Pagination<ActivityInfo>> getActivityInfos(@RequestBody SearchActivityInfoParam param) {
		return signUpService.getActivityInfos(param);
	}
	
	/**
	 * 
	 * <p>查询活动详情</p>
	 * @param activityCode
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/client/activity/getActivityDetail")
	public JsonResult<ActivityInfo> getActivityDetail(@RequestParam("activityCode") String activityCode) {
		return signUpService.getActivityDetail(activityCode);
	}
	
	/**
	 * 
	 * <p>查询活动报名人员信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/client/activity/getSignUpInfos")
	public JsonResult<Pagination<ClientSignUpInfo>> getSignUpInfos(@RequestBody SearchActivityInfoParam param) {
		return signUpService.getSignUpInfos(param);
	}
	
	/**
	 * 
	 * <p>报名</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月19日 上午10:05:41
	 */
	@RequestMapping("/client/activity/signUp")
	public JsonResult<String> signUp(@RequestBody SignUpParam param) {
		return signUpService.signUp(param);
	}
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/platform/activity/getPlatformActivityInfos")
	public JsonResult<Pagination<ActivityInfo>> getPlatformActivityInfos(@RequestBody SearchActivityInfoParam param) {
		return signUpService.getPlatformActivityInfos(param);
	}
	
	/**
	 * 
	 * <p>查询活动详情</p>
	 * @param activityCode
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/platform/activity/getPlatformActivityDetail")
	public JsonResult<ActivityInfo> getPlatformActivityDetail(@RequestParam("activityCode") String activityCode) {
		return signUpService.getPlatformActivityDetail(activityCode);
	}
	
	/**
	 * 
	 * <p>查询活动报名人员信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/platform/activity/getPlatformSignUpInfos")
	public JsonResult<Pagination<SignUpInfo>> getPlatformSignUpInfos(@RequestBody SearchActivityInfoParam param) {
		return signUpService.getPlatformSignUpInfos(param);
	}
	
	/**
	 * 
	 * <p>添加活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/platform/activity/addActivity")
	public JsonResult<String> addActivity(@RequestBody AddActivityParam param) {
		return signUpService.addActivity(param);
	}
	
	/**
	 * 
	 * <p>添加活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/platform/activity/editActivity")
	public JsonResult<String> editActivity(@RequestBody EditActivityParam param) {
		return signUpService.editActivity(param);
	}
	
	/**
	 * 
	 * <p>上架、下架或删除活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	@RequestMapping("/platform/activity/putOnOrPutOffOrDelActivity")
	public JsonResult<String> putOnOrPutOffOrDelActivity(@RequestBody PutOnOrPutOffOrDelActivityParam param){
		return signUpService.putOnOrPutOffOrDelActivity(param);
	}
	
	
}
