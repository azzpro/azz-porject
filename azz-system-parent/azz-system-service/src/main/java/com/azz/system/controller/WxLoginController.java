package com.azz.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.system.bo.WxClientRegistParam;
import com.azz.system.pojo.ClientWxUser;
import com.azz.system.service.WxLoginService;
import com.azz.system.vo.WxCallBackInfo;
import com.azz.system.vo.WxInfo;
import com.azz.system.vo.WxLoginInfo;

@RestController
@RequestMapping("/azz/api/wechat")
public class WxLoginController {

	@Autowired
	private WxLoginService wxLoginService;
	
	/**
	 * 去到微信扫码页面
	 * @return
	 */
	@RequestMapping("goWxScanPage")
	public JsonResult<WxInfo> goWxScanPage() {
		return wxLoginService.goWxScanPage();
	}
	
	/**
	 * 微信回调
	 * @param code
	 * @param state
	 * @param key
	 * @return
	 */
	@RequestMapping("callback")
	public JsonResult<WxCallBackInfo> callback(@RequestParam("code")String code,@RequestParam("state") String state) {
		return wxLoginService.callback(code, state);
	}
	
	/**
	 * 绑定或者注册
	 * @param wcbi
	 * @return
	 */
	@RequestMapping("goBindOrReg")
	public JsonResult<WxCallBackInfo> goBindOrReg(@RequestBody WxCallBackInfo wcbi) {
		return wxLoginService.goBindOrReg(wcbi);
	}
	
	@RequestMapping("insert")
	public Integer insert(@RequestBody ClientWxUser wsc) {
		int i = wxLoginService.insert(wsc);
		return i;
	}
	
	/**
	 * 注册并绑定
	 * @param wcrp
	 * @return
	 */
	@RequestMapping("regAndBind")
	public JsonResult<WxLoginInfo> regAndBind(@RequestBody WxClientRegistParam wcrp) {
		return wxLoginService.regAndBind(wcrp);
	}
}
