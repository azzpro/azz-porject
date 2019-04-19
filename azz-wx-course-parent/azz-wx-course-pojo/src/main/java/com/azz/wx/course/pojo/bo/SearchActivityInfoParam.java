/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 下午5:28:47
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import com.azz.core.common.QueryPage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月4日 下午5:28:47
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SearchActivityInfoParam extends QueryPage{

	private static final long serialVersionUID = 754959189102406644L;
	
	private String activityCode;
	
	private String activityName;
	
	private Byte status;
	
}

