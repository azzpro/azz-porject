/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月17日 下午6:08:33
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.core.constants;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年12月17日 下午6:08:33
 */
public interface PayConstants {
	
	public static final String PAYMENT_INSTITUTION = "连连支付";
	
	public enum PayCode{
		SUCCESS("交易成功","0000"),
		FAILD("交易失败","9999"),
		UPDATEFAILD("订单更新失败","1111"),
		PAID("订单已支付,请勿重复支付","2222");
		private String desc;
		private String code;
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		private PayCode(String desc, String code) {
			this.desc = desc;
			this.code = code;
		}
		
	}
	
	public enum PayType{
		BALANCEPAY("0","余额支付" ,1),
		IDEBITCARDPAY("1","网银借记卡支付",2),
		ICREDITCARDPAY("8","网银信用卡支付",3),
		CCREDITCARDPAY("9","企业网银信用卡支付",4),
		QUICKPAYDEBITCARDPAY("2","快捷支付(借记卡)",5),
		QUICKPAYCREDITCARDPAY("2","快捷支付(信用卡)",6),
		AUTHPAY("D","认证支付",7),
		WEIXINPAY("I","微信主扫",8),
		ZHIFUBAOPAY("L","支付宝主扫",9);
		
		private String code;
		private String desc;
		private Integer num;
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		private PayType(String desc) {
			this.desc = desc;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		private PayType(String code, String desc,Integer num) {
			this.code = code;
			this.desc = desc;
			this.num = num;
		}
		
		public Integer getNum() {
			return num;
		}
		public void setNum(Integer num) {
			this.num = num;
		}
		public static String getDesc(String code) {
			PayType[] values = PayType.values();
			for (PayType payType : values) {
				if(code.equals(payType.getCode())) {
					return payType.getDesc();
				}
			}
			return "";
		}
		
		public static Integer getNum(String code) {
			PayType[] values = PayType.values();
			for (PayType payType : values) {
				if(code.equals(payType.getCode())) {
					return payType.getNum();
				}
			}
			return 0;
		}
	}
}

