/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 下午4:30:16
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.vo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月4日 下午4:30:16
 */
@Data
public class ParamTerm {
	
	private String paramTermCode;
	
	private String paramName;
	
	private Byte paramType;
	
	private Byte paramChoice;
	
	// 逗号隔开的参数值
	private String paramValues;
}

