/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午5:18:06
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.merchant.pojo.MerchantDept;
import com.azz.merchant.pojo.bo.AddMerchantDeptParam;
import com.azz.merchant.pojo.bo.DelDeptParam;
import com.azz.merchant.pojo.bo.EditDeptIsEnableParam;
import com.azz.merchant.pojo.bo.EditMerchantDeptParam;
import com.azz.merchant.pojo.bo.SearchMerchantChildDeptParam;
import com.azz.merchant.pojo.bo.SearchMerchantDeptInfoParam;
import com.azz.merchant.pojo.bo.SearchMerchantDeptListParam;
import com.azz.merchant.pojo.vo.MerchantDeptList;
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
    
    @RequestMapping("/addFirstLevelDept")
    public JsonResult<String> addFirstLevelDept(@RequestBody AddMerchantDeptParam param){
        return deptService.addFirstLevelDept(param);
    }

    @RequestMapping("/addChildDept")
    public JsonResult<String> addChildDept(@RequestBody AddMerchantDeptParam param){
        return deptService.addChildDept(param);
    }
    
    @RequestMapping("/getDeptInfo")
    public JsonResult<MerchantDept> getDeptInfo(@RequestBody SearchMerchantDeptInfoParam param){
        return deptService.getDeptInfo(param);
    }
    
    @RequestMapping("/editDept")
    public JsonResult<String> editDept(@RequestBody EditMerchantDeptParam param){
        return deptService.editDept(param);
    }
    
    @RequestMapping("/searchDeptList")
    public JsonResult<List<MerchantDeptList>> searchDeptList(@RequestBody SearchMerchantDeptListParam param){
        return deptService.searchDeptList(param);
    }
    
    @RequestMapping("/searchChildDeptList")
    public JsonResult<List<MerchantDeptList>> searchChildDeptList(@RequestBody SearchMerchantChildDeptParam param){
        return deptService.searchChildDeptList(param);
    }
    
    @RequestMapping("/isEnableDept")
    public JsonResult<String> isEnableDept(@RequestBody EditDeptIsEnableParam param){
        return deptService.isEnableDept(param);
    }
    
    @RequestMapping("/delDept")
    public JsonResult<String> delDept(@RequestBody DelDeptParam param){
        return deptService.delDept(param);
    }
    
}

