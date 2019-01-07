/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月7日 下午4:31:19
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.vo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月7日 下午4:31:19
 */
@Data
public class RecommentProductInfo {
	
	private String productCode;
	
	private Byte productStatus;
	
	private Byte relatedStatus;// 产品的关联状态  0未关联 1已关联
	
	private BigDecimal minPrice;
	
	private Integer minDeliveryDate;

	private String paramValues;
}

