/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月2日 下午2:42:38
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月2日 下午2:42:38
 */
@Data
public class GoodsBrandInfo {
	
	private String brandName;
	
	private String brandPicUrl;
	
	private String brandCode;
	
	private String brandEnglishName;
	
	private String brandDescription;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
	private Date createTime;

}

