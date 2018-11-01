/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 上午11:14:02
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.pojo.bo.Param;
import com.azz.platform.merchant.pojo.bo.ParamsData;
import com.azz.platform.merchant.pojo.bo.SearchParams;
import com.azz.platform.merchant.pojo.vo.Params;
import com.azz.platform.merchant.pojo.vo.ParamsAll;
import com.azz.platform.merchant.service.ParamsService;

/**
 * <P>参数项控制器</P>
 * @version 1.0
 * @author 刘建麟  2018年10月31日 上午11:14:02
 */
@RestController
@RequestMapping("/azz/api/merchant")
public class ParamsController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ParamsService paramsService;
	
	/**
	 * <p>参数管理列表</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月31日 上午11:25:13
	 */
	@RequestMapping(value="searchParamsList",method=RequestMethod.POST)
	public JsonResult<Pagination<Params>> searchParamsList(@RequestBody SearchParams param){
		return paramsService.searchParamsList(param);
	}
	
	/**
	 * <p>新曾参数</p>
	 * @param params
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:49:11
	 */
	@RequestMapping(value="addParams",method=RequestMethod.POST)
	public JsonResult<String> addParams(@RequestBody ParamsData params){
		return paramsService.insertParams(params);
	}
	
	/**
	 * <p>更新参数</p>
	 * @param params
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:49:11
	 */
	@RequestMapping(value="updateParams",method=RequestMethod.POST)
	public JsonResult<String> updateParams(@RequestBody Param params){
		return paramsService.updateParams(params);
	}
	
	/**
	 * <p>去更新参数页面</p>
	 * @param code
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:51:21
	 */
	@RequestMapping(value="toUpdateParams",method=RequestMethod.POST)
	public JsonResult<List<ParamsAll>> toUpdateParams(String code){
		return paramsService.toUpdateParams(code);
	}
}

