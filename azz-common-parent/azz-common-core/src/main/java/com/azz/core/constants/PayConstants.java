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
		FAILD("交易失败","9999");
		
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
}

