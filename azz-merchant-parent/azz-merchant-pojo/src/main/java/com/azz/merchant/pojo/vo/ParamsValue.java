/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月3日 下午1:24:40
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.vo;

import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月3日 下午1:24:40
 */
@Data
public class ParamsValue {
	
	private String paramName;
	//1 下拉 2填写
	private Byte type;
	//1必选 2非必选
	private Byte choice;
	private List<Long> values;
	private Long termId;
}

