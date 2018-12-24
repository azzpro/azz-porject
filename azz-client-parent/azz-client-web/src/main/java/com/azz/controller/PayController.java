/******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午4:09:49
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.azz.core.common.JsonResult;
import com.azz.core.constants.PayConstants.PayCode;
import com.azz.order.api.client.ClientPayService;
import com.azz.order.client.pojo.PaymentInfo;
import com.azz.order.client.pojo.RetBean;
import com.azz.order.client.pojo.bo.PageOrder;
import com.azz.util.LLPayUtil;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午4:09:49
 */
@RestController
@RequestMapping("/azz/api/pay")
public class PayController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ClientPayService pfps;
	
	@RequestMapping("submitOrderPay")
	public JsonResult<PaymentInfo> submitOrderPay(HttpServletRequest request,PageOrder po){
		po.setClientIp(LLPayUtil.getIpAddr(request));
		return pfps.submitOrderPay(po);
	}
	
	@RequestMapping("payNotify")
	public void payNotify(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		log.info("进入支付回调接口");
		String reqStr = LLPayUtil.readReqStr(request);
		log.info("回调参数["+reqStr+"]");
		JsonResult<RetBean> payNotify = pfps.payNotify(reqStr);
		response.getWriter().write(JSON.toJSONString(payNotify.getData()));
		response.getWriter().flush();
			
	}
	
}