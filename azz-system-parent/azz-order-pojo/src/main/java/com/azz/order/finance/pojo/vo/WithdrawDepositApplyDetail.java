/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午6:46:36
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.finance.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 下午6:46:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawDepositApplyDetail {
	// 提现信息
	private ApplyInfo applyInfo;
	
	// 账户信息
	private AccountInfo accountInfo;
	
	// 订单信息
	private OrderInfo orderInfo;
	
}

