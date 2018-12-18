/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月18日 下午3:03:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.vo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年12月18日 下午3:03:43
 */

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionIndexData {

	private int selectionRecordCount;
	private int notPaidOrderCount;
	private int notSignedOrderCount;
	private List<SelectionCaseInfo> caseInfos;
	private List<ClassificationInfo> classificationInfos;
	
}

