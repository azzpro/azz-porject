/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月24日 下午1:35:52
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.wx.course.api.SignUpService;
import com.azz.wx.course.pojo.bo.SearchActivityInfoParam;
import com.azz.wx.course.pojo.bo.SignUpParam;
import com.azz.wx.course.pojo.vo.ActivityInfo;
import com.azz.wx.course.pojo.vo.ClientSignUpInfo;
import com.azz.wx.course.pojo.vo.WxUserInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月24日 下午1:35:52
 */
@RestController
@RequestMapping("/azz/api/client/activity")
public class ActivitySignUpController {
	
	@Autowired
	private SignUpService signUpService;
	
	/**
	 * 
	 * <p>获取微信用户是否关注公众号</p>
	 * @return
	 * @author 黄智聪  2019年4月17日 下午4:39:29
	 */
	@RequestMapping("/getWxUserSubscribe")
	public JsonResult<Integer> getWxUserSubscribe(@RequestParam("openid")String openid){
		return signUpService.getWxUserSubscribe(openid);
	}
	
	/**
	 * 
	 * <p>获取微信用户信息</p>
	 * @param code
	 * @return
	 * @author 黄智聪  2019年4月19日 下午6:03:31
	 */
	@RequestMapping("/getWxUserInfoByCode")
	public JsonResult<WxUserInfo> getWxUserInfoByCode(@RequestParam("code")String code) {
		return signUpService.getWxUserInfoByCode(code);
	}
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @return
	 * @author 黄智聪  2019年4月16日 下午5:58:40
	 */
	@RequestMapping("/getActivityInfos")
	public JsonResult<Pagination<ActivityInfo>> getActivityInfos(SearchActivityInfoParam param) {
		return signUpService.getActivityInfos(param);
	}
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @return
	 * @author 黄智聪  2019年4月16日 下午5:58:40
	 */
	@RequestMapping(value = "/getActivityDetail")
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
	@RequestMapping(value = "/getSignUpInfos")
	public JsonResult<Pagination<ClientSignUpInfo>> getSignUpInfos(SearchActivityInfoParam param) {
		return signUpService.getSignUpInfos(param);
	}
	
	/**
	 * 
	 * <p>报名</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月19日 上午10:05:41
	 */
	@RequestMapping(value = "/signUp")
	public JsonResult<String> signUp(SignUpParam param) {
		return signUpService.signUp(param);
	}
	
	/**
	 * 
	 * <p>getWxConfig获取微信的配置信息</p>
	 * @param requestUrl
	 * @return
	 * @author 黄智聪  2019年4月23日 下午3:46:43
	 */
	@RequestMapping("/activity/getWxConfig")
	public JsonResult<Map<String, Object>> getWxConfig(HttpServletRequest request) {
		String requestUrl = request.getRequestURL().toString();
		return signUpService.getWxConfig(requestUrl);
	}
	
}

