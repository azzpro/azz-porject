/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午2:03:13
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.client.user.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azz.core.constants.FileConstants;
import com.azz.system.api.SystemImageUploadService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月24日 下午2:03:13
 */
@Service
public class UploadService {
	
	@Autowired
	private SystemImageUploadService sis;
	
	public String upload(String data,String name) {
		return sis.uploadImage(FileConstants.IMAGE_BUCKETNAME, StringUtils.substringBefore(name, "."), StringUtils.substringAfter(name, "."), data, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_AVATAR_IMAGE_TYPE);
	}
}

