/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月23日 下午2:58:17
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月23日 下午2:58:17
 */
@Data
public class ChangeOrderStatusParam {

	private String clientUserCode;
	
	@NotBlank(message = "请选择订单")
	private String orderCode;
	
}

