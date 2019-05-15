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
import com.azz.wx.course.pojo.bo.AddSolicitContributionParam;
import com.azz.wx.course.pojo.bo.EditSolicitContributionParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelSolicitContributionParam;
import com.azz.wx.course.pojo.bo.SearchSolicitContributionParam;
import com.azz.wx.course.pojo.vo.SolicitContributionInfo;
import com.azz.wx.course.service.SolicitContributionService;

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
public class SolicitContributionController {
	
	@Autowired
	private SolicitContributionService solicitContributionService;
	
	/*************************************客户端start*************************************/
	
	/**
	 * 
	 * <p>查询征稿列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:08:04
	 */
	@RequestMapping("/client/solicitContribution/getSolicitContributionInfos")
	public JsonResult<Pagination<SolicitContributionInfo>> getSolicitContributionInfos(@RequestBody SearchSolicitContributionParam param) {
		param.setStatus(1);
		return solicitContributionService.getSolicitContributionInfos(param);
	}
	
	/**
	 * 
	 * <p>查询征稿详情</p>
	 * @param solicitContributionCode
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:23:36
	 */
	@RequestMapping("/client/solicitContribution/getSolicitContributionDetail")
	public JsonResult<SolicitContributionInfo> getSolicitContributionDetail(@RequestParam("solicitContributionCode")String solicitContributionCode){
		return solicitContributionService.getSolicitContributionDetail(solicitContributionCode);
	}
	
	/*************************************客户端end*************************************/
	
	
	/*************************************平台端start*************************************/
	
	/**
	 * 
	 * <p>查询征稿列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:08:04
	 */
	@RequestMapping("/platform/solicitContribution/getSolicitContributionInfos")
	public JsonResult<Pagination<SolicitContributionInfo>> getPlatformSolicitContributionInfos(@RequestBody SearchSolicitContributionParam param) {
		return solicitContributionService.getSolicitContributionInfos(param);
	}
	
	/**
	 * 
	 * <p>查询征稿详情</p>
	 * @param solicitContributionCode
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:23:36
	 */
	@RequestMapping("/platform/solicitContribution/getSolicitContributionDetail")
	public JsonResult<SolicitContributionInfo> getPlatformSolicitContributionDetail(@RequestParam("solicitContributionCode")String solicitContributionCode){
		return solicitContributionService.getSolicitContributionDetail(solicitContributionCode);
	}
	
	/**
	 * 
	 * <p>新增征稿</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:43:38
	 */
	@RequestMapping("/platform/solicitContribution/addSolicitContribution")
	public JsonResult<String> addSolicitContribution(@RequestBody AddSolicitContributionParam param){
		return  solicitContributionService.addSolicitContribution(param);
	}
	
	
	/**
	 * 
	 * <p>修改征稿</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/platform/solicitContribution/editSolicitContribution")
	public JsonResult<String> editSolicitContribution(@RequestBody EditSolicitContributionParam param) {
		return solicitContributionService.editSolicitContribution(param);
	}
	
	/**
	 * 
	 * <p>上架、下架或删除征稿</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	@RequestMapping("/platform/solicitContribution/putOnOrPutOffOrDelSolicitContribution")
	public JsonResult<String> putOnOrPutOffOrDelSolicitContribution(@RequestBody PutOnOrPutOffOrDelSolicitContributionParam param){
		return solicitContributionService.putOnOrPutOffOrDelSolicitContribution(param);
	}
	
	/*************************************平台端end*************************************/
}
