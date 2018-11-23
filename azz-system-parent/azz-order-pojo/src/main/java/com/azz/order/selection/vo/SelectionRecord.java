/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月23日 下午3:17:37
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.vo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月23日 下午3:17:37
 */

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class SelectionRecord {

	private Long selectionRecordId;
	private String productCode;
	private BigDecimal price;
	private Integer productStatus;
	private Integer deliveryDate;
	private String moduleName;
	private Date createTime;
	private String paramsValue;
	
}

