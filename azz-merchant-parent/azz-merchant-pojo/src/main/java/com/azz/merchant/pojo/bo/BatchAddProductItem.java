/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月14日 上午11:13:34
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.bo;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年12月14日 上午11:13:34
 */
@Data
public class BatchAddProductItem {
    @NotNull(message = "交期不允许为空")
    private Integer deliveryDate;
    @NotNull(message = "价格不允许为空")
    private BigDecimal price;
    @NotBlank(message = "商品编码不允许为空")
    private String productCode;
    @NotNull(message = "品牌不允许为空")
    private Long brandId;
    List<ProductParam> params;
}

