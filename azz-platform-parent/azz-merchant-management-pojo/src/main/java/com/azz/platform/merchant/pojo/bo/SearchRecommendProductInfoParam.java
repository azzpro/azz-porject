/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月7日 下午4:43:57
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
 * @author 黄智聪  2019年1月7日 下午4:43:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchRecommendProductInfoParam extends QueryPage{
	
	private static final long serialVersionUID = 6208484413353123030L;

	@NotBlank(message = "请选择模组")
	private String moduleCode;
	
	private String productCode;
	

}

