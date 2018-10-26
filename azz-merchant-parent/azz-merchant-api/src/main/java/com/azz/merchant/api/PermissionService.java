/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:30:18
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.merchant.pojo.bo.AddRoleParam;
import com.azz.merchant.pojo.bo.DelRoleParam;
import com.azz.merchant.pojo.bo.EditRoleParam;
import com.azz.merchant.pojo.bo.SearchRoleParam;
import com.azz.merchant.pojo.bo.SetRolePermissionParam;
import com.azz.merchant.pojo.vo.Permission;
import com.azz.merchant.pojo.vo.RoleInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月22日 上午10:30:18
 */
@FeignClient("azz-merchant-service")
public interface PermissionService {
    
    /**
     * 
     * <p>查询权限列表</p>
     * @param roleCode
     * @return
     * @author 黄智聪  2018年10月25日 上午10:10:51
     */
    @RequestMapping("/azz/api/merchant/permission/getPermissionList")
    JsonResult<List<Permission>> getPermissionList(@RequestParam("merchantCode")String merchantCode, @RequestParam("roleCode")String roleCode);
    
    /**
     * 
     * <p>新增角色</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月25日 上午10:10:57
     */
    @RequestMapping("/azz/api/merchant/permission/addRole")
    JsonResult<String> addRole(@RequestBody AddRoleParam param);
    
    /**
     * 
     * <p>编辑角色</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月25日 上午10:11:03
     */
    @RequestMapping("/azz/api/merchant/permission/editRole")
    JsonResult<String> editRole(@RequestBody EditRoleParam param);
    
    /**
     * 
     * <p>删除角色</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月25日 上午10:11:05
     */
    @RequestMapping("/azz/api/merchant/permission/delRole")
    JsonResult<String> delRole(@RequestBody DelRoleParam param);
    
    /**
     * 
     * <p>查询角色列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月25日 上午10:11:09
     */
    @RequestMapping("/azz/api/merchant/permission/getRoleList")
    JsonResult<List<RoleInfo>> getRoleList(@RequestBody SearchRoleParam param);
    
    /**
     * 
     * <p>查询角色权限</p>
     * @param roleCode
     * @return
     * @author 黄智聪  2018年10月25日 上午10:11:12
     */
    @RequestMapping("/azz/api/merchant/permission/getRolePermissions")
    JsonResult<List<String>> getRolePermissions(@RequestParam("merchantCode")String merchantCode, @RequestParam("roleCode")String roleCode);
    
    /**
     * 
     * <p>设置商户成员权限</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月25日 上午10:11:16
     */
    @RequestMapping("/azz/api/merchant/permission/setRolePermissions")
    JsonResult<String> setRolePermissions(@RequestBody SetRolePermissionParam param);
    
}

