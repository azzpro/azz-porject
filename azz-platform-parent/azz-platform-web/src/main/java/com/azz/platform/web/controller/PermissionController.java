/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月19日 下午4:17:40
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.platform.user.api.PermissionService;
import com.azz.platform.user.pojo.bo.AddRoleParam;
import com.azz.platform.user.pojo.bo.DelRoleParam;
import com.azz.platform.user.pojo.bo.EditRoleParam;
import com.azz.platform.user.pojo.bo.SearchRoleParam;
import com.azz.platform.user.pojo.bo.SetRolePermissionParam;
import com.azz.platform.user.pojo.vo.TreePermission;
import com.azz.platform.user.pojo.vo.Permission;
import com.azz.platform.user.pojo.vo.RoleInfo;
import com.azz.utils.WebUtils;

/**
 * <P>权限控制器</P>
 * @version 1.0
 * @author 黄智聪  2018年10月19日 下午4:17:40
 */
@RestController
@RequestMapping("/azz/api/permission")
public class PermissionController {
    
    @Autowired
    PermissionService permissionService;
    

    /**
     * 
     * <p>新增角色权限</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月19日 下午4:12:16
     */
    @RequestMapping(value = "/addRole")
    public JsonResult<String> addRole(AddRoleParam param) {
	param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
	return permissionService.addRole(param);
    }
    
    /**
     * 
     * <p>修改角色</p
     * @param param
     * @return
     * @author 黄智聪  2018年10月19日 上午9:26:26
     */
    @RequestMapping(value = "/editRole")
    public JsonResult<String> editRole(EditRoleParam param) {
	return permissionService.editRole(param);
    }
    
    /**
     * 
     * <p>删除角色（逻辑删除）</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月19日 上午9:26:26
     */
    @RequestMapping(value = "/delRole")
    public JsonResult<String> delRole(DelRoleParam param) {
	param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
	return permissionService.delRole(param);
    }
    
    /**
     * 
     * <p>查询角色列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月19日 上午9:26:26
     */
    @RequestMapping(value = "/getRoleList")
    public JsonResult<List<RoleInfo>> getRoleList(SearchRoleParam param) {
	return permissionService.getRoleList(param);
    }
    
    /**
     * 
     * <p>查询所有权限--树状结构</p>
     * @return
     * @author 黄智聪  2018年10月19日 上午9:22:49
     */
    @RequestMapping(value = "/getTreePermissions")
    public JsonResult<List<TreePermission>> getTreePermissions() {
	return permissionService.getTreePermissions();
    }
    
    /**
     * 
     * <p>查询所有权限</p>
     * @return
     * @author 黄智聪  2018年10月19日 上午9:22:49
     */
    @RequestMapping(value = "/getPermissionList")
    public JsonResult<List<Permission>> getPermissionList(String roleCode) {
	return permissionService.getPermissionList(roleCode);
    }
    
    /**
     * 
     * <p>查询角色权限</p>
     * @return
     * @author 黄智聪  2018年10月19日 上午9:22:49
     */
    @RequestMapping(value = "/getRolePermissions")
    public JsonResult<List<String>> getRolePermissions(String roleCode) {
	return permissionService.getRolePermissions(roleCode);
    }
    
    /**
     * 
     * <p>设置角色权限</p>
     * @return
     * @author 黄智聪  2018年10月19日 上午9:22:49
     */
    @RequestMapping(value = "/setRolePermissions")
    public JsonResult<String> setRolePermissions(SetRolePermissionParam param) {
	return permissionService.setRolePermissions(param);
    }

}

