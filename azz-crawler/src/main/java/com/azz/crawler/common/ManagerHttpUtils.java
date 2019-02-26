/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月26日 下午2:11:54
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.azz.crawler.pojo.User;

import lombok.experimental.UtilityClass;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年2月26日 下午2:11:54
 */
@UtilityClass
public class ManagerHttpUtils {
    /**
     * @param @param user
     * @param @param httpSession
     * @return void
     * @throws
     * @Title: setUser
     * @Description: 设值session用户信息
     * @author: PengBin
     */
    public static void setUser(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(30 * 60); // 设置session失效时间
        session.setAttribute("USER", user);
    }

    /**
     * @param @param  httpSession
     * @param @return
     * @return User
     * @throws
     * @Title: getUser
     * @Description: 获取session用户信息，需判断是否为null
     * @author: PengBin
     */
    public static User getUser(HttpSession session) {
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("USER");
        }
        return user;
    }

    /**
     * 用户退出
     */
    public static void exitUserInfo(HttpSession sessions) {
        if (sessions != null) {
            sessions.removeAttribute("USER");
        }
    }
}

