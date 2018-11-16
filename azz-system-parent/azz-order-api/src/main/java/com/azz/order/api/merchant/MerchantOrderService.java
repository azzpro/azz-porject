/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:30:18
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.api.merchant;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.merchant.pojo.bo.EditOrderStatus;
import com.azz.order.merchant.pojo.bo.SearchOrderDetailParam;
import com.azz.order.merchant.pojo.bo.SearchOrderListParam;
import com.azz.order.merchant.pojo.vo.ExpressCompanyInfo;
import com.azz.order.merchant.pojo.vo.OrderDetail;
import com.azz.order.merchant.pojo.vo.OrderList;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月15日 下午2:09:21
 */
@FeignClient("azz-order-service")
public interface MerchantOrderService {
    
    /**
     * <p>查询商户订单管理</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月13日 下午5:07:04
     */
    @RequestMapping(value="/azz/api/merchant/order/getMerchantOrderList",method=RequestMethod.POST)
    JsonResult<Pagination<OrderList>> getMerchantOrderList(@RequestBody SearchOrderListParam param);
    
    /**
     * <p>获取商户订单详情</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月15日 下午2:25:48
     */
    @RequestMapping(value="/azz/api/merchant/order/getMerchantOrderDetail",method=RequestMethod.POST)
    JsonResult<OrderDetail> getMerchantOrderDetail(@RequestBody SearchOrderDetailParam param);
    
    /**
     * <p>订单流转状态变更</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月15日 下午2:26:37
     */
    @RequestMapping(value="/azz/api/merchant/order/editMerchantOrderStatus",method=RequestMethod.POST)
    JsonResult<String> editMerchantOrderStatus(@RequestBody EditOrderStatus param);
    
    /**
     * <p>获取所有的快递公司信息</p>
     * @return
     * @author 彭斌  2018年11月16日 上午9:23:43
     */
    @RequestMapping(value="/azz/api/merchant/order/getAllExpressCompany",method=RequestMethod.POST)
    JsonResult<List<ExpressCompanyInfo>> getAllExpressCompany();
}

