/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月23日 下午6:45:04
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.pojo.bo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月23日 下午6:45:04
 */
@Data
public class OrderInvoiceParam {
    // （0 快递 1 自送）
    @NotNull(message = "参数无效")
    private Integer deliveryType;
    private Long expressCompanyId;
    private String number;
    private String deliveryPerson;
    private String deliveryPhone;
    private String merchantOrderCode;
    private String merchantUserCode;
}

