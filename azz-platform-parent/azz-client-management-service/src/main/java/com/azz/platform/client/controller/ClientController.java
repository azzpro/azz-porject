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
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.client.pojo.bo.SearchClientParam;
import com.azz.platform.client.pojo.vo.ClientCertification;
import com.azz.platform.client.pojo.vo.ClientInfo;
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
	@RequestMapping(value="searchClientList",method=RequestMethod.POST)
	public JsonResult<Pagination<ClientCertification>> searchClientList(@RequestBody SearchClientParam param) {
	    return clientService.searchMerchantList(param);
	}

	/**
	 * <p>根据用户编码获取详情</p>
	 * @param code
	 * @return
	 * @author 彭斌  2018年10月25日 下午2:10:48
	 */
	public JsonResult<ClientInfo> searchClientInfo(String clientUserCode){
	    return clientService.selectDetailsClientInfo(clientUserCode);
	}
}

