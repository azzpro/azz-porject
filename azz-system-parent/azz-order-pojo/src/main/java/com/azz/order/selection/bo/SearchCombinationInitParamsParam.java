/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月20日 下午8:06:35
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.bo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.azz.core.common.QueryPage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月20日 下午8:06:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchCombinationInitParamsParam extends QueryPage{
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "推荐组合编码不允许为空")
	private String combinationCode;
	
	// 选中的查询参数
	private List<SelectParam> selectParams;
	
	// 选中的输入参数
	private List<InputParam> inputParams;
	
}

