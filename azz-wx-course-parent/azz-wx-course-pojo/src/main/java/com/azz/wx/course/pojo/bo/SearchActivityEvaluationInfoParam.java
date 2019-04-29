/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年4月29日 下午5:06:32
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
 * @author 黄智聪  2019年4月29日 下午5:06:32
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SearchActivityEvaluationInfoParam extends QueryPage{
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "请选择活动")
	private String activityCode;

}

