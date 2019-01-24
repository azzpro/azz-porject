/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月23日 下午5:51:19
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.vo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月23日 下午5:51:19
 */
@Data
public class CourseSignUpInfo {
    private Long id;
    private String personName;
    private String phoneNumber;
    private String company;
    private Byte isDefault;
}

