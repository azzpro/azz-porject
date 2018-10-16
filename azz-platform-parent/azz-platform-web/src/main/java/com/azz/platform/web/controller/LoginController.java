/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.azz.login.api.LoginService;
import com.azz.login.pojo.Login;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 刘建麟 2018年10月15日 下午3:05:46
 */
@RestController
@RequestMapping("/azz/api/login")
public class LoginController {

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	/**
	 * <p>
	 * 登录
	 * </p>
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @author 刘建麟 2018年10月16日 上午10:49:10
	 */
	@RequestMapping("goLogin")
	public JSONObject goLogin(String name, String password) {
		LOG.info("用户名:" + name + ":密码:" + password);
		JSONObject jb = new JSONObject();
		JSONObject jb1 = new JSONObject();
		//token
		jb1.put("access_token", "aaaaaaaa");
		jb.put("code", 0);
		jb.put("msg", "登入成功");
		jb.put("data", jb1);
		return jb;
	}

}
