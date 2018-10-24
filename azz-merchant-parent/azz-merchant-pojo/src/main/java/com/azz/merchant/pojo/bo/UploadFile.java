/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月23日 下午9:26:19
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月23日 下午9:26:19
 */
@Data
public class UploadFile {
    
    @NotNull(message = "请上传相应文件")
    private MultipartFile file;
    
    @NotBlank(message = "商户编码不允许为空")
    private String merchantCode;

}

