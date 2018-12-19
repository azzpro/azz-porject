/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月18日 下午5:49:16
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.vo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年12月18日 下午5:49:16
 */
@Data
public class SelectionModuleInfo {
	
	private String moduleCode;
	
	private String moduleName;

	private String modulePicUrl;
	
	private String moduleRemark;
	
	private BigDecimal minPrice;
	
	private Integer minDeliveryDate;
}

