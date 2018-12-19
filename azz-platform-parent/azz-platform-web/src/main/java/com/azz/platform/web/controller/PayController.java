/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月3日 下午3:02:38
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.api.client.ClientPayService;
import com.azz.order.client.pojo.ClientPay;
import com.azz.order.client.pojo.bo.PayList;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年12月3日 下午3:02:38
 */
@RestController
@RequestMapping("/azz/api/pay")
public class PayController {
	
	@Autowired
	private ClientPayService pps;
	
	/**
	 * <p>支付列表</p>
	 * @param pl
	 * @return
	 * @author 刘建麟  2018年12月3日 下午3:04:26
	 */
	@RequestMapping("toPayList")
	public JsonResult<Pagination<ClientPay>> toPayList(PayList pl){
		return pps.toPayList(pl);
	}
}

