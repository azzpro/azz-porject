/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年4月19日 上午9:57:38
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年4月19日 上午9:57:38
 */

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class SignUpParam {
	
	@NotBlank(message = "请选择活动")
    private String activityCode;

	@NotBlank(message = "缺少请求参数")
    private String openid;

	@NotBlank(message = "缺少请求参数")
    private String nickname;

	@NotBlank(message = "缺少请求参数")
    private String headImageUrl;

	@NotBlank(message = "请填写姓名")
    private String userName;

	@NotBlank(message = "请填写手机号")
    private String phoneNumber;

	@NotBlank(message = "请填写公司名称")
    private String companyName;

	@NotBlank(message = "请填写职位")
    private String position;
	
	private String mainProductOrService;

}

