/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月14日 上午9:27:50 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.user.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.platform.user.api.PermissionService;
import com.azz.platform.user.mapper.PlatformPermissionMapper;
import com.azz.platform.user.mapper.PlatformRoleMapper;
import com.azz.platform.user.mapper.PlatformRolePermissionMapper;
import com.azz.platform.user.pojo.PlatformPermission;
import com.azz.platform.user.pojo.PlatformRole;
import com.azz.platform.user.pojo.PlatformRolePermission;
import com.azz.platform.user.pojo.bo.AddRoleParam;
import com.azz.util.JSR303ValidateUtils;

/**
 * <P>
 * 权限服务相关接口实现类
 * </P>
 * 
 * @version 1.0
 * @author 刘建麟 2018年10月14日 上午9:27:50
 */
@RestController
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PlatformRoleMapper platformRoleMapper;

    @Autowired
    PlatformRolePermissionMapper platformRolePermissionMapper;

    @Autowired
    PlatformPermissionMapper platformPermissionMapper;

    @Override
    public JsonResult<String> addRolePermission(@RequestBody AddRoleParam param) {
	this.validateRoleParam(param);
	List<String> permissionCodes = param.getPermissionCodes();
	Date nowDate = new Date();
	String creator = param.getCreator();
	PlatformRole roleRecord = PlatformRole.builder().createTime(nowDate).creator(creator).remark(param.getRemark())
		.roleCode(System.currentTimeMillis()+"")// TODO
		.roleName(param.getRoleName()).build();
	platformRoleMapper.insertSelective(roleRecord);
	System.err.println(roleRecord.getId());
	for (String permissionCode : permissionCodes) {
	    PlatformPermission persmission = platformPermissionMapper.getPermissionByPermissionCode(permissionCode);
	    PlatformRolePermission rolePermissionRecord = PlatformRolePermission.builder().createTime(nowDate)
		    .creator(creator).permissionId(persmission.getId()).roleId(roleRecord.getId()).build();
	    platformRolePermissionMapper.insertSelective(rolePermissionRecord);
	}
	return JsonResult.successJsonResult();
    }

    /**
     * 
     * <p>校验</p>
     * @param param
     * @author 黄智聪  2018年10月18日 下午5:30:23
     */
    private void validateRoleParam(AddRoleParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	String roleName = param.getRoleName();
	
    }

}
