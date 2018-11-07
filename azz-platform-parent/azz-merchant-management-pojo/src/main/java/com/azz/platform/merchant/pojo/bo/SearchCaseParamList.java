/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月7日 下午2:02:24
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月7日 下午2:02:24
 */

import com.azz.core.common.QueryPage;

import lombok.Data;

@Data
public class SearchCaseParamList extends QueryPage {
    private Long assortmentId;
    
    // 参数编号或者名称
    private String param;
}

