/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午8:37:36
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月22日 下午8:37:36
 */
@Data
public class MerchantInfo {
    private String merchantCode;
    private String merchantName;
    private String legalPersonName;
    private String legalPersonIdCard;
    private String companyName;
    private String creditCode;
    private String companyTel;
    private String address;
    private Integer status;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    
    private String tradingCertificateFirstFileName;

    /**
     * 第一张营业执照url
     *
     * @mbg.generated
     */
    private String tradingCertificateFirstFileUrl;

    /**
     * 第二张营业执照名称
     *
     * @mbg.generated
     */
    private String tradingCertificateSecondFileName;

    /**
     * 第二张营业执照url
     *
     * @mbg.generated
     */
    private String tradingCertificateSecondFileUrl;

    /**
     * 第三张营业执照名称
     *
     * @mbg.generated
     */
    private String tradingCertificateThirdFileName;

    /**
     * 第三张营业执照url
     *
     * @mbg.generated
     */
    private String tradingCertificateThirdFileUrl;

    /**
     * 公司经营执照名称
     *
     * @mbg.generated
     */
    private String businessLicenseFileName;

    /**
     * 公司经营执照url
     *
     * @mbg.generated
     */
    private String businessLicenseFileUrl;

}

