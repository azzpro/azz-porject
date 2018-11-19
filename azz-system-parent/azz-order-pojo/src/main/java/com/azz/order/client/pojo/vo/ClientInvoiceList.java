/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月19日 下午2:14:55
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.pojo.vo;

import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月19日 下午2:14:55
 */
@Data
public class ClientInvoiceList {
    private String clientApplyCode;
    private Integer invoiceType;
    private Long amount;
    private Integer quantity;
    private Integer status;
    private Date createTime;
    private String clientOrderCode;
}

