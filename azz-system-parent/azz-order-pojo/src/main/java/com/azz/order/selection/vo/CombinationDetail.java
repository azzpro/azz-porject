/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月22日 下午2:55:57
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.vo;

import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月22日 下午2:55:57
 */
@Data
public class CombinationDetail {
	
	private String combinationCode;

	private String combinationName;
	
	private String combinationPicUrl;

	private String combinationPicName;
	
	private String recommendReason;
	
	private Long caseId;

	// 选型参数
	private List<InitParams> params;
	
	// 产品信息
	private List<List<Object>> productInfos;

}

