/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月24日 下午5:23:02
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
import com.azz.wx.course.api.OrderService;
import com.azz.wx.course.pojo.bo.SearchCourseOrderParam;
import com.azz.wx.course.pojo.vo.PlatformCourseOrderDetail;
import com.azz.wx.course.pojo.vo.PlatformCourseOrderInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月24日 下午5:23:02
 */
@RestController
@RequestMapping("/azz/api/platform/course/order")
public class CourseOrderController {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 
	 * <p>平台端查询课程订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:15:33
	 */
	@RequestMapping(value = "/getPlatformCourseOrders", method = RequestMethod.POST)
	public JsonResult<Pagination<PlatformCourseOrderInfo>> getPlatformCourseOrders(SearchCourseOrderParam param){
		return orderService.getPlatformCourseOrders(param);
	}
	
	/**
	 * 
	 * <p>平台端查询课程订单详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:34:28
	 */
	@RequestMapping(value = "/getPlatformCourseOrderDetail", method = RequestMethod.POST)
	public JsonResult<PlatformCourseOrderDetail> getPlatformCourseOrderDetail(@RequestParam("orderCode") String orderCode){
		return orderService.getPlatformCourseOrderDetail(orderCode);
	}

}

