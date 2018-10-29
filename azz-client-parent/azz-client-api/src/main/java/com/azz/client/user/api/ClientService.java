/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午5:18:06
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.client.user.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.client.pojo.bo.AddClientUserParam;
import com.azz.client.pojo.bo.ClientRegistParam;
import com.azz.client.pojo.bo.DelDeptParam;
import com.azz.client.pojo.bo.EditClientDeptParam;
import com.azz.client.pojo.bo.EditClientUserParam;
import com.azz.client.pojo.bo.EditDeptIsEnableParam;
import com.azz.client.pojo.bo.EnableOrDisableOrDelClientUserParam;
import com.azz.client.pojo.bo.EnterpriseAuthParam;
import com.azz.client.pojo.bo.LoginParam;
import com.azz.client.pojo.bo.SearchClientChildDeptParam;
import com.azz.client.pojo.bo.SearchClientDeptParam;
import com.azz.client.pojo.bo.SearchClientUserParam;
import com.azz.client.pojo.vo.ClientDeptList;
import com.azz.client.pojo.vo.ClientUserInfo;
import com.azz.client.pojo.vo.LoginClientUserInfo;
import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;


/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月22日 下午5:18:06
 */
@FeignClient("azz-client-service")
public interface ClientService {

    @RequestMapping("/azz/api/client/loginAuth")
    public JsonResult<String> loginAuth(@RequestBody LoginParam param);
    
    @RequestMapping("/azz/api/client/getLoginClientUserInfoByPhoneNumber")
    public JsonResult<LoginClientUserInfo> getLoginClientUserInfoByPhoneNumber(@RequestParam("phoneNumber")String phoneNumber);
    
    /*    
    @RequestMapping("/sendVerificationCode")
    public JsonResult<Long> sendVerificationCode(String phoneNumber);
    */
    
    @RequestMapping("/azz/api/client/clientRegist")
    public JsonResult<String> clientRegist(@RequestBody ClientRegistParam param);
    
    @RequestMapping("/azz/api/client/enterpriseAuth")
    public JsonResult<String> enterpriseAuth(@RequestBody EnterpriseAuthParam param);
    
    @RequestMapping("/azz/api/client/addClientUser")
    public JsonResult<String> addClientUser(@RequestBody AddClientUserParam param);
    
    @RequestMapping("/azz/api/client/editClientUser")
    public JsonResult<String> editClientUser(@RequestBody EditClientUserParam param);
    
    @RequestMapping("/azz/api/client/getClientUserList")
    public JsonResult<Pagination<ClientUserInfo>> getClientUserList(@RequestBody SearchClientUserParam param);
    
    @RequestMapping("/azz/api/client/enableOrDisableOrDelClientUser")
    public JsonResult<String> enableOrDisableOrDelClientUser(@RequestBody EnableOrDisableOrDelClientUserParam param);
    
    @RequestMapping("/azz/api/client/getClientUserInfo")
    public JsonResult<ClientUserInfo> getClientUserInfo(@RequestParam("clientUserCode") String clientUserCode);

    @RequestMapping("/azz/api/client/searchClientDeptList")
    public JsonResult<List<ClientDeptList>> searchClientDeptList(@RequestBody SearchClientDeptParam param);
    
    @RequestMapping("/azz/api/client/searchChildClientDeptList")
    public JsonResult<List<ClientDeptList>> searchChildClientDeptList(@RequestBody SearchClientChildDeptParam param);
    
    @RequestMapping("/azz/api/client/editDept")
    public JsonResult<String> editDept(@RequestBody EditClientDeptParam param);
    
    @RequestMapping("/azz/api/client/isEnableDept")
    public JsonResult<String> isEnableDept(@RequestBody EditDeptIsEnableParam param);
    
    @RequestMapping("/azz/api/client/delDept")
    public JsonResult<String> delDept(@RequestBody DelDeptParam param);
}

