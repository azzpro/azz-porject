/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月18日 下午2:23:25
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>修改密码</P>
 * @version 1.0
 * @author 彭斌  2018年10月18日 下午2:23:25
 */
@Data
public class EditPasswordParam {
    
    @NotBlank(message = "手机号码不允许为空")
    private String phoneNumber;
    
    @NotBlank(message = "原始密码不允许为空")
    private String oldPassword;
    
    @NotBlank(message = "第一次密码不允许为空")
    private String firstPassword;
    
    @NotBlank(message = "第二次密码不允许为空")
    private String secondPassword;
}

