/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月19日 下午5:53:57
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.selection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.QueryPage;
import com.azz.core.common.page.Pagination;
import com.azz.order.selection.bo.SearchInitParamsParam;
import com.azz.order.selection.vo.CombinationInfo;
import com.azz.order.selection.vo.InitParams;
import com.azz.order.selection.vo.SelectionCaseInfo;
import com.azz.selection.service.SelectionService;

/**
 * <P>选型控制器</P>
 * @version 1.0
 * @author 黄智聪  2018年11月19日 下午5:53:57
 */
@RestController
@RequestMapping("/azz/api/selection")
public class SelectionController {
	
	@Autowired
	private SelectionService selectionService;
	
	/**
	 * <p>查询选型的所有方案列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月19日 下午5:57:39
	 */
	@RequestMapping("/getSelectionCaseInfos")
	public JsonResult<Pagination<SelectionCaseInfo>> getSelectionCaseInfos(@RequestBody QueryPage param){
		return selectionService.getSelectionCaseInfos(param);
	}
	
	/**
	 * 
	 * <p>查询初始化参数</p>
	 * @param caseCode
	 * @return
	 * @author 黄智聪  2018年11月20日 下午7:32:51
	 */
	@RequestMapping("/getInitParamsByCaseCode")
	public JsonResult<List<InitParams>> getInitParamsByCaseCode(/*@RequestBody */SearchInitParamsParam param){
		return selectionService.getInitParamsByCaseCode(param);
	}
	
	/**
	 * 
	 * <p>查询推荐组合列表</p>
	 * @param caseCode
	 * @return
	 * @author 黄智聪  2018年11月20日 下午7:32:51
	 */
	@RequestMapping("/getCombinationInfos")
	public JsonResult<Pagination<CombinationInfo>> getCombinationInfos(/*@RequestBody */SearchInitParamsParam param){
		return selectionService.getCombinationInfos(param);
	}

}

