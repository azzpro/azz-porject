/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午2:11:14
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.client.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.client.user.service.UploadService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月24日 下午2:11:14
 */
@RestController
@RequestMapping("/azz/api/client")
public class UploadController {
	
	@Autowired
	private UploadService us;
	
	@RequestMapping("upload")
	public String upload(String data,String name) {
		return us.upload(data,name);
	}
	
	@RequestMapping("getsequence")
	public String getsequence() {
		return us.getsequence();
	}
}

