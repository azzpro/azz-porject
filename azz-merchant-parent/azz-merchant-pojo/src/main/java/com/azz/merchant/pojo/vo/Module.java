/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月3日 上午10:50:24
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.vo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月3日 上午10:50:24
 */
@Data
public class Module{
	
	private String moduleName;
	private String moduleCode;
	private Byte moduleStatus;
	private Long moduleId;
}

