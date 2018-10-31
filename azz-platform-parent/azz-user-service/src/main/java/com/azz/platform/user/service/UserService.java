/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月14日 上午9:27:50 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.UserConstants;
import com.azz.core.constants.UserConstants.UserStatus;
import com.azz.core.exception.BaseException;
import com.azz.core.exception.ShiroAuthException;
import com.azz.exception.JSR303ValidationException;
import com.azz.model.Password;
import com.azz.platform.user.mapper.PlatformDeptMapper;
import com.azz.platform.user.mapper.PlatformPermissionMapper;
import com.azz.platform.user.mapper.PlatformRoleMapper;
import com.azz.platform.user.mapper.PlatformUserMapper;
import com.azz.platform.user.mapper.PlatformUserRoleMapper;
import com.azz.platform.user.pojo.PlatformDept;
import com.azz.platform.user.pojo.PlatformRole;
import com.azz.platform.user.pojo.PlatformUser;
import com.azz.platform.user.pojo.PlatformUserRole;
import com.azz.platform.user.pojo.bo.AddUserParam;
import com.azz.platform.user.pojo.bo.EditPasswordParam;
import com.azz.platform.user.pojo.bo.EditUserParam;
import com.azz.platform.user.pojo.bo.EnableOrDisableOrDelUserParam;
import com.azz.platform.user.pojo.bo.LoginParam;
import com.azz.platform.user.pojo.bo.SearchUserParam;
import com.azz.platform.user.pojo.vo.LoginUserInfo;
import com.azz.platform.user.pojo.vo.Menu;
import com.azz.platform.user.pojo.vo.UserInfo;
import com.azz.platform.user.pojo.vo.UserPermission;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.PasswordHelper;
import com.azz.util.StringUtils;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 刘建麟 2018年10月14日 上午9:27:50
 */
@Service
@Slf4j
public class UserService{

    @Autowired
    private PlatformUserMapper platformUserMapper;

    @Autowired
    private PlatformPermissionMapper platformPermissionMapper;

    @Autowired
    private PlatformDeptMapper platformDeptMapper;

    @Autowired
    private PlatformRoleMapper platformRoleMapper;

    @Autowired
    private PlatformUserRoleMapper platformUserRoleMapper;
    
    @Autowired
    private DbSequenceService dbSequenceService;

