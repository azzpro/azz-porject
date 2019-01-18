package com.azz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.system.api.WxLoginService;
import com.azz.system.bo.WxClientRegistParam;
import com.azz.system.bo.WxLoginParam;
import com.azz.system.vo.WxCallBackInfo;
import com.azz.system.vo.WxInfo;

/**
 * @author THINK
 * 微信登录
 */
@RestController
@RequestMapping("/azz/api/wechat")
public class WxLoginController {

	@Autowired
	private WxLoginService wxLoginService;
	
	/**
	 * 去到微信扫码页面
	 * @return
	 */
	@RequestMapping(value="goWxScanPage",method=RequestMethod.POST)
	public JsonResult<WxInfo> goWxScanPage(){
		return wxLoginService.goWxScanPage();
	}
		
	
	/**
	 * 微信回调
	 * @param code
	 * @param state
	 * @param key
	 * @return
	 */
	@RequestMapping(value="callback",method=RequestMethod.POST)
	public JsonResult<WxCallBackInfo> callback(@RequestParam("code")String code,@RequestParam("state") String state,@RequestParam("key") String key) {
		return wxLoginService.callback(code, state, key);
	}
	
	/**
	 * 绑定或者注册
	 * @param wcbi
	 * @return
	 */
	@RequestMapping(value="goBindOrReg",method=RequestMethod.POST)
	public JsonResult<WxCallBackInfo> goBindOrReg( WxCallBackInfo wcbi) {
		return wxLoginService.goBindOrReg(wcbi);
	}
	
	/**
	 * 登录并绑定
	 * @param param
	 * @return
	 */
	@RequestMapping(value="loginAndBind",method=RequestMethod.POST)
	public JsonResult<String> loginAndBind( WxLoginParam param) {
		return wxLoginService.loginAndBind(param);
	}
	
	/**
	 * 注册并绑定
	 * @param wcrp
	 * @return
	 */
	@RequestMapping(value="regAndBind",method=RequestMethod.POST)
	public JsonResult<String> regAndBind(@RequestBody WxClientRegistParam wcrp) {
		return wxLoginService.regAndBind(wcrp);
	}
}
