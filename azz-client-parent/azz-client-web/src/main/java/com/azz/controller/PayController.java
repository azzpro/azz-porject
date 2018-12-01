/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午4:09:49
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.system.api.PlatfromPayService;
import com.azz.system.bo.SubmitPayParams;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午4:09:49
 */
@RestController
@RequestMapping("/azz/api/pay")
public class PayController {
	
	@Autowired
	private PlatfromPayService pfps;
	
	@RequestMapping("submitOrderPay")
	public JsonResult<String> submitOrderPay(SubmitPayParams spp){
		return pfps.submitOrderPay(spp);
	}
}

