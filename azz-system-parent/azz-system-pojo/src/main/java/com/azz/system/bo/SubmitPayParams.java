/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午2:56:49
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.bo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午2:56:49
 */

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class SubmitPayParams {
	@NotBlank(message="订单号不能为空")
	private String orderNumber;
	@NotNull(message="订单方式不能为空")
	private Byte orderPayType;
	
}

