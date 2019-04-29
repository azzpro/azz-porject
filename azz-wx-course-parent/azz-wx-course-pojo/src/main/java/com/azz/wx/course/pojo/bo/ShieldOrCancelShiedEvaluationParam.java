/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年4月29日 下午2:09:49
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年4月29日 下午2:09:49
 */
@Data
public class ShieldOrCancelShiedEvaluationParam {

	@NotBlank(message = "请选择评价记录")
	private String evaluationCode;
	
	@NotNull(message = "缺少请求参数")
	private Byte status;
	
	private String modifier;
	
}

