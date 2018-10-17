/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月16日 下午7:57:53
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月16日 下午7:57:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginParam {
    
    @NotBlank(message = "手机号不允许为空")
    private String phoneNumber;
    
    @NotBlank(message = "密码不允许为空")
    private String password;

}

