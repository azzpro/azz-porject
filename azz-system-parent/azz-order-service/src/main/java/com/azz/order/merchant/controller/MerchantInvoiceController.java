/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.merchant.pojo.bo.ConfirmBillingParam;
import com.azz.order.merchant.pojo.bo.OrderInvoiceParam;
import com.azz.order.merchant.pojo.bo.SearchInvoiceListParam;
import com.azz.order.merchant.pojo.vo.MerchantInvoiceDetail;
import com.azz.order.merchant.pojo.vo.MerchantInvoiceList;
import com.azz.order.merchant.service.MerchantInvoiceService;

@RestController
@RequestMapping("/azz/api/merchant/invoice")
public class MerchantInvoiceController {
	
	@Autowired
	private MerchantInvoiceService merchantInvoiceService;
	
	/**
	 * <p>获取商户发票列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月22日 下午8:27:54
	 */
	@RequestMapping("/getMerchantInvoiceList")
	public JsonResult<Pagination<MerchantInvoiceList>> getMerchantInvoiceList(@RequestBody SearchInvoiceListParam param){
	    return merchantInvoiceService.getMerchantInvoiceList(param);
	}

	/**
	 * <p>获取商户发票详情</p>
	 * @param merchantOrderCode
	 * @return
	 * @author 彭斌  2018年11月23日 下午2:36:27
	 */
	@RequestMapping("/getMerchantInvoiceDetail")
	public JsonResult<MerchantInvoiceDetail> getMerchantInvoiceDetail(String merchantOrderCode){
	    return merchantInvoiceService.getMerchantInvoiceDetail(merchantOrderCode);
	}
	
	/**
	 * <p>确认开票申请</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月23日 下午6:44:16
	 */
	@RequestMapping("/confirmBillingApplication")
	public JsonResult<String> confirmBillingApplication(@RequestBody ConfirmBillingParam param){
	    return merchantInvoiceService.confirmBillingApplication(param);
	}
	
	/**
	 * <p>订单开票</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月23日 下午7:06:21
	 */
	@RequestMapping("/orderInvoice")
	public JsonResult<String> orderInvoice(@RequestBody OrderInvoiceParam param){
	    return merchantInvoiceService.orderInvoice(param);
	}
}


