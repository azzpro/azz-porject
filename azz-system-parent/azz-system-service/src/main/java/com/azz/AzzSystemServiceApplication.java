package com.azz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class AzzSystemServiceApplication {

	public static void main(String[] args) {
		 System.setProperty("spring.devtools.restart.enabled", "true");
		 SpringApplication.run(AzzSystemServiceApplication.class, args);
	}
}
