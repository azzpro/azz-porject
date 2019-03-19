package com.azz.platform.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azz.core.constants.PayConstants;
import com.azz.order.api.client.RegYeeMerchantService;
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

	private static final Logger log = LoggerFactory.getLogger("易宝企业商户入网");
	
	@Autowired
	private RegYeeMerchantService clientPayService;
	
	@RequestMapping("regMerchantYeeEnterpriseAccount")
	public Map<String,String> regMerchantYeeEnterpriseAccount(Enterprisereginfo enInfo) throws IOException {
		JSR303ValidateUtils.validate(enInfo);
		EnterprisereginfoCopy param = new EnterprisereginfoCopy();
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
		return clientPayService.regMerchantYeeEnterpriseAccount(param);
	}
	
	/**
	 * 子商户入网注册【企业】回调
	 * @param request
	 * @param po
	 * @return
	 */
	@RequestMapping("regEnterpriseNotify")
	public void regEnterpriseNotify(HttpServletRequest request,HttpServletResponse response) {
		log.info("进入企业商户入网回调");
		Map<String,String> result = (Map<String,String>) request.getAttribute("result");
		String returnCode = result.get("returnCode");
		String returnMsg = result.get("returnMsg");
		String parentMerchantNo = result.get("parentMerchantNo");
		String merchantNo = result.get("merchantNo");
		String requestNo = result.get("requestNo");
		String externalId = result.get("externalId");
		log.info("returnCode----------->"+returnCode);
	}
	
}
