/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月15日 下午2:27:14
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.api.platform;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.platform.bo.AuditInvoiceStatusParam;
import com.azz.order.platform.bo.SearchInvoiceListParam;
import com.azz.order.platform.vo.PlatformClientInvoiceApplyDetail;
import com.azz.order.platform.vo.PlatformClientInvoiceList;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月22日 下午12:41:30
 */
@FeignClient("azz-order-service")
public interface PlatformClientInvoiceService {
	
	/**
	 * <p>获取平台端客户发票列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月22日 下午12:42:38
	 */
	@RequestMapping("/azz/api/platform/client/invoice/getPlatformClientInvoiceList")
	JsonResult<Pagination<PlatformClientInvoiceList>> getPlatformClientInvoiceList(@RequestBody SearchInvoiceListParam param);
	
	/**
	 * <p>平台端申请详情</p>
	 * @param clientOrderCode
	 * @return
	 * @author 彭斌  2018年11月22日 下午3:00:48
	 */
	@RequestMapping("/azz/api/platform/client/invoice/getPlatformClientInvoiceOrderDetail")
	JsonResult<PlatformClientInvoiceApplyDetail> getPlatformClientInvoiceOrderDetail(@RequestParam("clientOrderCode") String clientOrderCode);

	/**
	 * <p>审核客户发票操作</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月22日 下午6:27:55
	 */
	@RequestMapping("/azz/api/platform/client/invoice/operationInvoiceStatus")
	JsonResult<String> operationInvoiceStatus(@RequestBody AuditInvoiceStatusParam param);
}

