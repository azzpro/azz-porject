/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月17日 下午5:59:05
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.platform.user.pojo.bo.AddRoleParam;
import com.azz.platform.user.pojo.bo.DelRoleParam;
import com.azz.platform.user.pojo.bo.EditRoleParam;
import com.azz.platform.user.pojo.bo.SearchRoleParam;
import com.azz.platform.user.pojo.vo.Permission;
import com.azz.platform.user.pojo.vo.RoleInfo;

/**
 * <P>权限服务相关接口</P>
 * @version 1.0
 * @author 黄智聪  2018年10月17日 下午5:59:05
 */
@FeignClient("azz-user-service")
public interface PermissionService {
    
    /**
     * 
     * <p>查询所有权限</p>
     * @return
     * @author 黄智聪  2018年10月19日 下午6:00:40
     */
    @GetMapping("getPermissions")
    JsonResult<List<Permission>> getPermissions();
    
    /**
     * 
     * <p>新增角色权限</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月19日 下午6:00:58
     */
    @PostMapping("addRolePermissions")
    JsonResult<String> addRolePermission(@RequestBody AddRoleParam param);
    
    /**
     * 
     * <p>修改角色权限</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月19日 下午6:01:10
     */
    @PostMapping("editRolePermission")
    JsonResult<String> editRolePermission(@RequestBody EditRoleParam param);
    
    /**
     * 
     * <p>删除角色</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月19日 下午6:01:22
     */
    @PostMapping("delRole")
    JsonResult<String> delRole(@RequestBody DelRoleParam param);
    
    /**
     * 
     * <p>查询角色列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月19日 下午6:01:30
     */
    @PostMapping("getRoleList")
    JsonResult<List<RoleInfo>> getRoleList(@RequestBody SearchRoleParam param);
    
}

