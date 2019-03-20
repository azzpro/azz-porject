package com.azz.order.client.wx.pojo;

import java.io.Serializable;

public class WxPay implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6663777349230290456L;
	private Long id;
	private String goodsBody;//商品简单描述
	private String goodsInfo;//商品详情描述
	private String feeType;//币种
	private String totalFee;//金额(分)
	private String createIp;//IP
	private String createOrderTime;//订单创建时间
	private String expireOrderTime;//订单失效时间
	private Integer orderStatus;//1 待支付 2支付成功 3支付关闭 4支付失败
	private String courseName;//课程名称
	private String courseNum;//开课编号
	private String coursePayNum;//课程订单编号；
	private String outTradeNo;//商户订单号
	private String payTimeEnd;//支付完成时间
	private String transactionId;//微信支付订单号
	private String deviceInfo;//设备号
	private String sign;
	
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGoodsBody() {
		return goodsBody;
	}
	public void setGoodsBody(String goodsBody) {
		this.goodsBody = goodsBody;
	}
	public String getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getCreateIp() {
		return createIp;
	}
	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}
	public String getCreateOrderTime() {
		return createOrderTime;
	}
	public void setCreateOrderTime(String createOrderTime) {
		this.createOrderTime = createOrderTime;
	}
	public String getExpireOrderTime() {
		return expireOrderTime;
	}
	public void setExpireOrderTime(String expireOrderTime) {
		this.expireOrderTime = expireOrderTime;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
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
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getPayTimeEnd() {
		return payTimeEnd;
	}
	public void setPayTimeEnd(String payTimeEnd) {
		this.payTimeEnd = payTimeEnd;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	@Override
	public String toString() {
		return "WxPay [id=" + id + ", goodsBody=" + goodsBody + ", goodsInfo=" + goodsInfo + ", feeType=" + feeType
				+ ", totalFee=" + totalFee + ", createIp=" + createIp + ", createOrderTime=" + createOrderTime
				+ ", expireOrderTime=" + expireOrderTime + ", orderStatus=" + orderStatus + ", courseName=" + courseName
				+ ", courseNum=" + courseNum + ", coursePayNum=" + coursePayNum + ", outTradeNo=" + outTradeNo
				+ ", payTimeEnd=" + payTimeEnd + ", transactionId=" + transactionId + ", deviceInfo=" + deviceInfo
				+ "]";
	}
	
	
	
}
