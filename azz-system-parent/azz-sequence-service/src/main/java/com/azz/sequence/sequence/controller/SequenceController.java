/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午4:32:29
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.sequence.sequence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azz.sequence.sequence.service.DbSequenceService;
import com.azz.sequence.sequence.service.RandomSequenceService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月24日 下午4:32:29
 */
@RestController
@RequestMapping("/azz/api/sequence")
public class SequenceController {

	@Autowired
	private DbSequenceService dbSequenceService;
	
	@Autowired
	private RandomSequenceService randomSequenceService;
	
	
	/**
	 * <p>客户端 客户编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="getClientCustomerNumber",method=RequestMethod.GET)
	public String getClientCustomerNumber() {
		return "IF"+randomSequenceService.getSequence();
	}
	
	/**
	 * <p>客户端 企业编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getClientCompanyNumber",method=RequestMethod.GET)
	public String getClientCompanyNumber() {
		return "IG"+randomSequenceService.getSequence();
	}
	
	/**
	 * <p>客户端 员工编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getClientEmployeeNumber",method=RequestMethod.GET)
	public String getClientEmployeeNumber() {
		return "IJ"+randomSequenceService.getSequence();
	}
	
	/**
	 * <p>客户端 部门编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getClientDepartmentNumber",method=RequestMethod.GET)
	public String getClientDepartmentNumber() {
		return "IK"+randomSequenceService.getSequence();
	}
	
	/**
	 * <p>客户端 权限编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getClientPowerNumber",method=RequestMethod.GET)
	public String getClientPowerNumber() {
		return "IM"+randomSequenceService.getSequence();
	}
	
	/**
	 * <p>平台端  员工编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getPlatEmployeeNumber",method=RequestMethod.GET)
	public String getPlatEmployeeNumber() {
		return "IA"+dbSequenceService.getSequence();
	}
	
	/**
	 * <p>平台端  部门编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getPlatDepartmentNumber",method=RequestMethod.GET)
	public String getPlatDepartmentNumber() {
		return "IB"+dbSequenceService.getSequence();
	}
	
	/**
	 * <p>平台端  部门编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getPlatPowerNumber",method=RequestMethod.GET)
	public String getPlatPowerNumber() {
		return "IH"+dbSequenceService.getSequence();
	}
	
	
	/**
	 * <p>商户端  员工编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getMerchantTenantNumber",method=RequestMethod.GET)
	public String getMerchantTenantNumber() {
		return "IC"+dbSequenceService.getSequence();
	}
	
	/**
	 * <p>商户端  员工编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getMerchantEmployeeNumber",method=RequestMethod.GET)
	public String getMerchantEmployeeNumber() {
		return "ID"+dbSequenceService.getSequence();
	}
	
	/**
	 * <p>商户端  部门编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getMerchantDepartmentNumber",method=RequestMethod.GET)
	public String getMerchantDepartmentNumber() {
		return "IE"+dbSequenceService.getSequence();
	}
	
	/**
	 * <p>商户端  权限编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getMerchantPowerNumber",method=RequestMethod.GET)
	public String getMerchantPowerNumber() {
		return "IL"+dbSequenceService.getSequence();
	}
	
	
}

