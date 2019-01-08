/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月7日 下午2:44:39
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月7日 下午2:44:39
 */
@Data
public class AddSpecialWebParam {
    
    @NotBlank(message = "专场名称不允许为空")
    private String specialName;
    
    private String creator;
    
    @NotNull(message = "请上传主图")
    private MultipartFile mainFile;
    
    @NotNull(message = "请上传专场背景图")
    private MultipartFile bgFile;
    
}

