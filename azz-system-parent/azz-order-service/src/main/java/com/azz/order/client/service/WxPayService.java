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

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.constants.ClientConstants.PayStatus;
import com.azz.core.constants.WxCourseConstants.CourseOrderStatus;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.client.mapper.WxPayMapper;
import com.azz.order.client.wx.bo.WxPayOrderInfo;
import com.azz.order.client.wx.pojo.WxPay;
import com.azz.util.HttpClientUtil;
import com.azz.wx.course.api.OrderService;
import com.azz.wx.course.pojo.vo.CourseOrderDetail;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayConstants.SignType;
import com.github.wxpay.sdk.WXPayUtil;



@Service
public class WxPayService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	private static SignType st;
	
	@Value("${wx.AppId}")
	private String appid;

	@Value("${wx.secrt}")
	private String secrt;

	@Value("${wx.mchId}")
	private String mchid;

	@Value("${wx.api}")
	private String api;
	
	@Value("${wx.callback}")
	private String callback;
	
	static {
		st = SignType.HMACSHA256;
	}

	
	@Autowired
	private WxPayMapper wpm;
	
	@Autowired
	private OrderService orderService;
	
	@Transactional
	public Map<String,String> submitOrderPay(@RequestBody WxPayOrderInfo po) {
		//根据课程编号去判断该笔订单是否已支付
		WxPay wxPay = wpm.selectWxOrder(po.getCoursePayNum());
		if(wxPay != null) {
			if(wxPay.getOrderStatus() != PayStatus.NOT_PAID.getValue()){
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "支付订单状态异常");
			}
		}
		JsonResult<CourseOrderDetail> detail = orderService.getCourseOrderDetail(po.getCoursePayNum());
		//判断微信课程订单是否关闭
		if(detail != null && detail.getCode() == SystemErrorCode.SUCCESS.getCode()) {
			if(detail.getData().getOrderStatus() == CourseOrderStatus.CLOSED.getValue()) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单已失效，请重新下单");
			}
			if(po.getOrderMoney().equals(detail.getData().getGrandTotal().toPlainString())) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单金额不一致");
			}
		}
		Map<String, String> reqData = null;
		try {
			reqData = createReqData(po.getOrderMoney(),"",po.getIp(),po.getCourseName());
		}catch (Exception e) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "创建订单错误");
		}
		if (null == reqData) {
			return null;
		}
		String reqUrl = WXPayConstants.DOMAIN_API + WXPayConstants.UNIFIEDORDER_URL_SUFFIX;
		String mapToXml = "";
		try {
			mapToXml = WXPayUtil.mapToXml(reqData);
		} catch (Exception e) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "请求参数有误");
		}
		if (StringUtils.isBlank(mapToXml)) {
			return null;
		}
		
		String result = "";
		try {
			result = HttpClientUtil.sendPost(reqUrl, mapToXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		WXPayUtil.getLogger().info("统一下单请求返回结果------------->" + result);
		String prepay_id = "";// 预支付id
		Map<String, String> payMap = new HashMap<String, String>();
		try {
			if (result.indexOf("SUCCESS") != -1) {
				Map<String, String> map = WXPayUtil.xmlToMap(result);
				prepay_id = (String) map.get("prepay_id");
			}
			payMap.put("appId", appid);
			payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
			payMap.put("nonceStr", WXPayUtil.generateNonceStr());
			payMap.put("signType", "MD5");
			payMap.put("package", "prepay_id=" + prepay_id);
			String paySign = WXPayUtil.generateSignature(payMap, api);
			payMap.put("paySign", paySign);
			return payMap;
		}catch (Exception e) {
			WXPayUtil.getLogger().error(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * 创建请求参数
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private Map<String,String> createReqData(String totalFee,String openid,String ip,String courseName) throws Exception{
		Map<String,String> reqMap = new HashMap<String,String>();
		reqMap.put("appid", appid);
		reqMap.put("mch_id", mchid);
		reqMap.put("nonce_str", WXPayUtil.generateNonceStr());
		reqMap.put("sign_type", WXPayConstants.HMACSHA256);
		reqMap.put("body", courseName);
		reqMap.put("out_trade_no", String.valueOf(WXPayUtil.getCurrentTimestamp()));
		reqMap.put("total_fee", totalFee); //单位分
		reqMap.put("spbill_create_ip",ip);
		reqMap.put("notify_url", callback);
		reqMap.put("trade_type", "JSAPI");
		if(openid.equals("")) {
			return null;
		}
		reqMap.put("openid", openid);
		reqMap.put("sign", WXPayUtil.generateSignature(reqMap, api, st));
		return reqMap;
	}
	
	public String callback(String xml) {
		Map<String, String> notifyMap;
		try {
			notifyMap = WXPayUtil.xmlToMap(xml);
			if(notifyMap.get("result_code").equals("SUCCESS")) {
				String ordersSn = notifyMap.get("out_trade_no");
				String amountpaid = notifyMap.get("total_fee");
				log.info("订单号---->"+ordersSn+"::金额------->"+amountpaid);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}