/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年4月16日 下午5:15:24
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.wx.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.wx.course.pojo.bo.AddActivityParam;
import com.azz.wx.course.pojo.bo.EditActivityParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelActivityParam;
import com.azz.wx.course.pojo.bo.SearchActivityInfoParam;
import com.azz.wx.course.pojo.vo.ActivityDetail;
import com.azz.wx.course.pojo.vo.ActivityInfo;

/**
 * <P>
 * 报名业务
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2019年4月16日 下午5:15:24
 */
@FeignClient("azz-wx-course-service")
public interface SignUpService {
	
	/**
	 * 
	 * <p>获取微信access_token</p>
	 * @return
	 * @author 黄智聪  2019年4月16日 下午5:54:47
	 */
	@RequestMapping(value = "/azz/api/client/activity/getAccesstoken", method = RequestMethod.POST)
	JsonResult<String> getAccesstoken();
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @param activityName
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping(value = "/azz/api/client/activity/getActivityInfos", method = RequestMethod.POST)
	JsonResult<Pagination<ActivityInfo>> getActivityInfos(@RequestBody SearchActivityInfoParam param);
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/azz/api/platform/activity/getPlatformActivityInfos")
	JsonResult<Pagination<ActivityInfo>> getPlatformActivityInfos(@RequestBody SearchActivityInfoParam param);
	
	/**
	 * 
	 * <p>查询活动详情</p>
	 * @param activityCode
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/azz/api/platform/activity/getPlatformActivityDetail")
	JsonResult<ActivityDetail> getPlatformActivityDetail(@RequestParam("activityCode") String activityCode);
	
	/**
	 * 
	 * <p>添加活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/azz/api/platform/activity/addActivity")
	JsonResult<String> addActivity(@RequestBody AddActivityParam param);
	
	/**
	 * 
	 * <p>添加活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/azz/api/platform/activity/editActivity")
	JsonResult<String> editActivity(@RequestBody EditActivityParam param);
	
	/**
	 * 
	 * <p>上架、下架或删除活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	@RequestMapping("/azz/api/platform/activity/putOnOrPutOffOrDelActivity")
	JsonResult<String> putOnOrPutOffOrDelActivity(@RequestBody PutOnOrPutOffOrDelActivityParam param);
	
}
