/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午3:09:13
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.pay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.system.bo.SubmitPayParams;
import com.azz.system.pay.service.PlatfromPayService;
import com.azz.util.JSR303ValidateUtils;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午3:09:13
 */
@RestController
@RequestMapping("/azz/api/pay")
public class PaltfromPayController {
	
	private final Logger LOG  =LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PlatfromPayService pps;
	/**
	 * <p>提交支付</p>
	 * @param spp
	 * @return
	 * @author 刘建麟  2018年11月26日 下午3:14:49
	 */
	@RequestMapping(value="submitOrderPay",method=RequestMethod.POST)
	public JsonResult<String> submitOrderPay(@RequestBody SubmitPayParams spp){
		return pps.submitOrderPay(spp);
	} 
}

