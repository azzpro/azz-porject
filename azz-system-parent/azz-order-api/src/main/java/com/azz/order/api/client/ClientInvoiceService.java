/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:30:18
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.api.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.client.pojo.ClientInvoiceTemplate;
import com.azz.order.client.pojo.bo.AddEditInvoiceTemplateParam;
import com.azz.order.client.pojo.bo.AddInvoiceApplyParam;
import com.azz.order.client.pojo.bo.SearchAddInvoiceApplyParam;
import com.azz.order.client.pojo.bo.SearchClientInvoiceParam;
import com.azz.order.client.pojo.bo.SearchClientOrderParam;
import com.azz.order.client.pojo.bo.SearchInvoiceTemplateParam;
import com.azz.order.client.pojo.vo.ClientAddInvoice;
import com.azz.order.client.pojo.vo.ClientInvoiceApplyDetail;
import com.azz.order.client.pojo.vo.ClientInvoiceList;
import com.azz.order.client.pojo.vo.ClientInvoiceTemplateList;
import com.azz.order.client.pojo.vo.ClientOrderItemInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月15日 下午2:09:21
 */
@FeignClient("azz-order-service")
public interface ClientInvoiceService {
    
    /**
     * <p>查询客户发票管理列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月20日 下午4:02:43
     */
	@RequestMapping("/azz/api/client/invoice/getClientInvoiceList")
	JsonResult<Pagination<ClientInvoiceList>> getClientInvoiceList(@RequestBody SearchClientInvoiceParam param);
	
	/**
	 * <p>根据发票类型和客户编码获取所有发票模板信息</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月20日 下午4:02:53
	 */
	@RequestMapping("/azz/api/client/invoice/getInvoiceTemplateList")
	JsonResult<List<ClientInvoiceTemplateList>> getInvoiceTemplateList(@RequestBody SearchInvoiceTemplateParam param);
	
	/**
	 * <p>获取新增开票申请列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月20日 下午4:03:00
	 */
	@RequestMapping("/azz/api/client/invoice/getInvoiceClientList")
	JsonResult<Pagination<ClientAddInvoice>> getInvoiceClientList(@RequestBody SearchAddInvoiceApplyParam param);
	
	/**
	 * <p>新增发票申请</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月20日 下午4:03:07
	 */
	@RequestMapping("/azz/api/client/invoice/addInvoiceApply")
	JsonResult<String> addInvoiceApply(@RequestBody AddInvoiceApplyParam param);
	
	/**
	 * <p>获取客户发票详情</p>
	 * @param invoiceId
	 * @return
	 * @author 彭斌  2018年11月20日 下午4:03:17
	 */
	@RequestMapping("/azz/api/client/invoice/getClientInvoiceTemplateDetail")
	JsonResult<ClientInvoiceTemplate> getClientInvoiceTemplateDetail(@RequestParam("invoiceId")Long invoiceId);
	
	/**
	 * <p>发票模板管理新增，编辑操作</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月20日 下午4:03:24
	 */
	@RequestMapping("/azz/api/client/invoice/addEditInvoiceTemplate")
	JsonResult<String> addEditInvoiceTemplate(@RequestBody AddEditInvoiceTemplateParam param);
	
	/**
	 * <p>删除发票模板</p>
	 * @param id
	 * @param userCode
	 * @return
	 * @author 彭斌  2018年11月21日 上午10:29:10
	 */
	@RequestMapping("/azz/api/client/invoice/delInvoiceTemplate")
	JsonResult<String> delInvoiceTemplate(@RequestParam("id") Long id,@RequestParam("userCode") String userCode);
	
	/**
	 * <p>申请详情</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月21日 下午2:04:33
	 */
	@RequestMapping("/azz/api/client/invoice/getClientInvoiceApplyDetail")
	JsonResult<ClientInvoiceApplyDetail> getClientInvoiceApplyDetail(@RequestBody SearchAddInvoiceApplyParam param);

	/**
	 * <p>获取客户订单明细</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月21日 下午4:25:59
	 */
	@RequestMapping("/azz/api/client/invoice/getClientOrderItems")
	JsonResult<List<ClientOrderItemInfo>> getClientOrderItems(@RequestBody SearchClientOrderParam param);
}

