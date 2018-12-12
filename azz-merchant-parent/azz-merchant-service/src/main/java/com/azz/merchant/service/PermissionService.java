/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月14日 上午9:27:50 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.constants.PermissionConstants.PermissionStatus;
import com.azz.core.constants.UserConstants;
import com.azz.exception.JSR303ValidationException;
import com.azz.merchant.mapper.MerchantMapper;
import com.azz.merchant.mapper.MerchantPermissionMapper;
import com.azz.merchant.mapper.MerchantRoleMapper;
import com.azz.merchant.mapper.MerchantRolePermissionMapper;
import com.azz.merchant.mapper.MerchantUserRoleMapper;
import com.azz.merchant.pojo.Merchant;
import com.azz.merchant.pojo.MerchantPermission;
import com.azz.merchant.pojo.MerchantRole;
import com.azz.merchant.pojo.MerchantRolePermission;
import com.azz.merchant.pojo.bo.AddRoleParam;
import com.azz.merchant.pojo.bo.DelRoleParam;
import com.azz.merchant.pojo.bo.EditRoleParam;
import com.azz.merchant.pojo.bo.SearchRoleParam;
import com.azz.merchant.pojo.bo.SetRolePermissionParam;
import com.azz.merchant.pojo.vo.Permission;
import com.azz.merchant.pojo.vo.RoleInfo;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.azz.util.SystemSeqUtils;

