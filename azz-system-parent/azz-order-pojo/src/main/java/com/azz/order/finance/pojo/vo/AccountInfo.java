/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午7:00:17
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.finance.pojo.vo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 下午7:00:17
 */
@Data
public class AccountInfo {
	
	/**
     * 开户账号
     *
     * @mbg.generated
     */
    private String accountName;

    /**
     * 开户银行
     *
     * @mbg.generated
     */
    private String accountBank;

    /**
     * 对公账号
     *
     * @mbg.generated
     */
    private String accountBankCardNumber;

    /**
     * 开户支行
     *
     * @mbg.generated
     */
    private String accountSubBranch;
    
    /**
     * 信用代码
     */
    private String creditCode;

}

