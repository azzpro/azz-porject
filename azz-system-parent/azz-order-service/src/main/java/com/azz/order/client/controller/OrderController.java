/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.client.pojo.bo.SearchClientOrderParam;
import com.azz.order.client.pojo.vo.ClientOrderDetail;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.order.client.service.ClientOrderService;

/**
 * <P>客户端订单业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月12日 下午3:14:43
 */
@RestController
@RequestMapping("/azz/api/client/order")
public class OrderController {
	
	@Autowired
	private ClientOrderService clientOrderService;
	
	@RequestMapping("/getClientOrderInfoList")
	public JsonResult<Pagination<ClientOrderInfo>> getClientOrderInfoList(/*@RequestBody */SearchClientOrderParam param){
		return clientOrderService.getClientOrderInfoList(param);
	}
	
	@RequestMapping("/getClientOrderDetail")
	public JsonResult<ClientOrderDetail> getClientOrderDetail(String clientOrderCode){
		return clientOrderService.getClientOrderDetail(clientOrderCode);
	}

}

