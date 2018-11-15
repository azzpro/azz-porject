/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.merchant.pojo.bo.EditOrderStatus;
import com.azz.order.merchant.pojo.bo.SearchOrderDetailParam;
import com.azz.order.merchant.pojo.bo.SearchOrderListParam;
import com.azz.order.merchant.pojo.vo.OrderDetail;
import com.azz.order.merchant.pojo.vo.OrderList;
import com.azz.order.merchant.service.MerchantOrderService;

/**
 * <P>商户订单管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月13日 下午6:10:27
 */
@RestController
@RequestMapping("/azz/api/merchant/order")
public class MerchantOrderController {
	
	@Autowired
	private MerchantOrderService merchantOrderService;
	
	/**
	 * <p>商户订单列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月13日 下午6:13:54
	 */
	@RequestMapping("/getMerchantOrderList")
	public JsonResult<Pagination<OrderList>> getMerchantOrderList(SearchOrderListParam param){
		return merchantOrderService.getMerchantOrderList(param);
	}

	/**
	 * <p>获取商户订单详情</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月13日 下午6:38:49
	 */
	@RequestMapping("/getMerchantOrderDetail")
	public JsonResult<OrderDetail> getMerchantOrderDetail(SearchOrderDetailParam param){
	    return merchantOrderService.getMerchantOrderDetail(param);
	}
	
	/**
	 * <p>订单流转状态变更</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月14日 下午4:11:36
	 */
	@RequestMapping("/editMerchantOrderStatus")
	public JsonResult<String> editMerchantOrderStatus(EditOrderStatus param){
	    return merchantOrderService.editOrderStatus(param);
	}
}

