/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月16日 下午2:16:33
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.platform.vo;

import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月16日 下午2:16:33
 */
@Data
public class OrderOperationRecord {

	private String optType;
	
	private Date optTime;
	
	private String operator;
	
	private String optRemark;
	
}

