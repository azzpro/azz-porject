/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月14日 下午5:05:17
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.platform.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月14日 下午5:05:17
 */
@Data
public class MerchantOrderInfoParam {
	
	@NotBlank(message = "请求参数无效")
	private String merchantCode;
	
	private String remark;

}

