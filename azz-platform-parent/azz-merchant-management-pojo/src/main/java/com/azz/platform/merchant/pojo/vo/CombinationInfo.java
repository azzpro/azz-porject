/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月5日 下午6:58:25
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月5日 下午6:58:25
 */
@Data
public class CombinationInfo {
	
	private String combinationCode;
	
	private String combinationName;
	
	private String caseName;
	
	private Integer status;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
	private Date createTime;
	
	private String creator;
	
	private String combinationPicName;
	
	private String combinationPicUrl;
	
	private List<GoodsModuleInfo> goodsModuleInfos;

}

