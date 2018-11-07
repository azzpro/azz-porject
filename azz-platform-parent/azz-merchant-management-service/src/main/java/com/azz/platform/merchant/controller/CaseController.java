/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午6:19:44
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.platform.merchant.pojo.bo.AddCaseParam;
import com.azz.platform.merchant.pojo.bo.AddSelectionParams;
import com.azz.platform.merchant.pojo.bo.DelSelecttionParams;
import com.azz.platform.merchant.pojo.bo.EditCaseParam;
import com.azz.platform.merchant.pojo.vo.CaseParams;
import com.azz.platform.merchant.pojo.vo.CaseParamsList;
import com.azz.platform.merchant.service.CaseService;

/**
 * <P>方案管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月7日 上午10:17:04
 */
@RestController
@RequestMapping("/azz/api/merchant/case")
public class CaseController {
	
	@Autowired
	private CaseService  caseService;
	
	/**
	 * <p>新增方案参数</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月7日 上午10:32:56
	 */
	@RequestMapping(value="addCase",method=RequestMethod.POST)
	public JsonResult<String> addCase(@RequestBody AddCaseParam param){
	    return caseService.addCase(param);
	}
	
	/**
	 * <p>编辑方案</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月7日 上午10:33:09
	 */
	@RequestMapping(value="editCase",method=RequestMethod.POST)
	public JsonResult<String> editCase(@RequestBody EditCaseParam param){
	    return caseService.editCase(param);
	}
	
	/**
	 * <p>根据分类编码获取参数信息</p>
	 * @param assortmentId
	 * @return
	 * @author 彭斌  2018年11月7日 上午10:34:25
	 */
	@RequestMapping(value="getCaseParamList",method=RequestMethod.POST)
	public JsonResult<List<CaseParams>> getCaseParamList(Long assortmentId){
	    return caseService.getCaseParamList(assortmentId);
	}
	
	/**
	 * <p>添加选型参数</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月7日 上午10:35:28
	 */
	@RequestMapping(value="addParam",method=RequestMethod.POST)
	public JsonResult<String> addParam(@RequestBody AddSelectionParams param){
	    return caseService.addParam(param);
	}
	
	/**
	 * <p>根据方案编码获取选型参数</p>
	 * @param caseCode
	 * @return
	 * @author 彭斌  2018年11月7日 上午10:36:38
	 */
	@RequestMapping(value="getCaseSelectionParameter",method=RequestMethod.POST)
	public JsonResult<List<CaseParamsList>> getCaseSelectionParameter(String caseCode){
	    return caseService.getCaseSelectionParameter(caseCode);
	}
	
	/**
	 * <p>移除选型参数</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月7日 上午10:38:08
	 */
	@RequestMapping(value="delSelectionParameter",method=RequestMethod.POST)
	public JsonResult<String> delSelectionParameter(@RequestBody DelSelecttionParams param){
	    return caseService.delSelectionParameter(param);
	}
	
	
}

