/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月14日 上午9:57:29
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月14日 上午9:57:29
 */
@Data
public class EditOrderStatus {
    
    // 商户id
    @NotNull(message = "商户id不允许为空")
    private Long merchantId;
    
    // 商户的订单编码
    @NotBlank(message = "商户的订单编码不允许为空")
    private String orderCode;
    
    // 1待确认  2待发货  3待签收  4已完成  5已取消
    @NotNull(message = "订单状态不允许为空")
    private Integer status;
    
    private String modifier;
}

