/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月23日 下午5:29:53
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
 * @author 黄智聪  2018年11月23日 下午5:29:53
 */
@Data
public class ShoppingCartProduct {

	@NotBlank(message = "产品编码不允许为空")
	private String productCode;
	
	@NotNull(message = "缺少请求参数")
	private Long productPriceId;
	
}

