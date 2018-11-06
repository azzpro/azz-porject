/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月6日 下午3:00:10
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
 * @author 黄智聪  2018年11月6日 下午3:00:10
 */
@Data
public class PutOnOrPutOffOrDelCombinationParam {

	@NotBlank(message = "推荐组合编码不能为空")
	private String combinationCode;
	
	@NotNull(message = "推荐组合状态不能为空")
	private Integer status;
	
	private String modifier;
}

