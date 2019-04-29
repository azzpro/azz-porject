/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年4月16日 下午5:15:24
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.wx.course.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.azz.core.common.JsonResult;
import com.azz.core.common.QueryPage;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.WxActivityConstants;
import com.azz.core.constants.ClientConstants.PayStatus;
import com.azz.core.constants.WxActivityConstants.ActivityStatus;
import com.azz.core.constants.WxActivityConstants.IsChangeActivityPic;
import com.azz.core.constants.WxActivityConstants.IsShield;
import com.azz.core.constants.WxActivityConstants.OrderStatus;
import com.azz.core.constants.WxCourseConstants.CourseOrderStatus;
import com.azz.core.exception.BaseException;
import com.azz.core.reconstructed.errorcode.ValidationErrorCode;
import com.azz.core.reconstructed.exception.BusinessException;
import com.azz.core.reconstructed.exception.ReturnDataException;
import com.azz.core.reconstructed.exception.ValidationException;
import com.azz.exception.JSR303ValidationException;
import com.azz.system.api.SystemImageUploadService;
import com.azz.system.bo.UploadImageParam;
import com.azz.util.DateUtils;
import com.azz.util.HttpClientUtils;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.OkHttpUtil;
import com.azz.util.StringUtils;
import com.azz.wx.course.mapper.WxActivityEvaluationMapper;
import com.azz.wx.course.mapper.WxActivityMapper;
import com.azz.wx.course.mapper.WxActivityOrderItemMapper;
import com.azz.wx.course.mapper.WxActivityOrderMapper;
import com.azz.wx.course.mapper.WxActivityOrderStatusMapper;
import com.azz.wx.course.mapper.WxActivityUserSignUpMapper;
import com.azz.wx.course.pojo.WxActivity;
import com.azz.wx.course.pojo.WxActivityEvaluation;
import com.azz.wx.course.pojo.WxActivityOrder;
import com.azz.wx.course.pojo.WxActivityOrderItem;
import com.azz.wx.course.pojo.WxActivityOrderStatus;
import com.azz.wx.course.pojo.WxActivityUserSignUp;
import com.azz.wx.course.pojo.WxCourseOrder;
import com.azz.wx.course.pojo.WxCourseOrderStatus;
import com.azz.wx.course.pojo.bo.ActivityPayOrderParam;
import com.azz.wx.course.pojo.bo.ActivityPic;
import com.azz.wx.course.pojo.bo.AddActivityParam;
import com.azz.wx.course.pojo.bo.CallBackParam;
import com.azz.wx.course.pojo.bo.EditActivityParam;
import com.azz.wx.course.pojo.bo.EvaluateActivityParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelActivityParam;
import com.azz.wx.course.pojo.bo.SearchActivityInfoParam;
import com.azz.wx.course.pojo.bo.ShieldOrCancelShiedEvaluationParam;
import com.azz.wx.course.pojo.bo.SignUpParam;
import com.azz.wx.course.pojo.bo.SuccessSignUpNoticeParam;
import com.azz.wx.course.pojo.bo.TemplateData;
import com.azz.wx.course.pojo.bo.WechatTemplate;
import com.azz.wx.course.pojo.vo.ActivityEvaluationInfo;
import com.azz.wx.course.pojo.vo.ActivityInfo;
import com.azz.wx.course.pojo.vo.ActivityPayOrderInfo;
import com.azz.wx.course.pojo.vo.ClientSignUpInfo;
import com.azz.wx.course.pojo.vo.PayOrderInfo;
import com.azz.wx.course.pojo.vo.SignUpInfo;
import com.azz.wx.course.pojo.vo.UploadFileInfo;
import com.azz.wx.course.pojo.vo.WechatRequestParam;
import com.azz.wx.course.pojo.vo.WechatResponse;
import com.azz.wx.course.pojo.vo.WxUserInfo;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

