/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月18日 下午3:23:27
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.azz.platform.user.pojo.vo.LoginUserInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月18日 下午3:23:27
 */
public class WebUtils {
    
    
    public static void setShiroSessionAttr(Object key, Object value) {
	getShiroSession().setAttribute(key, value);
    }
    
    public static LoginUserInfo getLoginUser() {
	return (LoginUserInfo)getShiroSession().getAttribute("loginUser");
    }
    
    public static Session getShiroSession() {
	return SecurityUtils.getSubject().getSession();
    }

}

