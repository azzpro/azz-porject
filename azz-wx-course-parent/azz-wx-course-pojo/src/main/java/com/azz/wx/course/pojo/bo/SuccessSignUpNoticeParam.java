/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年4月16日 下午6:00:11
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年4月16日 下午6:00:11
 */
@Data
public class SuccessSignUpNoticeParam {
	
	@NotNull(message = "缺少请求参数")
	private String openid;
	
	@NotNull(message = "缺少请求参数")
	private String activityCode;

}

