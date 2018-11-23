/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月22日 下午12:35:53
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.platform.vo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月22日 下午12:35:53
 */

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class PlatformClientInvoiceList {
    private String clientApplyCode;
    private Integer invoiceType;
    private String clientOrderCode;
    private BigDecimal amount;
    private String phoneNumber;
    private Date createTime;
    private Integer status;
}

