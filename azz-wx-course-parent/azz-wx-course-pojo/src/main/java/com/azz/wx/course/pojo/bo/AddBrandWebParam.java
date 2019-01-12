/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月12日 下午2:43:09
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月12日 下午2:43:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBrandWebParam {
    @NotBlank(message = "请输入品牌名称")
    private String brandName;
    
    @NotNull(message = "请上传品牌主图")
    private MultipartFile brandPicFile;

    private String brandInfo;
    
    private String brandDescription;
    
    private String creator;
}

