package com.azz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableEurekaClient
@SpringBootApplication
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class AzzMerchantWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
	System.setProperty("spring.devtools.restart.enabled", "true");
	SpringApplication.run(AzzMerchantWebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	return builder.sources(this.getClass());
    }

}
