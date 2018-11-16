/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月13日 上午10:54:35
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.pojo.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月13日 上午10:54:35
 */
@Data
public class OrderItem {
    private String productCode;
    private String moduleName;
    private String modulePicUrl;
    private BigDecimal productPrice;
    private Integer quantity;
    private Date deliveryTime;
    private BigDecimal subtotal;
}

