/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月23日 下午5:28:17
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月23日 下午5:28:17
 */
@Data
public class ConfirmBillingParam {
    private String merchantUserCode;
    @NotBlank(message = "参数无效")
    private String merchantOrderCode;
    
}

