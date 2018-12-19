/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月18日 下午6:01:02
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.bo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.azz.core.common.QueryPage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年12月18日 下午6:01:02
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SearchSelectionModuleParam extends QueryPage{
	
	private static final long serialVersionUID = 2659715707634914501L;

	@NotBlank(message = "请选择分类编码")
	private String assortmentCode;
	
	@NotBlank(message = "缺少请求参数")
	private Integer level;
	
	// 选中的查询参数
	private List<SelectParam> selectParams;
	
	// 选中的输入参数
	private List<InputParam> inputParams;
	
	private Integer isPriceAsc;
	
	private Integer isDeliveryDateAsc;

}

