/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午2:11:16
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.pojo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午2:11:16
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ClientPay implements Serializable{
	/**
	 * TODO
	 */
	private static final long serialVersionUID = 8646390552957634890L;
	private Long id;
	private String payNumber;//支付流水号
	private String threePartyNumber;//三方流水号
	private String orderNumber;//订单编号
	private Byte payStatus;//支付状态 1待支付 2支付成功 3关闭支付
	private String payInstitution;//支付类型 1支付 2 微信 3第三方
	private BigDecimal payMoney;//支付金额
	private BigDecimal channelMoney;//渠道费用
	private Long customerPhone;//客户联系方式
	private Byte payMethod;//支付类型 1在线 2线下
	private String payTime; //支付时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPayNumber() {
		return payNumber;
	}
	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}
	public String getThreePartyNumber() {
		return threePartyNumber;
	}
	public void setThreePartyNumber(String threePartyNumber) {
		this.threePartyNumber = threePartyNumber;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Byte getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Byte payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayInstitution() {
		return payInstitution;
	}
	public void setPayInstitution(String payInstitution) {
		this.payInstitution = payInstitution;
	}
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	public BigDecimal getChannelMoney() {
		return channelMoney;
	}
	public void setChannelMoney(BigDecimal channelMoney) {
		this.channelMoney = channelMoney;
	}
	public Long getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(Long customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Byte getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(Byte payMethod) {
		this.payMethod = payMethod;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	@Override
	public String toString() {
		return "PlatformPay [id=" + id + ", payNumber=" + payNumber + ", threePartyNumber=" + threePartyNumber
				+ ", orderNumber=" + orderNumber + ", payStatus=" + payStatus + ", payInstitution=" + payInstitution + ", payMoney="
				+ payMoney + ", channelMoney=" + channelMoney + ", customerPhone=" + customerPhone + ", payMethod="
				+ payMethod + ", payTime=" + payTime + "]";
	}
	
	
}

