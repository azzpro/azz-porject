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

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
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
import com.azz.order.client.pojo.PayDataBean;
import com.azz.order.client.pojo.PaymentInfo;
import com.azz.order.client.pojo.RetBean;
import com.azz.order.client.pojo.bo.OrderInfo;
import com.azz.order.client.pojo.bo.PageOrder;
import com.azz.order.client.pojo.bo.PayList;
import com.azz.order.client.pojo.vo.ClientOrderDetail;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.order.selection.bo.CallBackParam;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.DateUtils;
import com.azz.util.DecimalUtil;
import com.azz.util.HttpRequestSimple;
import com.azz.util.LLPayUtil;
import com.github.pagehelper.PageHelper;

@Service
public class ClientPayService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${llpay.version}")
	private String version;
	
	@Value("${llpay.oid-partner}")
	private String oidPartner;
	
	@Value("${llpay.sign-type}")
	private String signType;
	
	@Value("${llpay.busi-partner}")
	private String busiPartner;
	
	@Value("${llpay.notify-url}")
	private String notifyUrl;
	
	@Value("${llpay.url-return}")
	private String urlReturn;
	
	@Value("${llpay.md5-key}")
	private String md5Key;
	
	@Value("${llpay.trader-pri-key}")
	private String traderPriKey;
	
	@Value("${llpay.order-pay-effective-time}")
	private String orderPayEffectiveTime;
	
	@Value("${llpay.pay-url}")
	private String payUrl;
	
	@Value("${llpay.yt-pub-key}")
	private String ytPubKey;
	
	@Autowired
	private ClientPayMapper ppm;

	@Autowired
	private ClientOrderService cos;

	@Autowired
	private DbSequenceService rss;

	@Autowired
	private SelectionService selectService;

	@Transactional
	public JsonResult<PaymentInfo> submitOrderPay(@RequestBody PageOrder po) {
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
		//创建订单
		OrderInfo order = createOrder(orderInfo);
		//构造支付请求对象
		PaymentInfo payInfo = new PaymentInfo();
		payInfo.setVersion(version);
		payInfo.setOid_partner(oidPartner);
		payInfo.setUser_id(orderInfo.getClientUserCode());
		payInfo.setSign_type(signType);
		payInfo.setBusi_partner(busiPartner);
		payInfo.setNo_order(order.getNo_order());
		payInfo.setDt_order(order.getDt_order());
		//payInfo.setName_goods(order.getName_goods());
		payInfo.setName_goods("test_goods");
		payInfo.setInfo_order(order.getInfo_order());
		payInfo.setMoney_order(order.getMoney_order());
		payInfo.setNotify_url(notifyUrl);
		payInfo.setUrl_return(urlReturn);
		payInfo.setUserreq_ip(po.getClientIp());
		payInfo.setUrl_order("");
		payInfo.setValid_order(orderPayEffectiveTime);// 单位分钟，可以为空，默认7天
		payInfo.setTimestamp(LLPayUtil.getCurrentDateTimeStr());
		payInfo.setRisk_item(createRiskItem());
        // 加签名
        String sign = LLPayUtil.addSign(JSON.parseObject(JSON
                .toJSONString(payInfo)), traderPriKey,
                md5Key);
        payInfo.setSign(sign);
        payInfo.setReq_url(payUrl);
        log.info("连连支付请求参数["+payInfo+"]");
		ClientPay clientPay = new ClientPay();
		clientPay.setUserId(orderInfo.getClientUserCode());
		clientPay.setOrderMoney(orderInfo.getGrandTotal().toPlainString());
		clientPay.setUserreqIp(payInfo.getUserreq_ip());
		clientPay.setGoodsName(payInfo.getName_goods());
		clientPay.setBusiPartner(Integer.parseInt(payInfo.getBusi_partner()));
		clientPay.setOrderCustomerPhone(Long.parseLong(orderInfo.getClientPhoneNumber()));
		//clientPay.setOrderChannelMoney();//渠道费
		clientPay.setOrderNumber(orderInfo.getClientOrderCode());
		clientPay.setOrderMethod((byte) PayMethod.ONLINE.getValue());// 默认线上
		clientPay.setOrderTime(Long.parseLong(payInfo.getDt_order()));
		clientPay.setOrderStatus((byte) PayStatus.NOT_PAID.getValue());// 支付状态 默认待支付
		clientPay.setPayNumber(payInfo.getNo_order()); //订单流水号
		int i = ppm.insertPay(clientPay);
		if(i != 1) {
			return null;
		}
		return new JsonResult<>(payInfo);
	}

	
	public JsonResult<RetBean> payNotify(String reqStr) {
		log.info("进入支付异步处理......");
		RetBean retBean = new RetBean();
		if (LLPayUtil.isnull(reqStr)){
            retBean.setRet_code(PayCode.FAILD.getCode());
            retBean.setRet_msg(PayCode.FAILD.getDesc());
            return new JsonResult<>(retBean);
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
            	map1.put("order_status", (byte) PayStatus.NOT_PAID.getValue());
            	map1.put("order_info", payDataBean.getInfo_order());
            	map1.put("order_type", PayConstants.PayType.getDesc(payDataBean.getPay_type()));
            	map1.put("order_settle_date", payDataBean.getSettle_date());
            	map1.put("three_party_number", payDataBean.getOid_paybill());
            	map1.put("pay_number", payDataBean.getNo_order());
            	int number = ppm.updateOrderByNumber(map1);
            	if(number != 1) {
            		retBean.setRet_code(PayCode.FAILD.getCode());
                    retBean.setRet_msg(PayCode.FAILD.getDesc());
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
            	retBean.setRet_code(PayCode.FAILD.getCode());
                retBean.setRet_msg(PayCode.FAILD.getDesc());
                return new JsonResult<>(retBean);
            }
        }else {
        	retBean.setRet_code(PayCode.FAILD.getCode());
            retBean.setRet_msg(PayCode.FAILD.getDesc());
            return new JsonResult<>(retBean);
        }
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
	 * 获取订单状态
	 * @param map
	 * @return
	 */
	public boolean getOrderStatus(Map<String,Object> map) {
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
		OrderInfo orderInfo = new OrderInfo();
	    orderInfo.setNo_order(LLPayUtil.getCurrentDateTimeStr());
	    orderInfo.setDt_order(LLPayUtil.getCurrentDateTimeStr());
	    orderInfo.setMoney_order(corderInfo.getGrandTotal().toPlainString());
	    orderInfo.setName_goods("");
	    orderInfo.setInfo_order("用户购买");
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
