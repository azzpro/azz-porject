/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月6日 下午3:37:05
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import com.azz.core.common.QueryPage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月6日 下午3:37:05
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SearchCaseInfoParam extends QueryPage{
	
	private static final long serialVersionUID = -7781909912698491904L;
	
	private String searchInput;

}

