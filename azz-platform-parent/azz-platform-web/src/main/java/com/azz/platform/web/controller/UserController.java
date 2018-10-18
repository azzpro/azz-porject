/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.exception.ShiroAuthException;
import com.azz.core.exception.SuppressedException;
import com.azz.platform.user.api.UserService;
import com.azz.platform.user.pojo.bo.LoginParam;
import com.azz.platform.user.pojo.vo.LoginUserInfo;
import com.azz.util.JSR303ValidateUtils;

/**
 * 
 * <P>登录控制器</P>
 * @version 1.0
 * @author 黄智聪  2018年10月17日 下午1:42:55
 */
@RestController
@RequestMapping("/azz/api/user")
public class UserController {
    
    public static final long SESSION_TIME_OUT_MILLS = 30 * 60 * 1000;
    
    @Autowired
    UserService userService;
    
    /**
     * 
     * <p>未登录</p>
     * @author 黄智聪  2018年10月17日 下午5:50:41
     */
    @RequestMapping(value = "/noLogin")
    public void notLogin() {
        throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_NO_LOGIN);
    }

    /**
     * 
     * <p>无权限</p>
     * @author 黄智聪  2018年10月17日 下午5:50:51
     */
    @RequestMapping(value = "/noPermission")
    public void notRole() {
	throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_NO_PERMISSION);
    }

    /**
     * 
     * <p>登出</p>
     * @return
     * @author 黄智聪  2018年10月17日 下午5:51:01
     */
    @RequestMapping(value = "/logout")
    public JsonResult<String> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return JsonResult.successJsonResult();
    }

    /**
     * 
     * <p>登录</p>
     * @param phoneNumber 手机号
     * @param password 密码
     * @return
     * @author 黄智聪  2018年10月17日 下午5:50:02
     */
    @RequestMapping(value = "/login")
    public JsonResult<LoginUserInfo> login(LoginParam param, HttpServletResponse response) {
	// TODO
	response.setHeader("Access-Control-Allow-Origin", "*");
	response.setHeader("Access-Control-Allow-Method", "POST,GET");
	JSR303ValidateUtils.validate(param);
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(param.getPhoneNumber(), param.getPassword());
        try {
            // 执行认证登陆
            subject.login(token);
            // 设置登录超时时间
            subject.getSession().setTimeout(SESSION_TIME_OUT_MILLS);
	} catch (AuthenticationException e) {
	    Throwable[] throwables = e.getSuppressed();
	    int code = ((SuppressedException) throwables[0]).getCode();
	    String msg = ((SuppressedException) throwables[0]).getMessage();
	    JsonResult<LoginUserInfo> jr = new JsonResult<>();
	    jr.setCode(code);
	    jr.setMsg(msg);
	    return jr;
	}
        return userService.getLoginUserInfoByPhoneNumber(param.getPhoneNumber());
    }
    
    @RequestMapping(value = "/getMessage")
    public String getNomalUserMessage() {
        return "这是普通用户权限";
    }

}
