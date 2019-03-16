package com.azz.order.client.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.client.wx.pojo.WxPay;

@Mapper
public interface WxPayMapper {
	
	int insertPay(WxPay wp);
	
	WxPay selectWxOrder(String course_pay_num);
}
