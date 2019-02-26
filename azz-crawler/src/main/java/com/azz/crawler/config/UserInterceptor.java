/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月26日 下午1:40:07
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.azz.crawler.common.ManagerHttpUtils;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年2月26日 下午1:40:07
 */
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        if(null == ManagerHttpUtils.getUser(request.getSession())) {
            System.out.println("===============成功拦截失效未登陆的路由！====================");
            response.sendRedirect(request.getContextPath() + "/azz/crawler/login");
            return false;
        } else {
            System.out.println("===============校验成功！====================");
            response.sendRedirect(request.getContextPath() + "/azz/crawler/index");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {}
}

