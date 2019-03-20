/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午5:17:40
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.finance.pojo.vo;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 下午5:17:40
 */
@Data
public class MerchantOrderInfo {
	
	private String merchantOrderCode;
	
	private BigDecimal grandTotal;
	
	private BigDecimal withdrawDepositMoney;// 提现金额
	
	private BigDecimal transactionCost;// 手续费

	private Integer orderStatusId;
	
	private Integer withdrawDepositStatus;// 1可提现 2提现中 3已提现
	
	private Date orderDate;

}

