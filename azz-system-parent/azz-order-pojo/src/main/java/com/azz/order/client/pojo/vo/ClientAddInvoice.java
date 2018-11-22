/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月19日 下午4:54:09
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.pojo.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月19日 下午4:54:09
 */
@Data
public class ClientAddInvoice {
    private Long id;
    private String clientOrderCode;
    private BigDecimal grandTotal;
    private Integer invoiceStatus;
    private Integer orderStatusId;
    private Date createTime;
}

