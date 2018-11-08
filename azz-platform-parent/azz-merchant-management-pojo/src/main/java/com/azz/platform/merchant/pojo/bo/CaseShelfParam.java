/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月8日 上午11:13:13
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月8日 上午11:13:13
 */
@Data
public class CaseShelfParam {
    
    @NotBlank(message = "方案编码不允许为空")
    private String caseCode;
    
    @NotBlank(message = "方案状态不允许为空")
    private Integer stauts;
    
    private String modifier;
}

