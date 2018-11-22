/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午4:20:14
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.sequence.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月24日 下午4:20:14
 */
@FeignClient("azz-sequence-service")
public interface DbSequenceService {
	
	@RequestMapping(value="/azz/api/sequence/getPlatEmployeeNumber",method=RequestMethod.GET)
	String getPlatEmployeeNumber();
	
	@RequestMapping(value="/azz/api/sequence/getPlatDepartmentNumber",method=RequestMethod.GET)
	String getPlatDepartmentNumber();
	
	@RequestMapping(value="/azz/api/sequence/getPlatPowerNumber",method=RequestMethod.GET)
	String getPlatPowerNumber();
	
	@RequestMapping(value="/azz/api/sequence/getMerchantTenantNumber",method=RequestMethod.GET)
	String getMerchantTenantNumber();
	
	@RequestMapping(value="/azz/api/sequence/getMerchantEmployeeNumber",method=RequestMethod.GET)
	String getMerchantEmployeeNumber();
	
	@RequestMapping(value="/azz/api/sequence/getMerchantDepartmentNumber",method=RequestMethod.GET)
	String getMerchantDepartmentNumber();
	
	@RequestMapping(value="/azz/api/sequence/getMerchantPowerNumber",method=RequestMethod.GET)
    String getMerchantPowerNumber();
	
	@RequestMapping(value="/azz/api/sequence/getClientInvoiceApplyNumber",method=RequestMethod.GET)
	String getClientInvoiceApplyNumber();
	
	@RequestMapping(value="/azz/api/sequence/getMerchantInvoiceApplyNumber",method=RequestMethod.GET)
    String getMerchantInvoiceApplyNumber();
}

