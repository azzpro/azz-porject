/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月23日 下午4:34:01
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azz.client.user.api.SequenceApi;
import com.azz.client.user.api.UploadApi;
import com.azz.util.Base64;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月23日 下午4:34:01
 */
@RestController
@RequestMapping("/azz/api/client")
public class Demo {
	
	@Autowired
	private UploadApi ua;
	
	@Autowired
	private SequenceApi sa;
	
	@RequestMapping("upload")
	public String get(@RequestParam("file") MultipartFile file) throws IOException {
		String name = file.getOriginalFilename();
		String url = ua.upload(Base64.encode(file.getBytes()), name);
		return url;
	}
	
	@RequestMapping("getsequence")
	public String getsequence() {
		return sa.getsequence();
	}
}

