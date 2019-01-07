/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月7日 下午3:34:24
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月7日 下午3:34:24
 */
@Data
public class ChangeSpecialStatus {
    @NotBlank(message = "专场编码不允许为空")
    private String specialCode;
    @NotBlank(message = "状态不允许为空")
    private String status;
    private String modifier;
}

