/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.wx.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.wx.course.pojo.bo.AddActivityParam;
import com.azz.wx.course.pojo.bo.EditActivityParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelActivityParam;
import com.azz.wx.course.pojo.bo.SearchActivityInfoParam;
import com.azz.wx.course.pojo.vo.ActivityDetail;
import com.azz.wx.course.pojo.vo.ActivityInfo;
import com.azz.wx.course.service.SignUpService;

/**
 * 
 * <P>
 * 课程相关控制器
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月17日 下午1:42:55
 */
@RestController
@RequestMapping("/azz/api")
public class SignUpController {
	
	@Autowired
	private SignUpService signUpService;
	
	/**
	 * 
	 * <p>获取微信access_token</p>
	 * @return
	 * @author 黄智聪  2019年4月17日 下午4:39:59
	 */
	@RequestMapping("/client/activity/getAccesstoken")
	public JsonResult<String> getAccesstoken(){
		return signUpService.getAccesstoken();
	}
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @return
	 * @author 黄智聪  2019年4月16日 下午5:58:40
	 */
	@RequestMapping("/client/activity/getActivityInfos")
	public JsonResult<Pagination<ActivityInfo>> getActivityInfos(@RequestBody SearchActivityInfoParam param) {
		return signUpService.getActivityInfos(param);
	}
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/platform/activity/getPlatformActivityInfos")
	public JsonResult<Pagination<ActivityInfo>> getPlatformActivityInfos(@RequestBody SearchActivityInfoParam param) {
		return signUpService.getPlatformActivityInfos(param);
	}
	
	/**
	 * 
	 * <p>查询活动详情</p>
	 * @param activityCode
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/platform/activity/getPlatformActivityDetail")
	public JsonResult<ActivityDetail> getPlatformActivityDetail(@RequestParam("activityCode") String activityCode) {
		return signUpService.getPlatformActivityDetail(activityCode);
	}
	
	/**
	 * 
	 * <p>添加活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/platform/activity/addActivity")
	public JsonResult<String> addActivity(@RequestBody AddActivityParam param) {
		return signUpService.addActivity(param);
	}
	
	/**
	 * 
	 * <p>添加活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/platform/activity/editActivity")
	public JsonResult<String> editActivity(@RequestBody EditActivityParam param) {
		return signUpService.editActivity(param);
	}
	
	/**
	 * 
	 * <p>上架、下架或删除活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	@RequestMapping("/platform/activity/putOnOrPutOffOrDelActivity")
	public JsonResult<String> putOnOrPutOffOrDelActivity(@RequestBody PutOnOrPutOffOrDelActivityParam param){
		return signUpService.putOnOrPutOffOrDelActivity(param);
	}
	
	
}
