/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月14日 下午12:19:54
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.pojo.bo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月14日 下午12:19:54
 */
@Data
public class SearchOrderStatusParam {
    /**
     * 关联商户订单id
     *
     * @mbg.generated
     */
    private Long merchantOrderId;

    /**
     * 订单状态
     *
     * @mbg.generated
     */
    private Integer merchantStatusId;

}

