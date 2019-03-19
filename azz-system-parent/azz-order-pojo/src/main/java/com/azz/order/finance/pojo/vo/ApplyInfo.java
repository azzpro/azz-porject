/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午6:46:36
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
 * @author 黄智聪  2019年3月19日 下午6:46:36
 */
@Data
public class ApplyInfo {

    private String applyCode;

    private BigDecimal totalWithdrawDepositMoney;
    
    private String phoneNumber;

    private Byte status;

    private String remark;

    private String creator;

    private Date createTime;
	
}

