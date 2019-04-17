/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月24日 下午1:35:52
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.wx.course.api.SignUpService;
import com.azz.wx.course.pojo.bo.SearchActivityInfoParam;
import com.azz.wx.course.pojo.vo.ActivityInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月24日 下午1:35:52
 */
@RestController
@RequestMapping("/azz/api/client/activity")
public class ActivitySignUpController {
	
	@Autowired
	private SignUpService signUpService;
	
	/**
	 * 
	 * <p>获取微信access_token</p>
	 * @return
	 * @author 黄智聪  2019年4月17日 下午4:39:29
	 */
	@RequestMapping("/getAccesstoken")
	public JsonResult<String> getAccesstoken(){
		return signUpService.getAccesstoken();
	}
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @return
	 * @author 黄智聪  2019年4月16日 下午5:58:40
	 */
	@RequestMapping("/getActivityInfos")
	public JsonResult<Pagination<ActivityInfo>> getActivityInfos(@RequestBody SearchActivityInfoParam param) {
		return signUpService.getActivityInfos(param);
	}
}

