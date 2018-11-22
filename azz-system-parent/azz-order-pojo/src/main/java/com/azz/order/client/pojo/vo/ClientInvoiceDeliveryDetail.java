/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月21日 下午12:55:48
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.pojo.vo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月21日 下午12:55:48
 */

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ClientInvoiceDeliveryDetail {
    private BigDecimal applyAmount;
    private String merchantApplyCode;
    private Integer deliveryType;
    private String companyName;
    private String number;
    private String deliveryPerson;
    private String deliveryPhone;
}

