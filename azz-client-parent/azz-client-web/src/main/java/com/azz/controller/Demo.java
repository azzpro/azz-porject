/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月23日 下午4:34:01
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.system.api.SystemImageUploadService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月23日 下午4:34:01
 */
@RestController
@RequestMapping("/azz/api")
public class Demo {
	
	@Autowired
	private SystemImageUploadService iu;
	
	@RequestMapping("imageUpload")
	public String get(String bucketname,String filename,String suffix,String filedata,Integer imageType,Integer plattype) {
		String image = iu.uploadImage(bucketname, filename, suffix, filedata, plattype, imageType);
		return image;
	}
}

