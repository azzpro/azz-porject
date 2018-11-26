/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午1:41:54
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月26日 下午1:41:54
 */
@Data
public class PayOrderParam {
	
	@NotBlank(message = "订单编码不允许为空")
	private String clientOrderCode;
	
	@NotNull(message = "支付类型")
	private Integer paymentType;
	
}

