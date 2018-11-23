/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月22日 下午4:04:30
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.platform.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月22日 下午4:04:30
 */
@Data
public class AuditInvoiceStatusParam {
    @NotBlank(message = "请求参数无效")
    private String platformUserCode;
    @NotBlank(message = "请求参数无效")
    private String clientInvoiceApplyCode;
    @NotNull(message = "请求参数无效")
    private Integer status;
}

