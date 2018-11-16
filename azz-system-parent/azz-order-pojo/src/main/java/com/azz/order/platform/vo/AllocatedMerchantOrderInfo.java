/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午4:19:35
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.platform.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月12日 下午4:19:35
 */
@Data
public class AllocatedMerchantOrderInfo {

	private String clientOrderCode;
	
	private BigDecimal grandTotal;
	
	private Integer orderStatusId;// 这里是客户订单的状态
	
	private Integer paymentMethod;
	
	private Date handlerTime;// 处理时间，即拆单时间
	
	// 拆单信息
	private List<MerchantOrderInfo> merchantOrderInfos;
	
}

