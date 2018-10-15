package com.azz.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.azz.core.exception.handler.BaseExceptionHandler;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@RestControllerAdvice
public class AzzGoodsServiceApplication extends BaseExceptionHandler{

	public static void main(String[] args) {
		 SpringApplication.run(AzzGoodsServiceApplication.class, args);
	}
}
