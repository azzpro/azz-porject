/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.api.platform.PlatformClientInvoiceService;
import com.azz.order.platform.bo.AuditInvoiceStatusParam;
import com.azz.order.platform.bo.SearchInvoiceListParam;
import com.azz.order.platform.vo.PlatformClientInvoiceApplyDetail;
import com.azz.order.platform.vo.PlatformClientInvoiceList;
import com.azz.utils.WebUtils;

/**
 * <P>平台发票管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月22日 下午6:43:21
 */
@RestController
@RequestMapping("/azz/api/platform/client/invoice")
public class InvoiceController {

	private static final Logger LOG = LoggerFactory.getLogger(InvoiceController.class);

	@Autowired
	PlatformClientInvoiceService platformClientInvoiceService;
	
	/**
	 * <p>获取平台端客户发票列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月22日 下午6:43:16
	 */
	@RequestMapping("/getPlatformClientInvoiceList")
	public JsonResult<Pagination<PlatformClientInvoiceList>> getPlatformClientInvoiceList(SearchInvoiceListParam param){
	    return platformClientInvoiceService.getPlatformClientInvoiceList(param);
	}
	
	/**
     * <p>平台端申请详情</p>
     * @param clientOrderCode
     * @return
     * @author 彭斌  2018年11月22日 下午3:00:48
     */
    @RequestMapping("/getPlatformClientInvoiceOrderDetail")
    JsonResult<PlatformClientInvoiceApplyDetail> getPlatformClientInvoiceOrderDetail(@RequestParam("clientOrderCode") String clientOrderCode){
        return platformClientInvoiceService.getPlatformClientInvoiceOrderDetail(clientOrderCode);
    }
    
    /**
     * <p>审核操作</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月22日 下午6:47:35
     */
    @RequestMapping("/operationInvoiceStatus")
    public JsonResult<String> operationInvoiceStatus(AuditInvoiceStatusParam param){
        param.setPlatformUserCode(WebUtils.getLoginUser().getUserInfo().getUserCode());
        return platformClientInvoiceService.operationInvoiceStatus(param);
    }
}
