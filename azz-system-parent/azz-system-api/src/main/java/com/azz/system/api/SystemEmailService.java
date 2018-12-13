/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月13日 下午1:47:18
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.azz.core.common.JsonResult;
import com.azz.system.bo.MailCheck;
import com.azz.system.bo.MailCodeValidation;
import com.azz.system.bo.MailParam;
import com.azz.system.vo.SmsInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年12月13日 下午1:47:18
 */
@FeignClient("azz-system-service")
public interface SystemEmailService {
		
	@RequestMapping(value="/azz/api/system/sendMail",method=RequestMethod.POST)
	JsonResult<SmsInfo> sendMail(@RequestBody MailParam m);
	
	@RequestMapping(value="/azz/api/system/validationMailCodeTime",method=RequestMethod.POST)
	JsonResult<SmsInfo> validationMailCodeTime(@RequestBody MailCodeValidation sv);

	@RequestMapping(value="/azz/api/system/checkMailCode",method=RequestMethod.POST)
	JsonResult<SmsInfo> checkMailCode(@RequestBody MailCheck sc);
}

