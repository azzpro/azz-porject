/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月7日 上午11:43:53
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
 * @author 黄智聪  2019年1月7日 上午11:43:53
 */
@Data
public class EditRecommendParam {

	@NotBlank(message = "请选择推荐活动")
	private String recommendCode;
	
	@NotBlank(message = "请输入推荐活动名称")
	private String recommendName;
	
	@NotNull(message = "请选择推荐状态")
	private Byte status; 
	
	private String modifier;
	
}

