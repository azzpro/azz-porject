/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年4月17日 下午7:41:30
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azz.core.common.JsonResult;
import com.azz.core.common.QueryPage;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.WxActivityConstants.IsChangeActivityPic;
import com.azz.util.Base64;
import com.azz.util.JSR303ValidateUtils;
import com.azz.utils.WebUtils;
import com.azz.wx.course.api.SignUpService;
import com.azz.wx.course.pojo.bo.ActivityPic;
import com.azz.wx.course.pojo.bo.AddActivityParam;
import com.azz.wx.course.pojo.bo.AddActivityWebParam;
import com.azz.wx.course.pojo.bo.EditActivityParam;
import com.azz.wx.course.pojo.bo.EditActivityWebParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelActivityParam;
import com.azz.wx.course.pojo.bo.SearchActivityInfoParam;
import com.azz.wx.course.pojo.bo.ShieldOrCancelShiedEvaluationParam;
import com.azz.wx.course.pojo.vo.ActivityEvaluationInfo;
import com.azz.wx.course.pojo.vo.ActivityInfo;
import com.azz.wx.course.pojo.vo.SignUpInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年4月17日 下午7:41:30
 */
@RestController
@RequestMapping("/azz/api/platform/activity")
public class ActivitySignUpController {
	
	@Autowired
	SignUpService signUpService;

	/**
	 * 
	 * <p>查询活动列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("getPlatformActivityInfos")
	public JsonResult<Pagination<ActivityInfo>> getPlatformActivityInfos(SearchActivityInfoParam param) {
		return signUpService.getPlatformActivityInfos(param);
	}
	
	/**
	 * 
	 * <p>查询活动详情</p>
	 * @param activityCode
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("getPlatformActivityDetail")
	public JsonResult<ActivityInfo> getPlatformActivityDetail(@RequestParam("activityCode") String activityCode) {
		return signUpService.getPlatformActivityDetail(activityCode);
	}
	
	/**
	 * 
	 * <p>查询活动报名人员信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("getPlatformSignUpInfos")
	public JsonResult<Pagination<SignUpInfo>> getPlatformSignUpInfos(SearchActivityInfoParam param) {
		return signUpService.getPlatformSignUpInfos(param);
	}
	
	/**
	 * 
	 * <p>添加活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 * @throws IOException 
	 */
	@RequestMapping("addActivity")
	public JsonResult<String> addActivity(AddActivityWebParam webParam) throws IOException {
		JSR303ValidateUtils.validateInputParam(webParam);
		AddActivityParam param = new AddActivityParam();
		BeanUtils.copyProperties(webParam, param);
		MultipartFile activityPicFile = webParam.getActivityPicFile();
		ActivityPic activityPic = new ActivityPic(activityPicFile.getOriginalFilename(),
				activityPicFile.getSize(), Base64.encode(activityPicFile.getBytes()));
		param.setActivityPic(activityPic);
		param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return signUpService.addActivity(param);
	}
	
	/**
	 * 
	 * <p>添加活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("editActivity")
	public JsonResult<String> editActivity(EditActivityWebParam webParam) throws IOException{
		JSR303ValidateUtils.validateInputParam(webParam);
		EditActivityParam param = new EditActivityParam();
		BeanUtils.copyProperties(webParam, param);
		MultipartFile activityPicFile = webParam.getActivityPicFile();
		if (webParam.getIsChangeActivityPic() == IsChangeActivityPic.Y.getValue() && activityPicFile != null) {
			ActivityPic activityPic = new ActivityPic(activityPicFile.getOriginalFilename(),
					activityPicFile.getSize(), Base64.encode(activityPicFile.getBytes()));
			param.setActivityPic(activityPic);
		}
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return signUpService.editActivity(param);
	}
	
	/**
	 * 
	 * <p>上架、下架或删除活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	@RequestMapping("putOnOrPutOffOrDelActivity")
	public JsonResult<String> putOnOrPutOffOrDelActivity(PutOnOrPutOffOrDelActivityParam param){
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return signUpService.putOnOrPutOffOrDelActivity(param);
	}
	
	/**
	 * 
	 * <p>查询活动评价</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月29日 下午2:01:16
	 */
	@RequestMapping("getPlatformEvaluationInfos")
	public JsonResult<Pagination<ActivityEvaluationInfo>> getPlatformEvaluationInfos(@RequestBody QueryPage param) {
		return signUpService.getPlatformEvaluationInfos(param);
	}
	
	/**
	 * 
	 * <p>屏蔽或取消屏蔽评论</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月29日 下午2:19:45
	 */
	@RequestMapping("shieldOrCancelShiedEvaluation")
	public JsonResult<String> shieldOrCancelShiedEvaluation(@RequestBody ShieldOrCancelShiedEvaluationParam param) {
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return signUpService.shieldOrCancelShiedEvaluation(param);
	}
	
}

