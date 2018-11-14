package com.azz.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@SpringBootApplication
@EnableZipkinStreamServer
@EnableEurekaClient
public class AzzZipkinServerApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(AzzZipkinServerApplication.class, args);
	}
}
