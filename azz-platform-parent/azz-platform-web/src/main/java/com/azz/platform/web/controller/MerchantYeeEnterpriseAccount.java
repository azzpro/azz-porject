package com.azz.platform.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.constants.PayConstants;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.api.client.RegYeeMerchantService;
import com.azz.order.client.pojo.Enterprisereginfoadd;
import com.azz.order.client.pojo.RetBean;
import com.azz.order.client.pojo.bo.Enterprisereginfo;
import com.azz.order.client.pojo.bo.EnterprisereginfoCopy;
import com.azz.order.client.pojo.bo.YeeModulePic;
import com.azz.util.Base64;
import com.azz.util.JSR303ValidateUtils;
import com.azz.utils.WebUtils;

/**
 * 易宝 企业商户入网
 * @author THINK
 *
 */
@RestController
@RequestMapping("/azz/api/merchant")
public class MerchantYeeEnterpriseAccount {

	private  Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RegYeeMerchantService regYeeMerchantService;
	
	/**
	 * 易宝商户入网注册
	 * @param enInfo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("regMerchantYeeEnterpriseAccount")
	public Map<String,String> regMerchantYeeEnterpriseAccount(Enterprisereginfo enInfo) throws IOException {
		JSR303ValidateUtils.validate(enInfo);
		EnterprisereginfoCopy param = new EnterprisereginfoCopy();
		param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		BeanUtils.copyProperties(enInfo, param);
		MultipartFile businessPic = enInfo.getBusinessPic();
		MultipartFile icpAuthPic = enInfo.getIcpAuthPic();
		MultipartFile legalBackPic = enInfo.getLegalBackPic();
		MultipartFile legalFrontPic = enInfo.getLegalFrontPic();
		MultipartFile openAccountPic = enInfo.getOpenAccountPic();
		YeeModulePic business = new YeeModulePic(businessPic.getOriginalFilename(),
				businessPic.getSize(), Base64.encode(businessPic.getBytes()),PayConstants.RegYee.businessPic.getCode());
		YeeModulePic icpAuth = new YeeModulePic(icpAuthPic.getOriginalFilename(),
				icpAuthPic.getSize(), Base64.encode(icpAuthPic.getBytes()),PayConstants.RegYee.icpAuthPic.getCode());
		YeeModulePic legalBack = new YeeModulePic(legalBackPic.getOriginalFilename(),
				legalBackPic.getSize(), Base64.encode(legalBackPic.getBytes()),PayConstants.RegYee.legalBackPic.getCode());
		YeeModulePic legalFront = new YeeModulePic(legalFrontPic.getOriginalFilename(),
				legalFrontPic.getSize(), Base64.encode(legalFrontPic.getBytes()),PayConstants.RegYee.legalFrontPic.getCode());
		YeeModulePic openAccount = new YeeModulePic(openAccountPic.getOriginalFilename(),
				openAccountPic.getSize(), Base64.encode(openAccountPic.getBytes()),PayConstants.RegYee.openAccountPic.getCode());
		param.setLegalFrontPic(legalFront);
		param.setLegalBackPic(legalBack);
		param.setIcpAuthPic(icpAuth);
		param.setBusinessPic(business);
		param.setOpenAccountPic(openAccount);
		param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return regYeeMerchantService.regMerchantYeeEnterpriseAccount(param);
	}
	
	/**
	 * 子商户入网注册【企业】回调
	 * @param request
	 * @param po
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("regEnterpriseNotify")
	public void regEnterpriseNotify(HttpServletRequest request,HttpServletResponse response) throws IOException {
		log.info("进入企业商户入网回调");
		String responseMsg = request.getParameter("response");
		String customerId = request.getParameter("customerIdentification");
		log.info("responseMsg---->"+responseMsg);
		log.info("customerId---->"+customerId);
		JsonResult<RetBean> notify = regYeeMerchantService.regEnterpriseNotify(responseMsg,customerId);
		response.getWriter().write(notify.getMsg());
		response.getWriter().flush();
	}
	
	/**
	 * 获取入网商户详细信息
	 * @param merchantCode
	 * @return
	 */
	@RequestMapping("enterpriseInfo")
	public JsonResult<Enterprisereginfoadd> enterpriseInfo(@RequestParam("merchantCode") String merchantCode){
		if(StringUtils.isBlank(merchantCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户编号不能为空");
		}
		return regYeeMerchantService.enterpriseInfo(merchantCode);
	}
	
}
