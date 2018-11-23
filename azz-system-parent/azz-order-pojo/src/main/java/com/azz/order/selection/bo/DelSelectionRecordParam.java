/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月23日 下午5:28:15
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.bo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月23日 下午5:28:15
 */
@Data
public class DelSelectionRecordParam {
	
	@NotBlank(message = "客户编码不允许为空")
	private String clientUserCode;
	
	@NotEmpty(message = "请选择产品")
	private List<Long> selectionRecordIds;

}

