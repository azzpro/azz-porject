/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月23日 上午11:14:05
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月23日 上午11:14:05
 */
@Data
public class BindingPhomeParam {
    @NotBlank(message = "参数异常")
    private String openId;
    @NotBlank(message = "手机号码不允许为空")
    private String phoneNumber;
    @NotBlank(message = "验证密码不允许为空")
    private String verificationCode;
    private String avatarUrl;
    private String nickName;
}

