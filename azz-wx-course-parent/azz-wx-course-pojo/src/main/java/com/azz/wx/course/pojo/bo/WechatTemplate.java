/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年4月16日 下午3:08:24
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import java.util.Map;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年4月16日 下午3:08:24
 */
@Data
public class WechatTemplate {
	
	private String touser;
	
	private String template_id;
	
	private String url;
	
	private Map<String, TemplateData> data;
 
}

