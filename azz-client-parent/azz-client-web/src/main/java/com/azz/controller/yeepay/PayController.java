/******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午4:09:49
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.controller.yeepay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.azz.core.common.JsonResult;
import com.azz.core.constants.PayConstants;
import com.azz.core.constants.ClientConstants.PayMethod;
import com.azz.core.constants.ClientConstants.PayStatus;
import com.azz.order.api.client.ClientPayService;
import com.azz.order.client.pojo.ClientPay;
import com.azz.order.client.pojo.PaymentInfo;
import com.azz.order.client.pojo.RetBean;
import com.azz.order.client.pojo.bo.OrderInfo;
import com.azz.order.client.pojo.bo.PageOrder;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.util.LLPayUtil;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午4:09:49
 */
@RestController
@RequestMapping("/azz/api/pay")
public class PayController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${yeepay.notify-url}")
	private String notifyUrl;
	
	
	@Autowired
	private ClientPayService pfps;
	
	@RequestMapping("submitOrderPay")
	public JsonResult<String> submitOrderPay(HttpServletRequest request,PageOrder po){
		JsonResult<ClientOrderInfo> orderInfo = pfps.submitOrderPay(po);
		if(orderInfo.getData() != null) {
			OrderInfo order = createOrder(orderInfo.getData());
			System.out.println("order---->"+order);
			if(null == order) {
				return null;
			}
			String goodsParamExt = "{\"goodsName\":\""+order.getGoodsName()+"\",\"goodsDesc\":\""+order.getGoodsDesc()+"\"}";
			String industryParamExt = "{\"bizSource\":\""+""+"\",\"bizEntity\":\""+""+"\"}";
			String ext = "{\"appId\":\""+""+"\",\"openId\":\""+""+"\",\"clientId\":\""+""+"\"}";
			Map<String, String> params = new HashMap<>();
			params.put("orderId", order.getOrderId()); //商户订单编号
			params.put("orderAmount", order.getOrderAmount()); //订单金额
			//params.put("timeoutExpress", timeoutExpress); //订单有效期  可以不传
			params.put("requestDate", order.getRequestDate()); //请求时间
			//params.put("redirectUrl", redirectUrl); //页面回调地址 可以不传
			params.put("notifyUrl", notifyUrl); //回调地址
			params.put("goodsParamExt", goodsParamExt);
			//params.put("paymentParamExt", paymentParamExt);
			params.put("industryParamExt", industryParamExt);
			//params.put("memo", memo);
			//params.put("riskParamExt", riskParamExt);
			//params.put("csUrl", csUrl);
			
			Map<String, String> result = new HashMap<>();
			try {
				result = YeepayService.requestYOP(params, YeepayService.TRADEORDER_URL, YeepayService.TRADEORDER);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String token = result.get("token");
			String codeRe = result.get("code");
			if(!"OPR00000".equals(codeRe)){
				String message = result.get("message");
				log.info("支付返回消息----->"+message);
			}
			
			params.put("parentMerchantNo", YeepayService.getParentMerchantNo());
			params.put("merchantNo", YeepayService.getMerchantNo());
			params.put("orderId", order.getOrderId());
			params.put("token", token);
			params.put("timestamp", order.getTimestamp());
			//params.put("directPayType", directPayType);
			//params.put("cardType", cardType);
			params.put("userNo", order.getUserNo());
			params.put("userType", order.getUserType());
			params.put("ext", ext);
			
			String url = "";
			try {
				url = YeepayService.getUrl(params);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			System.out.println("url-------->"+url);
			//构造支付请求对象
			
			ClientPay clientPay = new ClientPay();
			clientPay.setUserId(orderInfo.getData().getClientUserCode());
			clientPay.setOrderMoney(orderInfo.getData().getGrandTotal().toPlainString());
			//clientPay.setUserreqIp(payInfo.getUserreq_ip());
			//clientPay.setGoodsName(payInfo.getName_goods());
			//clientPay.setBusiPartner(Integer.parseInt(payInfo.getBusi_partner()));
			clientPay.setOrderCustomerPhone(Long.parseLong(orderInfo.getData().getClientPhoneNumber()));
			//clientPay.setOrderChannelMoney();//渠道费
			clientPay.setOrderNumber(orderInfo.getData().getClientOrderCode());
			clientPay.setOrderMethod((byte) PayMethod.ONLINE.getValue());// 默认线上
			//clientPay.setOrderTime(Long.parseLong(payInfo.getDt_order()));
			clientPay.setOrderStatus((byte) PayStatus.NOT_PAID.getValue());// 支付状态 默认待支付
			//clientPay.setPayNumber(payInfo.getNo_order()); //订单流水号
			clientPay.setPayInstruation(PayConstants.PAYMENT_INSTITUTION);//支付机构
			//int i = ppm.insertPay(clientPay);
			//if(i != 1) {
			//	return null;
			//}
			
		}
		System.out.println("111");
		return JsonResult.successJsonResult();
	}
	
	@RequestMapping("payNotify")
	public void payNotify(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		log.info("进入支付回调接口");
		String reqStr = LLPayUtil.readReqStr(request);
		log.info("回调参数["+reqStr+"]");
		JsonResult<String> payNotify = pfps.payNotify(reqStr);
		response.getWriter().write(JSON.toJSONString(payNotify.getData()));
		response.getWriter().flush();
			
	}
	
	/**
	 * <p>创建订单</p>
	 * @param req
	 * @return
	 * @author 刘建麟  2018年12月17日 下午2:05:32
	 */
	private OrderInfo createOrder(ClientOrderInfo corderInfo){
		if(null == corderInfo || StringUtils.isBlank(corderInfo.getClientUserCode())) {
			return null;
		}
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderId(LLPayUtil.getCurrentDateTimeStr());//订单编号
		orderInfo.setTimestamp(LLPayUtil.getCurrentDateTimeStr());//订单时间戳
		orderInfo.setOrderAmount(corderInfo.getGrandTotal().toPlainString());//订单金额
		orderInfo.setGoodsName("测试购买");//TODO fix
		orderInfo.setGoodsDesc("");
		orderInfo.setRequestDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//下单时间
	    orderInfo.setUserType("USER_ID"); //用户标示类型 默认USER_ID
		orderInfo.setUserNo(corderInfo.getClientUserCode());//用户CODE
	    log.info("订单创建成功----->"+orderInfo);
		return orderInfo;
	} 
	
}