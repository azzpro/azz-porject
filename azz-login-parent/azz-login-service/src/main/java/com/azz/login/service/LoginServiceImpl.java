/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月14日 上午9:27:50
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.azz.login.api.LoginService;
import com.azz.login.mapper.LoginMapper;
import com.azz.login.pojo.Login;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月14日 上午9:27:50
 */
@RestController
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginMapper loginMapper;
	

	/**
	 * @see com.azz.login.api.LoginService#getLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public Login getLogin(String name, String password) {
		return loginMapper.getLogin(name, password);
	}
	
	
	

}

