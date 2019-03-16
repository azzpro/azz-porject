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
import com.azz.wx.course.pojo.bo.CallBackParam;
import com.azz.wx.course.pojo.bo.ChangeOrderStatusParam;
import com.azz.wx.course.pojo.bo.EvaluateCourseParam;
import com.azz.wx.course.pojo.bo.PayOrderParam;
import com.azz.wx.course.pojo.bo.SearchCourseOrderParam;
import com.azz.wx.course.pojo.bo.SearchPersonalOrderParam;
import com.azz.wx.course.pojo.vo.CourseOrderDetail;
import com.azz.wx.course.pojo.vo.CourseOrderInfo;
import com.azz.wx.course.pojo.vo.PayOrderInfo;
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
@RequestMapping("/azz/api/client/course/order")
public class ClientOrderController {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 
	 * <p>生成去付款的课程订单信息</p>
	 * @return
	 * @author 黄智聪  2018年11月24日 上午10:58:45
	 */
	@RequestMapping(value = "generatePayOrderInfo", method = RequestMethod.POST)
	public JsonResult<PayOrderInfo> generatePayOrderInfo(@RequestBody PayOrderParam param){
		return orderService.generatePayOrderInfo(param);
	}
	
	/**
	 * 
	 * <p>查询待支付的课程订单信息</p>
	 * @return
	 * @author 黄智聪  2018年11月24日 上午10:58:45
	 */
	@RequestMapping(value = "getPayOrderInfo", method = RequestMethod.POST)
	public JsonResult<PayOrderInfo> getPayOrderInfo(@RequestParam("orderCode")String orderCode){
		return orderService.getPayOrderInfo(orderCode);
	}
	
	/**
	 * 
	 * <p>查询个人课程订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:15:33
	 */
	@RequestMapping(value = "getCourseOrders", method = RequestMethod.POST)
	public JsonResult<Pagination<CourseOrderInfo>> getCourseOrders(@RequestBody SearchCourseOrderParam param){
		return orderService.getCourseOrders(param);
	}
	
	/**
	 * 
	 * <p>查询个人报名课程信息列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月15日 下午12:07:16
	 */
	@RequestMapping(value = "getPersonalCourseOrders", method = RequestMethod.POST)
	public JsonResult<Pagination<CourseOrderInfo>> getPersonalCourseOrders(@RequestBody SearchPersonalOrderParam param){
		return orderService.getPersonalCourseOrders(param);
	}
	
	/**
	 * 
	 * <p>查询课程订单详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:34:28
	 */
	@RequestMapping(value = "getCourseOrderDetail", method = RequestMethod.POST)
	public JsonResult<CourseOrderDetail> getCourseOrderDetail(@RequestParam("orderCode")String orderCode){
		return orderService.getCourseOrderDetail(orderCode);
	} 
	
	/**
	 * 
	 * <p>评价课程</p>
	 * @return
	 * @author 黄智聪  2019年1月23日 上午10:48:25
	 */
	@RequestMapping(value = "evaluateCourse", method = RequestMethod.POST)
	public JsonResult<String> evaluateCourse(@RequestBody EvaluateCourseParam param){
		return orderService.evaluateCourse(param);
	}
	
	/**
	 * 
	 * <p>取消课程订单</p>
	 * @return
	 * @author 黄智聪  2019年1月23日 上午10:48:25
	 */
	@RequestMapping(value = "cancelCourseOrder", method = RequestMethod.POST)
	public JsonResult<String> cancelCourseOrder(@RequestBody ChangeOrderStatusParam param){
		return orderService.cancelCourseOrder(param);
	}
	
	/**
	 * 
	 * <p>确认课程订单</p>
	 * @return
	 * @author 黄智聪  2019年1月23日 上午10:48:25
	 */
	@RequestMapping(value = "confirmCourseOrder", method = RequestMethod.POST)
	public JsonResult<String> confirmCourseOrder(@RequestBody ChangeOrderStatusParam param){
		return orderService.confirmCourseOrder(param);
	}
	
	/**
	 * 
	 * <p>微信订单支付成功后的操作</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月26日 下午3:41:55
	 */
	@RequestMapping(value = "courseOrderPaySuccessOpt", method = RequestMethod.POST)
	public JsonResult<String> courseOrderPaySuccessOpt(@RequestBody CallBackParam param){
		return orderService.courseOrderPaySuccessOpt(param);
	}
	
}
