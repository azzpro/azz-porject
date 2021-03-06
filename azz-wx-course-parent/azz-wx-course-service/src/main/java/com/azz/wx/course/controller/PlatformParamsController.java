/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 上午11:14:02
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.wx.course.pojo.bo.ParamsData;
import com.azz.wx.course.pojo.bo.SearchParams;
import com.azz.wx.course.pojo.vo.Params;
import com.azz.wx.course.pojo.vo.ParamsAll;
import com.azz.wx.course.service.ParameterService;

/**
 * <P>参数项控制器</P>
 * @version 1.0
 * @author 刘建麟  2018年10月31日 上午11:14:02
 */
@RestController
@RequestMapping("/azz/api/platform/course")
public class PlatformParamsController {

	@Autowired
	private ParameterService paramsService;
	
	
	
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
	public JsonResult<String> updateParams(@RequestBody ParamsData params){
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
	
	/**
	 * <p>删除参数</p>
	 * @param params
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:47:30
	 */
	@RequestMapping(value="deleteParams",method=RequestMethod.POST)
	public JsonResult<String> deleteParams(String code){
		return paramsService.deleteParams(code);
	}
}

