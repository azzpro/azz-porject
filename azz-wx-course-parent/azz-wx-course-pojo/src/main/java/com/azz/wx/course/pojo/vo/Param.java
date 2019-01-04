/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 上午11:57:28
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.vo;

import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月4日 上午11:57:28
 */
@Data
public class Param {
	
	private String paramCode;
	
	private List<ParamTerm> paramTerms;
}

