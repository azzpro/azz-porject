/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONArray;
import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.merchant.mapper.MerchantOrderMapper;
import com.azz.order.merchant.pojo.bo.SearchOrderDetailParam;
import com.azz.order.merchant.pojo.bo.SearchOrderListParam;
import com.azz.order.merchant.pojo.vo.OrderDetail;
import com.azz.order.merchant.pojo.vo.OrderList;
import com.azz.order.merchant.pojo.vo.ReceiverAddress;
import com.azz.order.merchant.pojo.vo.ShipInfo;
import com.azz.order.merchant.pojo.vo.SignFileInfo;
import com.azz.order.merchant.pojo.vo.SignForInfo;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>商户订单管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月13日 下午5:04:00
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class MerchantOrderService {
	
	@Autowired
	private MerchantOrderMapper merchantOrderMapper;
	
	/**
	 * <p>查询商户订单管理</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月13日 下午5:07:04
	 */
	public JsonResult<Pagination<OrderList>> getMerchantOrderList(@RequestBody SearchOrderListParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<OrderList> list = merchantOrderMapper.selectOrderList(param);
		return JsonResult.successJsonResult(new Pagination<>(list));
	}
	
	/**
	 * <p>订单详情</p>
	 * @param clientOrderCode
	 * @return
	 * @author 彭斌  2018年11月13日 下午5:08:02
	 */
	public JsonResult<OrderDetail> getMerchantOrderDetail(@RequestBody SearchOrderDetailParam param){
	    JSR303ValidateUtils.validate(param);
	    // 获取订单基本详情和产品详情信息
	    OrderDetail od = merchantOrderMapper.selectOrderInfo(param);
		if(ObjectUtils.isNotNull(od)) {
		    String merchantOrderCode = param.getOrderCode();
		    // 获取收货地址信息
		    ReceiverAddress receiverAddress = merchantOrderMapper.selectReceiverAddressInfo(merchantOrderCode);
		    if(ObjectUtils.isNotNull(receiverAddress)) {
		        od.setReceiverAddress(receiverAddress);
		    }
		    // 获取发货信息
		    ShipInfo shipInfo = merchantOrderMapper.selectShipInfo(merchantOrderCode);
		    if(ObjectUtils.isNotNull(shipInfo)) {
                od.setShipInfo(shipInfo);
            }
		    // 获取签收信息
		    SignForInfo signForInfo = merchantOrderMapper.selectSignFor(merchantOrderCode);
		    if(ObjectUtils.isNotNull(signForInfo)) {
		        String signFileInfoJson = signForInfo.getSignFileInfo();
		        List<SignFileInfo> signFileInfos = JSONArray.parseArray(signFileInfoJson, SignFileInfo.class);
		        signForInfo.setSignFileInfos(signFileInfos);
		        od.setSignForInfo(signForInfo);
            }
		}
		return JsonResult.successJsonResult(od);
	}

}

