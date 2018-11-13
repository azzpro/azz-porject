/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午7:09:16
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.pojo.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月12日 下午7:09:16
 */
@Data
public class DeliveryInfo {
	
	private Integer deliveryType;

	private String deliveryPerson;
	
	private String deliveryPhoneNumber;
	
	private String expressCompany;// 快递公司名称

	private String logistiscCompanyName;// 物流名称

	private String number;// 快递或物流单号
	
	private List<ExpressFileInfo> expressFileInfos;
	
	private String shipmentFileInfo;// 发货文件信息的json字符串，后面会转换成ExpressFileInfo
	
	private String productCodes;// 逗号隔开的产品编码
	
	private Date deliveryTime;// 发货时间
	
}

