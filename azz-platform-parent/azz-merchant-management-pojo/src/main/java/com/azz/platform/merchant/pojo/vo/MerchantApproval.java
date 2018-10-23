/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午8:15:07
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * <P>商户审批管理列表</P>
 * @version 1.0
 * @author 彭斌  2018年10月22日 下午8:15:07
 */
@Data
public class MerchantApproval {
    
    private String merchantCode;
    
    private String companyName;
    
    private String registeredPerson;
    
    private String contactPhone;
    
    private Integer status;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
}

