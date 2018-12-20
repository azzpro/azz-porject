/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月19日 下午3:37:53
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.vo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年12月19日 下午3:37:53
 */
@Data
public class ModuleProductInfo {
	
	private String productCode;
	
	private Integer deliveryDate;
	
	private BigDecimal price;
	
	private String brandName;
	
	private List<ProductParams> productParams;

}

