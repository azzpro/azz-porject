/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月29日 上午11:51:34
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月29日 上午11:51:34
 */
@Data
public class MerchantInfo {

    private String merchantCode;
    
    private Integer qualificationApplyStatus;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    
    private String merchantName;
    
    private String legalPersonName;
    
    private String legalPersonIdCard;
    
    private String companyName;
    
    private String companyTel;
    
    private String address;

    private String tradingCertificateFirstFileName;
    
    private String tradingCertificateFirstFileUrl;
    
    private String tradingCertificateSecondFileName;
    
    private String tradingCertificateSecondFileUrl;
    
    private String tradingCertificateThirdFileName;
    
    private String tradingCertificateThirdFileUrl;

    private String businessLicenseFileName;
    
    private String businessLicenseFileUrl;
    
}

