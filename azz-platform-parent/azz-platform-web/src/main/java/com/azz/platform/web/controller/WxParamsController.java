/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午2:17:53
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.utils.WebUtils;
import com.azz.wx.course.api.ParamService;
import com.azz.wx.course.pojo.bo.ParamsData;
import com.azz.wx.course.pojo.bo.SearchParams;
import com.azz.wx.course.pojo.vo.Params;
import com.azz.wx.course.pojo.vo.ParamsAll;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月31日 下午2:17:53
 */
@RestController
@RequestMapping("/azz/api/platform/course")
public class WxParamsController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ParamService paramService;
	
	/**
	 * <p>参数列表</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月31日 下午2:38:44
	 */
	@RequestMapping(value="searchParamsList",method=RequestMethod.POST)
	public JsonResult<Pagination<Params>> searchParamsList(SearchParams param){
		return paramService.searchParamsList(param);
	}
	
	/**
	 * <p>新增参数</p>
	 * @param params
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:47:30
	 */
	@RequestMapping(value="addParams",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public JsonResult<String> addParams(@RequestBody ParamsData params){
		params.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return paramService.addParams(params);
	}
	
	/**
	 * <p>去更新参数页面</p>
	 * @param params
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:47:30
	 */
	@RequestMapping(value="toUpdateParams",method=RequestMethod.POST)
	public JsonResult<List<ParamsAll>> toUpdateParams(@RequestParam("code") String code){
		return paramService.toUpdateParams(code);
	}
	
	/**
	 * <p>更新参数</p>
	 * @param params
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:47:30
	 */
	@RequestMapping(value="updateParams",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public JsonResult<String> updateParams(@RequestBody ParamsData params){
		params.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return paramService.updateParams(params);
	}
	
	/**
	 * <p>删除参数</p>
	 * @param params
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:47:30
	 */
	@RequestMapping(value="deleteParams",method=RequestMethod.POST)
	public JsonResult<String> deleteParams(@RequestParam("code") String code){
		return paramService.deleteParams(code);
	}
	
}

