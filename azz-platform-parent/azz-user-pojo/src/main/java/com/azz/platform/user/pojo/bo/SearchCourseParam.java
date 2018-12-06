/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月6日 上午10:51:49
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.pojo.bo;

import com.azz.core.common.QueryPage;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年12月6日 上午10:51:49
 */
@Data
public class SearchCourseParam extends QueryPage{
    private Integer status;
    private String courseName;
}

