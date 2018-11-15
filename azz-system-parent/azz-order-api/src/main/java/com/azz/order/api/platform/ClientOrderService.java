/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月15日 下午2:27:14
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.api.platform;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.client.pojo.vo.ClientOrderDetail;
import com.azz.order.platform.bo.AllocateClientOrderParam;
import com.azz.order.platform.bo.SearchPlatformClientOrderParam;
import com.azz.order.platform.vo.AllocatedMerchantOrderInfo;
import com.azz.order.platform.vo.PlatformClientOrderInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月15日 下午2:27:14
 */
@FeignClient("azz-system-service")
public interface ClientOrderService {
	
	/**
	 * 
	 * <p>查询平台客户订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月13日 上午10:54:40
	 */
	@RequestMapping("/azz/api/platform/client/order/getClientOrderInfoList")
	JsonResult<Pagination<PlatformClientOrderInfo>> getClientOrderInfoList(@RequestBody SearchPlatformClientOrderParam param);
	
	/**
	 * 
	 * <p>查询客户订单详情</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月13日 上午10:56:03
	 */
	@RequestMapping("/azz/api/platform/client/order/getClientOrderDetail")
	JsonResult<ClientOrderDetail> getClientOrderDetail(String clientOrderCode);
	
	/**
	 * 
	 * <p>查询客户订单拆分后的商户订单列表</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月14日 下午3:58:10
	 */
	@RequestMapping("/azz/api/platform/client/order/getAllocatedMerchantOrder")
	JsonResult<AllocatedMerchantOrderInfo> getAllocatedMerchantOrder(String clientOrderCode);
	
	/**
	 * 
	 * <p>分配客户订单</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月14日 下午2:31:17
	 */
	@RequestMapping("/azz/api/platform/client/order/allocateClientOrder")
	JsonResult<String> allocateClientOrder(@RequestBody AllocateClientOrderParam param);
	
}

