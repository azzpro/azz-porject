package com.azz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableTransactionManagement
@EnableHystrix
@EnableHystrixDashboard
public class AzzClientServiceApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "true");
		 SpringApplication.run(AzzClientServiceApplication.class, args);
	}
}
