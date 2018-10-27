/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azz.client.pojo.bo.AddClientUserParam;
import com.azz.client.pojo.bo.ClientRegistParam;
import com.azz.client.pojo.bo.EditClientUserParam;
import com.azz.client.pojo.bo.EnableOrDisableOrDelClientUserParam;
import com.azz.client.pojo.bo.EnterpriseAuthParam;
import com.azz.client.pojo.bo.EnterpriseAuthWebParam;
import com.azz.client.pojo.bo.LoginParam;
import com.azz.client.pojo.bo.SearchClientUserParam;
import com.azz.client.pojo.bo.TradingCertificate;
import com.azz.client.pojo.vo.ClientUserInfo;
import com.azz.client.pojo.vo.LoginClientUserInfo;
import com.azz.client.user.api.ClientService;
import com.azz.controller.utils.WebUtils;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.ClientConstants;
import com.azz.core.exception.ShiroAuthException;
import com.azz.core.exception.SuppressedException;
import com.azz.util.Base64;
import com.azz.util.JSR303ValidateUtils;

/**
 * 
 * <P>
 * 登录控制器
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月17日 下午1:42:55
 */
@RestController
@RequestMapping("/azz/api/client")
public class ClientController {

    @Value("${shiro.session.timeout}")
    private Long sessionTimeout;

    @Autowired
    ClientService clientService;

    /**
     * 
     * <p>
     * 未登录
     * </p>
     * 
     * @author 黄智聪 2018年10月17日 下午5:50:41
     */
    @RequestMapping(value = "/noLogin")
    public void notLogin() {
	throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_NO_LOGIN);
    }

    /**
     * 
     * <p>
     * 无权限
     * </p>
     * 
     * @author 黄智聪 2018年10月17日 下午5:50:51
     */
    @RequestMapping(value = "/noPermission")
    public void notRole() {
	throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_NO_PERMISSION);
    }
    
    /**
     * 
     * <p>供前端调用，测试是否已经登录失效</p>
     * @return
     * @author 黄智聪  2018年10月19日 上午9:22:49
     */
    @RequestMapping(value = "/check")
    public JsonResult<String> check() {
	return JsonResult.successJsonResult();
    }

    /**
     * 
     * <p>
     * 登出
     * </p>
     * 
     * @return
     * @author 黄智聪 2018年10月17日 下午5:51:01
     */
    @RequestMapping(value = "/logout")
    public JsonResult<String> logout() {
	Subject subject = SecurityUtils.getSubject();
	subject.logout();
	return JsonResult.successJsonResult();
    }

    /**
     * 
     * <p>
     * 登录
     * </p>
     * 
     * @param phoneNumber
     *            手机号
     * @param password
     *            密码
     * @return
     * @author 黄智聪 2018年10月17日 下午5:50:02
     */
    @RequestMapping(value = "/login")
    public JsonResult<LoginClientUserInfo> login(LoginParam param) {
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
	    int code = ((SuppressedException) throwables[0]).getCode();
	    String msg = ((SuppressedException) throwables[0]).getMessage();
	    JsonResult<LoginClientUserInfo> jr = new JsonResult<>();
	    jr.setCode(code);
	    jr.setMsg(msg);
	    return jr;
	}
	JsonResult<LoginClientUserInfo> jr = clientService.getLoginClientUserInfoByPhoneNumber(param.getPhoneNumber());
	LoginClientUserInfo loginClientUser = jr.getData();
	loginClientUser.setSessionId(subject.getSession().getId());
	WebUtils.setShiroSessionAttr(ClientConstants.LOGIN_CLIENT_USER, loginClientUser);
	return jr;
    }

    /**
     * 
     * <p>商户注册</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月23日 下午6:37:26
     */
    @RequestMapping(value = "/regist")
    public JsonResult<String> clientRegist(ClientRegistParam param) {
	return clientService.clientRegist(param);
    }
    
    /**
     * 
     * <p>完善商户信息</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月23日 下午8:04:27
     * @throws IOException 
     */
    @RequestMapping(value = "/enterpriseAuth")
    public JsonResult<String> completeClientInfo(EnterpriseAuthWebParam webParam) throws IOException {
	JSR303ValidateUtils.validate(webParam);
	EnterpriseAuthParam param = new EnterpriseAuthParam();
	BeanUtils.copyProperties(webParam, param);
	List<TradingCertificate> tradingCertificates = new ArrayList<>();
	for (MultipartFile tradingCertificateFile : webParam.getTradingCertificateFiles()) {
	    TradingCertificate tradingCertificate = new TradingCertificate(tradingCertificateFile.getOriginalFilename(),
		    tradingCertificateFile.getSize(), Base64.encode(tradingCertificateFile.getBytes()));
	    tradingCertificates.add(tradingCertificate);
	    
	}
	param.setTradingCertificates(tradingCertificates);
	return clientService.enterpriseAuth(param);
    }
    
    @RequestMapping("/addClientUser")
    public JsonResult<String> addClientUser(AddClientUserParam param){
	param.setClientUserCompanyId(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCompanyId());
	param.setCreator(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCode());
	return clientService.addClientUser(param);
    }
    
    @RequestMapping("/editClientUser")
    public JsonResult<String> editClientUser(EditClientUserParam param) {
	param.setModifier(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCode());
	return clientService.editClientUser(param);
    }
    
    @RequestMapping("/getClientUserList")
    public JsonResult<Pagination<ClientUserInfo>> getClientUserList(SearchClientUserParam param) {
	return clientService.getClientUserList(param);
    }
    
    @RequestMapping("/editClientUserStatus")
    public JsonResult<String> enableOrDisableOrDelClientUser(EnableOrDisableOrDelClientUserParam param) {
	return clientService.enableOrDisableOrDelClientUser(param);
    }
    
    @RequestMapping("/getClientUserInfo")
    public JsonResult<ClientUserInfo> getClientUserInfo(String clientUserCode) {
	return clientService.getClientUserInfo(clientUserCode);
    }

}
