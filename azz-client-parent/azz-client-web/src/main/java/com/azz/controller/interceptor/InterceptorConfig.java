/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年5月6日 上午10:18:57
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.controller.interceptor;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.exception.ShiroAuthException;
import com.azz.core.reconstructed.exception.ValidationException;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年5月6日 上午10:18:57
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration interceptor = registry.addInterceptor(new ActivityInterceptor());
        interceptor.addPathPatterns("/azz/api/client/activity/*");
        interceptor.excludePathPatterns("/azz/api/client/activity/login");
	}
	
	class ActivityInterceptor implements HandlerInterceptor{

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			Subject subject = SecurityUtils.getSubject();
			if(!subject.isAuthenticated()) {
				throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_NO_LOGIN);
			}
			return true;
		}

		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
		}

		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
				Exception ex) throws Exception {
		}
		
	}
	
}

