/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月5日 下午1:54:08
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.vo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月5日 下午1:54:08
 */

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class PlatfomrProductList {

	private String systemCode;
	private String productCode;
	private String merchantName;
	private BigDecimal price;
	private String params;
	private Byte status;
	private Date createTime;
	private Long productId;
}

