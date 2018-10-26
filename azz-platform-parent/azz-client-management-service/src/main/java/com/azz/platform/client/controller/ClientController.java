/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午6:19:44
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.client.pojo.ClientUser;
import com.azz.platform.client.pojo.bo.AuditParam;
import com.azz.platform.client.pojo.bo.SearchClientManagerParam;
import com.azz.platform.client.pojo.bo.SearchClientMerchantManagerParam;
import com.azz.platform.client.pojo.bo.SearchClientParam;
import com.azz.platform.client.pojo.vo.ClientAccountInfo;
import com.azz.platform.client.pojo.vo.ClientCertification;
import com.azz.platform.client.pojo.vo.ClientCompanyInfo;
import com.azz.platform.client.pojo.vo.ClientInfo;
import com.azz.platform.client.pojo.vo.ClientMerchantInfo;
import com.azz.platform.client.service.AuditService;
import com.azz.platform.client.service.ClientService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月25日 上午12:03:12
 */
@RestController
@RequestMapping("/azz/api/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AuditService auditService;
	
	/**
	 * <p>获取客户审批列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年10月25日 下午2:10:45
	 */
	@RequestMapping(value="searchClientCertificationList",method=RequestMethod.POST)
	public JsonResult<Pagination<ClientCertification>> searchClientList(@RequestBody SearchClientParam param) {
	    return clientService.searchClientList(param);
	}

	/**
	 * <p>平台 客户管理</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月25日 下午3:22:40
	 */
	@RequestMapping(value="selectClientUserList",method=RequestMethod.POST)
	 public JsonResult<Pagination<ClientUser>> selectClientUserList(@RequestBody SearchClientManagerParam param) {
		return clientService.selectClientUserList(param);
	}
	
	
	/**
	 * <p>平台 客户管理 企业用户</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月25日 下午3:22:40
	 */
	@RequestMapping(value="selectClientMerchantList",method=RequestMethod.POST)
	 public JsonResult<Pagination<ClientMerchantInfo>> selectClientMerchantList(@RequestBody SearchClientMerchantManagerParam param) {
		return clientService.selectClientMerchantList(param);
	}
	
	
	/**
	 * <p>客户管理 启用 禁用  1启用 0 禁用</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月24日 下午7:35:48
	 */
	@RequestMapping(value="updateClientUserStatus",method=RequestMethod.POST)
	 public JsonResult<String> updateClientUserStatus(String code,Integer status) {
		return clientService.updateClientUserStatus(code,status);
	}
	
	/**
	 * <p>客户管理 账户详情</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月24日 下午7:35:48
	 */
	@RequestMapping(value="selectClientUserInfo",method=RequestMethod.POST)
	 public JsonResult<ClientAccountInfo> selectClientUserInfo(String code) {
		return clientService.selectClientUserInfo(code);
	}
	
	/**
	 * <p>客户管理 企业详情</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月24日 下午7:35:48
	 */
	@RequestMapping(value="selectClientCompanyDetail",method=RequestMethod.POST)
	 public JsonResult<ClientCompanyInfo> selectClientCompanyDetail(String code) {
		return clientService.selectClientCompanyDetail(code);
	}
	
	/**
	 * <p>根据用户编码获取详情</p>
	 * @param code
	 * @return
	 * @author 彭斌  2018年10月25日 下午2:10:48
	 */
	@RequestMapping(value="searchClientInfo",method=RequestMethod.POST)
	public JsonResult<ClientInfo> searchClientInfo(@RequestParam("companyCode") String companyCode) {
	    return clientService.selectDetailsClientInfo(companyCode);
	}
	
	/**
	 * <p>审核客户信息</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年10月25日 下午4:32:43
	 */
	@RequestMapping(value="auditClient",method=RequestMethod.POST)
	public JsonResult<String> auditClient(@RequestBody AuditParam param) {
	    return auditService.auditClient(param);
	}
}

