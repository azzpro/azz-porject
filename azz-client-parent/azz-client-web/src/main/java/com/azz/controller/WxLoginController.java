package com.azz.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.client.pojo.vo.LoginClientUserInfo;
import com.azz.client.user.api.ClientService;
import com.azz.controller.utils.WebUtils;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.constants.ClientConstants;
import com.azz.core.exception.ShiroAuthException;
import com.azz.core.exception.SuppressedException;
import com.azz.system.api.WxLoginService;
import com.azz.system.bo.WxClientRegistParam;
import com.azz.system.bo.WxLoginParam;
import com.azz.system.pojo.ClientWxUser;
import com.azz.system.vo.WxCallBackInfo;
import com.azz.system.vo.WxInfo;
import com.azz.util.JSR303ValidateUtils;

/**
 * @author THINK
 * 微信登录
 */
@RestController
@RequestMapping("/azz/api/wechat")
public class WxLoginController {

	@Autowired
	private WxLoginService wxLoginService;
	
	@Autowired
	private ClientService clientService;
	
	@Value("${shiro.session.timeout}")
    private Long sessionTimeout;
	
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
	public JsonResult<WxCallBackInfo> callback(@RequestParam("code")String code,@RequestParam("state") String state) {
		return wxLoginService.callback(code, state);
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
	public JsonResult<LoginClientUserInfo> loginAndBind( WxLoginParam param) {
		JSR303ValidateUtils.validate(param);
		// 从SecurityUtils里边创建一个 subject
		Subject subject = SecurityUtils.getSubject();
		// 在认证提交前准备 token（令牌）
		UsernamePasswordToken token = new UsernamePasswordToken(param.getPhoneNumber(), param.getPassword());
		try {
		    // 执行认证登陆
		    subject.login(token);
		    // 设置登录超时时间
		    subject.getSession().setTimeout(sessionTimeout);
		} catch (AuthenticationException e) {
		    Throwable[] throwables = e.getSuppressed();
		    if(throwables != null && throwables.length != 0) {
		    	int code = ((SuppressedException) throwables[0]).getCode();
			    String msg = ((SuppressedException) throwables[0]).getMessage();
			    JsonResult<LoginClientUserInfo> jr = new JsonResult<>();
			    jr.setCode(code);
			    jr.setMsg(msg);
			    return jr;
		    }
		    throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR,"登录失败,请重试");
		}
		JsonResult<LoginClientUserInfo> jr = clientService.getLoginClientUserInfoByPhoneNumber(param.getPhoneNumber());
		LoginClientUserInfo loginClientUser = jr.getData();
		loginClientUser.setSessionId(subject.getSession().getId());
		WebUtils.setShiroSessionAttr(ClientConstants.LOGIN_CLIENT_USER, loginClientUser);
		ClientWxUser wsc = new ClientWxUser();
		wsc.setAccess_token(param.getAccessToken());
		wsc.setExpires_in(Long.parseLong(param.getExpiresIn()));
		wsc.setOpenid(param.getOpenid());
		wsc.setScope(param.getScope());
		wsc.setUnionid(param.getUnionid());
		wsc.setRefresh_token(param.getRefreshToken());
		wsc.setUserCode(loginClientUser.getClientUserInfo().getClientUserCode());
		wxLoginService.insert(wsc);
		return jr;
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
