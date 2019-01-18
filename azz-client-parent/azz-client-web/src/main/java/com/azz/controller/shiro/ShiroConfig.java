/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月16日 上午9:51:10
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.controller.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月16日 上午9:51:10
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
	ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
	// 必须设置 SecurityManager
	shiroFilterFactoryBean.setSecurityManager(securityManager);
	// setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
	shiroFilterFactoryBean.setLoginUrl("/azz/api/client/noLogin");
	// 设置无权限时跳转的 url;
	shiroFilterFactoryBean.setUnauthorizedUrl("/azz/api/client/noPermission");
	// 设置拦截器
	Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
	// 开放登陆接口
	filterChainDefinitionMap.put("/azz/api/client/login", "anon");
	filterChainDefinitionMap.put("/azz/api/client/regist", "anon");
	filterChainDefinitionMap.put("/azz/api/client/sendVerificationCode", "anon");
	
	//开放微信回调接口
	filterChainDefinitionMap.put("/azz/api/wechat/callback", "anon");
	
	// 开放官网选型部分接口
	filterChainDefinitionMap.put("/azz/api/client/selection/getSelectionCaseInfos", "anon");
	filterChainDefinitionMap.put("/azz/api/client/selection/getInitParamsByCaseCode", "anon");
	filterChainDefinitionMap.put("/azz/api/client/selection/getCombinationInitParams", "anon");
	filterChainDefinitionMap.put("/azz/api/client/selection/getCombinationParams", "anon");
	filterChainDefinitionMap.put("/azz/api/client/selection/getCombinationInfos", "anon");
	filterChainDefinitionMap.put("/azz/api/client/selection/getCombinationDetail", "anon");
	filterChainDefinitionMap.put("/azz/api/client/selection/getProductInfos", "anon");
	filterChainDefinitionMap.put("/azz/api/client/selection/getProductPrice", "anon");
	filterChainDefinitionMap.put("/azz/api/pay/payNotify", "anon");
	// 开放官网选型二期接口
	filterChainDefinitionMap.put("/azz/api/client/selection/getSelectionIndexData", "anon");
	filterChainDefinitionMap.put("/azz/api/client/selection/getSelectionModuleInfos", "anon");
	filterChainDefinitionMap.put("/azz/api/client/selection/getSelectionModuleParams", "anon");
	filterChainDefinitionMap.put("/azz/api/client/selection/getModuleDetail", "anon");
	filterChainDefinitionMap.put("/azz/api/client/selection/getClassificationChildPagination", "anon");
	filterChainDefinitionMap.put("/azz/api/client/selection/getClassificationList", "anon");
	
	// 官网首页数据
	filterChainDefinitionMap.put("/azz/api/index/*", "anon");
	
	// 其余接口一律拦截
	// 主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
	filterChainDefinitionMap.put("/**", "authc");
	shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	return shiroFilterFactoryBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
	// 对响应头进行CORS授权
	ShiroCorsRegistration corsRegistration = new ShiroCorsRegistration("*");
	corsRegistration
		// 允许向该服务器提交请求的URI，*表示全部允许
		.allowedOrigins("*")
		// 允许提交请求的方法，*表示全部允许
		.allowedMethods("*")
		// 允许的头信息,*标识全部允许
		.allowedHeaders("*")
		// 暴露的头信息
		.exposedHeaders(HttpHeaders.SET_COOKIE)
		// 允许Cookie跨域，在做登录校验的时候有用
		.allowCredentials(true);
	// 注册CORS过滤器
	UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
	// 第一个参数表示过滤的url,*表示过滤所有
	configurationSource.registerCorsConfiguration("/**", corsRegistration.getCorsConfiguration());
	CorsFilter corsFilter = new CorsFilter(configurationSource);
	return new FilterRegistrationBean(corsFilter);
    }

    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager() {
	DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
	// 设置realm.
	securityManager.setRealm(customRealm());
	securityManager.setSessionManager(sessionManager());
	return securityManager;
    }
    
    @Bean
    public SessionManager sessionManager() {
	return new ShiroSessionManager();
    }
    
    /**
     * 自定义身份认证 realm;
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm， 否则会影响 CustomRealm类 中其他类的依赖注入
     */
    @Bean
    public CustomRealm customRealm() {
	return new CustomRealm();
    }
}
