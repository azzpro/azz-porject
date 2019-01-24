/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月24日 下午3:36:25
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月24日 下午3:36:25
 */
@Data
public class AddCourseSuggestionsParam {
    
    /**
     * 用户编码
     *
     * @mbg.generated
     */
    @NotBlank(message = "用户编码不许为空")
    private String userCode;

    /**
     * 微信openid
     *
     * @mbg.generated
     */
    @NotBlank(message = "openid不许为空")
    private String openid;

    /**
     * 问题类型
     *
     * @mbg.generated
     */
    @NotBlank(message = "请输入问题类型")
    private String questionType;

    /**
     * 联系方式
     *
     * @mbg.generated
     */
    @NotBlank(message = "请输入联系方式")
    private String contact;

    /**
     * 问题描述
     *
     * @mbg.generated
     */
    @NotBlank(message = "请输入问题描述")
    private String questionDescription;
}

