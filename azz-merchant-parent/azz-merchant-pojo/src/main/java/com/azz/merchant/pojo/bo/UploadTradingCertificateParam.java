/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月23日 下午9:26:19
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.bo;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月23日 下午9:26:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadTradingCertificateParam {
    
    @NotNull(message = "营业执照文件名不允许为空")
    private String fileName;
    
    @Max(value = 100000000L, message = "营业执照文件大小不能超过20M")
    private long fileSize;
    
    @NotBlank(message = "商户编码不允许为空")
    private String merchantCode;
    
    @NotBlank(message = "营业执照文件内容不能为空")
    private String fileBase64Str;

}

