/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月14日 上午11:24:54
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.constants.SmsConstants;
import com.azz.core.constants.SmsType;
import com.azz.system.api.SystemSmsSendService;
import com.azz.system.bo.SmsCodeValidation;
import com.azz.system.bo.SmsParams;
import com.azz.system.vo.SmsInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月14日 上午11:24:54
 */
@RestController
@RequestMapping("/azz/api/system")
public class SmsController {

	@Autowired
	private SystemSmsSendService sl;
	
	@RequestMapping("smsSend")
	public JsonResult<String> sendSmsCode(SmsParams sp){
		sp.setMsgType(SmsType.ACCOUNT_CREATE_SUCCESS);
		return sl.sendSmsCode(sp);
	}
	
	@RequestMapping("validationCode")
	public JsonResult<SmsInfo> validationCode(SmsCodeValidation s){
		return sl.validationCode(s);
	}
	
}

