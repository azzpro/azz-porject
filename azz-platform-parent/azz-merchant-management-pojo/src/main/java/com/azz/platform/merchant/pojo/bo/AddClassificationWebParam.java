/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 上午9:39:14
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
 * @author 彭斌  2018年11月1日 上午9:39:14
 */
@Data
public class AddClassificationWebParam {
    /**
     * 上级分类编号，可为空
     *
     * @mbg.generated
     */
    private String assortmentParentCode;

    /**
     * 分类名称
     *
     * @mbg.generated
     */
    @NotBlank(message = "分类名称不允许为空")
    private String assortmentName;
    
    /**
     * 分类排序(默认0)
     *
     * @mbg.generated
     */
    private Integer assortmentSort;
    
    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String creator;
    
    @NotNull(message = "请上传主图")
    private MultipartFile classificationFile;
}

