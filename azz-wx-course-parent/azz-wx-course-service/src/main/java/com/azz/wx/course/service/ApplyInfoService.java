/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月22日 上午10:40:05
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.azz.wx.course.mapper.WxCourseAapplyInfoMapper;

/**
 * <P>报名信息业务</P>
 * @version 1.0
 * @author 黄智聪  2019年1月22日 上午10:40:05
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ApplyInfoService {
	
	@Autowired
	private WxCourseAapplyInfoMapper wxCourseAapplyInfoMapper;
	
}

