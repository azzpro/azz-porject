package com.azz.order.client.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.order.client.wx.pojo.WxPay;

@Mapper
public interface WxPayMapper {
	
	int insertPay(WxPay wp);
	
	List<WxPay> selectWxOrder(String course_pay_num);
	
	String selectWxCourseNum(String no);
	
	Map<String,String> selectTotalFeeByOrderNum(String out_trade_no);
	
	int updateWxPayByCallback(@Param("otn") String otn,@Param("os") Integer os,@Param("ptp") String ptp,@Param("di") String di,@Param("son") String son);
}
