/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月14日 上午9:27:50
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.exception.ShiroAuthException;
import com.azz.platform.user.api.UserService;
import com.azz.platform.user.mapper.PlatformUserMapper;
import com.azz.platform.user.pojo.PlatformUser;
import com.azz.platform.user.pojo.bo.LoginParam;
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

    @Override
    public JsonResult<String> loginAuth(@RequestBody LoginParam param) {
	log.debug("————身份认证方法————");
	String phoneNumber = param.getPhoneNumber();
	String password = param.getPassword();
	PlatformUser platformUser = platformUserMapper.getUserByPhoneNumber(phoneNumber);
	if (platformUser == null) {// 无效用户
	    throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "无效用户");
	}
	boolean isRight = PasswordHelper.checkPassword(password, platformUser.getSalt(), platformUser.getPassword());
	if (!isRight) {// 与盐值加密的密码不匹配
	    throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "手机号或密码错误");
	}
	return JsonResult.successJsonResult();
    }

}
