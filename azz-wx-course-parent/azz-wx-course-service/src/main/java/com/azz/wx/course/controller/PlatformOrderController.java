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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.wx.course.pojo.bo.SearchCourseOrderParam;
import com.azz.wx.course.pojo.vo.PlatformCourseOrderDetail;
import com.azz.wx.course.pojo.vo.PlatformCourseOrderInfo;
import com.azz.wx.course.service.OrderService;

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
@RequestMapping("/azz/api/platform/course/order")
public class PlatformOrderController {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 
	 * <p>平台端查询课程订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:15:33
	 */
	@RequestMapping(value = "getPlatformCourseOrders", method = RequestMethod.POST)
	public JsonResult<Pagination<PlatformCourseOrderInfo>> getPlatformCourseOrders(@RequestBody SearchCourseOrderParam param){
		return orderService.getPlatformCourseOrders(param);
	}
	
	/**
	 * 
	 * <p>平台端查询课程订单详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:34:28
	 */
	@RequestMapping(value = "getPlatformCourseOrderDetail", method = RequestMethod.POST)
	public JsonResult<PlatformCourseOrderDetail> getPlatformCourseOrderDetail(@RequestParam("orderCode") String orderCode){
		return orderService.getPlatformCourseOrderDetail(orderCode);
	} 
	
}
