/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月23日 下午1:52:04
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.ObjectMetadata;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.constants.FileConstants;
import com.azz.exception.JSR303ValidationException;
import com.azz.util.AzzImageUtil;
import com.azz.util.Base64;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月23日 下午1:52:04
 */
@Service
public class SystemImageService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SystemImageService.class);
	
	@Value("${aliyun.endpoint}")
	private String endpoint;
	
	@Value("${aliyun.accessKeyId}")
	private String accessKeyId;
	
	@Value("${aliyun.accessKeySecret}")
	private String accessKeySecret;
	
	public String uploadImage(String bucketname,String filename,String suffix,String filedata,Integer imageType,Integer plattype){
		if(StringUtils.isBlank(bucketname)) {
    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"存储空间名称不能为空");
    	}
		if(!bucketNameExist(bucketname)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"存储空间名称不存在");
		}
		if(StringUtils.isBlank(filename)) {
    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"文件名不能为空");
    	}
		if(StringUtils.isBlank(suffix)) {
    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"文件后缀不能为空");
    	}
		//生产环境放开
		if(StringUtils.isBlank(filedata) || filedata.length() <= 0) {
    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"文件数据不能为空");
    	}
		if(null == imageType) {
    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"图片类型不能为空");
    	}
		if(null == plattype) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"平台类型不能为空");
		}
		//后缀
		boolean contains = FileConstants.AZZ_IMAGE_SUFFIX.contains(suffix);
		
		if(!contains) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"不支持的文件后缀");
		}
		
		//返回图片Url
		StringBuilder imageurl = new StringBuilder(endpoint);
		//文件名
		StringBuilder finalName = new StringBuilder();
		//获取平台名 ：平台端 商户断 客户端
		String type = AzzImageUtil.getPlatByType(plattype);
		if(StringUtils.isBlank(type) || type.length() <= 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"平台类型错误");
		}else {
			finalName.append(type);
		}
		//获取图片类型：营业执照  法人照片 图像 其他
		String imagetype = AzzImageUtil.getImageByType(imageType);
		if(StringUtils.isBlank(imagetype) || imagetype.length() <= 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"图片类型错误");
		}else {
			finalName.append("/");
			finalName.append(imagetype);
			finalName.append("/");
		}
		finalName.append(filename).append(".").append(suffix);
		LOG.info("生成的图片名称------->["+finalName.toString()+"]");
		//base64 
		/**生产环境使用****/
		byte[] decode = Base64.decode(filedata);
		InputStream is = new ByteArrayInputStream(decode);
		
		/**本地测试****/
		/*InputStream is = null;
		try {
			 is = new FileInputStream(new File("D:\\123.jpg"));
		}catch (FileNotFoundException e) {
			LOG.error("文件找不到");
		}*/
		//判断文件大小 不能超过20MB
		try {
			if(FileConstants.IMAGE_SIZE < is.available()/1024/1024) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"图片超过20MB");
			}
		}catch (IOException e) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"获取图片大小失败");
		}
		
		//设置文件元信息
		ObjectMetadata meta = new ObjectMetadata();
		try {
			meta.setContentLength(is.available());
		}catch (IOException e) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"文件无效");
		}
		
		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 上传文件流。
		ossClient.putObject(bucketname, finalName.toString(), is);
		// 关闭OSSClient。
		ossClient.shutdown();
		imageurl.insert(7, bucketname+".");
		imageurl.append("/").append(finalName.toString());
		return imageurl.toString();
	}
	
	
	/**
	 * <p>创建存储空间</p>
	 * @author 刘建麟  2018年10月23日 下午2:12:49
	 */
	public String createBucketName(String bucketname) {
		if(StringUtils.isBlank(bucketname)) {
    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"存储空间名称不能为空");
    	}
		boolean b = bucketNameExist(bucketname);
		if(!b) {
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			Bucket bucket = ossClient.createBucket(bucketname);
			return bucket.getName();
		}else {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"存储空间名称已存在");
		}
		
	}
	
	/**
	 * <p>存储空间是否存在</p>
	 * @author 刘建麟  2018年10月23日 下午2:12:49
	 */
	public Boolean bucketNameExist(String bucketname) {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		boolean exist = ossClient.doesBucketExist(bucketname);
		ossClient.shutdown();
		return exist;
	}
	
	/**
	 * <p>删除存储空间</p>
	 * @author 刘建麟  2018年10月23日 下午2:12:49
	 */
	public void deleteBucketName(String bucketname) {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		ossClient.deleteBucket(bucketname);
		ossClient.shutdown();
	}
}

