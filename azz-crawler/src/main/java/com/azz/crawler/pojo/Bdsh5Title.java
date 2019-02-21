/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月20日 下午2:54:38
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年2月20日 下午2:54:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bdsh5Title {
	
	/**
	 * 是否为第一标题
	 */
	private boolean isFirstTitle;
	
	/**
	 * 标题的名字
	 */
	private String name;
	
	/**
	 * 标题对应的url
	 */
	private String url;
	
	/**
	 * 子标题
	 */
	private List<Bdsh5Title> subTitles;
	
}

