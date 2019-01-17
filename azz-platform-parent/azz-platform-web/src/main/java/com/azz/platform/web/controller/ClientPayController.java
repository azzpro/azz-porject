package com.azz.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.api.client.ClientPayService;
import com.azz.order.client.pojo.ClientPay;
import com.azz.order.client.pojo.bo.PayList;

@RestController
@RequestMapping("/azz/api/pay")
public class ClientPayController {

	@Autowired
	private ClientPayService clientPayService;
	
	/**
	 * <p>支付管理列表</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年12月3日 下午2:46:08
	 */
	@RequestMapping("toPayList")
	public JsonResult<Pagination<ClientPay>> toPayList(PayList pl){
		return clientPayService.toPayList(pl);
	}
	
	/**
	 * <p>
	 * 支付订单详情
	 * </p>
	 * 
	 */
	@RequestMapping("getOrderInfo")
	public JsonResult<ClientPay> getOrderInfo(@RequestParam("number") String number) {
		return clientPayService.getOrderInfo(number);
	}
}
