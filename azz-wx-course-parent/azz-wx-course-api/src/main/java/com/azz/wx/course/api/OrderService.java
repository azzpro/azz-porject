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
import com.azz.wx.course.pojo.bo.CallBackParam;
import com.azz.wx.course.pojo.bo.ChangeOrderStatusParam;
import com.azz.wx.course.pojo.bo.EvaluateCourseParam;
import com.azz.wx.course.pojo.bo.PayOrderParam;
import com.azz.wx.course.pojo.bo.SearchCourseOrderParam;
import com.azz.wx.course.pojo.bo.SearchPersonalOrderParam;
import com.azz.wx.course.pojo.vo.CourseOrderDetail;
import com.azz.wx.course.pojo.vo.CourseOrderInfo;
import com.azz.wx.course.pojo.vo.PayOrderInfo;
import com.azz.wx.course.pojo.vo.PlatformCourseOrderDetail;
import com.azz.wx.course.pojo.vo.PlatformCourseOrderInfo;

/**
 * 
 * <P>
 * 课程相关控制器
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月17日 下午1:42:55
 */
@FeignClient("azz-wx-course-service")
public interface OrderService {
	
	/**
	 * 
	 * <p>生成去付款的课程订单信息</p>
	 * @return
	 * @author 黄智聪  2018年11月24日 上午10:58:45
	 */
	@RequestMapping(value = "/azz/api/client/course/order/generatePayOrderInfo", method = RequestMethod.POST)
	public JsonResult<PayOrderInfo> generatePayOrderInfo(@RequestBody PayOrderParam param);
	
	/**
	 * 
	 * <p>查询待支付的课程订单信息</p>
	 * @return
	 * @author 黄智聪  2018年11月24日 上午10:58:45
	 */
	@RequestMapping(value = "/azz/api/client/course/order/getPayOrderInfo", method = RequestMethod.POST)
	public JsonResult<PayOrderInfo> getPayOrderInfo(@RequestParam("orderCode")String orderCode);
	
	/**
	 * 
	 * <p>查询个人课程订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:15:33
	 */
	@RequestMapping(value = "/azz/api/client/course/order/getCourseOrders", method = RequestMethod.POST)
	public JsonResult<Pagination<CourseOrderInfo>> getCourseOrders(@RequestBody SearchCourseOrderParam param);
	
	/**
	 * 
	 * <p>查询个人报名课程信息列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月15日 下午12:08:00
	 */
	@RequestMapping(value = "/azz/api/client/course/order/getPersonalCourseOrders", method = RequestMethod.POST)
	public JsonResult<Pagination<CourseOrderInfo>> getPersonalCourseOrders(@RequestBody SearchPersonalOrderParam param);
	
	/**
	 * 
	 * <p>查询课程订单详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:34:28
	 */
	@RequestMapping(value = "/azz/api/client/course/order/getCourseOrderDetail", method = RequestMethod.POST)
	public JsonResult<CourseOrderDetail> getCourseOrderDetail(@RequestParam("orderCode")String orderCode);
	
	/**
	 * 
	 * <p>评价课程</p>
	 * @return
	 * @author 黄智聪  2019年1月23日 上午10:48:25
	 */
	@RequestMapping(value = "/azz/api/client/course/order/evaluateCourse", method = RequestMethod.POST)
	public JsonResult<String> evaluateCourse(@RequestBody EvaluateCourseParam param);
	
	/**
	 * 
	 * <p>取消课程订单</p>
	 * @return
	 * @author 黄智聪  2019年1月23日 上午10:48:25
	 */
	@RequestMapping(value = "/azz/api/client/course/order/cancelCourseOrder", method = RequestMethod.POST)
	public JsonResult<String> cancelCourseOrder(@RequestBody ChangeOrderStatusParam param);
	
	/**
	 * 
	 * <p>确认课程订单</p>
	 * @return
	 * @author 黄智聪  2019年1月23日 上午10:48:25
	 */
	@RequestMapping(value = "/azz/api/client/course/order/confirmCourseOrder", method = RequestMethod.POST)
	public JsonResult<String> confirmCourseOrder(@RequestBody ChangeOrderStatusParam param);

	/**
	 * 
	 * <p>微信订单支付成功后的操作</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月26日 下午3:41:55
	 */
	@RequestMapping(value = "/azz/api/client/course/order/courseOrderPaySuccessOpt", method = RequestMethod.POST)
	public JsonResult<String> courseOrderPaySuccessOpt(@RequestBody CallBackParam param);
	
	
	/**
	 * 
	 * <p>平台端查询课程订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:15:33
	 */
	@RequestMapping(value = "/azz/api/platform/course/order/getPlatformCourseOrders", method = RequestMethod.POST)
	public JsonResult<Pagination<PlatformCourseOrderInfo>> getPlatformCourseOrders(@RequestBody SearchCourseOrderParam param);
	
	/**
	 * 
	 * <p>平台端查询课程订单详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月22日 下午6:34:28
	 */
	@RequestMapping(value = "/azz/api/platform/course/order/getPlatformCourseOrderDetail", method = RequestMethod.POST)
	public JsonResult<PlatformCourseOrderDetail> getPlatformCourseOrderDetail(@RequestParam("orderCode") String orderCode);

}
