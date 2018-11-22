/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.order.client.pojo.vo.ClientOrderItemInfo;
import com.azz.order.client.service.ClientInvoiceService;

/**
 * <P>客户发票管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月20日 下午3:40:17
 */
@RestController
@RequestMapping("/azz/api/client/invoice")
public class ClientInvoiceController {
	
	@Autowired
	private ClientInvoiceService clientInvoiceService;
	
	/**
	 * <p>查询客户发票管理列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月20日 下午3:48:30
	 */
	@RequestMapping("/getClientInvoiceList")
	public JsonResult<Pagination<ClientInvoiceList>> getClientInvoiceList(@RequestBody SearchClientInvoiceParam param){
		return clientInvoiceService.getClientInvoiceList(param);
	}
	
	/**
	 * <p>根据发票类型和客户编码获取所有发票模板信息</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月20日 下午3:48:33
	 */
	@RequestMapping("/getInvoiceTemplateList")
	public JsonResult<List<ClientInvoiceTemplateList>> getInvoiceTemplateList(@RequestBody SearchInvoiceTemplateParam param){
	    return clientInvoiceService.getInvoiceTemplateList(param);
	}
	
	/**
	 * <p>获取新增开票申请列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月20日 下午3:48:37
	 */
	@RequestMapping("/getInvoiceClientList")
	public JsonResult<Pagination<ClientAddInvoice>> getInvoiceClientList(@RequestBody SearchAddInvoiceApplyParam param){
	    return clientInvoiceService.getInvoiceClientList(param);
	}
	
	/**
	 * <p>新增发票申请</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月20日 下午3:48:40
	 */
	@RequestMapping("/addInvoiceApply")
	public JsonResult<String> addInvoiceApply(@RequestBody AddInvoiceApplyParam param){
	    return clientInvoiceService.addInvoiceApply(param);
	}
	
	/**
	 * <p>获取客户发票详情</p>
	 * @param invoiceId
	 * @return
	 * @author 彭斌  2018年11月20日 下午3:48:43
	 */
	@RequestMapping("/getClientInvoiceTemplateDetail")
	public JsonResult<ClientInvoiceTemplate> getClientInvoiceTemplateDetail(Long invoiceId){
	    return clientInvoiceService.getClientInvoiceTemplateDetail(invoiceId);
	}
	
	/**
	 * <p>发票模板管理新增，编辑操作</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月20日 下午3:48:46
	 */
	@RequestMapping("/addEditInvoiceTemplate")
	public JsonResult<String> addEditInvoiceTemplate(@RequestBody AddEditInvoiceTemplateParam param){
	    return clientInvoiceService.addEditInvoiceTemplate(param);
	}
	
	/**
	 * <p>删除发票模板</p>
	 * @param id
	 * @param userCode
	 * @return
	 * @author 彭斌  2018年11月21日 上午10:28:16
	 */
	@RequestMapping("/delInvoiceTemplate")
	public JsonResult<String> delInvoiceTemplate(Long id,String userCode){
	    return clientInvoiceService.delInvoiceTemplate(id, userCode);
	}
	
	/**
	 * <p>申请详情</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月21日 下午1:59:05
	 */
	@RequestMapping("/getClientInvoiceApplyDetail")
	public JsonResult<ClientInvoiceApplyDetail> getClientInvoiceApplyDetail(@RequestBody SearchAddInvoiceApplyParam param){
	    return clientInvoiceService.getClientInvoiceApplyDetail(param);
	}
	
	/**
	 * <p>获取客户订单明细</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月21日 下午4:26:40
	 */
	@RequestMapping("/getClientOrderItems")
	public JsonResult<List<ClientOrderItemInfo>> getClientOrderItems(@RequestBody SearchClientOrderParam param){
	    return clientInvoiceService.getClientOrderItems(param);
	}
}

