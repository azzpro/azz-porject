/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月14日 下午4:23:54
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.platform.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月14日 下午4:23:54
 */
@Data
public class MerchantOrderList {
    private String orderCode;
    private String clientOrderCode;
    private String merchantName;
    private BigDecimal grandTotal;
    private String orderStatus;
    private String processinPerson;
    private Date processingTime;
}

