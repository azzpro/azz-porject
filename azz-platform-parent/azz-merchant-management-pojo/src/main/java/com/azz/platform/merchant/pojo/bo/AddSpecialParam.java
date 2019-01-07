/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月7日 下午2:44:39
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月7日 下午2:44:39
 */
@Data
public class AddSpecialParam {
    
    @NotBlank(message = "专场名称不允许为空")
    private String specialName;
    
    private String creator;
    
    // 专场主图
    private SpecialPic mainPic;
    
    // 专场背景图
    private SpecialPic bgPic;
    
}

