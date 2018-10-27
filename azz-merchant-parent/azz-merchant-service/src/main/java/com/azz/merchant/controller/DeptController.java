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
import com.azz.merchant.pojo.bo.AddMerchantDeptParam;
import com.azz.merchant.service.DeptService;


/**
 * <P>部门服务</P>
 * @version 1.0
 * @author 彭斌  2018年10月27日 下午4:14:41
 */
@RestController
@RequestMapping("/azz/api/merchant/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;
    
    /*@RequestMapping("/loginAuth")
    public JsonResult<String> loginAuth(@RequestBody LoginParam param) {
	return merchantService.loginAuth(param);
    }
    
    @RequestMapping("/getLoginMerchantUserInfoByPhoneNumber")
    public JsonResult<LoginMerchantUserInfo> getLoginMerchantUserInfoByPhoneNumber(@RequestParam("phoneNumber")String phoneNumber){
	return merchantService.getLoginMerchantUserInfoByPhoneNumber(phoneNumber);
    }*/
    
}

