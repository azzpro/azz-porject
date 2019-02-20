/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月23日 下午4:56:02
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.bo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月23日 下午4:56:02
 */

import java.util.Date;

import lombok.Data;

@Data
public class AddWxBindingRecordParam {
    private String openid;
    private String userCode;
    private Date bindingTime;
}

