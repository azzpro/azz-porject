/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.wx.course.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.wx.course.pojo.bo.ActivityPayOrderParam;
import com.azz.wx.course.pojo.bo.AddActivityParam;
import com.azz.wx.course.pojo.bo.CallBackParam;
import com.azz.wx.course.pojo.bo.EditActivityParam;
import com.azz.wx.course.pojo.bo.EvaluateActivityParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelActivityParam;
import com.azz.wx.course.pojo.bo.SearchActivityEvaluationInfoParam;
import com.azz.wx.course.pojo.bo.SearchActivityInfoParam;
import com.azz.wx.course.pojo.bo.ShieldOrCancelShiedEvaluationParam;
import com.azz.wx.course.pojo.bo.SignUpParam;
import com.azz.wx.course.pojo.vo.ActivityEvaluationInfo;
import com.azz.wx.course.pojo.vo.ActivityInfo;
import com.azz.wx.course.pojo.vo.ActivityPayOrderInfo;
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
	 * <p>获取微信用户是否关注公众号</p>
	 * @return
	 * @author 黄智聪  2019年4月17日 下午4:39:59
	 */
	@RequestMapping("/client/activity/getWxUserSubscribe")
	public JsonResult<Integer> getWxUserSubscribe(@RequestParam("openid")String openid) {
		return signUpService.getWxUserSubscribe(openid);
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
	public JsonResult<ActivityInfo> getActivityDetail(@RequestBody SearchActivityInfoParam param) {
		return signUpService.getActivityDetail(param);
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
	 * <p>生成去付款的活动订单信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月29日 下午4:13:31
	 */
	@RequestMapping("/client/activity/generatePayOrderInfo")
	public JsonResult<ActivityPayOrderInfo> generatePayOrderInfo(@RequestBody ActivityPayOrderParam param){
		return signUpService.generatePayOrderInfo(param);
	}
	
	/**
	 * 
	 * <p>微信订单支付成功后的操作</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月26日 下午3:41:55
	 */
	@RequestMapping("/client/activity/activityOrderPaySuccessOpt")
	public JsonResult<String> activityOrderPaySuccessOpt(@RequestBody CallBackParam param){
		return signUpService.activityOrderPaySuccessOpt(param);
	}
	
	
	/**
	 * 
	 * <p>查询活动评价</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月21日 下午7:26:26
	 */
	@RequestMapping("/client/activity/getEvaluationInfos")
	public JsonResult<Pagination<ActivityEvaluationInfo>> getEvaluationInfos(@RequestBody SearchActivityEvaluationInfoParam param){
		return signUpService.getEvaluationInfos(param);
	}
	
	
	/**
	 * 
	 * <p>评价活动</p>
	 * @return
	 * @author 黄智聪  2019年1月23日 上午10:48:25
	 */
	@RequestMapping("/client/activity/evaluateActivity")
	public JsonResult<String> evaluateActivity(@RequestBody EvaluateActivityParam param){
		return signUpService.evaluateActivity(param);
	}
	
	
	
	/**
	 * 
	 * <p>getWxConfig获取微信的配置信息</p>
	 * @param requestUrl
	 * @return
	 * @author 黄智聪  2019年4月23日 下午3:46:43
	 */
	@RequestMapping("/client/activity/getWxConfig")
	public JsonResult<Map<String, Object>> getWxConfig(@RequestParam("requestUrl")String requestUrl) {
		return signUpService.getWxConfig(requestUrl);
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
	 * <p>查询活动评价</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月29日 下午2:01:16
	 */
	@RequestMapping("/platform/activity/getPlatformEvaluationInfos")
	public JsonResult<Pagination<ActivityEvaluationInfo>> getPlatformEvaluationInfos(@RequestBody SearchActivityEvaluationInfoParam param) {
		return signUpService.getPlatformEvaluationInfos(param);
	}
	
	/**
	 * 
	 * <p>屏蔽或取消屏蔽评论</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月29日 下午2:19:45
	 */
	@RequestMapping("/platform/activity/shieldOrCancelShiedEvaluation")
	public JsonResult<String> shieldOrCancelShiedEvaluation(@RequestBody ShieldOrCancelShiedEvaluationParam param) {
		return signUpService.shieldOrCancelShiedEvaluation(param);
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
