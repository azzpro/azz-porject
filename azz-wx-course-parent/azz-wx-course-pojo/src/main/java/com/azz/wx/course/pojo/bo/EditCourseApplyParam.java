/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月24日 上午10:48:16
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月24日 上午10:48:16
 */
@Data
public class EditCourseApplyParam {
    @NotBlank(message = "用户编码不许为空")
    private String userCode;
    @NotBlank(message = "请输入报名姓名")
    private String personName;
    @NotBlank(message = "请输入手机号码")
    private String phoneNumber;
    @NotBlank(message = "请输入任职公司")
    private String company;
    @NotBlank(message = "请输入邮箱")
    private String email;
    @NotBlank(message = "请输入毕业院校")
    private String graduateSchool;
    @NotBlank(message = "请选择申请编码")
    private String applyCode;
}

