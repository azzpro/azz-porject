package com.azz;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class AzzCrawlerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(AzzCrawlerApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
    }
	
	 @Bean
	 public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
		 return new EmbeddedServletContainerCustomizer() {
			 @Override
			 public void customize(ConfigurableEmbeddedServletContainer container) {
				 //设置session超时时间为1分钟
				 container.setSessionTimeout(365, TimeUnit.DAYS);
			 }
		 };
	 }

}