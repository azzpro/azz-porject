/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午5:18:06
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.merchant.pojo.bo.AddMerchantUserParam;
import com.azz.merchant.pojo.bo.CompleteMerchantInfoParam;
import com.azz.merchant.pojo.bo.EditMerchantUserParam;
import com.azz.merchant.pojo.bo.EnableOrDisableOrDelMerchantUserParam;
import com.azz.merchant.pojo.bo.LoginParam;
import com.azz.merchant.pojo.bo.MerchantRegistParam;
import com.azz.merchant.pojo.bo.SearchMerchantUserParam;
import com.azz.merchant.pojo.bo.UploadTradingCertificateParam;
import com.azz.merchant.pojo.vo.LoginMerchantUserInfo;
import com.azz.merchant.pojo.vo.MerchantUserInfo;
import com.azz.merchant.pojo.vo.UploadFileInfo;
import com.azz.merchant.service.MerchantService;


/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月22日 下午5:18:06
 */
@RestController
@RequestMapping("/azz/api/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;
    
    @RequestMapping("/loginAuth")
    public JsonResult<String> loginAuth(@RequestBody LoginParam param) {
	return merchantService.loginAuth(param);
    }
    
    @RequestMapping("/getLoginMerchantUserInfoByPhoneNumber")
    public JsonResult<LoginMerchantUserInfo> getLoginMerchantUserInfoByPhoneNumber(String phoneNumber){
	return merchantService.getLoginMerchantUserInfoByPhoneNumber(phoneNumber);
    }
    
    @RequestMapping("/sendVerificationCode")
    public JsonResult<Long> sendVerificationCode(String phoneNumber) {
	return merchantService.sendVerificationCode(phoneNumber);
    }
    
    @RequestMapping("/merchantRegist")
    public JsonResult<String> merchantRegist(@RequestBody MerchantRegistParam param) {
	return merchantService.merchantRegist(param);
    }
    
    @RequestMapping("/getMerchantQualificationApplyStatus")
    public JsonResult<Integer> getMerchantQualificationApplyStatus(String merchantCode) {
	return merchantService.getMerchantQualificationApplyStatus(merchantCode);
    }
    
    @RequestMapping("/completeMerchantInfo")
    public JsonResult<String> completeMerchantInfo(@RequestBody CompleteMerchantInfoParam param) {
	return merchantService.completeMerchantInfo(param);
    }
    
    @RequestMapping("/uploadTradingCertificateFile")
    public JsonResult<UploadFileInfo> uploadTradingCertificateFile(@RequestBody UploadTradingCertificateParam param){
	return merchantService.uploadTradingCertificateFile(param);
    }
    
    @RequestMapping("/addMerchantUser")
    public JsonResult<String> addMerchantUser(@RequestBody AddMerchantUserParam param){
	return merchantService.addMerchantUser(param);
    }
    
    @RequestMapping("/editMerchantUser")
    public JsonResult<String> editMerchantUser(@RequestBody EditMerchantUserParam param) {
	return merchantService.editMerchantUser(param);
    }
    
    @RequestMapping("/getMerchantUserList")
    public JsonResult<Pagination<MerchantUserInfo>> getUserList(@RequestBody SearchMerchantUserParam param) {
	return merchantService.getMerchantUserList(param);
    }
    
    @RequestMapping("/enableOrDisableOrDelMerchantUser")
    public JsonResult<String> enableOrDisableOrDelMerchantUser(@RequestBody EnableOrDisableOrDelMerchantUserParam param) {
	return merchantService.enableOrDisableOrDelMerchantUser(param);
    }
    
    @RequestMapping("/getMerchantUserInfo")
    public JsonResult<MerchantUserInfo> getMerchantUserInfo(@RequestParam("merchantUserCode") String merchantUserCode) {
	return merchantService.getMerchantUserInfo(merchantUserCode);
    }
}

