/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午5:18:06
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.client.pojo.bo.AddClientUserParam;
import com.azz.client.pojo.bo.ClientRegistParam;
import com.azz.client.pojo.bo.EditClientUserParam;
import com.azz.client.pojo.bo.EnableOrDisableOrDelClientUserParam;
import com.azz.client.pojo.bo.EnterpriseAuthParam;
import com.azz.client.pojo.bo.LoginParam;
import com.azz.client.pojo.bo.SearchClientUserParam;
import com.azz.client.pojo.vo.ClientUserInfo;
import com.azz.client.pojo.vo.LoginClientUserInfo;
import com.azz.client.service.ClientService;
import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;


/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月22日 下午5:18:06
 */
@RestController
@RequestMapping("/azz/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    
    @RequestMapping("/loginAuth")
    public JsonResult<String> loginAuth(@RequestBody LoginParam param) {
	return clientService.loginAuth(param);
    }
    
    @RequestMapping("/getLoginClientUserInfoByPhoneNumber")
    public JsonResult<LoginClientUserInfo> getLoginClientUserInfoByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber){
	return clientService.getLoginClientUserInfoByPhoneNumber(phoneNumber);
    }
    
/*    @RequestMapping("/sendVerificationCode")
    public JsonResult<Long> sendVerificationCode(String phoneNumber) {
	return clientService.sendVerificationCode(phoneNumber);
    }*/
    
    @RequestMapping("/clientRegist")
    public JsonResult<String> clientRegist(@RequestBody ClientRegistParam param) {
	return clientService.clientRegist(param);
    }
    
    @RequestMapping("/enterpriseAuth")
    public JsonResult<String> enterpriseAuth(@RequestBody EnterpriseAuthParam param) {
	return clientService.enterpriseAuth(param);
    }
    
/*    @RequestMapping("/uploadTradingCertificateFile")
    public JsonResult<UploadFileInfo> uploadTradingCertificateFile(@RequestBody UploadTradingCertificateParam param){
	return clientService.uploadTradingCertificateFile(param);
    }*/
    
    @RequestMapping("/addClientUser")
    public JsonResult<String> addClientUser(@RequestBody AddClientUserParam param){
	return clientService.addClientUser(param);
    }
    
    @RequestMapping("/editClientUser")
    public JsonResult<String> editClientUser(@RequestBody EditClientUserParam param) {
	return clientService.editClientUser(param);
    }
    
    @RequestMapping("/getClientUserList")
    public JsonResult<Pagination<ClientUserInfo>> getUserList(@RequestBody SearchClientUserParam param) {
	return clientService.getClientUserList(param);
    }
    
    @RequestMapping("/enableOrDisableOrDelClientUser")
    public JsonResult<String> enableOrDisableOrDelClientUser(@RequestBody EnableOrDisableOrDelClientUserParam param) {
	return clientService.enableOrDisableOrDelClientUser(param);
    }
    
    @RequestMapping("/getClientUserInfo")
    public JsonResult<ClientUserInfo> getClientUserInfo(@RequestParam("clientUserCode") String clientUserCode) {
	return clientService.getClientUserInfo(clientUserCode);
    }
}

