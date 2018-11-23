/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月22日 下午8:41:20
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.pojo.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月22日 下午8:41:20
 */
@Data
public class MerchantInvoiceDetail {
    private Long merchantId;
    private String merchantOrderCode;
    private Integer clientOrderStatus;
    private Integer paymentMethod;
    private BigDecimal grandTotal;
    private BigDecimal applyAmount;
    private Integer merchantInvoiceApplyStatus;
    private String merchantApplyCode;
    private Integer invoiceType;
    private Date createTime;
    private String invoiceTitle;
    private String taxIdentificationNumber;
    private String companyName;
    private String bank;
    private String bankAccount;
    private String regAddress;
    private String regTelephone;
    private String receiverName;
    private String receiverPhoneNumber;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String addressAlias;
    private String detailAddress;
    private String deliveryCreator;
    private Date deliveryCreateTime;
    private Integer deliveryType;
    private String deliveryCompanyName;
    private String number;
    private String deliveryPerson;
    private String deliveryPhone;
    
    private List<OrderItem> orderItems;
}

