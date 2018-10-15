package com.azz.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AzzEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzzEurekaServerApplication.class, args);
	}
}
