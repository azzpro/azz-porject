/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月2日 下午1:43:21
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.vo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月2日 下午1:43:21
 */

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class MerchantProductList {
	private String productCode;
	private String moduleName;
	private Integer deliveryDate;
	private BigDecimal price;
	private String creator;
	private Date createTime;
	private Integer status;
	private String systemCode;
}

