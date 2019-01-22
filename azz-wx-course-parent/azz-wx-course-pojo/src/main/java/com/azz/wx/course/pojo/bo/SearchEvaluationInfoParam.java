/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午3:19:29
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import com.azz.core.common.QueryPage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月1日 下午3:19:29
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SearchEvaluationInfoParam extends QueryPage{

	private static final long serialVersionUID = -8538894648942065844L;
	
	@NotBlank(message = "请选择课程")
	private String courseCode;
	
}

