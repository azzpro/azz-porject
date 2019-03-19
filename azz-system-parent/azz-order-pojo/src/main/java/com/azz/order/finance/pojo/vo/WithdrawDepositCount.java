/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午4:24:22
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.finance.pojo.vo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * <P>提现统计</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 下午4:24:22
 */
@Data
public class WithdrawDepositCount {

	// 总收入
	private BigDecimal totalIncome;
	
	// 已提现金额
	private BigDecimal withdrawDepositMoney;
	
	// 可提现金额
	private BigDecimal notWithdrawDepositMoney;
}

