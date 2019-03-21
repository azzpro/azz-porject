/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午3:50:48
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.finance.pojo.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 下午3:50:48
 */
@Data
public class WithdrawDepositApplyInfo {
	
	private String applyCode;
	
	private BigDecimal totalWithdrawDepositMoney;
	
	private BigDecimal commissionCharge;// 手续费
	
	private Byte status;
	
	private String withdrawDepositAccount;
	
	private Date createTime;
	
	private Date payWithTime;// 打款时间
	
	private String merchantName;
	
	private BigDecimal totalOrderMoney;
	
	private String creator;
	
}

