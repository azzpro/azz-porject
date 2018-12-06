/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月6日 上午10:34:06
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.pojo.vo;

import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年12月6日 上午10:34:06
 */
@Data
public class SignUpCourse {
    private Long id;
    private Date creatTime;
    private Integer status;
    private String name;
    private String articleName;
    private Integer gender;
    private String mobilePhone;
    private String email;
    private String qq;
    private String company;
    private String post;
    private String modifier;
    private Date modifierTime;
    private String remark;
}

