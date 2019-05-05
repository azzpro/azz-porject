/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年4月16日 下午5:15:24
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.wx.course.api;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

/**
 * <P>
 * 报名业务
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2019年4月16日 下午5:15:24
 */
@FeignClient("azz-wx-course-service")
public interface SignUpService {
	
	/**
	 * 
	 * <p>获取微信用户是否关注公众号</p>
	 * @return
	 * @author 黄智聪  2019年4月16日 下午5:54:47
	 */
	@RequestMapping(value = "/azz/api/client/activity/getWxUserSubscribe", method = RequestMethod.POST)
	JsonResult<Integer> getWxUserSubscribe(@RequestParam("openid")String openid);
	
	/**
	 * 
	 * <p>获取微信用户信息</p>
	 * @param code
	 * @return
	 * @author 黄智聪  2019年4月19日 下午6:03:31
	 */
	@RequestMapping("/azz/api/client/activity/getWxUserInfoByCode")
	JsonResult<WxUserInfo> getWxUserInfoByCode(@RequestParam("code")String code);
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @param activityName
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping(value = "/azz/api/client/activity/getActivityInfos", method = RequestMethod.POST)
	JsonResult<Pagination<ActivityInfo>> getActivityInfos(@RequestBody SearchActivityInfoParam param);
	
	/**
	 * 
	 * <p>查询活动详情</p>
	 * @param activityCode
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping(value = "/azz/api/client/activity/getActivityDetail", method = RequestMethod.POST)
	JsonResult<ActivityInfo> getActivityDetail(@RequestBody SearchActivityInfoParam param);
	
	/**
	 * 
	 * <p>查询活动评价</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月21日 下午7:26:26
	 */
	@RequestMapping(value = "/azz/api/client/activity/getEvaluationInfos", method = RequestMethod.POST)
	JsonResult<Pagination<ActivityEvaluationInfo>> getEvaluationInfos(@RequestBody SearchActivityEvaluationInfoParam param);
	
	/**
	 * 
	 * <p>评价活动</p>
	 * @return
	 * @author 黄智聪  2019年1月23日 上午10:48:25
	 */
	@RequestMapping(value = "/azz/api/client/activity/evaluateActivity", method = RequestMethod.POST)
	JsonResult<String> evaluateActivity(@RequestBody EvaluateActivityParam param);
	
	/**
	 * 
	 * <p>查询活动报名人员信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping(value = "/azz/api/client/activity/getSignUpInfos", method = RequestMethod.POST)
	JsonResult<Pagination<ClientSignUpInfo>> getSignUpInfos(@RequestBody SearchActivityInfoParam param);
	
	/**
	 * 
	 * <p>报名</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月19日 上午10:56:40
	 */
	@RequestMapping(value = "/azz/api/client/activity/signUp", method = RequestMethod.POST)
	JsonResult<String> signUp(@RequestBody SignUpParam param);
	
	/**
	 * 
	 * <p>生成去付款的活动订单信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月29日 下午4:13:31
	 */
	@RequestMapping("/azz/api/client/activity/generatePayOrderInfo")
	JsonResult<ActivityPayOrderInfo> generatePayOrderInfo(@RequestBody ActivityPayOrderParam param);
	
	/**
	 * 
	 * <p>微信订单支付成功后的操作</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月26日 下午3:41:55
	 */
	@RequestMapping("/azz/api/client/activity/activityOrderPaySuccessOpt")
	JsonResult<String> activityOrderPaySuccessOpt(@RequestBody CallBackParam param);
	
	/**
	 * 
	 * <p>查询活动订单详情</p>
	 * @param orderCode
	 * @return
	 * @author 黄智聪  2019年5月5日 下午2:06:26
	 */
	@RequestMapping("/azz/api/client/activity/getActivityOrder")
	JsonResult<ActivityPayOrderInfo> getActivityOrder(@RequestParam("orderCode")String orderCode);
	
	/**
	 * 
	 * <p>getWxConfig获取微信的配置信息</p>
	 * @param requestUrl
	 * @return
	 * @author 黄智聪  2019年4月23日 下午3:46:43
	 */
	@RequestMapping("/azz/api/client/activity/getWxConfig")
	JsonResult<Map<String, Object>> getWxConfig(@RequestParam("requestUrl")String requestUrl);
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/azz/api/platform/activity/getPlatformActivityInfos")
	JsonResult<Pagination<ActivityInfo>> getPlatformActivityInfos(@RequestBody SearchActivityInfoParam param);
	
	/**
	 * 
	 * <p>查询活动详情</p>
	 * @param activityCode
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/azz/api/platform/activity/getPlatformActivityDetail")
	JsonResult<ActivityInfo> getPlatformActivityDetail(@RequestParam("activityCode") String activityCode);
	
	
	/**
	 * 
	 * <p>查询活动报名人员信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/azz/api/platform/activity/getPlatformSignUpInfos")
	JsonResult<Pagination<SignUpInfo>> getPlatformSignUpInfos(@RequestBody SearchActivityInfoParam param);
	
	/**
	 * 
	 * <p>查询活动评价</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月21日 下午7:26:26
	 */
	@RequestMapping("/azz/api/platform/activity/getPlatformEvaluationInfos")
	JsonResult<Pagination<ActivityEvaluationInfo>> getPlatformEvaluationInfos(@RequestBody SearchActivityInfoParam param);
	
	/**
	 * 
	 * <p>屏蔽或取消屏蔽评论</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月29日 下午2:19:45
	 */
	@RequestMapping("/azz/api/platform/activity/shieldOrCancelShiedEvaluation")
	JsonResult<String> shieldOrCancelShiedEvaluation(@RequestBody ShieldOrCancelShiedEvaluationParam param);
	
	/**
	 * 
	 * <p>添加活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/azz/api/platform/activity/addActivity")
	JsonResult<String> addActivity(@RequestBody AddActivityParam param);
	
	/**
	 * 
	 * <p>添加活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/azz/api/platform/activity/editActivity")
	JsonResult<String> editActivity(@RequestBody EditActivityParam param);
	
	/**
	 * 
	 * <p>上架、下架或删除活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	@RequestMapping("/azz/api/platform/activity/putOnOrPutOffOrDelActivity")
	JsonResult<String> putOnOrPutOffOrDelActivity(@RequestBody PutOnOrPutOffOrDelActivityParam param);
	
}
