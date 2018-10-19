/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.web.controller;

import java.util.List;

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
import com.azz.core.exception.ShiroAuthException;
import com.azz.core.exception.SuppressedException;
import com.azz.platform.user.api.PermissionService;
import com.azz.platform.user.api.UserService;
import com.azz.platform.user.pojo.bo.AddRoleParam;
import com.azz.platform.user.pojo.bo.DelRoleParam;
import com.azz.platform.user.pojo.bo.EditPasswordParam;
import com.azz.platform.user.pojo.bo.EditRoleParam;
import com.azz.platform.user.pojo.bo.LoginParam;
import com.azz.platform.user.pojo.bo.SearchRoleParam;
import com.azz.platform.user.pojo.vo.LoginUserInfo;
import com.azz.platform.user.pojo.vo.Permission;
import com.azz.platform.user.pojo.vo.RoleInfo;
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

    @Autowired
    PermissionService permissionService;

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
	WebUtils.setShiroSessionAttr("loginUser", jr.getData());
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

    @RequestMapping(value = "/getMessage")
    public LoginUserInfo getNomalUserMessage() {
	return WebUtils.getLoginUser();
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

    @RequestMapping(value = "/addRolePermission")
    public JsonResult<String> addRolePermission(AddRoleParam param) {
	param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
	return permissionService.addRolePermission(param);
    }
    
    /**
     * 
     * <p>修改角色权限</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月19日 上午9:26:26
     */
    @RequestMapping(value = "/editRolePermission")
    public JsonResult<String> editRolePermission(EditRoleParam param) {
	return permissionService.editRolePermission(param);
    }
    
    /**
     * 
     * <p>删除角色（逻辑删除）</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月19日 上午9:26:26
     */
    @RequestMapping(value = "/delRole")
    public JsonResult<String> delRole(DelRoleParam param) {
	param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
	return permissionService.delRole(param);
    }
    
    /**
     * 
     * <p>查询角色列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月19日 上午9:26:26
     */
    @RequestMapping(value = "/getRoleList")
    public JsonResult<List<RoleInfo>> getRoleList(SearchRoleParam param) {
	return permissionService.getRoleList(param);
    }
    
    /**
     * 
     * <p>查询所有权限</p>
     * @return
     * @author 黄智聪  2018年10月19日 上午9:22:49
     */
    @RequestMapping(value = "/getPermissions")
    public JsonResult<List<Permission>> getPermissions() {
	return permissionService.getPermissions();
    }
    
}
