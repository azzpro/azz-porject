/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:30:18
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.api.merchant;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.merchant.pojo.bo.ConfirmBillingParam;
import com.azz.order.merchant.pojo.bo.OrderInvoiceParam;
import com.azz.order.merchant.pojo.bo.SearchInvoiceListParam;
import com.azz.order.merchant.pojo.vo.MerchantInvoiceDetail;
import com.azz.order.merchant.pojo.vo.MerchantInvoiceList;

@FeignClient("azz-order-service")
public interface MerchantInvoiceService {
    
    /**
     * <p>获取商户发票管理列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月22日 下午8:29:32
     */
    @RequestMapping(value="/azz/api/merchant/invoice/getMerchantInvoiceList",method=RequestMethod.POST)
    JsonResult<Pagination<MerchantInvoiceList>> getMerchantInvoiceList(@RequestBody SearchInvoiceListParam param);
    
    /**
     * <p>获取商户发票详情</p>
     * @param merchantOrderCode
     * @return
     * @author 彭斌  2018年11月23日 下午2:38:02
     */
    @RequestMapping(value="/azz/api/merchant/invoice/getMerchantInvoiceDetail",method=RequestMethod.POST)
    JsonResult<MerchantInvoiceDetail> getMerchantInvoiceDetail(@RequestParam("merchantOrderCode")String merchantOrderCode);

    /**
     * <p>确认开票申请</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月23日 下午7:08:15
     */
    @RequestMapping(value="/azz/api/merchant/invoice/confirmBillingApplication",method=RequestMethod.POST)
    public JsonResult<String> confirmBillingApplication(@RequestBody ConfirmBillingParam param);
    
    /**
     * <p>订单开票</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月23日 下午7:06:21
     */
    @RequestMapping(value="/azz/api/merchant/invoice/orderInvoice",method=RequestMethod.POST)
    public JsonResult<String> orderInvoice(@RequestBody OrderInvoiceParam param);
}

