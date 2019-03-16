package com.azz.order.client.wx.bo;

public class WxPayOrderInfo {
	private String courseName;//课程名称
	private String courseNum;//开课编号
	private String coursePayNum;//课程订单编号；
	private String orderMoney;//订单金额
	private String ip;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}
	public String getCoursePayNum() {
		return coursePayNum;
	}
	public void setCoursePayNum(String coursePayNum) {
		this.coursePayNum = coursePayNum;
	}
	public String getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
