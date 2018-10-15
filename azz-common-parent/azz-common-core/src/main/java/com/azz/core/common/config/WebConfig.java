/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午7:35:16
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.core.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <P>web相关配置</P>
 * @version 1.0
 * @author 黄智聪  2018年10月15日 下午7:35:16
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowedOrigins(CorsConfiguration.ALL);
        super.addCorsMappings(registry);
    }
    
    @Override  
    public void addInterceptors(InterceptorRegistry registry) {  
        //注册自定义拦截器，添加拦截路径和排除拦截路径  
    }
}

