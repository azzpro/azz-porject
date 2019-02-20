package com.azz.order.client.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.azz.util.Md5Utils;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.client.YopRsaClient;
import com.yeepay.g3.sdk.yop.encrypt.CertTypeEnum;
import com.yeepay.g3.sdk.yop.encrypt.DigestAlgEnum;
import com.yeepay.g3.sdk.yop.encrypt.DigitalSignatureDTO;
import com.yeepay.g3.sdk.yop.utils.DigitalEnvelopeUtils;
import com.yeepay.g3.sdk.yop.utils.InternalConfig;

@Service
public class YeepayService {
	
	private static String parentMerchantNo;
	
	private static String merchantNo;
	
	private static String baseURL;
	
	private static String tradeOrderURI;
	
	private static String orderQueryURI;
	
	private static String CASHIERI;
	
	private static String APICASHIERI;
	
	private static String privatekey;
	
	private static String publickey;
	
	private static String hmackeyqueryURI;
	
	
	
	public static String getHmackeyqueryURI() {
		return hmackeyqueryURI;
	}
	@Value("${yeepay.hmackeyqueryURI}")
	public  void setHmackeyqueryURI(String hmackeyqueryURI) {
		YeepayService.hmackeyqueryURI = hmackeyqueryURI;
	}
	public static String getPublickey() {
		return publickey;
	}
	@Value("${yeepay.publickey}")
	public  void setPublickey(String publickey) {
		YeepayService.publickey = publickey;
	}

	public static String getParentMerchantNo() {
		return parentMerchantNo;
	}
	
	@Value("${yeepay.parentMerchantNo}")
	public  void setParentMerchantNo(String parentMerchantNo) {
		this.parentMerchantNo = parentMerchantNo;
	}

	public static String getMerchantNo() {
		return merchantNo;
	}
	@Value("${yeepay.merchantNo}")
	public  void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public static String getBaseURL() {
		return baseURL;
	}
	@Value("${yeepay.baseURL}")
	public  void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public static String getTradeOrderURI() {
		return tradeOrderURI;
	}
	@Value("${yeepay.tradeOrderURI}")
	public  void setTradeOrderURI(String tradeOrderURI) {
		this.tradeOrderURI = tradeOrderURI;
	}

	public static String getOrderQueryURI() {
		return orderQueryURI;
	}
	@Value("${yeepay.orderQueryURI}")
	public  void setOrderQueryURI(String orderQueryURI) {
		this.orderQueryURI = orderQueryURI;
	}

	public static String getCASHIERI() {
		return CASHIERI;
	}
	@Value("${yeepay.CASHIER}")
	public  void setCASHIERI(String cASHIERI) {
		this.CASHIERI = cASHIERI;
	}

	public static String getAPICASHIERI() {
		return APICASHIERI;
	}
	@Value("${yeepay.APICASHIER}")
	public  void setAPICASHIERI(String aPICASHIERI) {
		this.APICASHIERI = aPICASHIERI;
	}

