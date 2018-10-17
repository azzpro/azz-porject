/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.exception.BaseException;
import com.azz.core.exception.SuppressedException;

/**
 * 
 * <P>登录控制器</P>
 * @version 1.0
 * @author 黄智聪  2018年10月17日 下午1:42:55
 */
@RestController
@RequestMapping("/azz/api/user")
public class UserController {
    
    @RequestMapping(value = "/noLogin")
    public void notLogin() {
        throw new BaseException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_NO_LOGIN);
    }

    @RequestMapping(value = "/noPermission")
    public void notRole() {
	throw new BaseException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_NO_PERMISSION);
    }

    @RequestMapping(value = "/logout")
    public JsonResult<String> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return JsonResult.successJsonResult();
    }

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     */
    @RequestMapping(value = "/login")
    public JsonResult<String> login(String username, String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            // 执行认证登陆
            subject.login(token);
	} catch (AuthenticationException e) {
	    Throwable[] throwables = e.getSuppressed();
	    int code = ((SuppressedException) throwables[0]).getCode();
	    String msg = ((SuppressedException) throwables[0]).getMessage();
	    JsonResult<String> jr = new JsonResult<>();
	    jr.setCode(code);
	    jr.setMsg(msg);
	    return jr;
	}
        return JsonResult.successJsonResult();
    }

}
