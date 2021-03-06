/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月22日 下午2:39:40
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.vo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月22日 下午2:39:40
 */
@Data
public class CombinationInfo {

	private String combinationCode;

	private String combinationName;
	
	private String combinationPicUrl;

	private String combinationPicName;
	
	private String recommendReason;

	private Integer deliveryDate;

	private BigDecimal price;
	
}

