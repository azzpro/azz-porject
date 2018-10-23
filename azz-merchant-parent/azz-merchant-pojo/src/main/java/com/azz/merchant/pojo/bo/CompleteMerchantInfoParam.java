/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午2:38:07
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月22日 下午2:38:07
 */
@Data
public class CompleteMerchantInfoParam {

    @NotBlank(message = "商户编码不允许为空")
    private String merchantCode;
    @NotBlank(message = "商户名称不允许为空")
    private String merchantName;
    @NotBlank(message = "企业名称不允许为空")
    private String companyName;
    @NotBlank(message = "信用代码不允许为空")
    private String creditCode;
    @NotBlank(message = "公司电话不允许为空")
    private String companytel;
    @NotBlank(message = "省代码不允许为空")
    private String proviceCode;
    @NotBlank(message = "请选择省份")
    private String proviceName;
    @NotBlank(message = "市代码不允许为空")
    private String cityCode;
    @NotBlank(message = "请选择市不允许为空")
    private String cityName;
    @NotBlank(message = "区代码不允许为空")
    private String areaCode;
    @NotBlank(message = "请选择区")
    private String areaName;
    @NotBlank(message = "详细地址不允许为空")
    private String detailAddress;
    @NotBlank(message = "至少上传一张营业执照")
    private String tradingCertificateFirstFileName;
    private String tradingCertificateFirstFileUrl;
    private String tradingCertificateSecondFileName;
    private String tradingCertificateSecondFileUrl;
    private String tradingCertificateThirdFileName;
    private String tradingCertificateThirdFileUrl;
    @NotBlank(message = "请上传经营信息")
    private String businessLicenseFileName;
    private String businessLicenseFileUrl;
    
}
