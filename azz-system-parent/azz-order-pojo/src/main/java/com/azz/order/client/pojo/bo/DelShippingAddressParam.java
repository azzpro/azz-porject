/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月13日 下午3:36:00
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.pojo.bo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月13日 下午3:36:00
 */
@Data
public class DelShippingAddressParam {
	
	@NotNull(message = "请选择收货地址")
	private Long shippingId;

	private String modifier;

}

