/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.MerchantConstants.IsChangeCombinationPic;
import com.azz.platform.merchant.api.CombinationService;
import com.azz.platform.merchant.pojo.bo.AddCombinationParam;
import com.azz.platform.merchant.pojo.bo.AddCombinationWebParam;
import com.azz.platform.merchant.pojo.bo.CombinationPic;
import com.azz.platform.merchant.pojo.bo.EditCombinationParam;
import com.azz.platform.merchant.pojo.bo.EditCombinationWebParam;
import com.azz.platform.merchant.pojo.bo.PutOnOrPutOffOrDelCombinationParam;
import com.azz.platform.merchant.pojo.bo.SearchCaseInfoParam;
import com.azz.platform.merchant.pojo.bo.SearchCombinationParam;
import com.azz.platform.merchant.pojo.bo.SearchGoodsModuleParam;
import com.azz.platform.merchant.pojo.bo.SearchProductInfoParam;
import com.azz.platform.merchant.pojo.vo.CaseInfo;
import com.azz.platform.merchant.pojo.vo.CombinationInfo;
import com.azz.platform.merchant.pojo.vo.GoodsModuleInfo;
import com.azz.platform.merchant.pojo.vo.ProdInfo;
import com.azz.util.Base64;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.azz.utils.WebUtils;

/**
 * 
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月1日 下午9:20:41
 */
@RestController
@RequestMapping("/azz/api/merchant/combination")
public class CombinationController {
	
	@Autowired
	CombinationService combinationService;
	
	/**
	 * 
	 * <p>查询组合列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月5日 下午7:10:34
	 */
	@RequestMapping("/getCombinationInfoList")
	public JsonResult<Pagination<CombinationInfo>> getCombinationInfoList(SearchCombinationParam param){
		return combinationService.getCombinationInfoList(param);
	}
	
	/**
	 * 
	 * <p>查询组合详情</p>
	 * @param combinationCode
	 * @return
	 * @author 黄智聪  2018年11月5日 下午7:21:10
	 */
	@RequestMapping("/getCombinationInfo")
	public JsonResult<CombinationInfo> getCombinationInfo(@RequestParam("combinationCode")String combinationCode){
		return combinationService.getCombinationInfo(combinationCode);
	}
	
	/**
	 * 
	 * <p>新增推荐组合</p>
	 * @param webParam
	 * @return
	 * @throws IOException
	 * @author 黄智聪  2018年11月6日 上午10:55:31
	 */
	@RequestMapping("/addCombination")
	public JsonResult<String> addCombination(AddCombinationWebParam webParam) throws IOException {
		JSR303ValidateUtils.validate(webParam);
		AddCombinationParam param = new AddCombinationParam();
		BeanUtils.copyProperties(webParam, param, "moduleCodes");
		String moduleCodes = webParam.getModuleCodes();
		if(StringUtils.isNotBlank(moduleCodes)) {
			String[] moduleCodesArray = moduleCodes.split(",");
			List<String> codes = new ArrayList<>();
			CollectionUtils.addAll(codes, moduleCodesArray);
			param.setModuleCodes(codes);
		}
		MultipartFile combinationPicFile = webParam.getCombinationPicFile();
		CombinationPic combinationPic = new CombinationPic(combinationPicFile.getOriginalFilename(),
				combinationPicFile.getSize(), Base64.encode(combinationPicFile.getBytes()));
		param.setCombinationPic(combinationPic);
		param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return combinationService.addCombination(param);
	}
	
	/**
	 * 
	 * <p>修改推荐组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月5日 下午7:41:49
	 * @throws IOException 
	 */
	@RequestMapping("/editCombination")
	public JsonResult<String> editCombination(EditCombinationWebParam webParam) throws IOException {
		JSR303ValidateUtils.validate(webParam);
		EditCombinationParam param = new EditCombinationParam();
		BeanUtils.copyProperties(webParam, param, "moduleCodes");
		String moduleCodes = webParam.getModuleCodes();
		if(StringUtils.isNotBlank(moduleCodes)) {
			String[] moduleCodesArray = moduleCodes.split(",");
			List<String> codes = new ArrayList<>();
			CollectionUtils.addAll(codes, moduleCodesArray);
			param.setModuleCodes(codes);
		}
		
		MultipartFile combinationPicFile = webParam.getCombinationPicFile();
		if (webParam.getIsChangeCombinationPic() == IsChangeCombinationPic.Y.getValue() && combinationPicFile != null) {
			CombinationPic combinationPic = new CombinationPic(combinationPicFile.getOriginalFilename(),
					combinationPicFile.getSize(), Base64.encode(combinationPicFile.getBytes()));
			param.setCombinationPic(combinationPic);
		}
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return combinationService.editCombination(param);
	}
	
	/**
	 * 
	 * <p>上架、下架或删除组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:01:18
	 */
	@RequestMapping("/putOnOrPutOffOrDelCombination")
	public JsonResult<String> putOnOrPutOffOrDelCombination(PutOnOrPutOffOrDelCombinationParam param) {
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return combinationService.putOnOrPutOffOrDelCombination(param);
	}
	
	/**
	 * 
	 * <p>查询方案列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:43:38
	 */
	@RequestMapping("/getCaseInfoList")
	public JsonResult<Pagination<CaseInfo>> getCaseInfoList(SearchCaseInfoParam param){
		return combinationService.getCaseInfoList(param);
	}
	
	/**
	 * 
	 * <p>查询模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:43:38
	 */
	@RequestMapping("/getModuleInfoList")
	public JsonResult<Pagination<GoodsModuleInfo>> getModuleInfoList(SearchGoodsModuleParam param){
		return combinationService.getModuleInfoList(param);
	}
	
	/**
	 * 
	 * <p>查询产品列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:43:38
	 */
	@RequestMapping("/getProductInfoList")
	public JsonResult<Pagination<ProdInfo>> getProductInfoList(SearchProductInfoParam param){
		return combinationService.getProductInfoList(param);
	}

}
