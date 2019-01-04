/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 上午11:57:28
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月4日 上午11:57:28
 */
@Data
public class CourseParam {
	
	@NotBlank(message = "存在空的参数编码")
	private String paramCode;
	
	@NotBlank(message = "存在空的参数项编码")
	private String paramTermCode;
	
	@NotBlank(message = "存在空的参数项名称")
	private String paramName;
	
	@NotBlank(message = "存在空的参数值")
	private String paramValue;
	
	@NotNull(message = "存在空的参数类型")
	private Byte paramType;
	
	@NotNull(message = "请选中参数是否必选")
	private Byte paramChoice;
	
}

