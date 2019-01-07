/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月7日 上午11:18:15
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import com.azz.core.common.QueryPage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月7日 上午11:18:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchRelatedModuleInfoParam extends QueryPage{
	
	private static final long serialVersionUID = 5609724001487875597L;

	@NotBlank(message = "请选择专场")
	private String recommendCode;
	
	private String recommendName;

}

