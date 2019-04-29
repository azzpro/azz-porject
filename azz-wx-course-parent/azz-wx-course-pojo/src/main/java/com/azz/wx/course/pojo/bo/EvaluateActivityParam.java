/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月23日 上午11:13:55
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月23日 上午11:13:55
 */
@Data
public class EvaluateActivityParam {
	
	@NotBlank(message = "请选择课程")
	private String activityCode;
	
	@NotNull(message = "请选择星级")
	@Min(value = 1, message = "星级范围为1~5")
	@Max(value = 5, message = "星级范围为1~5")
	private Byte grade;
	
	private String evaluationContent;

	@NotBlank(message = "缺少请求参数")
	private String openid;
	
	@NotBlank(message = "缺少请求参数")
	private String nickname;
	
	@NotBlank(message = "缺少请求参数")
	private String headImageUrl;

}

