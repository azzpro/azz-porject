package com.azz.platform.client.api;
/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午3:54:14
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.client.pojo.ClientUser;
import com.azz.platform.client.pojo.bo.SearchClientManagerParam;
import com.azz.platform.client.pojo.bo.SearchClientMerchantManagerParam;
import com.azz.platform.client.pojo.bo.SearchClientParam;
import com.azz.platform.client.pojo.vo.ClientAccountInfo;
import com.azz.platform.client.pojo.vo.ClientCertification;
import com.azz.platform.client.pojo.vo.ClientCompanyEmployee;
import com.azz.platform.client.pojo.vo.ClientCompanyInfo;
import com.azz.platform.client.pojo.vo.ClientInfo;
import com.azz.platform.client.pojo.vo.ClientMerchantInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月22日 下午3:54:14
 */
@FeignClient("azz-client-management-service")
public interface ClientService {
    
	
	/**
	 * <p>平台客户管理</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月25日 下午3:20:44
	 */
	@RequestMapping(value="/azz/api/client/selectClientUserList",method=RequestMethod.POST)
	public JsonResult<Pagination<ClientUser>> selectClientUserList(@RequestBody SearchClientManagerParam param);
	
	/**
     * <p>平台端的客户 启用 禁用</p>
     * @param merchantCode
     * @return
     * @author 刘建麟  2018年10月23日 上午10:10:06
     */
    @PostMapping("/azz/api/client/updateClientUserStatus")
    JsonResult<String> updateClientUserStatus(@RequestParam("code") String code,@RequestParam("status") Integer status);
    
    
    /**
     * <p>平台端的客户 企业管理 启用 禁用</p>
     * @param merchantCode
     * @return
     * @author 刘建麟  2018年10月23日 上午10:10:06
     */
    @PostMapping("/azz/api/client/updateClientCompnayStatus")
    JsonResult<String> updateClientCompnayStatus(@RequestParam("code") String code,@RequestParam("status") Integer status);
    
    /**
     * <p>平台端的客户 账户详情</p>
     * @param merchantCode
     * @return
     * @author 刘建麟  2018年10月23日 上午10:10:06
     */
    @PostMapping("/azz/api/client/selectClientUserInfo")
    JsonResult<ClientAccountInfo> selectClientUserInfo(@RequestParam("code") String code);
    
    /**
     * <p>平台端的客户 企业详情</p>
     * @param merchantCode
     * @return
     * @author 刘建麟  2018年10月23日 上午10:10:06
     */
    @PostMapping("/azz/api/client/selectClientCompanyDetail")
    JsonResult<ClientCompanyInfo> selectClientCompanyDetail(@RequestParam("code") String code);
    
    /**
     * <p>TODO</p>
     * @param 平台 客户 企业成员 param
     * @return
     * @author 刘建麟  2018年10月26日 下午2:05:01
     */
    @RequestMapping(value="/azz/api/client/selectClientCompanyEmployeeList",method=RequestMethod.POST)
	JsonResult<Pagination<ClientCompanyEmployee>> selectClientCompanyEmployeeList(@RequestBody SearchClientManagerParam param);
    
    /**
     * <p>客户 企业用户</p>
     * @param param
     * @return
     * @author 刘建麟  2018年10月25日 下午5:39:50
     */
    @PostMapping("/azz/api/client/selectClientMerchantList")
    JsonResult<Pagination<ClientMerchantInfo>> selectClientMerchantList(@RequestBody SearchClientMerchantManagerParam param);
    
    /**
     * <p>平台客户审批认证列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月25日 下午5:19:39
     */
    @RequestMapping(value="/azz/api/client/searchClientCertificationList",method=RequestMethod.POST)
    public JsonResult<Pagination<ClientCertification>> searchClientList(@RequestBody SearchClientParam param);
    
    
    /**
     * <p>获取客户详情</p>
     * @param clientUserCode
     * @return
     * @author 彭斌  2018年10月25日 下午5:29:46
     */
    @PostMapping("/azz/api/client/searchClientInfo")
    public JsonResult<ClientInfo> selectDetailsClientInfo(@RequestParam("companyCode") String companyCode);
}

