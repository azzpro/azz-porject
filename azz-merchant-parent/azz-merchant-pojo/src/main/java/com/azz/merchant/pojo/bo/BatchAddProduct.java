/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月14日 上午10:39:04
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.bo;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年12月14日 上午10:39:04
 */
@Data
public class BatchAddProduct {
    // 分类id
    @NotNull(message = "分类编码不许为空")
    private Long assortmentId;
    private String creator;
    private Long merchantId;
    
    private List<BatchAddProductItem> items;
}

