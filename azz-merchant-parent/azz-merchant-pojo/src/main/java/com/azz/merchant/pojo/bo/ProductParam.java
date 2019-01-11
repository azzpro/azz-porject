/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月2日 下午2:41:15
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.bo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月2日 下午2:41:15
 */
@Data
public class ProductParam {
	private String paramName;
	private Byte type;
	private String values;
	private Byte choice;
	private Long termId;
	private Byte paramsHidden;
}

