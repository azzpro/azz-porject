/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月14日 下午4:02:27
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.platform.vo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月14日 下午4:02:27
 */
@Data
public class MerchantOrderInfo {
	
	private String merchantOrderCode;
	
	private String merchantCode;
	
	private String merchantName;
	
	private BigDecimal eachMerchantGrandTotal;// 每个商户订单的订单金额总和
	
	private Integer orderStatusId;// 商户订单的状态
	
	private List<MerchantOrderItemInfo> orderItems;
	
}

