/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月7日 下午7:45:29
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import com.azz.core.common.QueryPage;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月7日 下午7:45:29
 */
@Data
public class SearchCaseListParam extends QueryPage{
    private Long status;
    private String param;
}

