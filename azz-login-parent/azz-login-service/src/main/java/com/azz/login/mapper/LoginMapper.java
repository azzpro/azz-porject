/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月14日 上午9:22:36
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.login.pojo.Login;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月14日 上午9:22:36
 */
@Mapper
public interface LoginMapper {
	
	Login getLogin(@Param("name") String name,@Param("password") String password);
}

