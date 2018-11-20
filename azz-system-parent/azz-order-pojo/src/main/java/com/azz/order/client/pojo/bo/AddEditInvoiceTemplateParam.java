/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月20日 上午11:58:11
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.pojo.bo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月20日 上午11:58:11
 */
@Data
public class AddEditInvoiceTemplateParam {
    //  模板id 有参数将修改操作反着则新增 
    private Long id;
    
    private String clientUserCode;
    
    // 0 普通发票 1 增值税专用发票
    @NotNull(message = "发票类型不许为空")
    private Integer invoiceType;
    
    private String invoiceTitle;
    
    // 信用代码
    private String number;
    
    private String remark;
    
    private String companyName;
    
    private String regAddress;
    
    private String regPhone;
    
    private String bank;
    
    private String bankAccount;
}

