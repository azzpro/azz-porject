/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月21日 上午11:44:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.pojo.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月21日 上午11:44:46
 */
@Data
public class ClientInvoiceApplyDetail {
    private String orderCode;
    private Integer statusId;
    private Integer paymentMethod;
    private BigDecimal grandTotal;
    private BigDecimal applyAmount;
    private String receiverName;
    private String receiverPhoneNumber;
    private String addressAlias;
    private String detailAddress;
    private String clientApplyCode;
    private Integer invoiceType;
    private Date createTime;
    private String invoiceTitle;
    private String taxIdentificationNumber;
    private String companyName;
    private String bank;
    private String bankAccount;
    private String regAddress;
    private String regTelephone;
    private String remark;
    
    private List<ClientInvoiceDeliveryDetail> invoiceDelivery;

    private List<ClientOrderItemInfo> orderItem;
}

