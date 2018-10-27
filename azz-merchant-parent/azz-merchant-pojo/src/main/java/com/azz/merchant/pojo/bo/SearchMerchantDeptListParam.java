/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月26日 下午8:15:52
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月26日 下午8:15:52
 */
@Data
public class SearchMerchantDeptListParam {
    @NotBlank(message = "商户编码不允许为空")
    private String merchantCode;
    private String deptNameCode;
}

