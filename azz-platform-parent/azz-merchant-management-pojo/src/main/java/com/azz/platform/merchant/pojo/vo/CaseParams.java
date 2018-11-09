/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月6日 下午3:28:44
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.vo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月6日 下午3:28:44
 */
@Data
public class CaseParams {
    
    private Long id;
    // 参数编号
    private String paramCode;
    // 参数名称
    private String paramName;
    // 参数值
    private String paramValue;
    // 参数项类型 1 下拉 2填写
    private Integer paramsType;
}

