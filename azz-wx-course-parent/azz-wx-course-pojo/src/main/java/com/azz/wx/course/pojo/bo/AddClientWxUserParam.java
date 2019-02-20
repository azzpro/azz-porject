/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月23日 下午2:11:07
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月23日 下午2:11:07
 */
@Data
public class AddClientWxUserParam {
    private String openid;
    private String userCode;
}

