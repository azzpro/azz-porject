/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月20日 下午2:50:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>审核企业信息</P>
 * @version 1.0
 * @author 彭斌  2018年10月20日 下午2:50:46
 */
@Data
public class AuditParam {
    /**
     * 商户编码
     */
    @NotBlank(message = "商户编码不允许为空")
    private String merchantCode;
    
    /**
     * 审核结果信息
     */
    @NotNull(message = "审核结果不允许为空")
    private Integer status;
    
    /**
     * 审核人
     */
    private String auditor;
}

