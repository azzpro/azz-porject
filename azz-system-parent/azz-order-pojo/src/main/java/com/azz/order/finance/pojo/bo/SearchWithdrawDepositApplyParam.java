/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午3:50:54
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.finance.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import com.azz.core.common.QueryPage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 下午3:50:54
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SearchWithdrawDepositApplyParam extends QueryPage{
	
	private static final long serialVersionUID = 8770090692145173298L;
	
	@NotBlank(message = "缺少请求参数")
	private String merchantCode;
	
	private String searchInput;

}

