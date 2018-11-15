/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月15日 下午2:42:26
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.api.platform.PlatformMerchantOrderService;
import com.azz.order.merchant.pojo.bo.SearchOrderDetailParam;
import com.azz.order.merchant.pojo.vo.OrderDetail;
import com.azz.order.platform.bo.SearchMerchantOrderParam;
import com.azz.order.platform.vo.MerchantOrderList;

/**
 * <P>商户订单管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月15日 下午4:07:37
 */
@RestController
@RequestMapping("/azz/api/platform/merchant/order")
public class MerchantOrderController {
	
	@Autowired
	private PlatformMerchantOrderService platformMerchantOrderService;
	
	/**
	 * <p>查询商户订单管理</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月15日 下午4:09:36
	 */
	@RequestMapping("/getPlatformMerchantOrderList")
	public JsonResult<Pagination<MerchantOrderList>> getClientOrderInfoList(SearchMerchantOrderParam param){
		return platformMerchantOrderService.getPlatformMerchantOrderList(param);
	}
	
	/**
	 * <p>获取平台端商户订单详情</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月15日 下午4:10:21
	 */
	@RequestMapping("/getMerchantOrderDetail")
	public JsonResult<OrderDetail> getMerchantOrderDetail(SearchOrderDetailParam param){
		return platformMerchantOrderService.getMerchantOrderDetail(param);
	}
	
	

}

