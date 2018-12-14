/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月13日 下午4:38:36
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.bo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年12月13日 下午4:38:36
 */
@Data
public class AddModuleProductParam {

	private String modifier;
	
	@NotBlank(message = "缺少请求参数")
	private String moduleCode;
	
	@NotEmpty(message = "请选择产品")
	private List<String> productCodes;
	
}

