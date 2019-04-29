/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月24日 下午1:35:52
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.azz.client.pojo.vo.WxUserInfo;
import com.azz.controller.utils.WebUtils;
import com.azz.core.common.JsonResult;
import com.azz.core.common.QueryPage;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.ClientConstants;
import com.azz.core.constants.WxCourseConstants;
import com.azz.core.exception.BaseException;
import com.azz.core.exception.ShiroAuthException;
import com.azz.core.exception.SuppressedException;
import com.azz.exception.JSR303ValidationException;
import com.azz.util.OkHttpUtil;
import com.azz.util.StringUtils;
import com.azz.wx.course.api.SignUpService;
import com.azz.wx.course.pojo.bo.ActivityPayOrderParam;
import com.azz.wx.course.pojo.bo.CallBackParam;
import com.azz.wx.course.pojo.bo.EvaluateActivityParam;
import com.azz.wx.course.pojo.bo.SearchActivityInfoParam;
import com.azz.wx.course.pojo.bo.SignUpParam;
import com.azz.wx.course.pojo.vo.ActivityEvaluationInfo;
import com.azz.wx.course.pojo.vo.ActivityInfo;
import com.azz.wx.course.pojo.vo.ActivityPayOrderInfo;
import com.azz.wx.course.pojo.vo.ClientSignUpInfo;

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
	
	@Value("${wx.appid}")
	private String appid;
	
	@Value("${wx.secret}")
	private String secret;
	
	/**
	 * 
	 * <p>微信活动报名登录</p>
	 * @param openid
	 * @return
	 * @author 黄智聪  2019年1月25日 下午1:03:08
	 */
    @RequestMapping(value = "/login")
    public JsonResult<Map<Object, Object>> login(String code) {
    	WxUserInfo wxUserInfo = new WxUserInfo();
    	Map<Object,Object> resultMap = new HashMap<Object, Object>();
    	if(StringUtils.isBlank(code)) {
    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "缺少请求参数");
    	}
    	String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    	url = url.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
    	// 获取accessToken
    	String accessTokenResult = OkHttpUtil.get(url);
    	JSONObject jsonObject = JSONObject.parseObject(accessTokenResult);
    	String openid = jsonObject.getString("openid");
    	if (openid != null) {
    		//拉取用户信息
    		String access_token = jsonObject.getString("access_token");
    		url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
    		//第二次请求，用openid与access_token获取用户的信息
    		String usesrInfo = OkHttpUtil.get(url);
    		jsonObject = JSONObject.parseObject(usesrInfo);
    		String nickname = jsonObject.getString("nickname");
    		String headimgurl = jsonObject.getString("headimgurl");
    		wxUserInfo.setOpenid(openid);
    		wxUserInfo.setNickname(nickname);
    		wxUserInfo.setHeadimgurl(headimgurl);
    	}else {
    		throw new BaseException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "获取微信用户信息出错");
    	}
    	// 从SecurityUtils里边创建一个 subject
    	Subject subject = SecurityUtils.getSubject();
    	// 在认证提交前准备 token（令牌）
    	UsernamePasswordToken token = new UsernamePasswordToken(WxCourseConstants.WX_ACTIVITY_LOGIN_USER_NAME_PREFIX + openid, "noUse");
    	try {
    		// 执行认证登陆
    		subject.login(token);
    		// 设置为负数表示永不超时
    		subject.getSession().setTimeout(-1000L);
    	} catch (AuthenticationException e) {
    		Throwable[] throwables = e.getSuppressed();
    		if(throwables != null && throwables.length != 0) {
    			int c = ((SuppressedException) throwables[0]).getCode();
    			String msg = ((SuppressedException) throwables[0]).getMessage();
    			JsonResult<Map<Object,Object>> jr = new JsonResult<>();
    			resultMap.put("wxUserInfo", wxUserInfo);
    			jr.setData(resultMap);
    			jr.setCode(c);
    			jr.setMsg(msg);
    			return jr;
    		}
    		throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "登录失败,请重试");
    	}
    	WebUtils.setShiroSessionAttr(ClientConstants.LOGIN_CLIENT_USER, wxUserInfo);
    	resultMap.put("wxUserInfo", wxUserInfo);
    	resultMap.put("sessionId", subject.getSession().getId());
    	return JsonResult.successJsonResult(resultMap);
    }
    	
	
	
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
	public JsonResult<com.azz.wx.course.pojo.vo.WxUserInfo> getWxUserInfoByCode(@RequestParam("code")String code) {
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
	public JsonResult<ActivityInfo> getActivityDetail(SearchActivityInfoParam param) {
		return signUpService.getActivityDetail(param);
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
	 * @param url
	 * @return
	 * @author 黄智聪  2019年4月23日 下午3:46:43
	 */
	@RequestMapping("/getWxConfig")
	public JsonResult<Map<String, Object>> getWxConfig(@RequestParam("url") String url) {
		return signUpService.getWxConfig(url);
	}
	
	/**
	 * 
	 * <p>评价活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月19日 上午10:05:41
	 */
	@RequestMapping(value = "/evaluateActivity")
	public JsonResult<String> evaluateActivity(EvaluateActivityParam param) {
		return signUpService.evaluateActivity(param);
	}
	
	/**
	 * 
	 * <p>查询活动评价</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月23日 下午3:46:43
	 */
	@RequestMapping("/getEvaluationInfos")
	public JsonResult<Pagination<ActivityEvaluationInfo>> getEvaluationInfos(QueryPage param) {
		return signUpService.getEvaluationInfos(param);
	}
	
	/**
	 * 
	 * <p>生成去付款的活动订单信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月29日 下午4:13:31
	 */
	@RequestMapping("/generatePayOrderInfo")
	public JsonResult<ActivityPayOrderInfo> generatePayOrderInfo(ActivityPayOrderParam param){
		return signUpService.generatePayOrderInfo(param);
	}
	
	/**
	 * 
	 * <p>微信订单支付成功后的操作</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月26日 下午3:41:55
	 */
	@RequestMapping("/activityOrderPaySuccessOpt")
	public JsonResult<String> activityOrderPaySuccessOpt(CallBackParam param){
		return signUpService.activityOrderPaySuccessOpt(param);
	}
}

