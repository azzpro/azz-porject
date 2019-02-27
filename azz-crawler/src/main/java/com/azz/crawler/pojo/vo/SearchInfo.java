/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月20日 下午1:51:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.pojo.vo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年2月20日 下午1:51:43
 */
@Data
public class SearchInfo {
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 描述
	 */
	private String desc;
	
	/**
	 * 联系人手机号
	 */
	private String phoneNumber;
	
	/**
	 * 联系人姓名
	 */
	private String name;
	
	/**
	 * 其他描述
	 */
	private String otherDesc;

}

