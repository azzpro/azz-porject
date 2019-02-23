package com.azz.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.system.bo.QQClientRegistParam;
import com.azz.system.bo.WxClientRegistParam;
import com.azz.system.pojo.ClientWxUser;
import com.azz.system.service.QQLoginService;
import com.azz.system.service.bean.Result;
import com.azz.system.vo.QQCallBackInfo;
import com.azz.system.vo.QQLoginInfo;
import com.azz.system.vo.WxCallBackInfo;
import com.azz.system.vo.WxInfo;
import com.azz.system.vo.WxLoginInfo;
import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;

@RestController
@RequestMapping("/azz/api/qq")
public class QQLoginController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private QQLoginService qqLoginService;
	
	/**
	 * 去到QQ扫码页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("goQQScanPage")
	public JsonResult<WxInfo> goQQScanPage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		WxInfo wi = new WxInfo();
		String url = "";
		try {
			url = new Oauth().getAuthorizeURL(request);//认证登录
		} catch (QQConnectException e) {
			e.printStackTrace();
			wi.setUrl("");
			return new JsonResult<>(wi);
		}
		String header = request.getHeader("Referer");
		log.info("QQ登录请求url------------>" + url+"::::请求header------>"+header);
		wi.setUrl(url);
		return new JsonResult<>(wi);
	}
	
	
	
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
