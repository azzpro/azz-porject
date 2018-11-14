/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月14日 下午2:21:15
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.platform.bo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月14日 下午2:21:15
 */
@Data
public class AllocateClientOrderParam {

	@NotBlank(message = "客户订单号不允许为空")
	private String clientOrderCode;
	
	@NotEmpty(message = "缺少请求参数")
	private List<MerchantOrderInfoParam> infos;
	
	// 分配人，即当前平台的操作人
	private String allocatePerson;
	
}

