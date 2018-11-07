/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午4:58:06
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月31日 下午4:58:06
 */
import lombok.Data;
@Data
public class Param {
	private String paramName;
	private Byte paramsType;
	private Byte paramsChoice;
	private String[] param;
	private String paramCode;
	private String modifier;
	private String parentCode;
	private String assortmentCode;
}

