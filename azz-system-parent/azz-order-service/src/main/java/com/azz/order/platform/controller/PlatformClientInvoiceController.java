/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.platform.bo.AuditInvoiceStatusParam;
import com.azz.order.platform.bo.SearchInvoiceListParam;
import com.azz.order.platform.service.PlatformInvoiceService;
import com.azz.order.platform.vo.PlatformClientInvoiceApplyDetail;
import com.azz.order.platform.vo.PlatformClientInvoiceList;

/**
 * <P>平台端发票管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月13日 下午6:10:27
 */
@RestController
@RequestMapping("/azz/api/platform/client/invoice")
public class PlatformClientInvoiceController {
	
	@Autowired
	private PlatformInvoiceService platformInvoiceService;
	
	/**
	 * <p>获取平台端客户发票列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月22日 下午3:46:26
	 */
	@RequestMapping("/getPlatformClientInvoiceList")
	public JsonResult<Pagination<PlatformClientInvoiceList>> getPlatformClientInvoiceList(@RequestBody SearchInvoiceListParam param){
		return platformInvoiceService.getPlatformClientInvoiceList(param);
	}
	
	/**
	 * <p>平台端申请详情</p>
	 * @param clientOrderCode
	 * @return
	 * @author 彭斌  2018年11月22日 下午3:47:40
	 */
	@RequestMapping("/getPlatformClientInvoiceOrderDetail")
	public JsonResult<PlatformClientInvoiceApplyDetail> getPlatformClientInvoiceDetail(@RequestParam("clientOrderCode") String clientOrderCode){
	    return platformInvoiceService.getPlatformClientInvoiceDetail(clientOrderCode);
	}
	
	/**
	 * <p>平台端审核客户发票信息</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月22日 下午5:45:09
	 */
	@RequestMapping("/operationInvoiceStatus")
	public JsonResult<String> operationInvoiceStatus(@RequestBody AuditInvoiceStatusParam param){
	    return platformInvoiceService.operationInvoiceStatus(param);
	}
}

