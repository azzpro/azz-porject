/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午3:19:35
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.api.client;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.order.client.pojo.RetBean;
import com.azz.order.client.wx.bo.WxPayOrderInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午3:19:35
 */
@FeignClient("azz-order-service")
public interface WxPayService {
	
	/**
	 * <p>支付回调</p>
	 * @author 刘建麟  2018年12月17日 下午6:27:10
	 */
	@RequestMapping(value="/azz/api/wx/pay/callback",method=RequestMethod.POST)
	public String callback(@RequestParam("xml") String xml);
	
	/**
	 * <p>提交支付</p>
	 * @param spp
	 * @return
	 * @author 刘建麟  2018年11月26日 下午3:20:20
	 */
	@RequestMapping(value="/azz/api/wx/pay/submitOrderPay",method=RequestMethod.POST)
	public Map<String,String> submitOrderPay(@RequestBody WxPayOrderInfo po);
	
}

