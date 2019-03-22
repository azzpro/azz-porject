/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月23日 下午1:47:30
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;

/**
 * <P>图片上传</P>
 * @version 1.0
 * @author 刘建麟  2018年10月23日 下午1:47:30
 */
@FeignClient("azz-system-service")
public interface SystemImageUploadService {
	
	/**
	 * <p>上传图片</p>
	 * @param bucketname
	 * @param filename
	 * @param suffix
	 * @param filedata
	 * @param plattype
	 * @param imagetype
	 * @return
	 * @author 刘建麟  2018年10月23日 下午4:31:52
	 */
	@RequestMapping(value="/azz/api/imageUpload")
	JsonResult<String> uploadImage(@RequestParam("bucketname") String bucketname,@RequestParam("filename")String filename,@RequestParam("suffix") String suffix,@RequestParam("filedata") String filedata,@RequestParam("plattype") Integer plattype,@RequestParam("imagetype") Integer imagetype);
}

