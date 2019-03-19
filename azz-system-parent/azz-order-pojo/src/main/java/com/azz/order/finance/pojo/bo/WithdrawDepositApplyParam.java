/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午4:54:45
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.finance.pojo.bo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 下午4:54:45
 */
@Data
public class WithdrawDepositApplyParam {
	
	@NotBlank(message = "缺少请求参数")
	private String merchantCode;
	
	@NotBlank(message = "缺少请求参数")
	private String merchantUserCode;
	
	@NotBlank(message = "缺少请求参数")
	private String withdrawDepositAccount;
	
	@NotEmpty(message = "请选取订单")
	private List<String> merchantOrderCodes; 

}

