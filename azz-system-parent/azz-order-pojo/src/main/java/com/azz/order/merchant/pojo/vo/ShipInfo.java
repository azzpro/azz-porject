/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月13日 下午4:01:58
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.pojo.vo;

import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月13日 下午4:01:58
 */
@Data
public class ShipInfo {
    // 发货人
    private String creator;
    // 发货时间
    private Date createTime;
    // 公司名称
    private String companyName;
    // 物流公司名称
    private String logistiscCompanyName;
    // 单号
    private String number;
    // 配送人
    private String deliveryPerson;
    // 配送人电话
    private String deliveryPhoneNumber;
    // 出货文件信息
    private String shipmentFileInfo;
    
}

