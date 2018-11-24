/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月23日 下午7:18:37
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.bo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月23日 下午7:18:37
 */
@Data
public class OrderParam {
	
	// 前端传入的产品编码，协同客户编码查出该产品信息，然后获取
	@NotBlank(message = "缺少请求参数")
	private String clientUserCode;

	// 收货地址id
	@NotNull(message = "请选择收货地址")
	private Long shippingId;
	
	// 产品信息
	@NotEmpty(message = "缺少请求参数")
	private List<OrderItem> orderItems;

	// 订单备注
	private String remark;

}

