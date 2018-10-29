/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月20日 上午10:25:17
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.client.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>启用或禁用用户的参数</P>
 * @version 1.0
 * @author 黄智聪  2018年10月20日 上午10:25:17
 */
@Data
public class RemoveClientUserParam {

    @NotBlank(message = "用户编码不允许为空")
    private String clientUserCode;
    
    private String modifier;
    
}

