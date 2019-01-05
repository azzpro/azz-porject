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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
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
@FeignClient("azz-wx-course-service")
public interface StartClassService {
	
	/**
	 * 
	 * <p>查询开课信息列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:37:39
	 */
	@RequestMapping(value = "/azz/api/platform/startClass/getStartClassRecords", method = RequestMethod.POST)
	public JsonResult<Pagination<StartClassRecord>> getStartClassRecords(@RequestBody SearchStartClassRecordParam param);
	
	/**
	 * 
	 * <p>查询开课信息详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:37:39
	 */
	@RequestMapping(value = "/azz/api/platform/startClass/getStartClassRecordDetail", method = RequestMethod.POST)
	public JsonResult<StartClassRecord> getStartClassRecordDetail(@RequestParam("startClassCode") String startClassCode);
	
	/**
	 * 
	 * <p>新增开课信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:57:17
	 */
	@RequestMapping(value = "/azz/api/platform/startClass/addStartClassRecord", method = RequestMethod.POST)
	public JsonResult<String> addStartClassRecord(@RequestBody AddStartClassRecordParam param);
	
	/**
	 * 
	 * <p>修改开课信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午5:57:17
	 */
	@RequestMapping(value = "/azz/api/platform/startClass/editStartClassRecord", method = RequestMethod.POST)
	public JsonResult<String> editStartClassRecord(@RequestBody EditStartClassRecordParam param);
	
	/**
	 * 
	 * <p>上架、下架或删除开课信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	@RequestMapping(value = "/azz/api/platform/startClass/putOnOrPutOffOrDelStartClassRecord", method = RequestMethod.POST)
	public JsonResult<String> putOnOrPutOffOrDelStartClassRecord(@RequestBody PutOnOrPutOffOrDelStartClassRecordParam param);
	
}
