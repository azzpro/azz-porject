/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.azz.login.api.LoginService;
import com.azz.login.pojo.Login;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月15日 下午3:05:46
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {
	
	@Autowired
	private LoginService lsc;
	
	@RequestMapping(value="goLogin",produces = "application/json;charset=UTF-8")
	public JSONObject goLogin(String data) {
		JSONObject object = JSON.parseObject(data);
		JSONObject s = (JSONObject)object.get("field");
		JSONObject jb = new JSONObject();
		JSONObject jb1 = new JSONObject();
		Login login = lsc.getLogin(s.getString("username"), s.getString("password"));
		if(null != login) {
			jb1.put("access_token", "aaaaaaaa");
			jb.put("code", 0);
			jb.put("msg", "登入成功");
			jb.put("data", jb1);
		}
		return jb;
	}
}

