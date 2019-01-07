/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月7日 下午2:49:05
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月7日 下午2:49:05
 */
@Data
public class AddOrRemoveProductParam {
	
	@NotNull(message = "缺少请求参数")
	private Integer addOrRemove;// 新增1 移除2

	@NotBlank(message = "请选择模组")
	private String moduleCode;
	
	@NotBlank(message = "请选择产品")
	private String productCode;
	
	private String creator;
	
}

