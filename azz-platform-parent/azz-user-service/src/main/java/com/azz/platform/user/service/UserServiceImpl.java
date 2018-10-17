/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月14日 上午9:27:50
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.exception.BaseException;
import com.azz.platform.user.api.UserService;
import com.azz.platform.user.mapper.PlatformUserMapper;
import com.azz.platform.user.pojo.PlatformUser;
import com.azz.platform.user.pojo.bo.LoginParam;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.PasswordHelper;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月14日 上午9:27:50
 */
@RestController
public class UserServiceImpl implements UserService{

	@Autowired
	private PlatformUserMapper platformUserMapper;

	@Override
	public JsonResult<PlatformUser> login(LoginParam param) {
	    JSR303ValidateUtils.validate(param);
	    PlatformUser platformUser = platformUserMapper.getUserByPhoneNumber(param.getPhoneNumber());
	    if(platformUser == null) {// 无效用户
	        throw new BaseException(PlatformUserErrorCode.PLATFORM_USER_ERROR_INVALID_USER);
	    }
	    boolean isRight = PasswordHelper.checkPassword(param.getPassword(), platformUser.getSalt(),platformUser.getPassword());
            if (!isRight) {// 与盐值加密的密码不匹配
        	 throw new BaseException(PlatformUserErrorCode.PLATFORM_USER_ERROR_WRONG_PHONE_OR_PASSWORD);
            }
            return JsonResult.successJsonResult(platformUser);
            
	}

}

