/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.utils.WebUtils;
import com.azz.wx.course.api.StartClassService;
import com.azz.wx.course.pojo.bo.AddStartClassRecordParam;
import com.azz.wx.course.pojo.bo.EditStartClassRecordParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelStartClassRecordParam;
import com.azz.wx.course.pojo.bo.SearchStartClassRecordParam;
import com.azz.wx.course.pojo.vo.StartClassRecord;

/**
 * 
 * <P>
 * 开课相关控制器
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月17日 下午1:42:55
 */
@RestController
@RequestMapping("/azz/api/platform/startClass")
public class StartClassController {
	
	@Autowired
	StartClassService startClassService;
	
	/**
	 * 
	 * <p>查询开课信息列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:37:39
	 */
	@RequestMapping(value = "getStartClassRecords", method = RequestMethod.POST)
	public JsonResult<Pagination<StartClassRecord>> getStartClassRecords(SearchStartClassRecordParam param){
		return startClassService.getStartClassRecords(param);
	}
	
	/**
	 * 
	 * <p>查询开课信息详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:37:39
	 */
	@RequestMapping(value = "getStartClassRecordDetail", method = RequestMethod.POST)
	public JsonResult<StartClassRecord> getStartClassRecordDetail(@RequestParam("startClassCode") String startClassCode){
		return startClassService.getStartClassRecordDetail(startClassCode);
	}
	
	/**
	 * 
	 * <p>新增开课信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:57:17
	 */
	@RequestMapping(value = "addStartClassRecord", method = RequestMethod.POST)
	public JsonResult<String> addStartClassRecord(AddStartClassRecordParam param){
		param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return startClassService.addStartClassRecord(param);
	}
	
	/**
	 * 
	 * <p>修改开课信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:57:17
	 */
	@RequestMapping(value = "editStartClassRecord", method = RequestMethod.POST)
	public JsonResult<String> editStartClassRecord(EditStartClassRecordParam param){
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return startClassService.editStartClassRecord(param);
	}
	
	/**
	 * 
	 * <p>上架、下架或删除开课信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	@RequestMapping(value = "putOnOrPutOffOrDelStartClassRecord", method = RequestMethod.POST)
	public JsonResult<String> putOnOrPutOffOrDelStartClassRecord(PutOnOrPutOffOrDelStartClassRecordParam param){
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return startClassService.putOnOrPutOffOrDelStartClassRecord(param);
	}
	
}
