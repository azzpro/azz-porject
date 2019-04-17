/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午7:45:21
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
 * @author 黄智聪  2018年11月1日 下午7:45:21
 */
@Data
public class PutOnOrPutOffOrDelActivityParam {
	
	@NotBlank(message = "活动编码不能为空")
	private String activityCode;
	
	@NotNull(message = "状态不能为空")
	private Byte status;
	
	private String modifier;

}

