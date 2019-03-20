/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月20日 下午12:02:28
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.finance.pojo.vo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年3月20日 下午12:02:28
 */
@Data
public class ThirdInfo {

	/**
     * 三方信息--单号
     *
     * @mbg.generated
     */
    private String thirdInfoCode;

    /**
     * 三方信息--三方状态
     *
     * @mbg.generated
     */
    private String thirdInfoStatus;

    /**
     * 三方信息--提现金额
     *
     * @mbg.generated
     */
    private BigDecimal thirdInfoWithdrawDepositMoney;
	
}

