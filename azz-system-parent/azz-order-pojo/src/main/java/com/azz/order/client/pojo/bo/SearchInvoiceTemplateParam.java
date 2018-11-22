/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月19日 下午3:06:24
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月19日 下午3:06:24
 */
@Data
public class SearchInvoiceTemplateParam {
    
    @NotNull(message = "发票类型不许为空")
    private Integer invoiceType;
    @NotBlank(message = "用户编码不许为空")
    private String clientUserCode;
}

