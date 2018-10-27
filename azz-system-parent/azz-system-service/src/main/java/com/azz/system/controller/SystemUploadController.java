/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月23日 上午10:40:47
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.controller;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.system.service.SystemImageService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月23日 上午10:40:47
 */
@RestController
@RequestMapping("/azz/api")
public class SystemUploadController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SystemUploadController.class);
	
	@Autowired
	private SystemImageService imageService;
	
	
	/**
	 * <p>上传图片</p>
	 * @param bucketname
	 * @param filename
	 * @param suffix
	 * @param filedata
	 * @param plattype
	 * @param imagetype
	 * @return
	 * @throws FileNotFoundException
	 * @author 刘建麟  2018年10月23日 下午4:19:44
	 */
	@RequestMapping(value="imageUpload",method=RequestMethod.POST)
	public JsonResult<String> imageUpload(String bucketname,String filename,String suffix,String filedata,Integer plattype,Integer imagetype) throws FileNotFoundException {
		JsonResult<String> image = imageService.uploadImage(bucketname,filename,suffix,filedata,plattype,imagetype);
		return image;
	}
	
	/**
	 * <p>创建存储空间</p>
	 * @param bucketname
	 * @return
	 * @author 刘建麟  2018年10月23日 下午2:25:34
	 */
	@Deprecated
	@RequestMapping(value="createBucketName",method=RequestMethod.POST)
	public JsonResult<String> createBucketName(String bucketname){
		String name = imageService.createBucketName(bucketname);
		return JsonResult.successJsonResult(name);
	}
	
}

