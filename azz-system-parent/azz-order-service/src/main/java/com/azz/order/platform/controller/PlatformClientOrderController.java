/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.client.pojo.vo.ClientOrderDetail;
import com.azz.order.platform.bo.AllocateClientOrderParam;
import com.azz.order.platform.bo.SearchPlatformClientOrderParam;
import com.azz.order.platform.service.PlatformClientOrderService;
import com.azz.order.platform.vo.AllocatedMerchantOrderInfo;
import com.azz.order.platform.vo.OrderOperationRecord;
import com.azz.order.platform.vo.PlatformClientOrderInfo;

/**
 * <P>平台端商户订单管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月13日 下午6:10:27
 */
@RestController
@RequestMapping("/azz/api/platform/client/order")
public class PlatformClientOrderController {
	
	@Autowired
	private PlatformClientOrderService platformClientOrderService;
	
	/**
	 * 
	 * <p>查询平台客户订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月13日 上午10:54:40
	 */
	@RequestMapping("/getClientOrderInfoList")
	public JsonResult<Pagination<PlatformClientOrderInfo>> getClientOrderInfoList(@RequestBody SearchPlatformClientOrderParam param){
		return platformClientOrderService.getClientOrderInfoList(param);
	}
	
	/**
	 * 
	 * <p>查询客户订单详情</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月13日 上午10:56:03
	 */
	@RequestMapping("/getClientOrderDetail")
	public JsonResult<ClientOrderDetail> getClientOrderDetail(@RequestParam("clientOrderCode")String clientOrderCode){
		return platformClientOrderService.getClientOrderDetail(clientOrderCode);
	}
	
	/**
	 * 
	 * <p>查询客户订单拆分后的商户订单列表</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月14日 下午3:58:10
	 */
	@RequestMapping("/getAllocatedMerchantOrder")
	public JsonResult<AllocatedMerchantOrderInfo> getAllocatedMerchantOrder(@RequestParam("clientOrderCode")String clientOrderCode){
		return platformClientOrderService.getAllocatedMerchantOrder(clientOrderCode);
	}
	
	/**
	 * 
	 * <p>查询客户订单拆单后的生成的商户订单</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月14日 下午3:58:10
	 */
	@RequestMapping("/getGeneratedMerchantOrderInfo")
	public JsonResult<AllocatedMerchantOrderInfo> getGeneratedMerchantOrderInfo(@RequestParam("clientOrderCode")String clientOrderCode){
		return platformClientOrderService.getGeneratedMerchantOrderInfo(clientOrderCode);
	}
	
	/**
	 * 
	 * <p>分配客户订单</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月14日 下午2:31:17
	 */
	@RequestMapping("/allocateClientOrder")
	public JsonResult<String> allocateClientOrder(@RequestBody AllocateClientOrderParam param){
		return platformClientOrderService.allocateClientOrder(param);
	}
	
	/**
	 * 
	 * <p>查询客户订单操作记录</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月16日 下午3:18:05
	 */
	@RequestMapping("/getClientOrderOperationRecords")
	public JsonResult<List<OrderOperationRecord>> getClientOrderOperationRecords(@RequestParam("clientOrderCode")String clientOrderCode){
		return platformClientOrderService.getClientOrderOperationRecords(clientOrderCode);
	}
	
}

