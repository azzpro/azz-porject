/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午3:09:13
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.PayConstants;
import com.azz.order.client.pojo.ClientPay;
import com.azz.order.client.pojo.RetBean;
import com.azz.order.client.pojo.bo.BankBranch;
import com.azz.order.client.pojo.bo.Enterprisereginfo;
import com.azz.order.client.pojo.bo.EnterprisereginfoCopy;
import com.azz.order.client.pojo.bo.PageOrder;
import com.azz.order.client.pojo.bo.PayList;
import com.azz.order.client.pojo.bo.Personreginfo;
import com.azz.order.client.service.ClientPayService;
import com.azz.util.JSR303ValidateUtils;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午3:09:13
 */
@RestController
@RequestMapping("/azz/api/merchant")
public class RegYeeMerchantController {
	
	@Autowired
	private ClientPayService pps;
	/**
	 * 子商户入网注册【企业】
	 * @param request
	 * @param po
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("regMerchantYeeEnterpriseAccount")
	public Map<String,String> regMerchantYeeEnterpriseAccount(@RequestBody EnterprisereginfoCopy po) throws Exception{
		return pps.regMerchantYeeEnterpriseAccount(po);
	}
	
	
	/**
	 * 子商户入网注册【企业】 回调
	 * @param request
	 * @param po
	 * @return
	 */
	@RequestMapping("regEnterpriseNotify")
	public void regEnterpriseNotify(){
	}
	
	
	/**
	 * 获取支行信息
	 * @param request
	 * @param po
	 * @return
	 */
	@RequestMapping("getBankBranchInfo")
	public Map<String,String> getBankBranchInfo(@RequestBody BankBranch bb){
		return pps.getBankBranchInfo(bb);
	}
	
}

