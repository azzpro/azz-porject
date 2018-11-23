/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月19日 下午4:07:49
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.selection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.QueryPage;
import com.azz.core.common.page.Pagination;
import com.azz.order.selection.bo.SearchInitParamsParam;
import com.azz.order.selection.vo.CombinationInfo;
import com.azz.order.selection.vo.InitParams;
import com.azz.order.selection.vo.SelectionCaseInfo;
import com.azz.selection.mapper.SelectionMapper;
import com.azz.util.JSR303ValidateUtils;
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
	 * <p>查询选型的所有方案列表</p>
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
	 * <p>查询初始化参数</p>
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
	 * <p>查询推荐组合列表</p>
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
	
}

