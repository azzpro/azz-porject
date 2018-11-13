/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月13日 上午11:00:25
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.pojo.bo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月13日 上午11:00:25
 */
@Data
public class SearchOrderDetailParam {
    private Long merchantId;
    private String orderCode;
}

