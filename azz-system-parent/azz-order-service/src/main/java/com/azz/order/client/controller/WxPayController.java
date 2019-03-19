/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午3:09:13
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.client.pojo.ClientPay;
import com.azz.order.client.pojo.RetBean;
import com.azz.order.client.pojo.bo.BankBranch;
import com.azz.order.client.pojo.bo.Enterprisereginfo;
import com.azz.order.client.pojo.bo.PayList;
import com.azz.order.client.pojo.bo.Personreginfo;
import com.azz.order.client.service.WxPayService;
import com.azz.order.client.wx.bo.WxPayOrderInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午3:09:13
 */
@RestController
@RequestMapping("/azz/api/wx/pay")
public class WxPayController {
	
	@Autowired
	private WxPayService pps;
	/**
	 * <p>提交支付</p>
	 * @param spp
	 * @return
	 * @author 刘建麟  2018年11月26日 下午3:14:49
	 */
	@RequestMapping(value="submitOrderPay",method=RequestMethod.POST)
	public Map<String,String> submitOrderPay(@RequestBody WxPayOrderInfo po){
		return pps.submitOrderPay(po);
	} 
	
	
	/**
	 * 支付回调
	 * @param reqStr
	 * @return
	 */
	@RequestMapping("callback")
	public String callback(@RequestParam("xml") String wx) {
		return pps.callback(wx);
	}
	
	
}

