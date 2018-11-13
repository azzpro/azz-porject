/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月13日 上午10:52:49
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.pojo.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月13日 上午10:52:49
 */
@Data
public class OrderDetail {
    // 订单基本信息
    private String orderCode;
    private String orderStatus;
    private Date orderTime;
    private BigDecimal grandTotal;
    
    // 产品详情
    private List<OrderItem> orderItems;
    
    // 获取收货地址信息
    private ReceiverAddress receiverAddress;
    
    // 获取发货信息
    private ShipInfo shipInfo;
    
    // 获取签收信息
    private SignForInfo signForInfo;
}

