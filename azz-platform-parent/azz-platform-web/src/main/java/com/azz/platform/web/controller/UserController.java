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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.exception.ShiroAuthException;
import com.azz.core.exception.SuppressedException;
import com.azz.platform.user.api.UserService;
import com.azz.platform.user.pojo.bo.AddUserParam;
import com.azz.platform.user.pojo.bo.EditPasswordParam;
import com.azz.platform.user.pojo.bo.EditUserParam;
import com.azz.platform.user.pojo.bo.EnableOrDisableOrDelUserParam;
import com.azz.platform.user.pojo.bo.LoginParam;
import com.azz.platform.user.pojo.bo.SearchUserParam;
import com.azz.platform.user.pojo.vo.LoginUserInfo;
import com.azz.platform.user.pojo.vo.UserInfo;
import com.azz.util.JSR303ValidateUtils;
import com.azz.utils.WebUtils;

/**
 * 
 * <P>
 * 登录控制器
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月17日 下午1:42:55
 */
@RestController
@RequestMapping("/azz/api/user")
public class UserController {

    @Value("${shiro.session.timeout}")
    private Long sessionTimeout;

    @Autowired
    UserService userService;

    /**
     * 
     * <p>
     * 未登录
     * </p>
     * 
     * @author 黄智聪 2018年10月17日 下午5:50:41
     */
    @RequestMapping(value = "/noLogin")
    public void notLogin() {
	throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_NO_LOGIN);
    }

    /**
     * 
     * <p>
     * 无权限
     * </p>
     * 
     * @author 黄智聪 2018年10月17日 下午5:50:51
     */
    @RequestMapping(value = "/noPermission")
    public void notRole() {
	throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_NO_PERMISSION);
    }

    /**
     * 
     * <p>
     * 登出
     * </p>
     * 
     * @return
     * @author 黄智聪 2018年10月17日 下午5:51:01
     */
    @RequestMapping(value = "/logout")
    public JsonResult<String> logout() {
	Subject subject = SecurityUtils.getSubject();
	subject.logout();
	return JsonResult.successJsonResult();
    }

    /**
     * 
     * <p>
     * 登录
     * </p>
     * 
     * @param phoneNumber
     *            手机号
     * @param password
     *            密码
     * @return
     * @author 黄智聪 2018年10月17日 下午5:50:02
     */
    @RequestMapping(value = "/login")
    public JsonResult<LoginUserInfo> login(LoginParam param) {
	JSR303ValidateUtils.validate(param);
	// 从SecurityUtils里边创建一个 subject
	Subject subject = SecurityUtils.getSubject();
	// 在认证提交前准备 token（令牌）
	UsernamePasswordToken token = new UsernamePasswordToken(param.getPhoneNumber(), param.getPassword());
	try {
	    // 执行认证登陆
	    subject.login(token);
	    // 设置登录超时时间
	    subject.getSession().setTimeout(sessionTimeout);
	} catch (AuthenticationException e) {
	    Throwable[] throwables = e.getSuppressed();
	    int code = ((SuppressedException) throwables[0]).getCode();
	    String msg = ((SuppressedException) throwables[0]).getMessage();
	    JsonResult<LoginUserInfo> jr = new JsonResult<>();
	    jr.setCode(code);
	    jr.setMsg(msg);
	    return jr;
	}
	JsonResult<LoginUserInfo> jr = userService.getLoginUserInfoByPhoneNumber(param.getPhoneNumber());
	LoginUserInfo loginUser = jr.getData();
	loginUser.setSessionId(subject.getSession().getId());
	WebUtils.setShiroSessionAttr("loginUser", loginUser);
	return jr;
    }
    
    /**
     * 
     * <p>供前端调用，测试是否已经登录失效</p>
     * @return
     * @author 黄智聪  2018年10月19日 上午9:22:49
     */
    @RequestMapping(value = "/check")
    public JsonResult<String> check() {
	return JsonResult.successJsonResult();
    }

    /**
     * <p>
     * 修改密码
     * </p>
     * 
     * @param param
     * @return
     * @author 彭斌 2018年10月18日 下午3:02:23
     */
    @RequestMapping(value = "/editPassword")
    public JsonResult<String> editPassword(EditPasswordParam param){
        param.setUserInfo(WebUtils.getLoginUser().getUserInfo());
        return userService.editPassword(param);
    }
    
    /**
     * <p>
     * 新增用户
     * </p>
     * 
     * @param param
     * @return
     * @author 黄智聪 2018年10月18日 下午3:02:23
     */
    @RequestMapping(value = "/addUser")
    public JsonResult<String> addUser(AddUserParam param){
        param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
        return userService.addUser(param);
    }
    
    /**
     * <p>
     * 修改用户
     * </p>
     * 
     * @param param
     * @return
     * @author 黄智聪 2018年10月18日 下午3:02:23
     */
    @RequestMapping(value = "/editUser")
    public JsonResult<String> editUser(EditUserParam param){
        param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
        return userService.editUser(param);
    }
    
    /**
     * <p>
     * 根据条件查询用户列表
     * </p>
     * 
     * @param param
     * @return
     * @author 黄智聪 2018年10月18日 下午3:02:23
     */
    @RequestMapping(value = "/getUserList")
    public JsonResult<Pagination<UserInfo>> getUserList(SearchUserParam param){
        return userService.getUserList(param);
    }
    
    /**
     * <p>
     * 修改用户状态（禁用、启用、删除）
     * </p>
     * 
     * @param param
     * @return
     * @author 黄智聪 2018年10月18日 下午3:02:23
     */
    @RequestMapping(value = "/editUserStatus")
    public JsonResult<String> disableUser(EnableOrDisableOrDelUserParam param){
	return userService.enableOrDisableOrDelUser(param);
    }
    
    /**
     * <p>
     * 查询用户详情
     * </p>
     * 
     * @param param
     * @return
     * @author 黄智聪 2018年10月18日 下午3:02:23
     */
    @RequestMapping(value = "/getUserInfo")
    public JsonResult<UserInfo> getUserInfo(String userCode){
	return userService.getUserInfo(userCode);
    }
    
    
    
}
