package com.azz.order.client.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class CashWithdrawal{
	
	/**
	 * 
	 */
	@NotBlank(message="订单号不能为空")
	private String orderId;
	
	@NotBlank(message="商户编码不能为空")
	private String merchantCode;
	
}
