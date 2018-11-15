/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:30:18
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月15日 下午2:09:21
 */
@FeignClient("azz-merchant-service")
public interface OrderService {
    
    /**
     * <p>查询商户订单管理</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月13日 下午5:07:04
     */
    //public JsonResult<Pagination<OrderList>> getMerchantOrderList(@RequestBody SearchOrderListParam param);
    
}

