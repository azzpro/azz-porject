/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月19日 上午11:38:30
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年12月19日 上午11:38:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleDetail {
	
	private ModuleInfo moduleInfo;
	
	// 选型参数
	private List<InitParams> params;
	
	// 产品信息
	private List<List<Object>> productInfos;

}

