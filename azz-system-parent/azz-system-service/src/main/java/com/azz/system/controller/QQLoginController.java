package com.azz.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.system.bo.QQClientRegistParam;
import com.azz.system.pojo.ClientWxUser;
import com.azz.system.service.QQLoginService;
import com.azz.system.vo.QQCallBackInfo;
import com.azz.system.vo.QQLoginInfo;

@RestController
@RequestMapping("/azz/api/qq")
public class QQLoginController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private QQLoginService qqLoginService;
	
	
	/**
	 * QQ回调
	 * @param code
	 * @param state
	 * @param key
	 * @return
	 */
	@RequestMapping("callback")
	public JsonResult<QQCallBackInfo> callback(HttpServletRequest request,@RequestParam("access_token")String access_token,@RequestParam("openid")String openid) {
		return qqLoginService.callback(request,access_token,openid);
	}
	
	/**
	 * 绑定或者注册
	 * @param wcbi
	 * @return
	 */
	@RequestMapping("goBindOrReg")
	public JsonResult<QQCallBackInfo> goBindOrReg(@RequestBody QQCallBackInfo wcbi) {
		return qqLoginService.goBindOrReg(wcbi);
	}
	
	@RequestMapping("insert")
	public Integer insert(@RequestBody ClientWxUser wsc) {
		int i = qqLoginService.insert(wsc);
		return i;
	}
	
	/**
	 * 注册并绑定
	 * @param wcrp
	 * @return
	 */
	@RequestMapping("regAndBind")
	public JsonResult<QQLoginInfo> regAndBind(@RequestBody QQClientRegistParam wcrp) {
		return qqLoginService.regAndBind(wcrp);
	}
}
