/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午3:19:35
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.api.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.order.client.pojo.RetBean;
import com.azz.order.client.pojo.bo.BankBranch;
import com.azz.order.client.pojo.bo.EnterprisereginfoCopy;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午3:19:35
 */
@FeignClient("azz-order-service")
public interface RegYeeMerchantService {
	
	/**
	 * 子商户入网注册 【企业】
	 * @param po
	 * @return
	 */
	@RequestMapping(value="/azz/api/merchant/regMerchantYeeEnterpriseAccount",method=RequestMethod.POST)
	public Map<String,String> regMerchantYeeEnterpriseAccount(@RequestBody EnterprisereginfoCopy po);
	
	/**
	 * 子商户入网注册【企业】回调
	 * @param request
	 * @param po
	 * @return
	 */
	@RequestMapping("regEnterpriseNotify")
	public JsonResult<RetBean> regEnterpriseNotify(@RequestParam("responseMsg") String responseMsg,@RequestParam("customerId") String customerId);
	/**
	 * 获取支行信息
	 * @param request
	 * @param po
	 * @return
	 */
	@RequestMapping("getBankBranchInfo")
	public Map<String,String> getBankBranchInfo(@RequestBody BankBranch bb);
		
}

