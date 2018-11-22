/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月19日 下午6:00:59
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.pojo.bo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月19日 下午6:00:59
 */
@Data
public class AddInvoiceApplyParam {
    @NotNull(message = "订单金额不许为空")
    private BigDecimal amount;
    @NotNull(message = "客户订单编码不许为空")
    private String clientOrderCode;
    @NotNull(message = "客户发票模板id不许为空")
    private Long invoiceTemplateId;
    @NotNull(message = "客户收货地址id不许为空")
    private Long shippingAddressId;
    @NotBlank(message = "客户编码不许为空")
    private String creator;
}

