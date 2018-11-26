/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:30:18
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.api.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.client.pojo.bo.AddShippingAddressParam;
import com.azz.order.client.pojo.bo.DelShippingAddressParam;
import com.azz.order.client.pojo.bo.EditShippingAddressParam;
import com.azz.order.client.pojo.bo.SearchClientOrderParam;
import com.azz.order.client.pojo.bo.UploadSignFormParam;
import com.azz.order.client.pojo.vo.ClientOrderDetail;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.order.client.pojo.vo.ShippingAddress;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月15日 下午2:09:21
 */
@FeignClient("azz-order-service")
public interface ClientOrderService {
    
	/**
	 * 
	 * <p>查询客户端订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月13日 上午10:54:40
	 */
	@RequestMapping("/azz/api/client/order/getClientOrderInfoList")
	JsonResult<Pagination<ClientOrderInfo>> getClientOrderInfoList(@RequestBody SearchClientOrderParam param);
	
	/**
	 * 
	 * <p>查询客户订单详情</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月13日 上午10:56:03
	 */
	@RequestMapping("/azz/api/client/order/getClientOrderDetail")
	JsonResult<ClientOrderDetail> getClientOrderDetail(@RequestParam("clientOrderCode")String clientOrderCode);
	
	/**
	 * 
	 * <p>查询收货地址信息</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月13日 下午2:58:08
	 */
	@RequestMapping("/azz/api/client/order/getShippingAddressList")
	JsonResult<List<ShippingAddress>> getShippingAddressList(@RequestParam("clientUserCode")String clientUserCode);
	
	/**
	 * 
	 * <p>查询默认收货地址信息</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月13日 下午2:58:08
	 */
	@RequestMapping("/azz/api/client/order/getDefaultShippingAddress")
	JsonResult<ShippingAddress> getDefaultShippingAddress(@RequestParam("clientUserCode")String clientUserCode);
	
	/**
	 * 
	 * <p>查询收货地址信息</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月13日 下午2:58:08
	 */
	@RequestMapping("/azz/api/client/order/getShippingAddress")
	JsonResult<ShippingAddress> getShippingAddress(@RequestParam("shippingId")Long shippingId);
	
	/**
	 * 
	 * <p>新增收货地址信息</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月13日 下午2:58:08
	 */
	@RequestMapping("/azz/api/client/order/addShippingAddress")
	JsonResult<String> addShippingAddress(@RequestBody AddShippingAddressParam param);
	
	/**
	 * 
	 * <p>修改收货地址信息</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月13日 下午2:58:08
	 */
	@RequestMapping("/azz/api/client/order/editShippingAddress")
	JsonResult<String> editShippingAddress(@RequestBody EditShippingAddressParam param);
	
	/**
	 * 
	 * <p>查询收货地址信息</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月13日 下午2:58:08
	 */
	@RequestMapping("/azz/api/client/order/delShippingAddress")
	JsonResult<String> delShippingAddress(@RequestBody DelShippingAddressParam param);
	
	/**
	 * 
	 * <p>校验客户订单是否能执行签收操作</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月15日 上午10:27:31
	 */
	@RequestMapping("/azz/api/client/order/checkSignOperation")
	JsonResult<String> checkSignOperation(@RequestParam("clientOrderCode")String clientOrderCode);
	
	/**
	 * 
	 * <p>上传签收单</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月14日 上午10:55:45
	 */
	@RequestMapping("/azz/api/client/order/uploadSignForm")
	JsonResult<String> uploadSignForm(@RequestBody UploadSignFormParam param);
	
	/**
	 * 
	 * <p>关闭订单--6小时未支付的待支付订单，状态改为已关闭</p>
	 * @return
	 * @author 黄智聪  2018年11月15日 上午10:38:19
	 */
	//@RequestMapping("/azz/api/client/order/closeClientOrders")
	//JsonResult<String> closeClientOrders();
    
	/**
	 * 
	 * <p>客户订单是否支付成功</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月26日 下午5:43:24
	 */
	@RequestMapping("/azz/api/client/order/checkClientOrderPaySuccess")
	JsonResult<String> checkClientOrderPaySuccess(@RequestParam("clientOrderCode")String clientOrderCode);
	
}

