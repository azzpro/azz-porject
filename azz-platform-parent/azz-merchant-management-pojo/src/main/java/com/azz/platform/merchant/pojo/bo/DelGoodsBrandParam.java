/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午1:52:03
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * 
 * <P>品牌</P>
 * @version 1.0
 * @author 黄智聪  2018年11月2日 下午4:35:10
 */
@Data
public class DelGoodsBrandParam {
    
    @NotBlank(message = "品牌编码不允许为空")
    private String brandCode;

    private String modifier;
    
}

