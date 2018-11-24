/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月22日 下午8:00:06
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.pojo.bo;

import com.azz.core.common.QueryPage;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月22日 下午8:00:06
 */
@Data
public class SearchInvoiceListParam extends QueryPage{
    private Long merchantId;
    private Integer status;
    // 申请编号或者订单编号
    private String param;
}

