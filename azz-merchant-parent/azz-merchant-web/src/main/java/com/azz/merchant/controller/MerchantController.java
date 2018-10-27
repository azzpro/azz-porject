/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.controller;

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

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.MerchantConstants;
import com.azz.core.exception.ShiroAuthException;
import com.azz.core.exception.SuppressedException;
import com.azz.merchant.api.MerchantService;
import com.azz.merchant.pojo.bo.AddMerchantUserParam;
import com.azz.merchant.pojo.bo.BusinessLicense;
import com.azz.merchant.pojo.bo.CompleteMerchantInfoParam;
import com.azz.merchant.pojo.bo.CompleteMerchantInfoWebParam;
import com.azz.merchant.pojo.bo.EditMerchantUserParam;
import com.azz.merchant.pojo.bo.EnableOrDisableOrDelMerchantUserParam;
import com.azz.merchant.pojo.bo.LoginParam;
import com.azz.merchant.pojo.bo.MerchantRegistParam;
import com.azz.merchant.pojo.bo.SearchMerchantUserParam;
import com.azz.merchant.pojo.bo.TradingCertificate;
import com.azz.merchant.pojo.vo.LoginMerchantUserInfo;
import com.azz.merchant.pojo.vo.MerchantUserInfo;
import com.azz.merchant.utils.WebUtils;
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
@RequestMapping("/azz/api/merchant")
public class MerchantController {

    @Value("${shiro.session.timeout}")
    private Long sessionTimeout;

    @Autowired
    MerchantService merchantService;

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
    public JsonResult<LoginMerchantUserInfo> login(LoginParam param) {
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
	    JsonResult<LoginMerchantUserInfo> jr = new JsonResult<>();
	    jr.setCode(code);
	    jr.setMsg(msg);
	    return jr;
	}
	JsonResult<LoginMerchantUserInfo> jr = merchantService.getLoginMerchantUserInfoByPhoneNumber(param.getPhoneNumber());
	LoginMerchantUserInfo loginMerchantUser = jr.getData();
	loginMerchantUser.setSessionId(subject.getSession().getId());
	WebUtils.setShiroSessionAttr(MerchantConstants.LOGIN_MERCHANT_USER, loginMerchantUser);
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
    public JsonResult<String> merchantRegist(MerchantRegistParam param) {
	return merchantService.merchantRegist(param);
    }
    
    /**
     * 
     * <p>完善商户信息</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月23日 下午8:04:27
     * @throws IOException 
     */
    @RequestMapping(value = "/completeMerchantInfo")
    public JsonResult<String> completeMerchantInfo(CompleteMerchantInfoWebParam webParam) throws IOException {
	JSR303ValidateUtils.validate(webParam);
	CompleteMerchantInfoParam param = new CompleteMerchantInfoParam();
	BeanUtils.copyProperties(webParam, param);
	List<TradingCertificate> tradingCertificates = new ArrayList<>();
	List<BusinessLicense> businessLicenses = new ArrayList<>();
	for (MultipartFile tradingCertificateFile : webParam.getTradingCertificateFiles()) {
	    TradingCertificate tradingCertificate = new TradingCertificate(tradingCertificateFile.getOriginalFilename(),
		    tradingCertificateFile.getSize(), Base64.encode(tradingCertificateFile.getBytes()));
	    tradingCertificates.add(tradingCertificate);
	    
	}
	for (MultipartFile businessLicenseFile : webParam.getBusinessLicenseFiles()) {
	    BusinessLicense businessLicense = new BusinessLicense(businessLicenseFile.getOriginalFilename(),
		    businessLicenseFile.getSize(), Base64.encode(businessLicenseFile.getBytes()));
	    businessLicenses.add(businessLicense);
	}
	param.setTradingCertificates(tradingCertificates);
	param.setBusinessLicenses(businessLicenses);
	return merchantService.completeMerchantInfo(param);
    }
    
    @RequestMapping(value = "/getMerchantQualificationApplyStatus")
    public JsonResult<Integer> getMerchantQualificationApplyStatus(String merchantCode) {
	return merchantService.getMerchantQualificationApplyStatus(merchantCode);
    }
    
    @RequestMapping("/addMerchantUser")
    public JsonResult<String> addMerchantUser(AddMerchantUserParam param){
	param.setMerchantCode(WebUtils.getLoginMerchanUser().getMerchantUserInfo().getMerchantCode());
	param.setCreator(WebUtils.getLoginMerchanUser().getMerchantUserInfo().getMerchantUserCode());
	return merchantService.addMerchantUser(param);
    }
    
    @RequestMapping("/editMerchantUser")
    public JsonResult<String> editMerchantUser(EditMerchantUserParam param) {
	param.setMerchantCode(WebUtils.getLoginMerchanUser().getMerchantUserInfo().getMerchantCode());
	param.setModifier(WebUtils.getLoginMerchanUser().getMerchantUserInfo().getMerchantUserCode());
	return merchantService.editMerchantUser(param);
    }
    
    @RequestMapping("/getMerchantUserList")
    public JsonResult<Pagination<MerchantUserInfo>> getMerchantUserList(SearchMerchantUserParam param) {
	param.setMerchantCode(WebUtils.getLoginMerchanUser().getMerchantUserInfo().getMerchantCode());
	return merchantService.getMerchantUserList(param);
    }
    
    @RequestMapping("/editMerchantUserStatus")
    public JsonResult<String> enableOrDisableOrDelMerchantUser(EnableOrDisableOrDelMerchantUserParam param) {
	return merchantService.enableOrDisableOrDelMerchantUser(param);
    }
    
    @RequestMapping("/getMerchantUserInfo")
    public JsonResult<MerchantUserInfo> getMerchantUserInfo(String merchantUserCode) {
	return merchantService.getMerchantUserInfo(merchantUserCode);
    }

}
