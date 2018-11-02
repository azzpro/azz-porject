/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:28:59
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.vo;

import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月1日 下午2:28:59
 */
@Data
public class GoodModuleInfo {
	
	private String moduleCode;
	
	private String moduleName;
	
	private String modulePicUrl;
	
	private String classificationName;
	
	private String moduleStatus;
	
	private String moduleInfo;
	
	private String merchantName;
	
	private String creator;
	
	private Date createTime;
	
}

