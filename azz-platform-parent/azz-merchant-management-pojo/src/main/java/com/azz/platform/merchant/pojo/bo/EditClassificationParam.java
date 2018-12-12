/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午1:52:03
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月31日 下午1:52:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditClassificationParam {
    
    /**
     * 分类编码
     */
    @NotBlank(message = "分类编码不允许为空")
    private String assortmentCode;
    
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
     * 修改人
     *
     * @mbg.generated
     */
    private String modifier;
    
    /**
     * 分类主图
     */
    private ClassificationPic classificationPic;
    
    /**
     * 是否修改过主图 0 未修改 1修改过
     */
    private Integer isEditPic;
}

