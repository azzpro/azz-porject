package com.azz.druid.monitor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;


/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月13日 上午11:08:19
 */
@Component
@ConditionalOnProperty(name = "azz.datasource.monitorConfig.available", havingValue = "true", matchIfMissing = false)
public class DruidMonitorConfiguration {

    @Autowired
    private MonitorConfig monitorConfig;

  
    /**
     * <p>TODO</p>
     * @return
     * @author 刘建麟  2018年10月13日 上午11:08:45
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlte() {
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow", monitorConfig.getAllow());
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", monitorConfig.getDeny());
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", monitorConfig.getLoginUsername());
        servletRegistrationBean.addInitParameter("loginPassword", monitorConfig.getLoginPassword());
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", monitorConfig.getResetEnable());
        return servletRegistrationBean;
    }

   
    /**
     * <p>TODO</p>
     * @return
     * @author 刘建麟  2018年10月13日 上午11:08:39
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        return filterRegistrationBean;
    }

}