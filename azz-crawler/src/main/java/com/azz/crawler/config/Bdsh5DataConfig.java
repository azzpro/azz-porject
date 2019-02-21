/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月19日 下午6:35:42
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年2月19日 下午6:35:42
 */
@Configuration
public class Bdsh5DataConfig {
	
	/**
	 * 
	 * <p>本地生活数据实例</p>
	 * @return
	 * @author 黄智聪  2019年2月19日 下午6:37:43
	 */
	@Bean
	public Bdsh5Data getBdsh5Data() {
		return new Bdsh5Data();
	}

}

