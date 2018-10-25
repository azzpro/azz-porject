/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 上午10:35:45
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.client.pojo.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月24日 上午10:35:45
 */
@Data
public class ClientInfo {
    private String clientUserCode;
    private String companyName;
    private Integer status;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    private String creditCode;
    private String companyTel;
    private String address;
    private String tradingCertificateFirstFileName;
    private String tradingCertificateFirstFileUrl;
    private String tradingCertificateSecondFileName;
    private String tradingCertificateSecondFileUrl;
    private String tradingCertificateThirdFileName;
    private String tradingCertificateThirdFileUrl;
}

