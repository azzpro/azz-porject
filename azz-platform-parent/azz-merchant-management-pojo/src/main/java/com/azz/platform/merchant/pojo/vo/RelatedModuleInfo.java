/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月7日 下午3:35:32
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.vo;

import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月7日 下午3:35:32
 */
@Data
public class RelatedModuleInfo {
	
	private String recommendCode;
	
	private String recommendName;

	private String moduleCode;
	
	private String moduleName;
	
	private Integer moduleStatus;
	
	private String merchantName;
	
	private Integer productNumber;
	
	private Date relatedTime;
	
}

