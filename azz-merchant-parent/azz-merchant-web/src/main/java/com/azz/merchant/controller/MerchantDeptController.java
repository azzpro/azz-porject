/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.merchant.api.DeptService;
import com.azz.merchant.pojo.MerchantDept;
import com.azz.merchant.pojo.bo.AddMerchantDeptParam;
import com.azz.merchant.pojo.bo.SearchMerchantDeptInfoParam;
import com.azz.merchant.utils.WebUtils;

/**
 * <P>部门维护</P>
 * @version 1.0
 * @author 彭斌  2018年10月29日 下午2:47:28
 */
@RestController
@RequestMapping("/azz/api/merchant/dept")
public class MerchantDeptController {


    @Autowired
    DeptService deptService;

    
    @RequestMapping("/addFirstLevelDept")
    JsonResult<String> addFirstLevelDept(AddMerchantDeptParam param){
        param.setMerchantId(WebUtils.getLoginMerchanUser().getMerchantUserInfo().getMerchantId());
        param.setCreator(WebUtils.getLoginMerchanUser().getMerchantUserInfo().getMerchantUserCode());
        return deptService.addFirstLevelDept(param);
    }

    @RequestMapping("/addChildDept")
    JsonResult<String> addChildDept(AddMerchantDeptParam param){
        param.setMerchantId(WebUtils.getLoginMerchanUser().getMerchantUserInfo().getMerchantId());
        param.setCreator(WebUtils.getLoginMerchanUser().getMerchantUserInfo().getMerchantUserCode());
        return deptService.addChildDept(param);
    }
    
    @RequestMapping("/getDeptInfo")
    JsonResult<MerchantDept> getDeptInfo(SearchMerchantDeptInfoParam param){
        
        return deptService.getDeptInfo(deptCode);
    }
    
}
