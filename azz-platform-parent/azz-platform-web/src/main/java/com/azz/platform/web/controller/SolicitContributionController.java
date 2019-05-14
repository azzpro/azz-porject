/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年5月14日 上午10:10:45
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.utils.WebUtils;
import com.azz.wx.course.api.SolicitContributionService;
import com.azz.wx.course.pojo.bo.AddSolicitContributionParam;
import com.azz.wx.course.pojo.bo.EditSolicitContributionParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelSolicitContributionParam;
import com.azz.wx.course.pojo.bo.SearchSolicitContributionParam;
import com.azz.wx.course.pojo.vo.SolicitContributionInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年5月14日 上午10:10:45
 */
@RestController
@RequestMapping("/azz/api/platform/solicitContribution")
public class SolicitContributionController {
	
	@Autowired
	private SolicitContributionService solicitContributionService;
	
	/**
	 * 
	 * <p>查询征稿列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:08:04
	 */
	@RequestMapping("/getSolicitContributionInfos")
	public JsonResult<Pagination<SolicitContributionInfo>> getPlatformSolicitContributionInfos(SearchSolicitContributionParam param) {
		return solicitContributionService.getPlatformSolicitContributionInfos(param);
	}
	
	/**
	 * 
	 * <p>查询征稿详情</p>
	 * @param solicitContributionCode
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:23:36
	 */
	@RequestMapping("/getSolicitContributionDetail")
	public JsonResult<SolicitContributionInfo> getPlatformSolicitContributionDetail(@RequestParam("solicitContributionCode")String solicitContributionCode){
		return solicitContributionService.getPlatformSolicitContributionDetail(solicitContributionCode);
	}
	
	/**
	 * 
	 * <p>新增征稿</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:43:38
	 */
	@RequestMapping("/addSolicitContribution")
	public JsonResult<String> addSolicitContribution(AddSolicitContributionParam param){
		param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return  solicitContributionService.addSolicitContribution(param);
	}
	
	
	/**
	 * 
	 * <p>修改征稿</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/editSolicitContribution")
	public JsonResult<String> editSolicitContribution(EditSolicitContributionParam param) {
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return solicitContributionService.editSolicitContribution(param);
	}
	
	/**
	 * 
	 * <p>上架、下架或删除征稿</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	@RequestMapping("/putOnOrPutOffOrDelActivity")
	public JsonResult<String> putOnOrPutOffOrDelActivity(PutOnOrPutOffOrDelSolicitContributionParam param){
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return solicitContributionService.putOnOrPutOffOrDelActivity(param);
	}

}

