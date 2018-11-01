/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午2:55:20
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月31日 下午2:55:20
 */
@Data
public class ParamsData {
	
	private List<Param> params;
	private String assortmentCode;
	private String creator;
	
}

