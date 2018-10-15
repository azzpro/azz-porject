/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午2:52:17
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.login.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.azz.login.pojo.Login;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月15日 下午2:52:17
 */
@FeignClient("azz-login-service")
public interface LoginService {
	
	/**
	 * <p>TODO</p>
	 * @param name
	 * @param password
	 * @return
	 * @author 刘建麟  2018年10月15日 下午2:57:58
	 */
	@GetMapping("getLogin")
	Login getLogin(String name, String password);
}

