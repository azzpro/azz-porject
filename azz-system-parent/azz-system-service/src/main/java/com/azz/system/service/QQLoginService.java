package com.azz.system.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.constants.ClientConstants;
import com.azz.core.constants.SmsConstants.SmsCode;
import com.azz.core.wx.constants.WxConstants;
import com.azz.exception.JSR303ValidationException;
import com.azz.model.Password;
import com.azz.system.api.SystemSmsSendService;
import com.azz.system.bo.QQClientRegistParam;
import com.azz.system.bo.SmsCheck;
import com.azz.system.bo.SmsCodeValidation;
import com.azz.system.mapper.ClientUserMapper;
import com.azz.system.mapper.ClientWxUserMapper;
import com.azz.system.pojo.ClientUser;
import com.azz.system.pojo.ClientWxUser;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.system.vo.QQCallBackInfo;
import com.azz.system.vo.QQLoginInfo;
import com.azz.system.vo.SmsInfo;
import com.azz.util.HttpClientUtil;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.PasswordHelper;
import com.azz.util.SystemSeqUtils;

@Service
public class QQLoginService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Value("${qq.AppId}")
	private String AppId;
	
	@Value("${qq.AppSecret}")
	private String AppSecret;
	
	@Value("${qq.baseUrl}")
	private String BaseUrl;
	
	@Value("${qq.user_info_url}")
	private String userInfoUrl;
	
	@Value("${qq.conn_timeout}")
	private String connTimeout;
	
	@Value("${qq.read_timeout}")
	private String readTimeout;
	
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
	 * 微信回调
	 * 
	 * @param code
	 * @param state
	 * @param key
	 * @return
	 */
	public JsonResult<QQCallBackInfo> callback(HttpServletRequest request,String access_token,String openid) {
		log.info("进入QQ 回调"+access_token+":::"+openid);
		QQCallBackInfo wcbi = new QQCallBackInfo();
		if (StringUtils.isBlank(access_token) || StringUtils.isBlank(openid)) {
			wcbi.setCode(WxConstants.NOACCESSCODE);
			wcbi.setMsg(WxConstants.NOACCESS);
			return new JsonResult<>(wcbi);
		} else {
		    if(StringUtils.isNotBlank(openid)) {//直接完成登录操作
		        ClientWxUser wxUser = clientWxUserMapper.selectWxUserByOpenid(openid);
		        if(wxUser != null) {
		        	ClientUser clientUser = clientUserMapper.getClientUserByClientUserCode(wxUser.getUserCode());
						if(clientUser != null) {
								wcbi.setCode(WxConstants.LOGINCODE);
								wcbi.setPhone(clientUser.getPhoneNumber());
								redis.opsForValue().set(clientUser.getPhoneNumber(), "wxScan");
								redis.expire(clientUser.getPhoneNumber(), 30, TimeUnit.MINUTES);
								return new JsonResult<>(wcbi);
							}
		         }else {
		        	 String url = String.format(BaseUrl+userInfoUrl, access_token, AppId, openid);
		        	 String resultString = "";
		        		try {
							 resultString = HttpClientUtil.get(url, "UTF-8",Integer.parseInt(connTimeout), Integer.parseInt(readTimeout));
							 log.info(resultString);
		        		} catch (Exception e) {
							e.printStackTrace();
							wcbi.setCode(WxConstants.STATECODE);
							wcbi.setMsg(e.getMessage());
							return new JsonResult<>(wcbi);
						} 
		        		if(StringUtils.isNotBlank(resultString)) {
		        			JSONObject object = JSON.parseObject(resultString);
		        			String nickname = object.getString("nickname");
		        			String headUrl = object.getString("figureurl_qq_1");
		        			wcbi.setNickname(nickname);
							wcbi.setAccessToken(access_token);
							wcbi.setHeadimgurl(headUrl);
							wcbi.setExpiresIn("");
							wcbi.setOpenid(openid);
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
	public JsonResult<QQCallBackInfo> goBindOrReg(@RequestBody QQCallBackInfo wcbi) {
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
	public JsonResult<QQLoginInfo> regAndBind(@RequestBody QQClientRegistParam wcrp) {
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
		wsc.setOpenid(wcrp.getOpenid());
		wsc.setUserCode(clientUserRecord.getClientUserCode());
		wsc.setAvatarUrl(wcrp.getAvatarUrl());
		wsc.setNickName(wcrp.getNickName());
		wsc.setPlatformType("QQ");
		int i = clientWxUserMapper.insert(wsc);
		if(1 == i) {
			QQLoginInfo wi = new QQLoginInfo();
			wi.setCode(WxConstants.REGSUCCESSCODE);
			wi.setMsg(WxConstants.REGSUCCESSMSG);
			wi.setPassword(confirmPassword);
			wi.setPhone(phoneNumber);
			return new JsonResult<>(wi);
		}else {
			QQLoginInfo wi = new QQLoginInfo();
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
