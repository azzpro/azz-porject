/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月23日 下午5:18:16
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.exception.JSR303ValidationException;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月23日 下午5:18:16
 */
@Service
public class SystemSmsSendService {

private static final Logger LOG = LoggerFactory.getLogger(SystemSmsSendService.class);
	
	@Value("${aliyun.accessKeyId}")
	private String accessKeyId;
	
	@Value("${aliyun.accessKeySecret}")
	private String accessKeySecret;
	
	@Value("${aliyun.signName}")
	private String signName;
	
	@Value("${aliyun.product}")
	private String product;
	
	@Value("${aliyun.domain}")
	private String domain;
	
	/**
	 * <p>发送短信</p>
	 * @param phone
	 * @param nickname
	 * @author 刘建麟  2018年10月23日 下午5:36:36
	 */
	public Map<String,Object> sendSms(String phone,String nickname, String template) {
		if(StringUtils.isBlank(phone)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"手机号码不能为空");
		}
		//设置HTTP 超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"初始化短信发送失败");
		}
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名
        request.setSignName(signName);
        //必填:短信模板
        request.setTemplateCode(template);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        SendSmsResponse sendSmsResponse = null;
        try {
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ServerException e) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"短信发送异常");
		} catch (ClientException e) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"短信发送异常");
		}
        if(null == sendSmsResponse || !sendSmsResponse.getCode().equals("DELIVRD")) {
        	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"短信发送失败");
        }
        Map<String,Object> sms = new HashMap<String,Object>();
        sms.put("code", sendSmsResponse.getCode());
        sms.put("msg", sendSmsResponse.getMessage());
        return sms;
	}
}