	public static String getPrivatekey() {
		return privatekey;
	}
	@Value("${yeepay.privatekey}")
	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}
	
	//yop接口应用URI地址
	//public static final String BASE_URL = "baseURL";
	public static final String TRADEORDER_URL = "tradeOrderURI";
	public static final String ORDERQUERY_URL = "orderQueryURI";
	public static final String REFUND_URL = "refundURI";
	public static final String REFUNDQUERY_URL = "refundQueryURI";
	public static final String MULTIORDERQUERY_URL = "multiOrderQueryURI";
	public static final String ORDERCLOSE_URL = "orderCloseURI";
	public static final String DIVIDEORDER_URL="divideOrderURI";
	public static final String DIVIDEORDERQUERY_URL="divideOrderQueryURI";
	public static final String FULLSETTLE_URL="fullSettleURI";
	public static final String CERTORDER_URL = "certOrderURI";
	public static final String CERTORDERQUERY_URL = "certOrderQueryURI";
	public static final String APICASHIER_URI = "APICASHIER";
	
	//接口参数
	public static final String[] TRADEORDER = {"parentMerchantNo","merchantNo","orderId","orderAmount","timeoutExpress","requestDate","redirectUrl","notifyUrl","goodsParamExt","paymentParamExt","industryParamExt","memo","riskParamExt","csUrl","fundProcessType"};
	public static final String[] ORDERQUERY = {"parentMerchantNo","merchantNo","orderId","uniqueOrderNo"};
	public static final String[] REFUND = {"parentMerchantNo","merchantNo","orderId","uniqueOrderNo","refundRequestId","refundAmount","description","memo","notifyUrl"};
	public static final String[] REFUNDQUERY = {"parentMerchantNo","merchantNo","refundRequestId","orderId","uniqueRefundNo"};
	public static final String[] MULTIORDERQUERY = {"status","parentMerchantNo","merchantNo","requestDateBegin","requestDateEnd","pageNo","pageSize"};
	public static final String[] ORDERCLOSE = {"parentMerchantNo","merchantNo","orderId","uniqueOrderNo","description"};
	public static final String[] SETTLEMENTSQUERY = {"parentMerchantNo","merchantNo","startSettleDate","endSettleDate"};
	public static final String[] BALANCEQUERY = {"parentMerchantNo","merchantNo"};
	public static final String[] HMACKEYQUERY = {"parentMerchantNo","merchantNo"};
		
	public static final String[] TRADEDIVIDE = {"parentMerchantNo","merchantNo","divideRequestId","orderId","uniqueOrderNo","contractNo","divideDetail","infoParamExt"};
	public static final String[] TRADEDIVIDEQUERY = {"parentMerchantNo","merchantNo","divideRequestId","orderId","uniqueOrderNo"};
	public static final String[] TRADEFULLSETTLE = {"parentMerchantNo","merchantNo","orderId","uniqueOrderNo"};
	
	//支付方式
	public static final String[] CASHIER = {"merchantNo","token","timestamp","directPayType","cardType","userNo","userType","ext"};
	public static final String[] APICASHIER = {"token","payTool","payType","userNo","userType","appId","openId","payEmpowerNo","merchantTerminalId","merchantStoreNo","userIp","version"};

	
	//拼接支付链接
	public static String getUrl(Map<String,String> paramValues) throws UnsupportedEncodingException{
			StringBuffer url = new StringBuffer();
			url.append(CASHIERI);
			paramValues.put("merchantNo", merchantNo);
			StringBuilder stringBuilder = new StringBuilder();
			System.out.println(paramValues);
			for (int i = 0; i < CASHIER.length; i++) {
				String name = CASHIER[i];
				String value = paramValues.get(name);
				if(i != 0){
					stringBuilder.append("&");
				}
				stringBuilder.append(name+"=").append(value);
			}
			System.out.println(stringBuilder);
			String sign = getSign(stringBuilder.toString());
			url.append("?sign="+sign+"&"+stringBuilder);
			return url.toString();
	}
	
	
	//获取父商编私钥
		public static PrivateKey getSecretKey(){
			PrivateKey isvPrivateKey = InternalConfig.getISVPrivateKey(CertTypeEnum.RSA2048);
			return isvPrivateKey;
		}
		
		//获取公钥
		public static PublicKey getPublicKey(){
			PublicKey isvPublicKey = InternalConfig.getYopPublicKey(CertTypeEnum.RSA2048);
			return isvPublicKey;
		}
		//获取sign
		public static String getSign(String stringBuilder){
			String appKey = "OPR:"+parentMerchantNo;
			PrivateKey isvPrivateKey = getSecretKey();
			DigitalSignatureDTO digitalSignatureDTO = new DigitalSignatureDTO();
			digitalSignatureDTO.setAppKey(appKey);
			digitalSignatureDTO.setCertType(CertTypeEnum.RSA2048);
			digitalSignatureDTO.setDigestAlg(DigestAlgEnum.SHA256);
			digitalSignatureDTO.setPlainText(stringBuilder.toString());
			String sign = DigitalEnvelopeUtils.sign0(digitalSignatureDTO,isvPrivateKey);
			return sign;
		}
		
		//获取子商户密钥
		public static String getMerchantKey() throws IOException{
			Map<String, String> params = new HashMap<>();
			params.put("parentMerchantNo", parentMerchantNo);
			params.put("merchantNo", merchantNo);
			
			Map<String, String> result = new HashMap<>();
			result = YeepayService.requestYOP(params, hmackeyqueryURI, HMACKEYQUERY);
			
			System.out.println("result:"+result);
			return result.get("merHmacKey");
		}
		
		/**
		 * 请求YOP接口-----子商户注册接口使用
		 * params 请求参数,parentMerchantNo除外
		 * uri 请求yop的应用URI地址
		 * paramSign 请求参数的验签顺序
		 * @throws IOException 
		 */
		public static Map<String, String> requestYOP(Map<String, String> params, String uri, String[] paramSign) throws IOException{
			Map<String, String> result = new HashMap<String, String>();
			
			YopRequest request = new YopRequest("OPR:"+parentMerchantNo);
			System.out.println(baseURL);
			for (int i = 0; i < paramSign.length; i ++) {
				String key = paramSign[i];
				System.out.println("name:"+key);
				System.out.println("value:"+params.get(key));
				request.addParam(key, params.get(key)==null?"":params.get(key));
			}
			System.out.println("yoprui:"+uri);
			System.out.println("yopRequest:"+request.getParams());
					
			YopResponse response = YopRsaClient.post(uri, request);
			
			System.out.println(response.toString());
			if("FAILURE".equals(response.getState())){
				if(response.getError() != null)
				result.put("returnCode",response.getError().getCode());
				result.put("returnMsg",response.getError().getMessage());
				return result;
			}
			if (response.getStringResult() != null) {
				result = parseResponse(response.getStringResult());
			}
			
			return result;
		}

		
		/**
		 * 请求YOP接口---其他接口使用
		 * params 请求参数,parentMerchantNo除外
		 * uri 请求yop的应用URI地址
		 * paramSign 请求参数的验签顺序
		 * hmackey 子商编私钥
		 * paramHmac 验签参数
		 * @throws IOException 
		 */
		public static Map<String, String> requestYOP(Map<String, String> params, String uri, String[] paramSign, String[] paramHmac) throws IOException{
			Map<String, String> result = new HashMap<String, String>();
			
			String hmackey = getMerchantKey();
			
			YopRequest request = new YopRequest("OPR:" + parentMerchantNo);
			
			for (int i = 0; i < paramSign.length; i ++) {
				String key = paramSign[i];
				System.out.println("name:"+key);
				System.out.println("value:"+params.get(key));
				request.addParam(key, params.get(key)==null?"":params.get(key));
			}
			System.out.println("yoprui:"+uri);
			System.out.println("yopRequest:"+request.getParams());
			
			StringBuffer hmacBuffer = new StringBuffer();
			for(int i = 0; i < paramHmac.length; i++){
				String key = paramHmac[i];
				hmacBuffer.append(key).append("=").append(params.get(key)).append("&");
			}
			
			String hmacStr = hmacBuffer.subSequence(0, hmacBuffer.length()-1).toString();
			System.out.println("hmacStr:"+hmacStr);
			System.out.println("hmackey:"+hmackey);
			String hmac = Md5Utils.encoderHmacSha256(hmacStr, hmackey);
			System.out.println("hmac:"+hmac);
			request.addParam("hmac", hmac);
			
			System.out.println(request.getParams());
			YopResponse response = YopRsaClient.post(uri, request);
			
			System.out.println(response.toString());
			if("FAILURE".equals(response.getState())){
				if(response.getError() != null)
				result.put("code",response.getError().getCode());
				result.put("message",response.getError().getMessage());
				return result;
			}
			if (response.getStringResult() != null) {
				result = parseResponse(response.getStringResult());
			}
			
			return result;
		}
		
		public static Map<String, String> parseResponse(String response){
			
			Map<String,String> jsonMap  = new HashMap<>();
			jsonMap	= JSON.parseObject(response, 
					new TypeReference<TreeMap<String,String>>() {});
			
			return jsonMap;
		}
}
