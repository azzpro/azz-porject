/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午3:15:10
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.order.client.service;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午3:15:10
 */

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.ClientConstants;
import com.azz.core.constants.ClientConstants.ClientOrderStatus;
import com.azz.core.constants.ClientConstants.PayMethod;
import com.azz.core.constants.ClientConstants.PayStatus;
import com.azz.core.constants.PayConstants;
import com.azz.core.constants.PayConstants.PayCode;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.api.client.ClientOrderService;
import com.azz.order.api.client.SelectionService;
import com.azz.order.client.mapper.ClientPayMapper;
import com.azz.order.client.pojo.ClientPay;
import com.azz.order.client.pojo.RetBean;
import com.azz.order.client.pojo.bo.OrderInfo;
import com.azz.order.client.pojo.bo.PageOrder;
import com.azz.order.client.pojo.bo.PayList;
import com.azz.order.client.pojo.vo.ClientOrderDetail;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.util.DateUtils;
import com.azz.util.DecimalUtil;
import com.azz.util.LLPayUtil;
import com.github.pagehelper.PageHelper;


@Service
public class ClientPayService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${yeepay.notify-url}")
	private String notifyUrl;
	
	
	@Autowired
	private ClientPayMapper ppm;

	@Autowired
	private ClientOrderService cos;

	@Autowired
	private SelectionService selectService;

	@Transactional
	public Map<String,Object> submitOrderPay(@RequestBody PageOrder po) {
		List<ClientPay> selectOrder = ppm.selectOrder(po.getOrderCode());
		if(!selectOrder.isEmpty() && selectOrder.size() > 1) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "支付订单不唯一");
		}
		if(!selectOrder.isEmpty() && selectOrder.get(0).getOrderStatus() != PayStatus.NOT_PAID.getValue()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "支付订单状态异常");
		}
		// 判断该订单是否处于待支付 并且未失效
		JsonResult<ClientOrderDetail> detail = cos.getClientOrderDetail(po.getOrderCode());
		ClientOrderInfo orderInfo = detail.getData().getOrderInfo();
		if (detail == null || orderInfo == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户订单不存在");
		}
		if (ClientOrderStatus.NOT_PAID.getValue() != orderInfo.getOrderStatusId()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户订单状态异常");
		}
		// 订单失效时间为订单创建时间 + 6小时
		Date orderDeadTime = DateUtils.addHour(orderInfo.getOrderTime(), ClientConstants.CLIENT_ORDER_DEAD_TIME_HOURS);
		// 失效时间 < 当前时间，订单视为失效
		if (DecimalUtil.lt(new BigDecimal(orderDeadTime.getTime()), new BigDecimal(System.currentTimeMillis()))) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单已失效，请重新下单");
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//创建订单
		OrderInfo order = createOrder(orderInfo);
		if(null == order) {
			return null;
		}
		String goodsParamExt = "{\"goodsName\":\""+order.getGoodsName()+"\",\"goodsDesc\":\""+order.getGoodsDesc()+"\"}";
		String industryParamExt = "{\"bizSource\":\""+""+"\",\"bizEntity\":\""+""+"\"}";
		String ext = "{\"appId\":\""+""+"\",\"openId\":\""+""+"\",\"clientId\":\""+""+"\"}";
		Map<String, String> params = new HashMap<>();
		params.put("orderId", order.getOrderId()); //商户订单编号
		params.put("orderAmount", order.getOrderAmount()); //订单金额
		params.put("parentMerchantNo", YeepayService.getParentMerchantNo());
		params.put("merchantNo", YeepayService.getMerchantNo());
		params.put("timeoutExpress", ""); //订单有效期  可以不传
		params.put("requestDate", order.getRequestDate()); //请求时间
		params.put("redirectUrl", ""); //页面回调地址 可以不传
		params.put("notifyUrl", notifyUrl); //回调地址
		params.put("goodsParamExt", goodsParamExt);
		params.put("industryParamExt", industryParamExt);
		params.put("goodsParamExt", goodsParamExt);
		params.put("paymentParamExt", "");
		params.put("industryParamExt", industryParamExt);
		params.put("memo", "");
		params.put("riskParamExt", "");
		params.put("csUrl", "");
		Set<Entry<String, String>> entrySet = params.entrySet();
		for (Entry<String, String> entry : entrySet) {
			log.info("key-->"+entry.getKey()+"::value-->"+entry.getValue());
		}
		Map<String, String> result = new HashMap<>();
		String uri = YeepayService.getUrl(YeepayService.TRADEORDER_URL);
		try {
			result = YeepayService.requestYOP(params, uri, YeepayService.TRADEORDER, YeepayService.TRADEORDER_HMAC);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String token = result.get("token");
		String codeRe = result.get("code");
		if(!"OPR00000".equals(codeRe)){
			String message = result.get("message");
			log.info("支付返回消息----->"+message);
			resultMap.put("code", PayCode.FAILD.getCode());
			resultMap.put("msg", PayCode.FAILD.getDesc());
			return resultMap;
		}
		
		params.put("parentMerchantNo", YeepayService.getParentMerchantNo());
		params.put("merchantNo", YeepayService.getMerchantNo());
		params.put("token", token);
		params.put("timestamp", order.getTimestamp());
		params.put("userNo", order.getUserNo());
		params.put("userType", order.getUserType());
		params.put("directPayType", "");
		params.put("cardType", "");
		params.put("ext", ext);
		String url = "";
		try {
			url = YeepayService.getUrl(params);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", PayCode.FAILD.getCode());
			resultMap.put("msg", PayCode.FAILD.getDesc());
			return resultMap;
		}
		if(StringUtils.isNotBlank(url)) {
			resultMap.put("code", PayCode.SUCCESS.getCode());
			resultMap.put("msg", url);
			return resultMap;
		}
		ClientPay clientPay = new ClientPay();
		clientPay.setUserId(orderInfo.getClientUserCode());
		clientPay.setOrderMoney(orderInfo.getGrandTotal().toPlainString());
		//clientPay.setUserreqIp(payInfo.getUserreq_ip());
		//clientPay.setGoodsName(payInfo.getName_goods());
		//clientPay.setBusiPartner(Integer.parseInt(payInfo.getBusi_partner()));
		clientPay.setOrderCustomerPhone(Long.parseLong(orderInfo.getClientPhoneNumber()));
		//clientPay.setOrderChannelMoney();//渠道费
		clientPay.setOrderNumber(orderInfo.getClientOrderCode());
		clientPay.setOrderMethod((byte) PayMethod.ONLINE.getValue());// 默认线上
		//clientPay.setOrderTime(Long.parseLong(payInfo.getDt_order()));
		clientPay.setOrderStatus((byte) PayStatus.NOT_PAID.getValue());// 支付状态 默认待支付
		//clientPay.setPayNumber(payInfo.getNo_order()); //订单流水号
		clientPay.setPayInstruation(PayConstants.PAYMENT_INSTITUTION);//支付机构
		int i = ppm.insertPay(clientPay);
		if(i != 1) {
			return null;
		}
		resultMap.put("code", PayCode.FAILD.getCode());
		resultMap.put("msg", PayCode.FAILD.getDesc());
		return resultMap;
	}

	
	public JsonResult<RetBean> payNotify(String responseMsg,String customerId) {
		log.info("进入支付异步处理......");
		RetBean retBean = new RetBean();
		if(StringUtils.isBlank(responseMsg) || StringUtils.isBlank(customerId)) {
			retBean.setRet_code(PayCode.FAILD.getCode());
            retBean.setRet_msg(PayCode.FAILD.getDesc());
            return new JsonResult<>(retBean);
		}
		log.info("接收支付异步通知数据：【" + responseMsg + "】:【"+customerId+"】");
		Map<String, String> callback = YeepayService.callback(responseMsg);
		Set<Entry<String, String>> entrySet = callback.entrySet();
		for (Entry<String, String> entry : entrySet) {
			log.info("回调处理结果--->"+entry.getKey()+"::--value---->"+entry.getValue());
		}
		retBean.setRet_code(PayCode.SUCCESS.getCode());
        retBean.setRet_msg(PayCode.SUCCESS.getDesc());
        return new JsonResult<>(retBean);
		/*
		if (LLPayUtil.isnull(reqStr)){
            
        }
        log.info("接收支付异步通知数据：【" + reqStr + "】");
        try{
            if (!LLPayUtil.checkSign(reqStr, ytPubKey,md5Key)){
            	 retBean.setRet_code(PayCode.FAILD.getCode());
                 retBean.setRet_msg(PayCode.FAILD.getDesc());
                 log.info("异步通知验签失败");
                 return new JsonResult<>(retBean);
            }
        } catch (Exception e){
        	log.info("异步通知报文解析异常：" + e);
        	retBean.setRet_code(PayCode.FAILD.getCode());
            retBean.setRet_msg(PayCode.FAILD.getDesc());
            return new JsonResult<>(retBean);
        }
        log.info("支付异步通知数据接收处理成功");
        // 解析异步通知对象
        PayDataBean payDataBean = JSON.parseObject(reqStr, PayDataBean.class);
        log.info("异步通知结果解析----------->"+payDataBean);
        if(null != payDataBean && payDataBean.getResult_pay().equals("SUCCESS")) {
        	 //校验订单是否支付成功
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("no_order", payDataBean.getNo_order());
            map.put("money_order", payDataBean.getMoney_order());
            if(getOrderStatus(map)) {
            	Map<String,Object> map1 = new HashMap<String,Object>();
            	map1.put("order_status", (byte) PayStatus.PAY_SUCCESS.getValue());
            	map1.put("order_info", payDataBean.getInfo_order());
            	map1.put("order_type", PayConstants.PayType.getDesc(payDataBean.getPay_type()));
            	map1.put("order_settle_date", payDataBean.getSettle_date());
            	map1.put("three_party_number", payDataBean.getOid_paybill());
            	map1.put("pay_number", payDataBean.getNo_order());
            	int number = ppm.updateOrderByNumber(map1);
            	if(number != 1) {
            		retBean.setRet_code(PayCode.UPDATEFAILD.getCode());
                    retBean.setRet_msg(PayCode.UPDATEFAILD.getDesc());
                    return new JsonResult<>(retBean);
            	}
            	String orderCode = ppm.selectOrderCode(payDataBean.getNo_order());
            	CallBackParam cbp = new CallBackParam();
         		cbp.setClientOrderCode(orderCode);
         		cbp.setPayMethod(PayMethod.ONLINE.getValue());
         		cbp.setOrderType(PayConstants.PayType.getNum(payDataBean.getPay_type()));
         		selectService.clientOrderPaySuccessOpt(cbp);
         		retBean.setRet_code(PayCode.SUCCESS.getCode());
                retBean.setRet_msg(PayCode.SUCCESS.getDesc());
                return new JsonResult<>(retBean);
            }else {
            	retBean.setRet_code(PayCode.PAID.getCode());
                retBean.setRet_msg(PayCode.PAID.getDesc());
                return new JsonResult<>(retBean);
            }
        }else {
        	retBean.setRet_code(PayCode.FAILD.getCode());
            retBean.setRet_msg(PayCode.FAILD.getDesc());
            return new JsonResult<>(retBean);
        }*/
	}
	/**
	 * <p>
	 * 支付列表
	 * </p>
	 * 
	 * @param param
	 * @return
	 * @author 刘建麟 2018年10月31日 上午11:29:49
	 */
	public JsonResult<Pagination<ClientPay>> searchParamsList(@RequestBody PayList pl) {
		PageHelper.startPage(pl.getPageNum(), pl.getPageSize());
		List<ClientPay> selectPayList = ppm.selectPayList(pl);
		return JsonResult.successJsonResult(new Pagination<>(selectPayList));
	}
	
	/**
	 * <p>
	 * 支付订单详情
	 * </p>
	 * 
	 * @param param
	 * @return
	 * @author 刘建麟 2018年10月31日 上午11:29:49
	 */
	public JsonResult<ClientPay> getOrderInfo(String number) {
		ClientPay payNumber = ppm.selectPayInfoByPayNumber(number);
		// 判断该订单是否处于待支付 并且未失效
		JsonResult<ClientOrderDetail> detail = cos.getClientOrderDetail(number);
		ClientOrderInfo orderInfo = detail.getData().getOrderInfo();
		payNumber.setCoi(orderInfo);
		return JsonResult.successJsonResult(payNumber);
	}
	
	/**
	 * 获取订单状态
	 * @param map
	 * @return
	 */
	private boolean getOrderStatus(Map<String,Object> map) {
		int i = ppm.selectOrderStatus(map);
		if(i > 0) {
			return false;
		}
		return true;
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
	
	
    /**
     * <p>构建风控参数</p>
     * @return
     * @author 刘建麟  2018年12月17日 下午4:55:17
     */
    private String createRiskItem(){
        JSONObject riskItemObj = new JSONObject();
        riskItemObj.put("user_info_full_name", "你好");
        riskItemObj.put("frms_ware_category", "1999");
        return riskItemObj.toString();
    }

}
