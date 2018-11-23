/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月19日 下午4:07:49
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.selection.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.QueryPage;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.selection.bo.SearchCombinationInitParamsParam;
import com.azz.order.selection.bo.SearchInitParamsParam;
import com.azz.order.selection.vo.CombinationDetail;
import com.azz.order.selection.vo.CombinationInfo;
import com.azz.order.selection.vo.CombinationInitParams;
import com.azz.order.selection.vo.InitParams;
import com.azz.order.selection.vo.Params;
import com.azz.order.selection.vo.ProductInfo;
import com.azz.order.selection.vo.ProductParams;
import com.azz.order.selection.vo.ProductPrice;
import com.azz.order.selection.vo.SelectionCaseInfo;
import com.azz.selection.mapper.SelectionMapper;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>选型业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月19日 下午4:07:49
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class SelectionService {
	
	@Autowired
	private SelectionMapper selectionMapper;

	/**
	 * 
	 * <p>查询选型的所有方案列表     1</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月19日 下午5:35:02
	 */
	public JsonResult<Pagination<SelectionCaseInfo>> getSelectionCaseInfos(@RequestBody QueryPage param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<SelectionCaseInfo> infos = selectionMapper.getSelectionCaseInfos();
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询初始化参数   2</p>
	 * @param caseCode
	 * @return
	 * @author 黄智聪  2018年11月20日 下午7:32:14
	 */
	public JsonResult<List<InitParams>> getInitParamsByCaseCode(@RequestBody SearchInitParamsParam param){
		JSR303ValidateUtils.validate(param);
		return JsonResult.successJsonResult(selectionMapper.getInitParams(param));
	}
	
	/**
	 * 
	 * <p>根据参数完善页中选中的参数，获取符合这些参数的产品的公共参数   3</p>
	 * @param caseCode
	 * @return
	 * @author 黄智聪  2018年11月20日 下午7:32:14
	 */
	public JsonResult<List<CombinationInitParams>> getCombinationInitParams(@RequestBody SearchInitParamsParam param){
		JSR303ValidateUtils.validate(param);
		List<CombinationInitParams> infos = selectionMapper.getCombinationInitParams(param);
		return JsonResult.successJsonResult(infos);
	}
	
	/**
	 * 
	 * <p>查询推荐组合列表 4 </p>
	 * @param caseCode
	 * @return
	 * @author 黄智聪  2018年11月20日 下午7:32:14
	 */
	public JsonResult<Pagination<CombinationInfo>> getCombinationInfos(@RequestBody SearchInitParamsParam param){
		JSR303ValidateUtils.validate(param);
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<CombinationInfo> infos = selectionMapper.getCombinationInfos(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询推荐组合详情（包含推荐组合的信息、所包含的产品的公共选型参数、产品列表）  5</p>
	 * @param combinationCode
	 * @return
	 * @author 黄智聪  2018年11月22日 下午3:12:23
	 */
	public JsonResult<CombinationDetail> getCombinationDetail(String combinationCode){
		if(StringUtils.isBlank(combinationCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐组合编码不允许为空");
		}
		// 推荐组合的信息
		CombinationDetail detail = selectionMapper.getCombinationDetail(combinationCode);
		if(detail == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐组合不存在");
		}
		// 所包含的产品的公共选型参数
		SearchCombinationInitParamsParam param = new SearchCombinationInitParamsParam();
		param.setCombinationCode(combinationCode);
		List<List<Object>> results = new ArrayList<>();// 返回给前端的一个产品集合
		List<Object> title = new ArrayList<>();
		title.add("产品编码");
		title.add("单价");
		title.add("交期");
		// 查询方案下所有产品的公共参数
		List<Params> allProductParams = selectionMapper.getParamsNamesByCaseId(detail.getCaseId());
		for (Params eachProductParams : allProductParams) {
			title.add(eachProductParams.getParamsName());
		}
		results.add(title);// 此时第一条数据格式类似如：  产品编码、单价、交期、颜色、尺寸...
		// 产品列表
		List<ProductInfo> productInfos = selectionMapper.getProductInfos(param);
		//　处理每一条数据
		for (ProductInfo productInfo : productInfos) {
			List<Object> eachResult = new ArrayList<>();
			eachResult.add(productInfo.getProductCode());// 产品编码
			eachResult.add(productInfo.getPrice());//单价
			eachResult.add(productInfo.getDeliveryDate());//交期
			for (int i = 0; i < allProductParams.size(); i++) {
				Long paramsTermId = allProductParams.get(i).getParamsTermId();
				boolean hasParamsTermId = false;
				Object value = null;
				for (ProductParams productParams : productInfo.getProductParams()) {
					if(productParams.getParamsTermId().equals(paramsTermId)) {
						value = productParams.getParamsValue();
						hasParamsTermId = true;
						break;
					}
				}
				if(hasParamsTermId) {
					eachResult.add(value);
				}else {
					eachResult.add("-");
				}
			}
			results.add(eachResult);
		}
		detail.setProductInfos(results);
		// 查询初始化参数
		List<InitParams> params = selectionMapper.getCombinationDetailInitParams(param);
		detail.setParams(params);
		return JsonResult.successJsonResult(detail);
	}
	
	/**
	 * 
	 * <p>根据所选参数查询产品列表  6</p>
	 * @param searchParam
	 * @return
	 * @author 黄智聪  2018年11月22日 下午9:15:42
	 */
	public JsonResult<List<List<Object>>> getProductInfos(@RequestBody SearchCombinationInitParamsParam searchParam){
		JSR303ValidateUtils.validate(searchParam);
		// 推荐组合的信息
		CombinationDetail detail = selectionMapper.getCombinationDetail(searchParam.getCombinationCode());
		if(detail == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐组合不存在");
		}
		// 所包含的产品的公共选型参数
		SearchCombinationInitParamsParam param = new SearchCombinationInitParamsParam();
		param.setCombinationCode(param.getCombinationCode());
		List<List<Object>> results = new ArrayList<>();// 返回给前端的一个产品集合
		List<Object> title = new ArrayList<>();
		title.add("产品编码");
		title.add("单价");
		title.add("交期");
		// 查询方案下所有产品的公共参数
		List<Params> allProductParams = selectionMapper.getParamsNamesByCaseId(detail.getCaseId());
		for (Params eachProductParams : allProductParams) {
			title.add(eachProductParams.getParamsName());
		}
		results.add(title);// 此时第一条数据格式类似如：  产品编码、单价、交期、颜色、尺寸...
		// 产品列表
		List<ProductInfo> productInfos = selectionMapper.getProductInfos(param);
		//　处理每一条数据
		for (ProductInfo productInfo : productInfos) {
			List<Object> eachResult = new ArrayList<>();
			eachResult.add(productInfo.getProductCode());// 产品编码
			eachResult.add(productInfo.getPrice());//单价
			eachResult.add(productInfo.getDeliveryDate());//交期
			for (int i = 0; i < allProductParams.size(); i++) {
				Long paramsTermId = allProductParams.get(i).getParamsTermId();
				boolean hasParamsTermId = false;
				Object value = null;
				for (ProductParams productParams : productInfo.getProductParams()) {
					if(productParams.getParamsTermId().equals(paramsTermId)) {
						value = productParams.getParamsValue();
						hasParamsTermId = true;
						break;
					}
				}
				if(hasParamsTermId) {
					eachResult.add(value);
				}else {
					eachResult.add("-");
				}
			}
			results.add(eachResult);
		}
		return JsonResult.successJsonResult(results);
	}
	
	/**
	 * 
	 * <p>根据产品编码查询该产品的价格信息</p>
	 * @param productCode
	 * @return
	 * @author 黄智聪  2018年11月23日 上午11:11:52
	 */
	public JsonResult<ProductPrice> getProductPrice(String productCode){
		return JsonResult.successJsonResult(selectionMapper.getProductPrice(productCode));
	}
	
}

