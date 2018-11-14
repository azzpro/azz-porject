/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONArray;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.MerchantConstants;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.merchant.mapper.MerchantOrderMapper;
import com.azz.order.merchant.mapper.MerchantOrderStatusMapper;
import com.azz.order.merchant.pojo.MerchantOrder;
import com.azz.order.merchant.pojo.MerchantOrderStatus;
import com.azz.order.merchant.pojo.bo.EditOrderStatus;
import com.azz.order.merchant.pojo.bo.SearchOrderDetailParam;
import com.azz.order.merchant.pojo.bo.SearchOrderListParam;
import com.azz.order.merchant.pojo.bo.SearchOrderStatusParam;
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
	
	@Autowired
    private MerchantOrderStatusMapper merchantOrderStatusMapper;
	
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

	
	/**
	 * <p>订单流转</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月14日 上午9:59:49
	 */
	public JsonResult<String> editOrderStatus(@RequestBody EditOrderStatus param){
	    JSR303ValidateUtils.validate(param);
	    
	    MerchantOrder mo = merchantOrderMapper.selectMerchantOrderInfo(param.getOrderCode());
	    if(ObjectUtils.isNull(mo)) {
	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单不存在");
	    }
	    // 校验订单状态合法性
	    Integer statusId = mo.getOrderStatusId();
	    boolean isExist = MerchantConstants.MerchantOrderStatus.checkStatusExist(statusId);
	    if(!isExist) {
	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "无效的订单状态");
	    }
	    
	    if(MerchantConstants.MerchantOrderStatus.NOT_CONFIRMED.getValue() != statusId ||
	            MerchantConstants.MerchantOrderStatus.NOT_SENT_OUT.getValue() != statusId) {
	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单状态超出变更范围");
	    }
	    
	    SearchOrderStatusParam sosp = new SearchOrderStatusParam();
        sosp.setMerchantOrderId(mo.getId());
        sosp.setMerchantStatusId(param.getStatus());
        MerchantOrderStatus mos = merchantOrderStatusMapper.selectOrderStatus(sosp);
        if(ObjectUtils.isNotNull(mos)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单状态已变更");
        }
        
	    // 变更商户订单头部信息
	    mo.setOrderStatusId(statusId);
	    mo.setModifier(param.getModifier());
	    mo.setModifyTime(new Date());
	    merchantOrderMapper.updateByPrimaryKeySelective(mo);
	    
	    // 新增商户订单状态信息
	    mos = new MerchantOrderStatus();
	    mos.setMerchantOrderId(mo.getId());
	    mos.setMerchantStatusId(param.getStatus());
	    mos.setCreator(param.getModifier());
	    mos.setCreateTime(new Date());
	    merchantOrderStatusMapper.insertSelective(mos);
	    
	    // 订单发货需记录发货信息
	    if(MerchantConstants.MerchantOrderStatus.NOT_SENT_OUT.getValue() == statusId) {
	        
	    }
	    
	    return JsonResult.successJsonResult();
	}
	
}

