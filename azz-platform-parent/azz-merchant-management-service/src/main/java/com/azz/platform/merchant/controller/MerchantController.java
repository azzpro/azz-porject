/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午6:19:44
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.pojo.bo.AuditParam;
import com.azz.platform.merchant.pojo.bo.SearchMerchantParam;
import com.azz.platform.merchant.pojo.vo.MerchantApproval;
import com.azz.platform.merchant.pojo.vo.MerchantInfo;
import com.azz.platform.merchant.service.AuditService;
import com.azz.platform.merchant.service.MerchantService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月24日 下午6:19:44
 */
@RestController
@RequestMapping("/azz/api/merchant")
public class MerchantController {
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private AuditService auditService;
	
	@RequestMapping(value="searchMerchantList",method=RequestMethod.POST)
	 public JsonResult<Pagination<MerchantApproval>> searchMerchantList(@RequestBody SearchMerchantParam param) {
		return merchantService.searchMerchantList(param);
	}

	@RequestMapping(value="searchMerchantInfo",method=RequestMethod.GET)
    public JsonResult<MerchantInfo> searchMerchantInfo(String merchantCode) {
    	return merchantService.searchMerchantInfo(merchantCode);
    }
	
	@RequestMapping(value="auditEnterprise",method=RequestMethod.POST)
    public JsonResult<String> auditEnterprise(@RequestBody AuditParam param){
		return auditService.auditEnterprise(param);
	}
}

