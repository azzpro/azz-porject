/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月13日 上午9:49:47
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.pojo.vo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月13日 上午9:49:47
 */

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class OrderList {
    private String orderCode;
    private String statusName;
    private BigDecimal grandTotal;
    private String userName;
    private Date orderTime;
    private String receiver;
    private Date processingTime;
}

