package com.azz.wx.course.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.wx.course.pojo.bo.ParamsData;
import com.azz.wx.course.pojo.bo.SearchParams;
import com.azz.wx.course.pojo.vo.Params;
import com.azz.wx.course.pojo.vo.ParamsAll;

@FeignClient("azz-wx-course-service")
public interface ParamService {

	@PostMapping("/azz/api/platform/course/searchParamsList")
	public JsonResult<Pagination<Params>> searchParamsList(@RequestBody SearchParams param);
	
	@RequestMapping(value="/azz/api/platform/course/addParams",method=RequestMethod.POST)
	public JsonResult<String> addParams(@RequestBody ParamsData params);
	
	@RequestMapping(value="/azz/api/platform/course/toUpdateParams",method=RequestMethod.POST)
	public JsonResult<List<ParamsAll>> toUpdateParams(@RequestParam("code") String code);
	
	@RequestMapping(value="/azz/api/platform/course/updateParams",method=RequestMethod.POST)
	public JsonResult<String> updateParams(ParamsData params);
	
	@RequestMapping(value="/azz/api/platform/course/deleteParams",method=RequestMethod.POST)
	public JsonResult<String> deleteParams(@RequestParam("code") String code);
}
