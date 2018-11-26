/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午3:15:10
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.pay.service;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午3:15:10
 */

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.constants.ClientConstants;
import com.azz.core.constants.ClientConstants.ClientOrderStatus;
import com.azz.core.constants.ClientConstants.PayMethod;
import com.azz.core.constants.ClientConstants.PayStatus;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.api.client.ClientOrderService;
import com.azz.order.api.client.SelectionService;
import com.azz.order.client.pojo.vo.ClientOrderDetail;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.order.selection.bo.CallBackParam;
import com.azz.system.bo.SubmitPayParams;
import com.azz.system.pay.mapper.PlatformPayMapper;
import com.azz.system.pojo.PlatformPay;
import com.azz.system.sequence.api.RandomSequenceService;
import com.azz.util.DateUtils;
import com.azz.util.DecimalUtil;
import com.azz.util.JSR303ValidateUtils;
@Service
public class PlatfromPayService {
	
	@Autowired
	private PlatformPayMapper ppm;
	
	@Autowired
	private ClientOrderService cos;
	
	@Autowired
	private RandomSequenceService rss;
	
	@Autowired
	private SelectionService selectService;
	
	@Transactional
	public JsonResult<String> submitOrderPay(@RequestBody SubmitPayParams spp){
		JSR303ValidateUtils.validate(spp);
		
		//判断该订单是否处于待支付  并且未失效
		JsonResult<ClientOrderDetail> detail = cos.getClientOrderDetail(spp.getOrderNumber());
		ClientOrderInfo orderInfo = detail.getData().getOrderInfo();
		if(detail == null || orderInfo == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户订单不存在");
		}
		if(ClientOrderStatus.NOT_PAID.getValue() != orderInfo.getOrderStatusId()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户订单状态异常");
		}
		// 订单失效时间为订单创建时间 + 6小时
		Date orderDeadTime = DateUtils.addHour(orderInfo.getOrderTime(), ClientConstants.CLIENT_ORDER_DEAD_TIME_HOURS);
		// 失效时间  < 当前时间，订单视为失效
		if(DecimalUtil.lt(new BigDecimal(orderDeadTime.getTime()), new BigDecimal(System.currentTimeMillis()))) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单已失效，请重新下单");
		}
		
		PlatformPay pfp = new PlatformPay();
		//pfp.setChannelMoney(orderInfo.getGrandTotal());//渠道费
		pfp.setCustomerPhone(Long.parseLong(orderInfo.getClientPhoneNumber()));
		pfp.setOrderNumber(orderInfo.getClientOrderCode());
		pfp.setPayMethod((byte)PayMethod.ONLINE.getValue());//默认线上
		pfp.setPayMoney(orderInfo.getGrandTotal());
		pfp.setPayTime(new Date());
		pfp.setPayType(spp.getOrderPayType());
		pfp.setPayStatus((byte)PayStatus.NOT_PAID.getValue());//支付状态 默认待支付
		//pfp.setThreePartyNumber(); //
		pfp.setPayNumber(rss.getPayCodeNumber());
		ppm.insertPay(pfp);
		
		//更新订单状态
		CallBackParam cbp = new CallBackParam();
		cbp.setClientOrderCode(pfp.getOrderNumber());
		cbp.setPaymentMethod((int)pfp.getPayMethod());
		cbp.setPaymentType((int)pfp.getPayType());
		selectService.clientOrderPaySuccessOpt(cbp);
		return JsonResult.successJsonResult();
	}
}

