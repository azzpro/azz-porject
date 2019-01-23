/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月17日 下午1:36:59
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.azz.wx.course.service.OrderService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月17日 下午1:36:59
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class CourseOrderScheduler {
	
	private static final Logger log = LoggerFactory.getLogger(CourseOrderScheduler.class);
	
	@Autowired
	private OrderService orderService;
	
	
	/**
	 * 
	 * <p>每20分钟执行一次关闭订单的定时任务</p>
	 * @author 黄智聪  2018年11月17日 下午2:24:32
	 */
	@Scheduled(cron = "0 0/20 * * * ?")
	public void closeClientOrders() {
		log.info("定时任务【关闭6小时未支付的课程订单】执行开始");
		orderService.closeCourseOrders();
		log.info("定时任务【关闭6小时未支付的课程订单】执行完毕");
	}
}