/**
 * <P>
 * 报名业务
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2019年4月16日 下午5:15:24
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class SignUpService {
	
	@Value("${wx.config.appid}")
	private String appid;
	
	// 获取微信ACCESS_TOKEN接口
	@Value("${wx.config.api.getAccessTokenUrl}")
	private String getAccessTokenUrl;
	
	// 获取微信用户信息accessToken的接口
	@Value("${wx.config.api.getWxUserAccessTokenUrl}")
	private String getWxUserAccessTokenUrl;
	
	// 获取微信用户信息的接口
	@Value("${wx.config.api.getWxUserInfoUrl}")
	private String getWxUserInfoUrl;
	
	// 获取微信用户信息的接口
	@Value("${wx.config.api.getWxUserSubscribeUrl}")
	private String getWxUserSubscribeUrl;
	
	// 获取JSSDK权限接口
	@Value("${wx.config.api.getJsApiTicketURL}")
	private String getJsApiTicketURL;
	
	// 微信发送消息模板接口
	@Value("${wx.config.api.template.sendTemplateMessageUrl}")
	private String sendTemplateMessageUrl;
	
	// 用户接收到的推送消息的详情接口
	@Value("${wx.config.api.template.detailUrl}")
	private String detailUrl;
	
	// 消息模板id
	@Value("${wx.config.api.template.templateId}")
	private String templateId;
	
	@Autowired
	private WxActivityMapper wxActivityMapper;
	
	@Autowired
	private WxActivityUserSignUpMapper wxActivityUserSignUpMapper;
	
	@Autowired
	private WxActivityEvaluationMapper wxActivityEvaluationMapper;
	
	@Autowired
	private WxActivityOrderItemMapper wxActivityOrderItemMapper;
	
	@Autowired
	private WxActivityOrderMapper wxActivityOrderMapper;
	
	@Autowired
	private WxActivityOrderStatusMapper wxActivityOrderStatusMapper;
	
	@Autowired
	SystemImageUploadService systemImageUploadService;
	
	/************************************************** 客户端start **********************************************/
	
	public JsonResult<WxUserInfo> getWxUserInfoByCode(@RequestParam("code")String code) {
		WxUserInfo wxUserInfo = new WxUserInfo();
    	if(StringUtils.isBlank(code)) {
    		throw new ValidationException(ValidationErrorCode.VALIDATION_ERROR_MISSING_REQUEST_PARAM);
    	}
		String url = getWxUserAccessTokenUrl.replace("CODE", code);
		// 获取accessToken
		String accessTokenResult = OkHttpUtil.get(url);
		JSONObject jsonObject = JSONObject.parseObject(accessTokenResult);
		String openid = jsonObject.getString("openid");
        if (openid != null) {
            //拉取用户信息
            String access_token = jsonObject.getString("access_token");
            url = getWxUserInfoUrl.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
            //第二次请求，用openid与access_token获取用户的信息
            String usesrInfo = OkHttpUtil.get(url);
            jsonObject = JSONObject.parseObject(usesrInfo);
            String nickname = jsonObject.getString("nickname");
            String headimgurl = jsonObject.getString("headimgurl");
            wxUserInfo.setOpenid(openid);
            wxUserInfo.setNickName(nickname);
            wxUserInfo.setHeadimgurl(headimgurl);
        }else {
        	throw new ReturnDataException("获取微信用户信息出错");
        }
        return JsonResult.successJsonResult(wxUserInfo);
	}
	
	/**
	 * 
	 * <p>获取微信用户是否关注公众号</p>
	 * @return
	 * @author 黄智聪  2019年4月16日 下午5:54:47
	 */
	public JsonResult<Integer> getWxUserSubscribe(String openid){
		if(StringUtils.isBlank(openid)) {
			throw new ValidationException(ValidationErrorCode.VALIDATION_ERROR_MISSING_REQUEST_PARAM);
		}
		String accesstoken = accesstoken();
		String url = getWxUserSubscribeUrl.replace("ACCESS_TOKEN", accesstoken).replace("OPENID", openid);
		String json = HttpClientUtils.sendHttpGet(url);
		JSONObject responseJson = JSONObject.parseObject(json);
		if (null == responseJson) {
			throw new ReturnDataException("获取微信用户信息出错");
		}
		Integer subscribe = responseJson.getInteger("subscribe");
		if (null == subscribe) {
			throw new ReturnDataException("获取微信用户信息出错");
		} 
		return	JsonResult.successJsonResult(subscribe);
	}
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @param activityName
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	public JsonResult<Pagination<ActivityInfo>> getActivityInfos(@RequestBody SearchActivityInfoParam param) {
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<ActivityInfo> infos = wxActivityMapper.getActivityInfos(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询活动详情</p>
	 * @param activityCode
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	public JsonResult<ActivityInfo> getActivityDetail(@RequestBody SearchActivityInfoParam param) {
		if(StringUtils.isBlank(param.getOpenid())) {
			throw new ValidationException(ValidationErrorCode.VALIDATION_ERROR_MISSING_REQUEST_PARAM);
		}
		ActivityInfo detail = wxActivityMapper.getActivityInfoByActivityCode(param.getActivityCode(), param.getOpenid());
		if(detail == null) {
			throw new ReturnDataException("活动不存在");
		}
		String remark = detail.getRemark();
		if(StringUtils.isBlank(remark)) {
			remark = "0";
		}
		int intRemark = Integer.parseInt(remark);
		intRemark += 1;
		WxActivity record = WxActivity.builder().remark(intRemark + "").activityCode(param.getActivityCode()).build();
		wxActivityMapper.updateByCodeSelective(record);
		return JsonResult.successJsonResult(detail);
	}
	
	/**
	 * 
	 * <p>查询活动报名人员信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	public JsonResult<Pagination<ClientSignUpInfo>> getSignUpInfos(@RequestBody SearchActivityInfoParam param) {
		if(StringUtils.isBlank(param.getActivityCode())) {
			throw new ValidationException("请选择活动");
		}
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<ClientSignUpInfo> infos = wxActivityMapper.getActivityClientSignUpInfoByActivityCode(param.getActivityCode());
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>报名</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月19日 上午10:05:41
	 */
	public JsonResult<String> signUp(@RequestBody SignUpParam param){
		JSR303ValidateUtils.validateInputParam(param);
		WxActivity activity = wxActivityMapper.getActivityWithoutContentByActivityCode(param.getActivityCode());
		if(activity == null) {
			throw new ReturnDataException("活动不存在");
		}
		if(Calendar.getInstance().after(activity.getDeadline())) {
			throw new ReturnDataException("活动已结束，报名失败");
		}
		if(activity.getSignUpCount() == activity.getSignUpLimit()) {
			throw new ReturnDataException("已超出报名人数上限，报名失败");
		}
		int count = wxActivityUserSignUpMapper.countSignUpRecodeByOpenid(param.getOpenid(), param.getActivityCode());
		if(count > 0) {
			throw new BusinessException("请勿重复报名");
		}
		Date nowDate = new Date();
		WxActivityUserSignUp record = WxActivityUserSignUp.builder()
				.activityCode(param.getActivityCode())
				.companyName(param.getCompanyName())
				.createTime(nowDate)
				.headImageUrl(param.getHeadImageUrl())
				.nickname(param.getNickname())
				.openid(param.getOpenid())
				.phoneNumber(param.getPhoneNumber())
				.position(param.getPosition())
				.userName(param.getUserName())
				.mainProductOrService(param.getMainProductOrService())
				.build();
		wxActivityUserSignUpMapper.insertSelective(record);
		Integer signUpCount = activity.getSignUpCount();
		signUpCount += 1;// 报名人数+1
		WxActivity updateRecord = WxActivity.builder()
				.id(activity.getId())
				.signUpCount(signUpCount)
				.createTime(nowDate)
				.build();
		wxActivityMapper.updateByPrimaryKeySelective(updateRecord);
		try {
			SuccessSignUpNoticeParam successSignUpNoticeParam = new SuccessSignUpNoticeParam();
			successSignUpNoticeParam.setActivityCode(param.getActivityCode());
			successSignUpNoticeParam.setOpenid(param.getOpenid());
			this.successSignUpNotice(successSignUpNoticeParam);
			System.out.println("报名成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>生成去付款的活动订单信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月29日 下午4:13:31
	 */
	public JsonResult<ActivityPayOrderInfo> generatePayOrderInfo(@RequestBody ActivityPayOrderParam param){
		JSR303ValidateUtils.validateInputParam(param);
		WxActivity activity = wxActivityMapper.getActivityWithoutContentByActivityCode(param.getActivityCode());
		if(activity == null){
			throw new ValidationException("活动不存在");
		}
		Date nowDate = new Date();
		String orderCode = "AO"+System.currentTimeMillis();
		// 插入活动订单记录
		WxActivityOrder orderRecord = WxActivityOrder.builder()
				.createTime(nowDate)
				.creator(param.getOpenid())
				.grandTotal(activity.getPrice())
				.headImageUrl(param.getHeadImageUrl())
				.nickName(param.getNickname())
				.openid(param.getOpenid())
				.orderCode(orderCode)
				.orderStatus((byte)OrderStatus.NOT_PAID.getValue())
				.build();
		wxActivityOrderMapper.insertSelective(orderRecord);
		
		// 插入活动订单状态变化记录
		WxActivityOrderStatus orderStatusRecord = WxActivityOrderStatus.builder()
				.createTime(nowDate)
				.creator(param.getOpenid())
				.orderCode(orderCode)
				.orderStatus((byte)OrderStatus.NOT_PAID.getValue())
				.build();
		wxActivityOrderStatusMapper.insertSelective(orderStatusRecord);
		
		// 插入订单细项记录
		WxActivityOrderItem orderItemRecord = WxActivityOrderItem.builder()
				.createTime(nowDate)
				.creator(param.getOpenid())
				.orderCode(orderCode)
				.activityCode(param.getActivityCode())
				.activityName(activity.getActivityName())
				.price(activity.getPrice())
				.quantity(1)
				.build();
		wxActivityOrderItemMapper.insert(orderItemRecord);
		
		// 支付信息
		ActivityPayOrderInfo info = ActivityPayOrderInfo.builder()
				.orderCode(orderCode)
				.activityCode(param.getActivityCode())
				.activityName(activity.getActivityName())
			    .price(activity.getPrice())
			    .activityTime(activity.getActivityTime())
			    .orderStatus((byte)OrderStatus.NOT_PAID.getValue())
				.build();
		return JsonResult.successJsonResult(info);
	}
	
	/**
	 * 
	 * <p>微信订单支付成功后的操作</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月26日 下午3:41:55
	 */
	public JsonResult<String> activityOrderPaySuccessOpt(@RequestBody CallBackParam param){
		JSR303ValidateUtils.validateInputParam(param);
		String orderCode = param.getOrderCode();
		ActivityPayOrderInfo info = wxActivityOrderMapper.getPayOrderInfo(param.getOrderCode());
		if(info == null) {
			throw new ReturnDataException("活动订单不存在");
		}
		// 判断订单是否处于待支付状态
		if(info.getOrderStatus() != OrderStatus.NOT_PAID.getValue()) {
			throw new ReturnDataException("活动订单状态异常");
		}
		Date nowDate = new Date();
		// 修改订单
		WxActivityOrder orderRecord = WxActivityOrder.builder()
				.orderCode(orderCode)
				.orderStatus((byte)OrderStatus.PAID.getValue())
				.paymentMethod(param.getPayMethod().byteValue())
				.paymentType(param.getOrderType().byteValue())
				.paymentStatus((byte)PayStatus.PAY_SUCCESS.getValue())
				.modifyTime(nowDate)
				.build();
		wxActivityOrderMapper.updateByOrderCode(orderRecord);
		
		// 插入活动订单状态变化记录
		WxActivityOrderStatus orderStatusRecord = WxActivityOrderStatus.builder()
				.createTime(nowDate)
				.orderCode(orderCode)
				.orderStatus((byte)OrderStatus.PAID.getValue())
				.build();
		wxActivityOrderStatusMapper.insertSelective(orderStatusRecord);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>发送成功报名的推送信息</p>
	 * @return
	 * @author 黄智聪  2019年4月16日 下午5:58:40
	 */
	public JsonResult<String> sendSuccessSignUpNotice(@RequestBody SuccessSignUpNoticeParam param) {
		JSR303ValidateUtils.validateInputParam(param);
		this.successSignUpNotice(param);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>getWxConfig获取微信的配置信息</p>
	 * @param request
	 * @return
	 * @author 黄智聪  2019年4月23日 下午3:38:03
	 */
	public JsonResult<Map<String, Object>> getWxConfig(String requestUrl) {
		Map<String, Object> ret = new HashMap<String, Object>();
		String access_token = accesstoken();
		String jsapi_ticket = jsApiTicket(access_token);
		String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳
		String nonceStr = UUID.randomUUID().toString(); // 必填，生成签名的随机串
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url="
				+ requestUrl;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(sign.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret.put("appid", appid);
		ret.put("timestamp", timestamp);
		ret.put("nonceStr", nonceStr);
		ret.put("signature", signature);
		return JsonResult.successJsonResult(ret);
	}
	
	/**
	 * 
	 * <p>查询活动评价</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月21日 下午7:26:26
	 */
	public JsonResult<Pagination<ActivityEvaluationInfo>> getEvaluationInfos(@RequestBody QueryPage param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<ActivityEvaluationInfo> infos = wxActivityEvaluationMapper.getEvaluationInfos(1);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	
	/**
	 * 
	 * <p>评价活动</p>
	 * @return
	 * @author 黄智聪  2019年1月23日 上午10:48:25
	 */
	public JsonResult<String> evaluateActivity(@RequestBody EvaluateActivityParam param){
		JSR303ValidateUtils.validateInputParam(param);
		WxActivity activity = wxActivityMapper.getActivityWithoutContentByActivityCode(param.getActivityCode());
		if(activity == null) {
			throw new ReturnDataException("活动不存在");
		}
		int count = wxActivityEvaluationMapper.countActivityEvaluation(param.getOpenid(), param.getActivityCode());
		if(count > 0) {
			throw new ReturnDataException("您已评价");
		}
		Date nowDate = new Date();
		// 新增评论记录
		WxActivityEvaluation evaluationRecord = WxActivityEvaluation.builder()
				.activityCode(param.getActivityCode())
				.createTime(nowDate)
				.creator(param.getOpenid())
				.evaluationCode("EA" + System.currentTimeMillis())
				.evaluationContent(param.getEvaluationContent())
				.grade(param.getGrade())
				.openid(param.getOpenid())
				.headImageUrl(param.getHeadImageUrl())
				.nickname(param.getNickname())
				.build();
		wxActivityEvaluationMapper.insertSelective(evaluationRecord);
		
		return JsonResult.successJsonResult();
	}
	
	
	

	/**
	 * 
	 * <p>发送报名成功通知</p>
	 * @param param
	 * @author 黄智聪  2019年4月20日 上午9:55:49
	 */
	private void successSignUpNotice(SuccessSignUpNoticeParam param) {
		WechatTemplate wechatTemplate = new WechatTemplate();
		wechatTemplate.setTemplate_id(templateId);
		wechatTemplate.setTouser(param.getOpenid());// 此处是用户的OpenId
		wechatTemplate.setUrl(detailUrl.replace("ACTIVITY_CODE", param.getActivityCode()));
		// 查询开课信息详情
		WxActivity record = wxActivityMapper.getActivityByActivityCode(param.getActivityCode());
		Map<String, TemplateData> m = new HashMap<String, TemplateData>();
		TemplateData title = new TemplateData();
		title.setColor("#0033ff");
		title.setValue("您好，你已成功报名了活动");
		m.put("first", title);
		TemplateData activityName = new TemplateData();
		activityName.setColor("#0033ff");
		activityName.setValue(record.getActivityName());
		m.put("keyword1", activityName);
		TemplateData activityTime = new TemplateData();
		activityTime.setColor("#0033ff");
		activityTime.setValue(DateUtils.getYMDHMSDateTime(record.getActivityTime()));
		m.put("keyword2", activityTime);
		TemplateData activityAddress = new TemplateData();
		activityAddress.setColor("#0033ff");
		activityAddress.setValue(record.getActivityAddress());
		m.put("keyword3", activityAddress);
		TemplateData remark = new TemplateData();
		remark.setColor("#0033ff");
		remark.setValue("感谢你的参与，点击查看活动详情");
		m.put("remark", remark);
		wechatTemplate.setData(m);
		try {
			sendTemplateMessage(accesstoken(), wechatTemplate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>发送推送消息</p>
	 * @param accessToken
	 * @param wechatTemplate
	 * @author 黄智聪  2019年4月16日 下午6:18:25
	 */
	private void sendTemplateMessage(String accessToken, WechatTemplate wechatTemplate) {
		String jsonString = new Gson().toJson(wechatTemplate).toString();
		String requestUrl = sendTemplateMessageUrl.replace("ACCESS_TOKEN", accessToken);
		String json = HttpClientUtils.sendHttpPostJson(requestUrl, jsonString);
		WechatResponse weiXinResponse = new Gson().fromJson(json, WechatResponse.class);
		if (null != weiXinResponse) {
			int errorCode = weiXinResponse.getErrcode();
			if (0 == errorCode) {
				System.out.println("OK:" + JSONObject.toJSONString(weiXinResponse));
			} else {
				String errorMsg = weiXinResponse.getErrmsg();
				System.out.println("出错:" + errorMsg);
			}
		}
	}

	/**
	 * 
	 * <p>获取微信access_token</p>
	 * @return
	 * @author 黄智聪  2019年4月16日 下午5:54:47
	 */
	private String accesstoken() {
		try {
			String responseJson = HttpClientUtils.sendHttpGet(getAccessTokenUrl);
			WechatRequestParam param = new Gson().fromJson(responseJson, WechatRequestParam.class);
			if (param != null) {
				return param.getAccess_token();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * <p>获取微信jsApiTicket</p>
	 * @return
	 * @author 黄智聪  2019年4月16日 下午5:54:47
	 */
	private String jsApiTicket(String accessToken) {
		try {
			String url = getJsApiTicketURL.replace("ACCESS_TOKEN", accessToken);
			String responseJson = HttpClientUtils.sendHttpGet(url);
			JSONObject jsonObj = JSONObject.parseObject(responseJson);
			if(jsonObj != null && jsonObj.getString("ticket") != null) {
				return jsonObj.getString("ticket");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 方法名：byteToHex</br>
	 * 详述：字符串加密辅助方法 </br>
	 * 开发人员：souvc </br>
	 * 创建时间：2016-1-5 </br>
	 * 
	 * @param hash
	 * @return 说明返回值含义
	 * @throws 说明发生此异常的条件
	 */
	private String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;

	}
	
	
	/************************************************** 客户端end **********************************************/
	
	
	
	
	
	/************************************************** 平台端start **********************************************/
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	public JsonResult<Pagination<ActivityInfo>> getPlatformActivityInfos(@RequestBody SearchActivityInfoParam param) {
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<ActivityInfo> infos = wxActivityMapper.getActivityInfos(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询活动详情</p>
	 * @param activityCode
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	public JsonResult<ActivityInfo> getPlatformActivityDetail(@RequestParam("activityCode") String activityCode) {
		ActivityInfo detail = wxActivityMapper.getActivityInfoByActivityCode(activityCode, null);
		if(detail == null) {
			throw new ReturnDataException("活动不存在");
		}
		return JsonResult.successJsonResult(detail);
	}
	
	/**
	 * 
	 * <p>查询活动报名人员信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	public JsonResult<Pagination<SignUpInfo>> getPlatformSignUpInfos(@RequestBody SearchActivityInfoParam param) {
		if(StringUtils.isBlank(param.getActivityCode())) {
			throw new ValidationException("请选择活动");
		}
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<SignUpInfo> infos = wxActivityMapper.getActivitySignUpInfoByActivityCode(param.getActivityCode());
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询活动评价</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月21日 下午7:26:26
	 */
	public JsonResult<Pagination<ActivityEvaluationInfo>> getPlatformEvaluationInfos(@RequestBody QueryPage param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<ActivityEvaluationInfo> infos = wxActivityEvaluationMapper.getEvaluationInfos(0);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>屏蔽或取消屏蔽评论</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月29日 下午2:19:45
	 */
	public JsonResult<String> shieldOrCancelShiedEvaluation(@RequestBody ShieldOrCancelShiedEvaluationParam param) {
		JSR303ValidateUtils.validateInputParam(param);
		WxActivityEvaluation evaluation = wxActivityEvaluationMapper.selectByCode(param.getEvaluationCode());
		if(evaluation == null) {
			throw new ReturnDataException("评价记录不存在");
		}
		if(IsShield.checkStatusExist(param.getStatus())) {
			throw new ValidationException("是否屏蔽参数有误");
		}
		WxActivityEvaluation record = WxActivityEvaluation.builder()
				.id(evaluation.getId())
				.isShield(param.getStatus())
				.modifier(param.getModifier())
				.modifyTime(new Date())
				.build();
		wxActivityEvaluationMapper.updateByPrimaryKeySelective(record);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>添加活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	public JsonResult<String> addActivity(@RequestBody AddActivityParam param) {
		JSR303ValidateUtils.validateInputParam(param);
		ActivityPic pic = param.getActivityPic();
		String activityCode = WxActivityConstants.ACTIVITY_CODE_PRIFIX + System.currentTimeMillis();
		// 上传活动图片
		UploadFileInfo fileInfo = uploadActivityPic(pic, activityCode);
		WxActivity record = WxActivity.builder()
				.activityAddress(param.getActivityAddress())
				.activityCode(activityCode)
				.activityContent(param.getActivityContent())
				.activityName(param.getActivityName())
				.activityPicName(fileInfo.getOriginalFileName())
				.activityPicUrl(fileInfo.getImgUrl())
				.activityTime(param.getActivityTime())
				.createTime(new Date())
				.creator(param.getCreator())
				.deadline(param.getDeadline())
				.status(param.getStatus())
				.signUpLimit(param.getSignUpLimit())
				.signUpCount(0)
				.price(param.getPrice())
				.build();
		wxActivityMapper.insertSelective(record);
		
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>添加活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	public JsonResult<String> editActivity(@RequestBody EditActivityParam param) {
		JSR303ValidateUtils.validateInputParam(param);
		WxActivity activity = wxActivityMapper.getActivityByActivityCode(param.getActivityCode());
		if(activity == null) {
			throw new ValidationException("活动不存在");
		}
		WxActivity record = WxActivity.builder()
				.id(activity.getId())
				.activityAddress(param.getActivityAddress())
				.activityContent(param.getActivityContent())
				.activityName(param.getActivityName())
				.activityTime(param.getActivityTime())
				.deadline(param.getDeadline())
				.modifyTime(new Date())
				.modifier(param.getModifier())
				.status(param.getStatus())
				.signUpLimit(param.getSignUpLimit())
				.price(param.getPrice())
				.build();
		// 修改了主图，则重新上传
		int isChangeActivityPic = param.getIsChangeActivityPic();
		if(isChangeActivityPic == IsChangeActivityPic.Y.getValue()) {
			UploadFileInfo fileInfo = uploadActivityPic(param.getActivityPic(), param.getActivityCode());
			record.setActivityPicName(fileInfo.getOriginalFileName());
			record.setActivityPicUrl(fileInfo.getImgUrl());
		}
		wxActivityMapper.updateByPrimaryKeySelective(record);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>上架、下架或删除活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	public JsonResult<String> putOnOrPutOffOrDelActivity(@RequestBody PutOnOrPutOffOrDelActivityParam param){
		// 参数校验
		JSR303ValidateUtils.validateInputParam(param);
		WxActivity activity = wxActivityMapper.getActivityByActivityCode(param.getActivityCode());
		if(activity == null) {
			throw new ValidationException("活动不存在");
		}
		Byte status = param.getStatus();
		boolean exist = ActivityStatus.checkStatusExist(status);
		if(!exist) {
			throw new ValidationException("活动状态不存在");
		}
		WxActivity record = WxActivity.builder()
				.id(activity.getId())
				.modifyTime(new Date())
				.modifier(param.getModifier())
				.status(param.getStatus())
				.build();
		wxActivityMapper.updateByPrimaryKeySelective(record);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>上传活动主图文件</p>
	 * @param pic
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年11月1日 下午4:49:22
	 */
	public UploadFileInfo uploadActivityPic(ActivityPic pic, String activityCode) {
		String originalFileName = pic.getFileName();
	    if(StringUtils.isBlank(originalFileName)) {
	    	throw new ValidationException("活动主图文件名为空");
	    }
	    if(pic.getFileSize() > WxActivityConstants.ACTIVITY_PIC_FILE_SIZE_LIMIT) {
	    	throw new ValidationException("活动主图文件大小不能超过2M");
	    }
	    String filedata = pic.getFileBase64Str();
	    if(StringUtils.isBlank(filedata)) {
	    	throw new ValidationException("活动主图文件内容为空");
	    }
	    int dotIndex = originalFileName.lastIndexOf(".");
	    String fileNameNoSufix = originalFileName.substring(0, dotIndex);
	    String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
	    // 新名称为文件名 + 活动编码
	    String newFileName = fileNameNoSufix + "_" + activityCode;
	    // 图片url
	    UploadImageParam up = new UploadImageParam();
	    up.setBucketname(FileConstants.IMAGE_BUCKETNAME);
	    up.setFiledata(filedata);
	    up.setFilename(newFileName);
	    up.setImagetype(FileConstants.AZZ_WX_COURSE_IMAGE_TYPE);
	    up.setPlattype(FileConstants.AZZ_PLATFORM);
	    up.setSuffix(sufix);
	    JsonResult<String> jr = systemImageUploadService.uploadImage(up);
	    UploadFileInfo file = new UploadFileInfo();
	    if(jr.getCode() == SystemErrorCode.SUCCESS.getCode()) {
	    	file.setImgUrl(jr.getData());
	    	file.setOriginalFileName(originalFileName);
	    }else {
	    	throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE,"活动主图上传失败，请重试");
	    }
	    return file;
	}
	
	
	/*
	public static void main(String[] args) throws IOException, InvalidFormatException {
		File file = new File("C:\\Users\\THINK\\Desktop\\a.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 2 ; i < lastRowNum ;i++) {
			XSSFRow row = sheet.getRow(i);
			String name = row.getCell(2).getStringCellValue();
			String companyName = row.getCell(3).getStringCellValue();
			String phoneNumber = row.getCell(4).getRawValue();
			String sql = "insert into wx_activity_user_sign_up(activity_code,user_name,phone_number,company_name) values('A1555671473934','"+name+"','"+phoneNumber+"','"+companyName+"');";
			System.out.println(sql);
		}
		
	}*/
	
	/************************************************** 平台端end **********************************************/
	
}
