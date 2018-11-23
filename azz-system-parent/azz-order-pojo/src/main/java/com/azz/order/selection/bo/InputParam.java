/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月22日 上午10:26:21
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.selection.bo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月22日 上午10:26:21
 */

import java.math.BigDecimal;

import lombok.Data;

@Data	
public class InputParam {
	
	private BigDecimal minVal;
	
	private BigDecimal maxVal;

}