/**
 * 
 * <P>权限服务相关接口实现类</P>
 * @version 1.0
 * @author 黄智聪  2018年10月22日 下午4:06:22
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class PermissionService {

    @Autowired
    MerchantRoleMapper merchantRoleMapper;
    
    @Autowired
    MerchantUserRoleMapper merchantUserRoleMapper;

    @Autowired
    MerchantRolePermissionMapper merchantRolePermissionMapper;

    @Autowired
    MerchantPermissionMapper merchantPermissionMapper;
    
    @Autowired
    MerchantMapper merchantMapper;
    
    @Autowired
    private DbSequenceService dbSequenceService;

    public JsonResult<List<Permission>> getPermissionList(String merchantCode, String roleCode) {
	List<Permission> permissions = merchantPermissionMapper.getAllPermissions();
	if(StringUtils.isBlank(roleCode)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "角色编码不允许为空");
	}
	Merchant merchant = merchantMapper.getMerchantByMerchantCode(merchantCode);
	if(merchant == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在");
	}
	List<String> permissionCodes = merchantRolePermissionMapper.getPermissionCodesByRoleCode(merchant.getId(), roleCode);
	for (Permission permission : permissions) {
	    String permissionCode = permission.getPermissionCode();
	    if(permissionCodes.contains(permissionCode)) {
		permission.setIsSelected(1);
	    }
	}
	return JsonResult.successJsonResult(permissions);
    }
    
    public JsonResult<String> addRole(@RequestBody AddRoleParam param) {
	// 参数校验
	this.validateAddRoleParam(param);
	Date nowDate = new Date();
	String code = dbSequenceService.getMerchantPowerNumber();
	String creator = param.getCreator();
	Merchant merchant = merchantMapper.getMerchantByMerchantCode(param.getMerchantCode());
	MerchantRole roleRecord = MerchantRole.builder().createTime(nowDate).creator(creator).remark(param.getRemark())
		.roleCode(SystemSeqUtils.getSeq(code))
		.roleName(param.getRoleName())
		.merchantId(merchant.getId())
		.build();
	merchantRoleMapper.insertSelective(roleRecord);
	return JsonResult.successJsonResult();
    }

    public JsonResult<String> editRole(@RequestBody EditRoleParam param) {
	// 参数校验
	this.validateEditRoleParam(param);
	if(UserConstants.PLATFORM_ADMIN_ROLE_NAME.equals(param.getRoleName())) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "管理员角色不允许修改");
	}
	Date nowDate = new Date();
	String modifier = param.getModifier();
	String roleCode = param.getRoleCode();
	MerchantRole roleRecord = MerchantRole.builder().lastModifyTime(nowDate).modifier(modifier)
		.remark(param.getRemark()).roleCode(roleCode)
		.roleName(param.getRoleName()).build();
	merchantRoleMapper.updateByRoleCode(roleRecord);
	return JsonResult.successJsonResult();
    }
    
    public JsonResult<String> delRole(@RequestBody DelRoleParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	
	MerchantRole role = merchantRoleMapper.selectByRoleCode(param.getRoleCode());
	if(role == null) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "角色不存在");
	}
	
	// 校验是否存在绑定的成员
	int count = merchantUserRoleMapper.countBindingUserRole(role.getId());
	if(count > 0) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "该角色已绑定成员，请处理后删除");
	}
	
	MerchantRole roleRecord = MerchantRole.builder()
		.lastModifyTime(new Date())
		.modifier(param.getModifier())
		.roleCode(param.getRoleCode())
		.status(PermissionStatus.INVALID.getValue())// 无效
		.build();
	merchantRoleMapper.updateByRoleCode(roleRecord);
	return JsonResult.successJsonResult();
    }
    
    
    public JsonResult<List<RoleInfo>> getRoleList(@RequestBody SearchRoleParam param) {
	return JsonResult.successJsonResult(merchantRoleMapper.getRoleInfoBySearchParam(param));
    }
    
    
    public JsonResult<List<String>> getRolePermissions(String merchantCode, String roleCode) {
	if(StringUtils.isBlank(roleCode)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "角色编码不允许为空");
	}
	Merchant merchant = merchantMapper.getMerchantByMerchantCode(merchantCode);
	if(merchant == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在");
	}
	List<String> permissionCodes = merchantRolePermissionMapper.getPermissionCodesByRoleCode(merchant.getId(), roleCode);
	return JsonResult.successJsonResult(permissionCodes);
    }
    
    public JsonResult<String> setRolePermissions(@RequestBody SetRolePermissionParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	// 根据角色编码查询当前角色信息
	MerchantRole role = merchantRoleMapper.selectByRoleCode(param.getRoleCode());
	if (role == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "角色不存在");
	}
	Long roleId = role.getId();
	// 先将原来角色绑定的权限删除
	merchantRolePermissionMapper.deleteByRoleId(roleId);
	// 再重新绑定新的权限信息
	List<String> permissionCodes = param.getPermissionCodes();
	for (String permissionCode : permissionCodes) {
	    MerchantPermission persmission = merchantPermissionMapper.getMerchantPermissionByPermissionCode(permissionCode);
	    MerchantRolePermission rolePermissionRecord = MerchantRolePermission.builder().createTime(new Date())
		    .creator(param.getCreator()).permissionId(persmission.getId()).roleId(roleId).build();
	    merchantRolePermissionMapper.insertSelective(rolePermissionRecord);
	}
	return JsonResult.successJsonResult();
    }

    /**
     * 
     * <p>
     * 校验新增角色权限参数
     * </p>
     * 
     * @param param
     * @author 黄智聪 2018年10月18日 下午5:30:23
     */
    private void validateAddRoleParam(AddRoleParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	Merchant merchant = merchantMapper.getMerchantByMerchantCode(param.getMerchantCode());
	if(merchant == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在");
	}
	this.isExistRoleName(merchant.getId(), param.getRoleName(), null);
    }

    /**
     * 
     * <p>
     * 校验编辑角色权限参数
     * </p>
     * 
     * @param param
     * @author 黄智聪 2018年10月18日 下午5:30:23
     */
    private void validateEditRoleParam(EditRoleParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	Merchant merchant = merchantMapper.getMerchantByMerchantCode(param.getMerchantCode());
	if(merchant == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在");
	}
	this.isExistRoleName(merchant.getId(), param.getRoleName(), param.getRoleCode());
    }

    /**
     * 
     * <p>
     * 角色名称是否存在
     * </p>
     * 
     * @param roleName
     *            角色名称
     * @author 黄智聪 2018年10月19日 下午1:52:51
     */
    private void isExistRoleName(Long merchantId, String roleName, String roleCode) {
	// 校验角色名称是否已经存在，若是修改则需要传roleCode，用于判断是否改的为当前编码的角色名称
	MerchantRole role = merchantRoleMapper.hasRoleName(merchantId, roleName, roleCode);
	if (role != null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "角色名称已存在");
	}
    }

}
