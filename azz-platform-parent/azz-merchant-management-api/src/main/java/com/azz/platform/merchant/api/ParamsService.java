/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午2:16:15
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.pojo.bo.Param;
import com.azz.platform.merchant.pojo.bo.ParamsData;
import com.azz.platform.merchant.pojo.bo.SearchParams;
import com.azz.platform.merchant.pojo.vo.Params;
import com.azz.platform.merchant.pojo.vo.ParamsAll;
import com.github.pagehelper.PageHelper;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月31日 下午2:16:15
 */
@FeignClient("azz-merchant-management-service")
public interface ParamsService {
	
	@PostMapping("/azz/api/merchant/searchParamsList")
	JsonResult<Pagination<Params>> searchParamsList(@RequestBody SearchParams param);
	
	@RequestMapping(value="/azz/api/merchant/addParams",method=RequestMethod.POST)
	public JsonResult<String> addParams(@RequestBody ParamsData params);
	
	@RequestMapping(value="/azz/api/merchant/toUpdateParams",method=RequestMethod.POST)
	public JsonResult<List<ParamsAll>> toUpdateParams(@RequestParam("code") String code);
	
	@RequestMapping(value="/azz/api/merchant/updateParams",method=RequestMethod.POST)
	public JsonResult<String> updateParams(Param params);
	
	@RequestMapping(value="/azz/api/merchant/deleteParams",method=RequestMethod.POST)
	public JsonResult<String> deleteParams(@RequestParam("code") String code);
}

