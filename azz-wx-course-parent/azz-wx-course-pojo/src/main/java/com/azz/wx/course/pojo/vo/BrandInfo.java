/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月2日 下午2:42:38
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.vo;

import java.util.Date;
import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月4日 下午3:25:00
 */
@Data
public class BrandInfo {
	
	private String brandName;
	
	private String brandPicUrl;
	
	private String brandCode;
	
	private String brandDescription;
	
	private Date createTime;

}

