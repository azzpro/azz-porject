/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:30:18
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.api.platform;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.merchant.pojo.bo.SearchOrderDetailParam;
import com.azz.order.merchant.pojo.vo.OrderDetail;
import com.azz.order.platform.bo.SearchMerchantOrderParam;
import com.azz.order.platform.vo.MerchantOrderList;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月15日 下午2:09:21
 */
@FeignClient("azz-system-service")
public interface MerchantOrderService {
    
    /**
     * <p>查询商户订单管理</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月13日 下午5:07:04
     */
    @RequestMapping(value="/azz/api/platform/merchant/order/getPlatformMerchantOrderList",method=RequestMethod.POST)
    public JsonResult<Pagination<MerchantOrderList>> getPlatformMerchantOrderList(SearchMerchantOrderParam param);
    
    /**
     * <p>获取平台端商户订单详情</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月15日 下午2:25:48
     */
    @RequestMapping(value="/azz/api/platform/merchant/order/getMerchantOrderDetail",method=RequestMethod.POST)
    public JsonResult<OrderDetail> getMerchantOrderDetail(SearchOrderDetailParam param);
    
}