    public JsonResult<String> loginAuth(@RequestBody LoginParam param) {
	log.debug("————身份认证方法————");
	String phoneNumber = param.getPhoneNumber();
	String password = param.getPassword();
	PlatformUser platformUser = platformUserMapper.getUserByPhoneNumber(phoneNumber, null);
	if (platformUser == null) {// 无效用户
	    throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "请输入正确的账号或密码");
	}
	if(platformUser.getStatus() == UserStatus.INVALID.getValue()) {
		throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "账号已被禁用，请联系管理员解除");
	}
	boolean isRight = PasswordHelper.checkPassword(password, platformUser.getSalt(), platformUser.getPassword());
	if (!isRight) {// 与盐值加密的密码不匹配
	    throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "请输入正确的账号或密码");
	}
	return JsonResult.successJsonResult();
    }

    public JsonResult<LoginUserInfo> getLoginUserInfoByPhoneNumber(String phoneNumber) {
	LoginUserInfo info = new LoginUserInfo();
	UserInfo userInfo = platformUserMapper.getUserInfoByPhoneNumber(phoneNumber);
	List<UserPermission> userPermissions = platformPermissionMapper.getUserPermissionInfoByPhoneNumber(phoneNumber);
	info.setUserInfo(userInfo);
	info.setUserPermissions(userPermissions);
	info.setMenus(generateMenuTree(phoneNumber));
	return JsonResult.successJsonResult(info);
    }

    /**
     * 
     * <p>
     * 根据手机号查询当前用户角色并生成菜单树
     * </p>
     * 
     * @param phoneNumber
     *            手机号
     * @return
     * @author 黄智聪 2018年10月19日 上午10:36:34
     */
    private List<Menu> generateMenuTree(String phoneNumber) {
	// 根据手机号查询所有一级菜单权限
	List<UserPermission> oneMenuPermissions = platformPermissionMapper
		.getUserPermissionByPhoneNumberAndLevel(phoneNumber, 1);
	// 根据手机号查询所有二级菜单权限
	List<UserPermission> twoMenuPermissions = platformPermissionMapper
		.getUserPermissionByPhoneNumberAndLevel(phoneNumber, 2);
	List<Menu> oneLevelMenus = new ArrayList<>();
	for (UserPermission oneMenuPermission : oneMenuPermissions) {
	    // 一级菜单的权限编码
	    String oneLevelPermissionCode = oneMenuPermission.getPermissionCode();
	    List<Menu> twoLevelMenus = new ArrayList<>();
	    for (UserPermission twoMenuPermission : twoMenuPermissions) {
		// 二级菜单的父级权限编码
		String twoLevelPermissionCode = twoMenuPermission.getParentPermissionCode();
		if (twoLevelPermissionCode.equals(oneLevelPermissionCode)) {// 一二级菜单进行分类
		    Menu twoLevelMenu = new Menu(twoMenuPermission.getPermissionName(), twoMenuPermission.getPageUrl(),
			    twoMenuPermission.getIcon(), null);
		    twoLevelMenus.add(twoLevelMenu);
		}
	    }
	    Menu oneLevelMenu = new Menu(oneMenuPermission.getPermissionName(), oneMenuPermission.getPageUrl(),
		    oneMenuPermission.getIcon(), twoLevelMenus);
	    oneLevelMenus.add(oneLevelMenu);
	}
	return oneLevelMenus;
    }

    public JsonResult<String> editPassword(@RequestBody EditPasswordParam param) {
	JSR303ValidateUtils.validate(param);

	// 密码一致性校验
	if (!param.getFirstPassword().equals(param.getSecondPassword())) {
	    throw new BaseException(PlatformUserErrorCode.PLATFORM_USER_ERROR_INCONSISTENT_PASSWORD);
	}

	// 根据用户编码获取用户信息
	PlatformUser platformUser = platformUserMapper.getUserByUserCode(param.getUserCode());
	if (platformUser == null) {// 无效用户
	    throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "无效用户");
	}

	// 用户设置的新密码信息
	Password pwd = PasswordHelper.encryptPasswordByModel(param.getSecondPassword());
	platformUser.setPassword(pwd.getPassword());
	platformUser.setSalt(pwd.getSalt());
	platformUser.setModifier(param.getUserInfo().getUserCode());
	platformUser.setLastModifyTime(new Date());

	platformUserMapper.updateByPrimaryKeySelective(platformUser);

	return JsonResult.successJsonResult();
    }

    public JsonResult<String> addUser(@RequestBody AddUserParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);

	String password = param.getPassword();
	String confirmPassword = param.getConfirmPassword();
	// 密码与确认密码一致性校验
	if (!password.equals(confirmPassword)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "密码与确认密码不一致");
	}
	PlatformDept dept = platformDeptMapper.selectByDeptCode(param.getDeptCode());
	if (dept == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "部门不存在");
	}
	PlatformRole role = platformRoleMapper.selectByRoleCode(param.getRoleCode());
	if (role == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "角色不存在");
	}
	String email = param.getEmail();
	if (!StringUtils.isBlank(email)) {
	    PlatformUser user = platformUserMapper.getUserByEmail(email, null);
	    if (user != null) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "邮箱已存在");
	    }
	}
	PlatformUser u = platformUserMapper.getUserByPhoneNumber(param.getPhoneNumber(), null);
	if (u != null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号已存在");
	}

	// 生成盐值加密的密码
	Password pwd = PasswordHelper.encryptPasswordByModel(password);
	Date nowDate = new Date();
	String creator = param.getCreator();
	PlatformUser userRecord = PlatformUser.builder().createTime(nowDate).creator(creator).deptId(dept.getId())
		.email(param.getEmail()).password(pwd.getPassword()).phoneNumber(param.getPhoneNumber())
		.postName(param.getPostName()).userCode(dbSequenceService.getPlatEmployeeNumber())
		.userName(param.getUserName()).salt(pwd.getSalt()).build();
	platformUserMapper.insertSelective(userRecord);
	// 用户与角色绑定
	PlatformUserRole userRoleRecord = PlatformUserRole.builder().createTime(nowDate).creator(creator)
		.userId(userRecord.getId()).roleId(role.getId()).build();
	platformUserRoleMapper.insertSelective(userRoleRecord);
	return JsonResult.successJsonResult();
    }

    public JsonResult<String> editUser(@RequestBody EditUserParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	String password = param.getPassword();
	String confirmPassword = param.getConfirmPassword();

	Password pwd = null;
	if (!StringUtils.isBlank(password) || !StringUtils.isBlank(confirmPassword)) {
	    // 密码与确认密码一致性校验
	    if (!password.equals(confirmPassword)) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "密码与确认密码不一致");
	    }
	    // 生成盐值加密的密码
	    pwd = PasswordHelper.encryptPasswordByModel(password);
	}
	PlatformDept dept = platformDeptMapper.selectByDeptCode(param.getDeptCode());
	if (dept == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "部门不存在");
	}
	PlatformRole role = platformRoleMapper.selectByRoleCode(param.getRoleCode());
	if (role == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "角色不存在");
	}
	if(UserConstants.PLATFORM_ADMIN_ROLE_NAME.equals(role.getRoleName())) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "该用户不允许修改");
	}

	String userCode = param.getUserCode();
	PlatformUser user = platformUserMapper.getUserByUserCode(userCode);
	if (user == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "用户不存在");
	}
	String email = param.getEmail();
	if (!StringUtils.isBlank(email)) {
	    // 带上用户编码是为了排除当前用户以外是否存在邮箱了
	    PlatformUser u = platformUserMapper.getUserByEmail(email, userCode);
	    if (u != null) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "邮箱已存在");
	    }
	}
	PlatformUser u = platformUserMapper.getUserByPhoneNumber(param.getPhoneNumber(), userCode);
	if (u != null) {
	    // 带上用户编码是为了排除当前用户以外是否存在手机了
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号已存在");
	}

	Date nowDate = new Date();
	String modifier = param.getModifier();
	Long userId = user.getId();
	PlatformUser userRecord = PlatformUser.builder().modifier(modifier).lastModifyTime(nowDate).deptId(dept.getId())
		.email(param.getEmail()).password(pwd == null ? null : pwd.getPassword())
		.phoneNumber(param.getPhoneNumber()).postName(param.getPostName()).userName(param.getUserName())
		.salt(pwd == null ? null : pwd.getSalt()).id(userId).build();
	platformUserMapper.updateByPrimaryKeySelective(userRecord);

	// 先删除原先的用户与角色的绑定
	platformUserRoleMapper.deleteByUserId(userId);

	// 重新对用户与角色进行新的绑定
	PlatformUserRole userRoleRecord = PlatformUserRole.builder().createTime(nowDate).creator(modifier)
		.userId(userId).roleId(role.getId()).build();
	platformUserRoleMapper.insertSelective(userRoleRecord);
	return JsonResult.successJsonResult();
    }

    public JsonResult<Pagination<UserInfo>> getUserList(@RequestBody SearchUserParam param) {
	PageHelper.startPage(param.getPageNum(), param.getPageSize());
	List<UserInfo> users = platformUserMapper.getUserInfoBySearchParam(param);
	return JsonResult.successJsonResult(new Pagination<>(users));
    }

    public JsonResult<String> enableOrDisableOrDelUser(@RequestBody EnableOrDisableOrDelUserParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	int status = param.getStatus();
	this.checkStatusExist(status);
	PlatformUser userRecord = PlatformUser.builder().userCode(param.getUserCode()).status(status)
		.modifier(param.getModifier()).lastModifyTime(new Date()).build();
	platformUserMapper.updateByUserCode(userRecord);
	return JsonResult.successJsonResult();
    }

    public JsonResult<UserInfo> getUserInfo(String userCode) {
	if (StringUtils.isBlank(userCode)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "用户编码不允许为空");
	}
	UserInfo userInfo = platformUserMapper.getUserInfoByUserCode(userCode);
	if (userInfo == null) {
	    throw new BaseException(PlatformUserErrorCode.PLATFORM_USER_ERROR_INVALID_USER);
	}
	return JsonResult.successJsonResult(userInfo);
    }

    /**
     * 
     * <p>
     * 校验是否存在该状态
     * </p>
     * 
     * @param value
     * @return
     * @author 黄智聪 2018年10月20日 上午11:29:37
     */
    public void checkStatusExist(int value) {
	if (!UserStatus.checkStatusExist(value)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "用户状态不存在");
	}
    }

}
