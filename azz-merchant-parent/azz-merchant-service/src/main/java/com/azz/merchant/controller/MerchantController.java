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
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.merchant.pojo.bo.CompleteMerchantInfoParam;
import com.azz.merchant.pojo.bo.LoginParam;
import com.azz.merchant.pojo.bo.MerchantRegistParam;
import com.azz.merchant.pojo.vo.LoginMerchantInfo;
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
    
    @RequestMapping("/getLoginMerchantInfoByPhoneNumber")
    public JsonResult<LoginMerchantInfo> getLoginMerchantInfoByPhoneNumber(String phoneNumber){
	return merchantService.getLoginMerchantInfoByPhoneNumber(phoneNumber);
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
    
}

