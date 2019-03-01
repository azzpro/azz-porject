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
public class KeyWordDataConfig {
	
	/**
	 * 
	 * <p>本地生活关键字数据实例</p>
	 * @return
	 * @author 黄智聪  2019年2月19日 下午6:37:43
	 */
	@Bean
	public Bdsh5KeyWordData getBdsh5Data() {
		return new Bdsh5KeyWordData();
	}
	
	/**
	 * 
	 * <p>百姓网关键字数据实例</p>
	 * @return
	 * @author 黄智聪  2019年2月19日 下午6:37:43
	 */
	@Bean
	public BaixingKeyWordData getBaixingData() {
		return new BaixingKeyWordData();
	}

	/**
	 * <p>赶集网关键字数据实例</p>
	 * @return
	 * @author 彭斌  2019年2月27日 下午3:19:09
	 */
	@Bean
    public GanjiKeyWordData getGanjiKeyWordData() {
        return new GanjiKeyWordData();
    }
}

