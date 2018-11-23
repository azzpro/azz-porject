/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月15日 下午3:05:46 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.merchant.utils.WebUtils;
import com.azz.order.api.merchant.MerchantInvoiceService;
import com.azz.order.merchant.pojo.bo.ConfirmBillingParam;
import com.azz.order.merchant.pojo.bo.OrderInvoiceParam;
import com.azz.order.merchant.pojo.bo.SearchInvoiceListParam;
import com.azz.order.merchant.pojo.vo.MerchantInvoiceDetail;
import com.azz.order.merchant.pojo.vo.MerchantInvoiceList;

/**
 * <P>商户端发票管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月22日 下午8:31:21
 */
@RestController
@RequestMapping("/azz/api/merchant/invoice")
public class InvoiceController {


    @Autowired
    MerchantInvoiceService merchantInvoiceService;

    /**
     * <p>获取商户发票管理列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月22日 下午8:33:20
     */
    @RequestMapping("/getMerchantInvoiceList")
    public JsonResult<Pagination<MerchantInvoiceList>> getMerchantInvoiceList(SearchInvoiceListParam param) {
        param.setMerchantId(WebUtils.getLoginMerchanUser().getMerchantUserInfo().getMerchantId());
        return merchantInvoiceService.getMerchantInvoiceList(param);
    }

    /**
     * <p>获取商户发票详情</p>
     * @param merchantOrderCode
     * @return
     * @author 彭斌  2018年11月23日 下午3:51:26
     */
    @RequestMapping("/getMerchantInvoiceDetail")
    public JsonResult<MerchantInvoiceDetail> getMerchantInvoiceDetail(String merchantOrderCode){
        return merchantInvoiceService.getMerchantInvoiceDetail(merchantOrderCode);
    }
    
    /**
     * 
     * <p>确认开票申请</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月23日 下午7:09:02
     */
    @RequestMapping("/confirmBillingApplication")
    public JsonResult<String> confirmBillingApplication(ConfirmBillingParam param){
        param.setMerchantUserCode(WebUtils.getLoginMerchanUser().getMerchantUserInfo().getMerchantUserCode());
        return merchantInvoiceService.confirmBillingApplication(param);
    }
    
    /**
     * <p>订单开票</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月23日 下午7:06:21
     */
    @RequestMapping("/orderInvoice")
    public JsonResult<String> orderInvoice(OrderInvoiceParam param){
        param.setMerchantUserCode(WebUtils.getLoginMerchanUser().getMerchantUserInfo().getMerchantUserCode());
        return merchantInvoiceService.orderInvoice(param);
    }
}
