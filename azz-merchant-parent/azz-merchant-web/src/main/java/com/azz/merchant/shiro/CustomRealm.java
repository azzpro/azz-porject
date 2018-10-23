/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月16日 上午9:52:54
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.exception.SuppressedException;
import com.azz.merchant.api.MerchantService;
import com.azz.merchant.pojo.bo.LoginParam;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月16日 上午9:52:54
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    MerchantService merchantService;

    /**
     * 获取身份验证信息 Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken
     *            用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
	    throws AuthenticationException {
	UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
	String phoneNumber = token.getUsername();
	String password = new String((char[]) token.getCredentials());
	LoginParam param = new LoginParam(phoneNumber, password);
	JsonResult<String> jr = merchantService.loginAuth(param);
	if(jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
	    AuthenticationException authException = new AuthenticationException();
	    authException.addSuppressed(new SuppressedException(jr.getCode(),jr.getMsg()));
	    throw authException;
	}
	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
	return info;
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
	/*System.out.println("————权限认证————");
	String username = (String) SecurityUtils.getSubject().getPrincipal();*/
	SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	/*// 获得该用户角色
	String role = null;
	Set<String> set = new HashSet<>();
	// 需要将 role 封装到 Set 作为 info.setRoles() 的参数
	set.add(role);
	// 设置该用户拥有的角色
	info.setRoles(set);*/
	return info;
    }
}
