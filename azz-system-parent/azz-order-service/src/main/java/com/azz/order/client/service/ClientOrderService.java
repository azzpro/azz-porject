/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONArray;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.client.mapper.ClientOrderPersonalMapper;
import com.azz.order.client.pojo.bo.SearchClientOrderParam;
import com.azz.order.client.pojo.vo.ClientOrderDetail;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.order.client.pojo.vo.DeliveryInfo;
import com.azz.order.client.pojo.vo.ExpressFileInfo;
import com.azz.order.client.pojo.vo.SignFileInfo;
import com.azz.order.client.pojo.vo.SignInfo;
import com.github.pagehelper.PageHelper;

/**
 * <P>客户端订单业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月12日 下午3:14:43
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ClientOrderService {
	
	@Autowired
	private ClientOrderPersonalMapper clientOrderPersonalMapper;
	
	/**
	 * 
	 * <p>查询客户端订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月13日 上午10:54:40
	 */
	public JsonResult<Pagination<ClientOrderInfo>> getClientOrderInfoList(@RequestBody SearchClientOrderParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<ClientOrderInfo> infos = clientOrderPersonalMapper.getClientOrderInfoList(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询客户订单详情</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月13日 上午10:56:03
	 */
	public JsonResult<ClientOrderDetail> getClientOrderDetail(String clientOrderCode){
		// 查询订单详情
		ClientOrderInfo orderInfo = clientOrderPersonalMapper.getClientOrderDetailByClientOrderCode(clientOrderCode);
		if(orderInfo == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单不存在");
		}
		// 发货信息
		List<DeliveryInfo> deliveryInfos = clientOrderPersonalMapper.getDeliveryInfoByClientOrderCode(clientOrderCode);
		for (DeliveryInfo deliveryInfo : deliveryInfos) {
			if(deliveryInfo != null) {
				// 发货文件信息的json字符串
				String shipmentFileInfo = deliveryInfo.getShipmentFileInfo();
				List<ExpressFileInfo> expressFileInfos = JSONArray.parseArray(shipmentFileInfo, ExpressFileInfo.class);
				deliveryInfo.setExpressFileInfos(expressFileInfos);
			}
		}
		// 签收信息
		SignInfo signInfo = clientOrderPersonalMapper.getSignInfoByClientOrderCode(clientOrderCode);
		if(signInfo != null) {
			// 发货文件信息的json字符串
			String signFileInfo = signInfo.getSignFileInfo();
			List<SignFileInfo> signFileInfos = JSONArray.parseArray(signFileInfo, SignFileInfo.class);
			signInfo.setSignFileInfos(signFileInfos);
		}
		return JsonResult.successJsonResult(new ClientOrderDetail(orderInfo, deliveryInfos, signInfo));
	}

}

