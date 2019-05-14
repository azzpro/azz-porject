/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.wx.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.wx.course.pojo.bo.AddSolicitContributionParam;
import com.azz.wx.course.pojo.bo.EditSolicitContributionParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelSolicitContributionParam;
import com.azz.wx.course.pojo.bo.SearchSolicitContributionParam;
import com.azz.wx.course.pojo.vo.SolicitContributionInfo;

/**
 * 
 * <P>
 * 征稿
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月17日 下午1:42:55
 */
@FeignClient("azz-wx-course-service")
public interface SolicitContributionService {
	
	
	/*************************************客户端start*************************************/
	
	/**
	 * 
	 * <p>查询征稿列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:08:04
	 */
	@RequestMapping("/azz/api/client/solicitContribution/getSolicitContributionInfos")
	public JsonResult<Pagination<SolicitContributionInfo>> getSolicitContributionInfos(@RequestBody SearchSolicitContributionParam param);
	
	/**
	 * 
	 * <p>查询征稿详情</p>
	 * @param solicitContributionCode
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:23:36
	 */
	@RequestMapping("/azz/api/client/solicitContribution/getSolicitContributionDetail")
	public JsonResult<SolicitContributionInfo> getSolicitContributionDetail(@RequestParam("solicitContributionCode")String solicitContributionCode);
	
	/*************************************客户端end*************************************/
	
	
	/*************************************平台端start*************************************/
	
	/**
	 * 
	 * <p>查询征稿列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:08:04
	 */
	@RequestMapping("/azz/api/platform/solicitContribution/getSolicitContributionInfos")
	public JsonResult<Pagination<SolicitContributionInfo>> getPlatformSolicitContributionInfos(@RequestBody SearchSolicitContributionParam param);
	
	/**
	 * 
	 * <p>查询征稿详情</p>
	 * @param solicitContributionCode
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:23:36
	 */
	@RequestMapping("/azz/api/platform/solicitContribution/getSolicitContributionDetail")
	public JsonResult<SolicitContributionInfo> getPlatformSolicitContributionDetail(@RequestParam("solicitContributionCode")String solicitContributionCode);
	
	/**
	 * 
	 * <p>新增征稿</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:43:38
	 */
	@RequestMapping("/azz/api/platform/solicitContribution/addSolicitContribution")
	public JsonResult<String> addSolicitContribution(@RequestBody AddSolicitContributionParam param);
	
	/**
	 * 
	 * <p>修改征稿</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	@RequestMapping("/azz/api/platform/solicitContribution/editSolicitContribution")
	public JsonResult<String> editSolicitContribution(@RequestBody EditSolicitContributionParam param);
	
	/**
	 * 
	 * <p>上架、下架或删除征稿</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	@RequestMapping("/azz/api/platform/solicitContribution/putOnOrPutOffOrDelSolicitContribution")
	public JsonResult<String> putOnOrPutOffOrDelSolicitContribution(@RequestBody PutOnOrPutOffOrDelSolicitContributionParam param);
	
	/*************************************平台端end*************************************/
}
