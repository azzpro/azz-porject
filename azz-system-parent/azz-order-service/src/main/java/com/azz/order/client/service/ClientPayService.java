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
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
import com.azz.order.client.mapper.ClientEnterpriseRegInfoMapper;
import com.azz.order.client.mapper.ClientPayMapper;
import com.azz.order.client.mapper.ClientPersonRegInfoMapper;
import com.azz.order.client.pojo.ClientPay;
import com.azz.order.client.pojo.Enterprisereginfoadd;
import com.azz.order.client.pojo.RetBean;
import com.azz.order.client.pojo.bo.BankBranch;
import com.azz.order.client.pojo.bo.Enterprisereginfo;
import com.azz.order.client.pojo.bo.OrderInfo;
import com.azz.order.client.pojo.bo.PageOrder;
import com.azz.order.client.pojo.bo.PayList;
import com.azz.order.client.pojo.bo.Personreginfo;
import com.azz.order.client.pojo.vo.ClientOrderDetail;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.order.selection.bo.CallBackParam;
import com.azz.util.DateUtils;
import com.azz.util.DecimalUtil;
import com.azz.util.LLPayUtil;
import com.github.pagehelper.PageHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@Service
public class ClientPayService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${yeepay.notify-url}")
	private String notifyUrl;
	
	@Value("${yeepay.regEn-notify-url}")
	private String regEnNotifyUrl;
	
	@Value("${yeepay.regPe-notify-url}")
	private String regPeNotifyUrl;
	
	@Autowired
	private ClientPayMapper ppm;

	@Autowired
	private ClientOrderService cos;

	@Autowired
	private SelectionService selectService;
	
	@Autowired
	private ClientEnterpriseRegInfoMapper clientEnterpriseRegInfoMapper;
	
	@Autowired
	private ClientPersonRegInfoMapper clientPersonRegInfoMapper;

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
		params.put("timeoutExpress", "360"); //订单有效期  可以不传
		params.put("timeoutExpressType", PayConstants.Unit.HOUR.getPrc());
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
		params.put("assureType", "");
		params.put("assurePeriod", "");
		params.put("fundProcessType", "");
		params.put("divideDetail", "");
		params.put("divideNotifyUrl", "");
		params.put("timeoutNotifyUrl", "");
		Map<String, String> result = new HashMap<>();
		String uri = YeepayService.getUrl(YeepayService.TRADEORDER_URL);
		try {
			result = YeepayService.requestYOP(params, uri, YeepayService.TRADEORDER, YeepayService.TRADEORDER_HMAC);
		} catch (IOException e1) {
			e1.printStackTrace();
			resultMap.put("code", PayCode.FAILD.getCode());
			resultMap.put("msg", PayCode.FAILD.getDesc());
			return resultMap;
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
			ClientPay clientPay = new ClientPay();
			clientPay.setUserId(orderInfo.getClientUserCode());
			clientPay.setOrderMoney(orderInfo.getGrandTotal().toPlainString());
			clientPay.setUserreqIp(po.getClientIp());
			clientPay.setGoodsName(order.getGoodsName());
			clientPay.setOrderCustomerPhone(Long.parseLong(orderInfo.getClientPhoneNumber()));
			//clientPay.setOrderChannelMoney();//渠道费
			clientPay.setOrderNumber(orderInfo.getClientOrderCode());
			clientPay.setOrderMethod((byte) PayMethod.ONLINE.getValue());// 默认线上
			clientPay.setOrderTime(Long.parseLong(LLPayUtil.getCurrentDateTimeStr()));
			clientPay.setOrderStatus((byte) PayStatus.NOT_PAID.getValue());// 支付状态 默认待支付
			clientPay.setPayNumber(order.getOrderId()); //订单流水号
			clientPay.setPayInstruation(PayConstants.PAYMENT_INSTITUTION);//支付机构
			int i = ppm.insertPay(clientPay);
			if(i != 1) {
				resultMap.put("code", PayCode.FAILD.getCode());
				resultMap.put("msg", PayCode.FAILD.getDesc());
				return resultMap;
			}
			resultMap.put("code", PayCode.SUCCESS.getCode());
			resultMap.put("msg", url);
			return resultMap;
		}else {
			resultMap.put("code", PayCode.FAILD.getCode());
			resultMap.put("msg", PayCode.FAILD.getDesc());
			return resultMap;
		}
		
	}

	/**
	 * 子商户入网注册 【个人】
	 * @param po
	 * @return
	 * @throws Exception 
	 */
	public JsonResult<String> regPersonl(@RequestBody Personreginfo po) throws Exception{
		Map<String, String> upload = upload("http://azz-image.oss-cn-shenzhen.aliyuncs.com/yeepay-image/bank.jpg");
		Map<String, String> upload1 = upload("http://azz-image.oss-cn-shenzhen.aliyuncs.com/yeepay-image/h_1.jpg");
		Map<String, String> upload2 = upload("http://azz-image.oss-cn-shenzhen.aliyuncs.com/yeepay-image/h_2.jpg");
		Map<String, String> upload3 = upload("http://azz-image.oss-cn-shenzhen.aliyuncs.com/yeepay-image/idcard_1.jpg");
		Map<String, String> upload4 = upload("http://azz-image.oss-cn-shenzhen.aliyuncs.com/yeepay-image/idcard_2.jpg");
		Map<String,String> param = new HashMap<String,String>();
		Map<String,String> param1 = new HashMap<String,String>();
		Map<String,String> param2 = new HashMap<String,String>();
		Map<String,String> param3 = new HashMap<String,String>();
		Map<String,String> param4 = new HashMap<String,String>();
		if(!upload.isEmpty() && "REG00000".equals(upload.get("returnCode"))) {
			param.put("quaType", "SETTLE_BANKCARD");
			param.put("quaUrl", upload.get("merQualUrl"));
		}
		if(!upload1.isEmpty() && "REG00000".equals(upload1.get("returnCode"))) {
			param1.put("quaType", "HAND_BANKCARD");
			param1.put("quaUrl", upload1.get("merQualUrl"));
		}
		if(!upload2.isEmpty() && "REG00000".equals(upload2.get("returnCode"))) {
			param2.put("quaType", "HAND_IDCARD");
			param2.put("quaUrl", upload2.get("merQualUrl"));
		}
		if(!upload3.isEmpty() && "REG00000".equals(upload3.get("returnCode"))) {
			param3.put("quaType", "IDCARD_BACK");
			param3.put("quaUrl", upload3.get("merQualUrl"));
		}
		if(!upload4.isEmpty() && "REG00000".equals(upload4.get("returnCode"))) {
			param4.put("quaType", "IDCARD_FRONT");
			param4.put("quaUrl", upload4.get("merQualUrl"));
		}
		JSONObject jsonObject = JSONObject.fromObject(param);
		JSONObject jsonObject1 = JSONObject.fromObject(param1);
		JSONObject jsonObject2 = JSONObject.fromObject(param2);
		JSONObject jsonObject3 = JSONObject.fromObject(param3);
		JSONObject jsonObject4 = JSONObject.fromObject(param4);
		JSONArray array = new JSONArray();
		array.add(jsonObject.toString());
		array.add(jsonObject1.toString());
		array.add(jsonObject2.toString());
		array.add(jsonObject3.toString());
		array.add(jsonObject4.toString());
		array.toArray();
		if(null != po) {
			//String status = clientPersonRegInfoMapper.selectStatusByCardNoAndParentMerchantNo(po.getCardNo(), po.getParentMerchantNo());
			//if(StringUtils.isNotBlank(status) && status.equals(PayConstants.Status.UR.getStatus())) {
				//状态不为空 并且 未注册
				Map<String, String> params = new HashMap<>();
				params.put("legalName", po.getLegalName());
				params.put("legalIdCard", po.getLegalIdCard());
				if(StringUtils.isBlank(po.getMerShortName())) {
					params.put("merShortName", "");
				}else {
					params.put("merShortName", po.getMerShortName());
				}
				if(StringUtils.isBlank(po.getMerLegalEmail())) {
					params.put("merLegalEmail", "");
				}else {
					params.put("merLegalEmail", po.getMerLegalEmail());
				}
				if(StringUtils.isBlank(po.getMerLegalPhone())) {
					params.put("merLegalPhone", "");
				}else {
					params.put("merLegalPhone", po.getMerLegalPhone());
				}
				if(StringUtils.isBlank(po.getMerLevel1No())) {
					params.put("merLevel1No", "");
				}else {
					params.put("merLevel1No", po.getMerLevel1No());
				}
				if(StringUtils.isBlank(po.getMerLevel2No())) {
					params.put("merLevel2No", "");
				}else {
					params.put("merLevel2No", po.getMerLevel2No());
				}
				params.put("merProvince", po.getMerProvince());
				params.put("merCity", po.getMerCity());
				params.put("merDistrict", po.getMerDistrict());
				params.put("merAddress", po.getMerAddress());
				params.put("cardNo", po.getCardNo());
				params.put("headBankCode", po.getHeadBankCode());
				params.put("bankCode", po.getBankCode());
				if(StringUtils.isBlank(po.getBankProvince())) {
					params.put("bankProvince", "");
				}else {
					params.put("bankProvince", po.getBankProvince());
				}
				if(StringUtils.isBlank(po.getBankCity())) {
					params.put("bankCity", "");
				}else {
					params.put("bankCity", po.getBankCity());
				}
				params.put("fileInfo", array.toString());
				params.put("requestNo", po.getRequestNo());
				params.put("parentMerchantNo", po.getParentMerchantNo());
				params.put("notifyUrl", regEnNotifyUrl);
				if(StringUtils.isBlank( po.getMerAuthorizeType())) {
					params.put("merAuthorizeType", "");
				}else {
					params.put("merAuthorizeType", po.getMerAuthorizeType());
				}
				if(StringUtils.isBlank(po.getBusinessFunction())) {
					params.put("businessFunction", "");
				}else {
					params.put("businessFunction",po.getBusinessFunction());
				}
				String str = "{\"payProductMap\":{\"B2C_PAY\":{\"dsPayBankMap\":{\"NET_BANK\":{\"rateType\":\"PERCENTAGE\",\"rate\":\"0.3\"}}},\"B2B_PAY\":{\"dsPayBankMap\":{\"NET_BANK\":{\"rateType\":\"ONEPAY\",\"rate\":\"10\"}}}},\"payScenarioMap\":{\"WEB_ACCESS\":{\"webUrl\":\"http://www.izz2025.com\",\"icp\":\"18144663\"}}}";
				//String debitJson = "{\"payProductMap\":{\"ONE_KEY_PAY_DEBIT\":{\"dsPayBankMap\":{\"BANK_PAY_WAP\":{\"rateType\":\"PERCENTAGE\",\"rate\":\"0.6\"}}},\"ONE_KEY_PAY_CREDIT\":{\"dsPayBankMap\":{\"BANK_PAY_WAP\":{\"rateType\":\"PERCENTAGE\",\"rate\":\"0.6\"}}},\"B2C_PAY\":{\"dsPayBankMap\":{\"NET_BANK\":{\"rateType\":\"PERCENTAGE\",\"rate\":\"0.3\"}}},\"B2B_PAY\":{\"dsPayBankMap\":{\"NET_BANK\":{\"rateType\":\"ONEPAY\",\"rate\":\"10\"}}},\"USER_SCAN_PAY\":{\"dsPayBankMap\":{\"WECHAT_ATIVE_SCAN_OFFLINE\":{\"rateType\":\"PERCENTAGE\",\"rate\":\"0.6\"},\"ALIPAY_ONLINE\":{\"rateType\":\"PERCENTAGE\",\"rate\":\"0.6\"},\"ALIPAY_OFFLINE\":{\"rateType\":\"PERCENTAGE\",\"rate\":\"0.6\"}}}}}";
			    JSONObject productInfo = JSONObject.fromObject(str);
			    System.out.println("产品信息---->"+productInfo.toString());
				params.put("productInfo",productInfo.toString());
			    org.json.JSONObject json = new org.json.JSONObject(params);
				log.info("子商户入网注册参数----->"+json.toString());
				String uri = YeepayService.getUrl(YeepayService.PERSON_URL);
				Map<String, String> result = new HashMap<>();
				try {
					result = YeepayService.requestYOP(params, uri, YeepayService.PERSON);
				} catch (IOException e) {
					//TODO return code and msg
					e.printStackTrace();
				}
				String merchantNo = result.get("merchantNo"); //商户编号
				String returnMsg = result.get("returnMsg"); //返回信息
				String returnCode = result.get("returnCode"); //返回CODE
				String requestNo = result.get("requestNo"); //入网请求号
				String parentMerchantNo = result.get("parentMerchantNo"); //代理商编号
				String externalId = result.get("externalId"); //入网流水号
				log.info("【merchantNo】--->"+merchantNo+"【returnMsg】--->"+returnMsg+
						"【returnCode】--->"+returnCode+"【requestNo】--->"+requestNo+
						"【parentMerchantNo】--->"+parentMerchantNo+"【externalId】--->"+externalId);
				
			//}
			
		}
		return null;
	}
	
	
	/**
	 * 子商户入网注册 【企业】
	 * @param po
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/azz/api/pay/regEnterprise",method=RequestMethod.POST)
	public JsonResult<String> regEnterprise(@RequestBody Enterprisereginfo po) throws Exception{
		Map<String, String> upload = upload("http://azz-image.oss-cn-shenzhen.aliyuncs.com/yeepay-image/fa_1.jpg");
		Map<String, String> upload1 = upload("http://azz-image.oss-cn-shenzhen.aliyuncs.com/yeepay-image/fa_2.jpg");
		Map<String, String> upload2 = upload("http://azz-image.oss-cn-shenzhen.aliyuncs.com/yeepay-image/kaihu.jpeg");
		Map<String, String> upload3 = upload("http://azz-image.oss-cn-shenzhen.aliyuncs.com/yeepay-image/yinye.png");
		Map<String, String> upload4 = upload("http://azz-image.oss-cn-shenzhen.aliyuncs.com/yeepay-image/yinye.png");
		Map<String, String> upload5 = upload("http://azz-image.oss-cn-shenzhen.aliyuncs.com/yeepay-image/yinye.png");
		Map<String, String> upload6 = upload("http://azz-image.oss-cn-shenzhen.aliyuncs.com/yeepay-image/yinye.png");
		Map<String,String> param = new HashMap<String,String>();
		Map<String,String> param1 = new HashMap<String,String>();
		Map<String,String> param2 = new HashMap<String,String>();
		Map<String,String> param3 = new HashMap<String,String>();
		Map<String,String> param4 = new HashMap<String,String>();
		Map<String,String> param5 = new HashMap<String,String>();
		Map<String,String> param6 = new HashMap<String,String>();
		if(!upload.isEmpty() && "REG00000".equals(upload.get("returnCode"))) {
			param.put("quaType", "IDCARD_FRONT");
			param.put("quaUrl", upload.get("merQualUrl"));
		}
		if(!upload1.isEmpty() && "REG00000".equals(upload1.get("returnCode"))) {
			param1.put("quaType", "IDCARD_BACK");
			param1.put("quaUrl", upload1.get("merQualUrl"));
		}
		if(!upload2.isEmpty() && "REG00000".equals(upload2.get("returnCode"))) {
			param2.put("quaType", "OP_BANK_CODE");
			param2.put("quaUrl", upload2.get("merQualUrl"));
		}
		if(!upload3.isEmpty() && "REG00000".equals(upload3.get("returnCode"))) {
			param3.put("quaType", "CORP_CODE");
			param3.put("quaUrl", upload3.get("merQualUrl"));
		}
		if(!upload4.isEmpty() && "REG00000".equals(upload4.get("returnCode"))) {
			param4.put("quaType", "TAX_CODE");
			param4.put("quaUrl", upload4.get("merQualUrl"));
		}
		if(!upload5.isEmpty() && "REG00000".equals(upload5.get("returnCode"))) {
			param5.put("quaType", "ORG_CODE");
			param5.put("quaUrl", upload5.get("merQualUrl"));
		}
		if(!upload6.isEmpty() && "REG00000".equals(upload6.get("returnCode"))) {
			param6.put("quaType", "UNI_CREDIT_CODE");
			param6.put("quaUrl", upload6.get("merQualUrl"));
		}
		JSONObject jsonObject = JSONObject.fromObject(param);
		JSONObject jsonObject1 = JSONObject.fromObject(param1);
		JSONObject jsonObject2 = JSONObject.fromObject(param2);
		JSONObject jsonObject3 = JSONObject.fromObject(param3);
		JSONObject jsonObject4 = JSONObject.fromObject(param4);
		JSONObject jsonObject5 = JSONObject.fromObject(param5);
		JSONObject jsonObject6 = JSONObject.fromObject(param6);
		JSONArray array = new JSONArray();
		array.add(jsonObject.toString());
		array.add(jsonObject1.toString());
		array.add(jsonObject2.toString());
		array.add(jsonObject3.toString());
		array.add(jsonObject4.toString());
		array.add(jsonObject5.toString());
		array.add(jsonObject6.toString());
		array.toArray();
		if(null != po) {
			//String status = clientEnterpriseRegInfoMapper.selectStatusByCardNoAndMerFullName(po.getCardNo(), po.getMerFullName());
			//if(StringUtils.isNotBlank(status) && Objects.equals(status, PayConstants.Status.UR)) {
				//状态不为空 并且 未注册
				Map<String, String> params = new HashMap<>();
				params.put("merFullName", po.getMerFullName());
				params.put("merShortName", po.getMerShortName());
				params.put("merCertNo", po.getMerCertNo());
				params.put("merCertType", po.getMerCertType());
				params.put("legalName", po.getLegalName());
				params.put("legalIdCard", po.getLegalIdCard());
				params.put("merContactName", po.getMerContactName());
				params.put("merContactPhone", po.getMerContactPhone());
				if(StringUtils.isBlank(po.getMerContactEmail())) {
					params.put("merContactEmail", "");
				}else {
					params.put("merContactEmail", po.getMerContactEmail());
				}
				if(StringUtils.isBlank(po.getMerLevel1No())) {
					params.put("merLevel1No", "");
				}else {
					params.put("merLevel1No", po.getMerLevel1No());
				}
				if(StringUtils.isBlank(po.getMerLevel2No())) {
					params.put("merLevel2No", "");
				}else {
					params.put("merLevel2No", po.getMerLevel2No());
				}
				params.put("merProvince", po.getMerProvince());
				params.put("merCity", po.getMerCity());
				params.put("merDistrict", po.getMerDistrict());
				params.put("merAddress", po.getMerAddress());
				if(StringUtils.isBlank(po.getTaxRegistCert())) {
					params.put("taxRegistCert", "");
				}else {
					params.put("taxRegistCert", po.getTaxRegistCert());
				}
				params.put("accountLicense", po.getAccountLicense());
				if(StringUtils.isBlank(po.getOrgCode())) {
					params.put("orgCode", "");
				}else {
					params.put("orgCode", po.getOrgCode());
				}
				if(StringUtils.isBlank(po.getOrgCodeExpiry())) {
					params.put("orgCodeExpiry", "");
				}else {
					params.put("orgCodeExpiry", po.getOrgCodeExpiry());
				}
				if(StringUtils.isBlank(po.getIsOrgCodeLong())) {
					params.put("isOrgCodeLong", "");
				}else {
					params.put("isOrgCodeLong", po.getIsOrgCodeLong());
				}
				params.put("cardNo", po.getCardNo());
				params.put("headBankCode", po.getHeadBankCode());
				params.put("bankCode", "102584002660");
				if(StringUtils.isBlank(po.getBankProvince())) {
					params.put("bankProvince", "");
				}else {
					params.put("bankProvince", po.getBankProvince());
				}
				if(StringUtils.isBlank(po.getBankCity())) {
					params.put("bankCity", "");
				}else {
					params.put("bankCity", po.getBankCity());
				}
				String str = "{\"payProductMap\":{\"ONE_KEY_PAY_DEBIT\":{\"dsPayBankMap\":{\"BANK_PAY_WAP\":{\"rateType\":\"PERCENTAGE\",\"rate\":\"0.6\"}}},\"ONE_KEY_PAY_CREDIT\":{\"dsPayBankMap\":{\"BANK_PAY_WAP\":{\"rateType\":\"PERCENTAGE\",\"rate\":\"0.6\"}}},\"B2C_PAY\":{\"dsPayBankMap\":{\"NET_BANK\":{\"rateType\":\"PERCENTAGE\",\"rate\":\"0.3\"}}},\"B2B_PAY\":{\"dsPayBankMap\":{\"NET_BANK\":{\"rateType\":\"ONEPAY\",\"rate\":\"10\"}}},\"USER_SCAN_PAY\":{\"dsPayBankMap\":{\"WECHAT_ATIVE_SCAN\":{\"rateType\":\"PERCENTAGE\",\"rate\":\"0.6\"},\"ALIPAY\":{\"rateType\":\"PERCENTAGE\",\"rate\":\"0.6\"}}}},\"payScenarioMap\":{\"WEB_ACCESS\":{\"webUrl\":\"http://www.izz2025.com\",\"icp\":\"18144663\"}}}";
				JSONObject productInfo = JSONObject.fromObject(str);
				params.put("productInfo",productInfo.toString());
				params.put("fileInfo", array.toString());
				params.put("requestNo", po.getRequestNo());
				params.put("parentMerchantNo", po.getParentMerchantNo());
				params.put("notifyUrl", regEnNotifyUrl);
				if(StringUtils.isBlank( po.getMerAuthorizeType())) {
					params.put("merAuthorizeType", "");
				}else {
					params.put("merAuthorizeType", po.getMerAuthorizeType());
				}
				if(StringUtils.isBlank(po.getBusinessFunction())) {
					params.put("businessFunction", "");
				}else {
					params.put("businessFunction",po.getBusinessFunction());
				}
				org.json.JSONObject json = new org.json.JSONObject(params);
				log.info("子商户入网注册参数----->"+json.toString());
				String uri = YeepayService.getUrl(YeepayService.ENTERPRISE_URL);
				Map<String, String> result = new HashMap<>();
				try {
					result = YeepayService.requestYOP(params, uri, YeepayService.ENTERPRISE);
				} catch (IOException e) {
					//TODO return code and msg
					e.printStackTrace();
				}
				String merchantNo = result.get("merchantNo"); //商户编号
				String returnMsg = result.get("returnMsg"); //返回信息
				String returnCode = result.get("returnCode"); //返回CODE
				String requestNo = result.get("requestNo"); //入网请求号
				String parentMerchantNo = result.get("parentMerchantNo"); //代理商编号
				String externalId = result.get("externalId"); //入网流水号
				log.info("【merchantNo】--->"+merchantNo+"【returnMsg】--->"+returnMsg+
						"【returnCode】--->"+returnCode+"【requestNo】--->"+requestNo+
						"【parentMerchantNo】--->"+parentMerchantNo+"【externalId】--->"+externalId);
				
			}
			
		return null;
		
	}
	
	/**
	 * 获取支行信息
	 * @param bb
	 * @return
	 */
	public Map<String,String> getBankBranchInfo(@RequestBody BankBranch bb){
		Map<String, String> branchInfo = new HashMap<String,String>();
		try {
			branchInfo = YeepayService.getBankBranchInfo(bb.getHeadBankCode(), bb.getProvinceCode(), bb.getCityCode());
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return branchInfo;
	}
	
	
	/**
	 * 文件上传
	 * @param responseMsg
	 * @param customerId
	 * @return
	 * @throws Exception 
	 */
	private Map<String,String> upload(String url) throws Exception {
		String fileType = "IMAGE";
		Map<String, String> result = YeepayService.uploadUrlStream(fileType, url);
		return result;
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
		String result = callback.get("status");//SUCCESS 成功
		String platformType = callback.get("platformType"); //支付方式
		String paymentProduct = callback.get("paymentProduct");//支付产品
		String orderId = callback.get("orderId");//订单号
		String payAmount = callback.get("payAmount");//实付金额
		String uniqueOrderNo = callback.get("uniqueOrderNo");//三方流水号
		String requestDate = callback.get("requestDate");//下单时间
		String paySuccessDate = callback.get("paySuccessDate");//支付完成时间
		if(StringUtils.isNotBlank(result) && result.equals("SUCCESS")) {
       	 //校验订单是否支付成功
           Map<String,Object> map = new HashMap<String,Object>();
           map.put("no_order", orderId);
           map.put("money_order", payAmount);
           if(getOrderStatus(map)) {
           	Map<String,Object> map1 = new HashMap<String,Object>();
           	map1.put("order_status", (byte) PayStatus.PAY_SUCCESS.getValue());
           	map1.put("order_info", "");
           	map1.put("order_type", PayConstants.PayType.getDesc(paymentProduct)+"::"+PayConstants.PayPlatForm.getDesc(platformType));
           	map1.put("pay_success_date", paySuccessDate);
           	map1.put("three_party_number", uniqueOrderNo);
           	map1.put("pay_number", orderId);
           	int number = ppm.updateOrderByNumber(map1);
           	if(number != 1) {
           		retBean.setRet_code(PayCode.UPDATEFAILD.getCode());
                retBean.setRet_msg(PayCode.UPDATEFAILD.getDesc());
                return new JsonResult<>(retBean);
           	}
           	String orderCode = ppm.selectOrderCode(orderId);
           	CallBackParam cbp = new CallBackParam();
        		cbp.setClientOrderCode(orderCode);
        		cbp.setPayMethod(PayMethod.ONLINE.getValue());
        		cbp.setOrderType(PayConstants.PayPlatForm.getNum(platformType));
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
		orderInfo.setGoodsName("零件购买");//TODO fix
		orderInfo.setGoodsDesc("");
		orderInfo.setRequestDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//下单时间
	    orderInfo.setUserType("USER_ID"); //用户标示类型 默认USER_ID
		orderInfo.setUserNo(corderInfo.getClientUserCode());//用户CODE
	    log.info("订单创建成功----->"+orderInfo);
		return orderInfo;
	} 
	
	

}
