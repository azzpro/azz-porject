/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午8:08:01
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.vo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月31日 下午8:08:01
 */
@Data
public class ParamsAll {
	
	private String paramsCode;
	private String paramsName;
	private Byte paramsChoice;
	private Byte paramsType;
	private String values;
	private Byte updatFlag;
	private String parentCode;
	private String assortName;
	private String assortCode;
	private Long paramParentId;
}

