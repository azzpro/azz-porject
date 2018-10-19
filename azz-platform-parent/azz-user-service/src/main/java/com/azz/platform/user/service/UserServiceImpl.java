/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月14日 上午9:27:50 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.user.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.exception.BaseException;
import com.azz.core.exception.ShiroAuthException;
import com.azz.model.Password;
import com.azz.platform.user.api.UserService;
import com.azz.platform.user.mapper.PlatformPermissionMapper;
import com.azz.platform.user.mapper.PlatformUserMapper;
import com.azz.platform.user.pojo.PlatformUser;
import com.azz.platform.user.pojo.bo.EditPasswordParam;
import com.azz.platform.user.pojo.bo.LoginParam;
import com.azz.platform.user.pojo.vo.LoginUserInfo;
import com.azz.platform.user.pojo.vo.Menu;
import com.azz.platform.user.pojo.vo.UserInfo;
import com.azz.platform.user.pojo.vo.UserPermission;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.PasswordHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 刘建麟 2018年10月14日 上午9:27:50
 */
@RestController
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private PlatformUserMapper platformUserMapper;

    @Autowired
    private PlatformPermissionMapper platformPermissionMapper;

    @Override
    public JsonResult<String> loginAuth(@RequestBody LoginParam param) {
        log.debug("————身份认证方法————");
        String phoneNumber = param.getPhoneNumber();
        String password = param.getPassword();
        PlatformUser platformUser = platformUserMapper.getUserByPhoneNumber(phoneNumber);
        if (platformUser == null) {// 无效用户
            throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "无效用户");
        }
        boolean isRight = PasswordHelper.checkPassword(password, platformUser.getSalt(),
                platformUser.getPassword());
        if (!isRight) {// 与盐值加密的密码不匹配
            throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR,
                    "手机号或密码错误");
        }
        return JsonResult.successJsonResult();
    }

    @Override
    public JsonResult<LoginUserInfo> getLoginUserInfoByPhoneNumber(String phoneNumber) {
        LoginUserInfo info = new LoginUserInfo();
        UserInfo userInfo = platformUserMapper.getUserInfoByPhoneNumber(phoneNumber);
        List<UserPermission> userPermissions =
                platformPermissionMapper.getUserPermissionInfoByPhoneNumber(phoneNumber);
        info.setUserInfo(userInfo);
        info.setUserPermissions(userPermissions);
        info.setMenus(generateMenuTree(phoneNumber));
        return JsonResult.successJsonResult(info);
    }
    
    /**
     * 
     * <p>根据手机号查询当前用户角色并生成菜单树</p>
     * @param phoneNumber 手机号
     * @return
     * @author 黄智聪  2018年10月19日 上午10:36:34
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

    @Override
    public JsonResult<String> editPassword(@RequestBody EditPasswordParam param) {
        JSR303ValidateUtils.validate(param);

        // 密码一致性校验
        if (!param.getFirstPassword().equals(param.getSecondPassword())) {
            throw new BaseException(
                    PlatformUserErrorCode.PLATFORM_USER_ERROR_INCONSISTENT_PASSWORD);
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

}
