package com.azz.system.service;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.constants.ClientConstants;
import com.azz.core.constants.SmsConstants.SmsCode;
import com.azz.core.wx.constants.WxConstants;
import com.azz.exception.JSR303ValidationException;
import com.azz.model.Password;
import com.azz.system.api.SystemSmsSendService;
import com.azz.system.bo.SmsCheck;
import com.azz.system.bo.SmsCodeValidation;
import com.azz.system.bo.WxClientRegistParam;
import com.azz.system.mapper.ClientUserMapper;
import com.azz.system.mapper.ClientWxUserMapper;
import com.azz.system.pojo.ClientUser;
import com.azz.system.pojo.ClientWxUser;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.system.vo.SmsInfo;
import com.azz.system.vo.WxCallBackInfo;
import com.azz.system.vo.WxInfo;
import com.azz.system.vo.WxLoginInfo;
import com.azz.util.HttpClientUtil;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.MD5Encrypt;
import com.azz.util.PasswordHelper;
import com.azz.util.SystemSeqUtils;
import com.azz.util.URLEncodeUtils;

@Service
public class WxLoginService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Value("${wx.AppId}")
	private String appid;

	@Value("${wx.AppSecret}")
	private String secret;

	@Autowired
	private StringRedisTemplate redis;

	@Autowired
	private ClientUserMapper clientUserMapper;

	@Autowired
	private ClientWxUserMapper clientWxUserMapper;

	@Autowired
	private SystemSmsSendService systemSmsSendService;

	@Autowired
	private DbSequenceService dbSequenceService;

	/**
	 * 去到微信扫码页面
	 * 
	 * @return
	 */
	public JsonResult<WxInfo> goWxScanPage() {
		String hd = UUID.randomUUID().toString();
		String state = MD5Encrypt.encryptMD5(hd);
		String key = MD5Encrypt.encryptMD5(hd);
		redis.opsForValue().set(key, state);
		StringBuilder sb = new StringBuilder(WxConstants.WXURL);
		sb.append("?").append("appid=").append(appid).append("&redirect_uri=")
				.append(URLEncodeUtils.toURLEncoded(WxConstants.CALLBACKURL)).append("&response_type=").append("code")
				.append("&scope=").append(WxConstants.SCOPE).append("&state=").append(state).append("#wechat_redirect");
		log.info("微信登录请求url------------>" + sb.toString());

		WxInfo wi = new WxInfo();
		wi.setKey(key);
		wi.setUrl(sb.toString());
		wi.setState(state);
		return new JsonResult<>(wi);
	}

	/**
	 * 微信回调
	 * 
	 * @param code
	 * @param state
	 * @param key
	 * @return
	 */
	public JsonResult<WxCallBackInfo> callback(String code, String state) {
		String string = redis.opsForValue().get(state);
		WxCallBackInfo wcbi = new WxCallBackInfo();
		// 取缓存 state 防攻击
		if (!Objects.equals(state, string)) {
			wcbi.setCode(WxConstants.STATECODE);
			wcbi.setMsg(WxConstants.STATEMSG);
			return new JsonResult<>(wcbi);
		}
		if (StringUtils.isBlank(code)) {
			wcbi.setCode(WxConstants.NOACCESSCODE);
			wcbi.setMsg(WxConstants.NOACCESS);
			return new JsonResult<>(wcbi);
		} else {
			StringBuilder url = new StringBuilder();
			url.append(WxConstants.ACCESSTOKENURL);
			url.append("?appid=" + appid);
			url.append("&secret=" + secret);
			url.append("&code=" + code);
			url.append("&grant_type=authorization_code");
			String result = null;
			try {
				result = HttpClientUtil.get(url.toString(), "UTF-8");
			} catch (Exception e2) {
				e2.printStackTrace();
				wcbi.setCode(WxConstants.HTTPERRORCODE);
				wcbi.setMsg(WxConstants.HTTPERRORMSG);
				return new JsonResult<>(wcbi);
			}
			// 获取微信返回结果
			if (StringUtils.isNotBlank(result)) {
				JSONObject object = JSONObject.parseObject(result);
				String access_token = object.getString("access_token");
				String expires_in = object.getString("expires_in");
				String refresh_token = object.getString("refresh_token");
				String openid = object.getString("openid");
				String scope = object.getString("scope");
				String unionid = object.getString("unionid");
				if (!StringUtils.isBlank(openid) && StringUtils.isNotBlank(access_token)) {
					StringBuilder sd = new StringBuilder();
					sd.append(WxConstants.AUTHURL).append("?access_token=" + access_token).append("&openid=" + openid);
					String tokenInvalid = null;
					try {
						tokenInvalid = HttpClientUtil.get(sd.toString(), "UTF-8");
					} catch (Exception e1) {
						e1.printStackTrace();
						wcbi.setCode(WxConstants.HTTPERRORCODE);
						wcbi.setMsg(WxConstants.HTTPERRORMSG);
						return new JsonResult<>(wcbi);
					}
					if (StringUtils.isNotBlank(tokenInvalid)) {
						JSONObject oj = JSONObject.parseObject(tokenInvalid);
						// 校验access_token是否有效
						if (!Objects.equals(oj.get("errmsg"), "ok")) {
							wcbi.setCode(WxConstants.ACCESSTOKENERRORCODE);
							wcbi.setMsg(WxConstants.ACCESSTOKENERRORMSG);
							return new JsonResult<>(wcbi);
						}else {
							//直接完成登录操作
							ClientWxUser wxUser = clientWxUserMapper.selectWxUserByOpenid(openid);
							if(wxUser != null && StringUtils.isNotBlank(wxUser.getUserCode())) {
								/*取最新头像 昵称*/
								StringBuilder sb = new StringBuilder();
								sb.append(WxConstants.USERINFOURL).append("?access_token=" + access_token)
										.append("&openid=" + openid);
								String userInfo = null;
								try {
									userInfo = HttpClientUtil.get(sb.toString(), "UTF-8");
								} catch (Exception e) {
									e.printStackTrace();
									wcbi.setCode(WxConstants.HTTPERRORCODE);
									wcbi.setMsg(WxConstants.HTTPERRORMSG);
									return new JsonResult<>(wcbi);
								}
								if(userInfo != null) {
									JSONObject cc = JSONObject.parseObject(userInfo);
									int i = clientWxUserMapper.updateAvatarUrlAndNickname((String) cc.get("headimgurl"), (String) cc.get("nickname"), openid);
									log.info("更新微信昵称 微信头像---->"+(String) cc.get("headimgurl")+"::"+(String) cc.get("nickname"));
									if(i != 1) {
										wcbi.setCode(WxConstants.UPDATEERRORCODE);
										wcbi.setMsg(WxConstants.UPDATEERRORMSG);
										return new JsonResult<>(wcbi);
									}
								}
								ClientUser clientUser = clientUserMapper.getClientUserByClientUserCode(wxUser.getUserCode());
								if(clientUser != null) {
									wcbi.setCode(WxConstants.LOGINCODE);
									wcbi.setPhone(clientUser.getPhoneNumber());
									redis.opsForValue().set(clientUser.getPhoneNumber(), "wxScan");
									redis.expire(clientUser.getPhoneNumber(), 30, TimeUnit.MINUTES);
									return new JsonResult<>(wcbi);
								}
							}
						}
					}
					// 获取用户头像 用户昵称
					StringBuilder sb = new StringBuilder();
					sb.append(WxConstants.USERINFOURL).append("?access_token=" + access_token)
							.append("&openid=" + openid);
					String userInfo = null;
					try {
						userInfo = HttpClientUtil.get(sb.toString(), "UTF-8");
					} catch (Exception e) {
						e.printStackTrace();
						wcbi.setCode(WxConstants.HTTPERRORCODE);
						wcbi.setMsg(WxConstants.HTTPERRORMSG);
						return new JsonResult<>(wcbi);
					}
					if (userInfo != null) {
						JSONObject oj = JSONObject.parseObject(userInfo);
						wcbi.setHeadimgurl((String) oj.get("headimgurl"));
						wcbi.setNickname((String) oj.get("nickname"));
						wcbi.setAccessToken(access_token);
						wcbi.setExpiresIn(expires_in);
						wcbi.setOpenid(openid);
						wcbi.setRefreshToken(refresh_token);
						wcbi.setScope(scope);
						wcbi.setUnionid(unionid);
						wcbi.setCode(WxConstants.SUCCESSCODE);
						wcbi.setMsg(WxConstants.SUCCESSMSG);
						return new JsonResult<>(wcbi);
					}
				}
			}
			wcbi.setCode(WxConstants.STATECODE);
			wcbi.setMsg(WxConstants.STATEMSG);
			return new JsonResult<>(wcbi);
		}
	}

	/**
	 * 绑定已有账户或注册新用户绑定
	 * 
	 * @param wcbi
	 * @return
	 */
	public JsonResult<WxCallBackInfo> goBindOrReg(@RequestBody WxCallBackInfo wcbi) {
		return new JsonResult<>(wcbi);
	}

	/**
	 * @param wsc
	 * @return
	 */
	public Integer insert(@RequestBody ClientWxUser wsc) {
		int i = clientWxUserMapper.insert(wsc);
		return i;
	}
	

	/**
	 * 注册并绑定
	 * 
	 * @return
	 */
	public JsonResult<WxLoginInfo> regAndBind(@RequestBody WxClientRegistParam wcrp) {
		// 参数校验
		JSR303ValidateUtils.validate(wcrp);
		String password = wcrp.getPassword();
		String confirmPassword = wcrp.getConfirmPassword();
		// 密码与确认密码一致性校验
		if (!password.equals(confirmPassword)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "密码与确认密码不一致");
		}
		String phoneNumber = wcrp.getPhoneNumber();
		Date nowDate = new Date();
		ClientUser clientUser = clientUserMapper.getClientUserByPhoneNumber(phoneNumber);
		if (clientUser != null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号已被注册");
		}

		// 校验验证码
		this.checkVerificationCode(phoneNumber, wcrp.getVerificationCode());

		String clientUserCode = dbSequenceService.getClientCustomerNumber();
		// 生成盐值加密的密码
		Password pwd = PasswordHelper.encryptPasswordByModel(password);
		ClientUser clientUserRecord = ClientUser.builder().createTime(nowDate)
				.clientUserCode(SystemSeqUtils.getSeq(clientUserCode)).password(pwd.getPassword())
				.phoneNumber(phoneNumber).salt(pwd.getSalt()).remark("来自客户注册").creator(clientUserCode).build();
		clientUserMapper.insertSelective(clientUserRecord);
		ClientWxUser wsc = new ClientWxUser();
		wsc.setAccess_token(wcrp.getAccessToken());
		wsc.setExpires_in(Long.parseLong(wcrp.getExpiresIn()));
		wsc.setOpenid(wcrp.getOpenid());
		wsc.setScope(wcrp.getScope());
		wsc.setUnionid(wcrp.getUnionid());
		wsc.setRefresh_token(wcrp.getRefreshToken());
		wsc.setUserCode(clientUserRecord.getClientUserCode());
		wsc.setAvatarUrl(wcrp.getAvatarUrl());
		wsc.setNickName(wcrp.getNickName());
		wsc.setPlatformType("微信");
		int i = clientWxUserMapper.insert(wsc);
		if(1 == i) {
			WxLoginInfo wi = new WxLoginInfo();
			wi.setCode(WxConstants.REGSUCCESSCODE);
			wi.setMsg(WxConstants.REGSUCCESSMSG);
			wi.setPassword(confirmPassword);
			wi.setPhone(phoneNumber);
			return new JsonResult<>(wi);
		}else {
			WxLoginInfo wi = new WxLoginInfo();
			wi.setCode(WxConstants.REGFAILDCODE);
			wi.setMsg(WxConstants.REGFAILDMSG);
			return new JsonResult<>(wi);
		}
		
		
	}

	/**
	 * 
	 * <p>
	 * 校验验证码
	 * </p>
	 * 
	 * @param param
	 * @return
	 * @author 黄智聪 2018年11月26日 下午7:10:22
	 */
	public void checkVerificationCode(String phoneNumber, String verificationCode) {
		// 先校验验证码是否已失效
		SmsCodeValidation sv = new SmsCodeValidation();
		sv.setPhone(phoneNumber);
		sv.setSec(ClientConstants.CLIENT_REGIST_SMS_TIME_OUT);
		JsonResult<SmsInfo> jr = systemSmsSendService.checkMsgCodeTime(sv);
		if (jr.getData() == null || !jr.getData().getCode().equals(SmsCode.SUCCESS.getCode())) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "短信验证码已失效，请重新获取");
		}
		// 再校验验证码是否正确
		SmsCheck sc = new SmsCheck();
		sc.setCode(verificationCode);
		sc.setPhone(phoneNumber);
		jr = systemSmsSendService.checkMsgCode(sc);
		if (jr.getData() == null || !jr.getData().getCode().equals(SmsCode.SUCCESS.getCode())) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "验证码错误");
		}
	}
}
