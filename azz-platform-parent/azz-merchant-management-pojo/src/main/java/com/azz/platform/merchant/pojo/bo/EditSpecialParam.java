/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月7日 下午4:35:11
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月7日 下午4:35:11
 */
@Data
public class EditSpecialParam {
    @NotBlank(message = "专场名称不允许为空")
    private String specialName;
    
    private String specialCode;
    
    private String modifier;
    
    // 专场主图
    private SpecialPic mainPic;
    
    // 专场背景图
    private SpecialPic bgPic;
    
    /**
     * 是否修改过主图 0 未修改 1修改过
     */
    private Integer isEditMainPic;
    
    /**
     * 是否修改过主图 0 未修改 1修改过
     */
    private Integer isEditBgPic;
}

