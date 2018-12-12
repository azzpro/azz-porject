/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.merchant.api.PermissionService;
import com.azz.merchant.pojo.bo.AddRoleParam;
import com.azz.merchant.pojo.bo.DelRoleParam;
import com.azz.merchant.pojo.bo.EditRoleParam;
import com.azz.merchant.pojo.bo.SearchRoleParam;
import com.azz.merchant.pojo.bo.SetRolePermissionParam;
import com.azz.merchant.pojo.vo.Permission;
import com.azz.merchant.pojo.vo.RoleInfo;
import com.azz.merchant.utils.WebUtils;

/**
 * 
 * <P>
 * 登录控制器
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月17日 下午1:42:55
 */
@RestController
@RequestMapping("/azz/api/merchant/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    
    @RequestMapping("/getPermissionList")
    public JsonResult<List<Permission>> getPermissionList(String roleCode) {
	return permissionService.getPermissionList(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantCode(), roleCode);
    }
    
    @RequestMapping("/addRole")
    public JsonResult<String> addRole(AddRoleParam param) {
	param.setMerchantCode(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantCode());
	param.setCreator(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
	return permissionService.addRole(param);
    }
    
    @RequestMapping("/editRole")
    public JsonResult<String> editRole(EditRoleParam param) {
	param.setMerchantCode(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantCode());
	param.setModifier(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
	return permissionService.editRole(param);
    }
    
    @RequestMapping("/delRole")
    public JsonResult<String> delRole(DelRoleParam param) {
	param.setModifier(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
	return permissionService.delRole(param);
    }
    
    @RequestMapping("/getRoleList")
    public JsonResult<List<RoleInfo>> getRoleList(SearchRoleParam param) {
	param.setMerchantCode(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantCode());
	return permissionService.getRoleList(param);
    }
    
    @RequestMapping("/getRolePermissions")
    public JsonResult<List<String>> getRolePermissions(@RequestParam("roleCode")String roleCode) {
	return permissionService.getRolePermissions(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantCode(), roleCode);
    }
    
    @RequestMapping("/setRolePermissions")
    public JsonResult<String> setRolePermissions(SetRolePermissionParam param) {
	param.setCreator(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
	return permissionService.setRolePermissions(param);
    }

}
