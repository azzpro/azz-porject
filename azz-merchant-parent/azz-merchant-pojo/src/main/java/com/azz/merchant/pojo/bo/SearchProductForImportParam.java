/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月13日 下午3:48:23
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import com.azz.core.common.QueryPage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年12月13日 下午3:48:23
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SearchProductForImportParam extends QueryPage{
	
	private static final long serialVersionUID = -1520739486757240338L;
	
	private String productCode;
	
	@NotBlank(message = "缺少请求参数")
	private String moduleCode;

}

