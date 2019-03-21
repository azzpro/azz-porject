/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午7:02:51
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.finance.pojo.vo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 下午7:02:51
 */
@Data
public class OrderInfo {
	
	private Integer totalOrderCount;

    private BigDecimal totalOrderMoney;

    private BigDecimal totalWithdrawDepositMoney;
    
    private BigDecimal totalTransactionCost;// 总交易费用（手续费）
    
	private List<MerchantOrderInfo> orders;
	
	private Integer status;//状态

}

