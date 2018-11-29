/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月27日 下午5:38:01
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月27日 下午5:38:01
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SearchInitParamsParamWithSort extends SearchInitParamsParam{
	 
	private static final long serialVersionUID = -7802403691504049868L;
	
	private Integer isPriceAsc;
	
	private Integer isDeliveryDateAsc;

}

