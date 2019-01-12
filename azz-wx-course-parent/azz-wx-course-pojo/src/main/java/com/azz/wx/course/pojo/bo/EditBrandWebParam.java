/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月12日 下午2:46:34
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
 * @author 彭斌  2019年1月12日 下午2:46:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditBrandWebParam {
    @NotBlank(message = "品牌编码不允许为空")
    private String brandCode;

    @NotBlank(message = "请输入品牌名称")
    private String brandName;

    @NotBlank(message = "请输入品牌详情")
    private String brandInfo;

    private String brandDescription;

    // 是否换了品牌主图
    @NotNull(message = "缺少请求参数")
    private Integer isChangeGoodsBrandPic;
    
    private MultipartFile brandPicFile;

    private String modifier;
}

