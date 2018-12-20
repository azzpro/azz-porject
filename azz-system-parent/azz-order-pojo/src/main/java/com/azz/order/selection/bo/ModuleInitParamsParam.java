/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月19日 下午2:04:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.bo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年12月19日 下午2:04:43
 */
@Data
public class ModuleInitParamsParam {

	@NotBlank(message = "模组编码不允许为空")
	private String moduleCode;
	
	// 选中的查询参数
	private List<SelectParam> selectParams;
	
	// 选中的输入参数
	private List<InputParam> inputParams;
	
}

