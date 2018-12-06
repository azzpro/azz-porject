package com.azz.order.client.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrderPersonal implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 客户订单编号
     *
     * @mbg.generated
     */
    private String clientOrderCode;
    
    private String orderCreator;

    /**
     * 客户id
     *
     * @mbg.generated
     */
    private Long clientUserId;

    /**
     * 订单金额
     *
     * @mbg.generated
     */
    private BigDecimal grandTotal;

    /**
     * 订单状态id
     *
     * @mbg.generated
     */
    private Integer orderStatusId;

    /**
     * 支付方式
     *
     * @mbg.generated
     */
    private Integer paymentMethod;

    /**
     * 支付状态 1未支付    2待支付     3支付成功  4支付失败
     *
     * @mbg.generated
     */
    private Integer paymentStatus;

    /**
     * 支付类型  1微信   2支付宝  3其他 
     *
     * @mbg.generated
     */
    private Integer paymentType;

    /**
     * 开票状态 0未开票  1已开票
     *
     * @mbg.generated
     */
    private Integer invoiceStatus;

    /**
     * 配送地址id
     *
     * @mbg.generated
     */
    private Long orderShippingId;

    /**
     * 订单备注
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * 状态 0无效 1有效
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 处理人   平台端用户编码，拆单的人
     *
     * @mbg.generated
     */
    private String handler;

    /**
     * 处理时间
     *
     * @mbg.generated
     */
    private Date handlerTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String creator;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    private String modifier;

    /**
     * 最后修改时间
     *
     * @mbg.generated
     */
    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 client_order_personal.id
     *
     * @return 返回值对应 client_order_personal.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * client_order_personal.id
     *
     * @param id 值对应 client_order_personal.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 客户订单编号<br/>
     * 返回值对应的表列名 client_order_personal.client_order_code
     *
     * @return 返回值对应 client_order_personal.client_order_code
     *
     * @mbg.generated
     */
    public String getClientOrderCode() {
        return clientOrderCode;
    }

    /**
     * 客户订单编号<br/>
     * client_order_personal.client_order_code
     *
     * @param clientOrderCode 值对应 client_order_personal.client_order_code
     *
     * @mbg.generated
     */
    public void setClientOrderCode(String clientOrderCode) {
        this.clientOrderCode = clientOrderCode == null ? null : clientOrderCode.trim();
    }

    /**
     * 客户id<br/>
     * 返回值对应的表列名 client_order_personal.client_user_id
     *
     * @return 返回值对应 client_order_personal.client_user_id
     *
     * @mbg.generated
     */
    public Long getClientUserId() {
        return clientUserId;
    }

    /**
     * 客户id<br/>
     * client_order_personal.client_user_id
     *
     * @param clientUserId 值对应 client_order_personal.client_user_id
     *
     * @mbg.generated
     */
    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    /**
     * 订单金额<br/>
     * 返回值对应的表列名 client_order_personal.grand_total
     *
     * @return 返回值对应 client_order_personal.grand_total
     *
     * @mbg.generated
     */
    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    /**
     * 订单金额<br/>
     * client_order_personal.grand_total
     *
     * @param grandTotal 值对应 client_order_personal.grand_total
     *
     * @mbg.generated
     */
    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    /**
     * 订单状态id<br/>
     * 返回值对应的表列名 client_order_personal.order_status_id
     *
     * @return 返回值对应 client_order_personal.order_status_id
     *
     * @mbg.generated
     */
    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    /**
     * 订单状态id<br/>
     * client_order_personal.order_status_id
     *
     * @param orderStatusId 值对应 client_order_personal.order_status_id
     *
     * @mbg.generated
     */
    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    /**
     * 支付方式<br/>
     * 返回值对应的表列名 client_order_personal.payment_method
     *
     * @return 返回值对应 client_order_personal.payment_method
     *
     * @mbg.generated
     */
    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * 支付方式<br/>
     * client_order_personal.payment_method
     *
     * @param paymentMethod 值对应 client_order_personal.payment_method
     *
     * @mbg.generated
     */
    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * 支付状态 1未支付    2待支付     3支付成功  4支付失败<br/>
     * 返回值对应的表列名 client_order_personal.payment_status
     *
     * @return 返回值对应 client_order_personal.payment_status
     *
     * @mbg.generated
     */
    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * 支付状态 1未支付    2待支付     3支付成功  4支付失败<br/>
     * client_order_personal.payment_status
     *
     * @param paymentStatus 值对应 client_order_personal.payment_status
     *
     * @mbg.generated
     */
    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * 支付类型  1微信   2支付宝  3其他 <br/>
     * 返回值对应的表列名 client_order_personal.payment_type
     *
     * @return 返回值对应 client_order_personal.payment_type
     *
     * @mbg.generated
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * 支付类型  1微信   2支付宝  3其他 <br/>
     * client_order_personal.payment_type
     *
     * @param paymentType 值对应 client_order_personal.payment_type
     *
     * @mbg.generated
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 开票状态 0未开票  1已开票<br/>
     * 返回值对应的表列名 client_order_personal.invoice_status
     *
     * @return 返回值对应 client_order_personal.invoice_status
     *
     * @mbg.generated
     */
    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * 开票状态 0未开票  1已开票<br/>
     * client_order_personal.invoice_status
     *
     * @param invoiceStatus 值对应 client_order_personal.invoice_status
     *
     * @mbg.generated
     */
    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    /**
     * 配送地址id<br/>
     * 返回值对应的表列名 client_order_personal.order_shipping_id
     *
     * @return 返回值对应 client_order_personal.order_shipping_id
     *
     * @mbg.generated
     */
    public Long getOrderShippingId() {
        return orderShippingId;
    }

    /**
     * 配送地址id<br/>
     * client_order_personal.order_shipping_id
     *
     * @param orderShippingId 值对应 client_order_personal.order_shipping_id
     *
     * @mbg.generated
     */
    public void setOrderShippingId(Long orderShippingId) {
        this.orderShippingId = orderShippingId;
    }

    /**
     * 订单备注<br/>
     * 返回值对应的表列名 client_order_personal.remark
     *
     * @return 返回值对应 client_order_personal.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 订单备注<br/>
     * client_order_personal.remark
     *
     * @param remark 值对应 client_order_personal.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 状态 0无效 1有效<br/>
     * 返回值对应的表列名 client_order_personal.status
     *
     * @return 返回值对应 client_order_personal.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态 0无效 1有效<br/>
     * client_order_personal.status
     *
     * @param status 值对应 client_order_personal.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 处理人   平台端用户编码，拆单的人<br/>
     * 返回值对应的表列名 client_order_personal.handler
     *
     * @return 返回值对应 client_order_personal.handler
     *
     * @mbg.generated
     */
    public String getHandler() {
        return handler;
    }

    /**
     * 处理人   平台端用户编码，拆单的人<br/>
     * client_order_personal.handler
     *
     * @param handler 值对应 client_order_personal.handler
     *
     * @mbg.generated
     */
    public void setHandler(String handler) {
        this.handler = handler == null ? null : handler.trim();
    }

    /**
     * 处理时间<br/>
     * 返回值对应的表列名 client_order_personal.handler_time
     *
     * @return 返回值对应 client_order_personal.handler_time
     *
     * @mbg.generated
     */
    public Date getHandlerTime() {
        return handlerTime;
    }

    /**
     * 处理时间<br/>
     * client_order_personal.handler_time
     *
     * @param handlerTime 值对应 client_order_personal.handler_time
     *
     * @mbg.generated
     */
    public void setHandlerTime(Date handlerTime) {
        this.handlerTime = handlerTime;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 client_order_personal.creator
     *
     * @return 返回值对应 client_order_personal.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * client_order_personal.creator
     *
     * @param creator 值对应 client_order_personal.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 client_order_personal.create_time
     *
     * @return 返回值对应 client_order_personal.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * client_order_personal.create_time
     *
     * @param createTime 值对应 client_order_personal.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 client_order_personal.modifier
     *
     * @return 返回值对应 client_order_personal.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * client_order_personal.modifier
     *
     * @param modifier 值对应 client_order_personal.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 client_order_personal.modify_time
     *
     * @return 返回值对应 client_order_personal.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 最后修改时间<br/>
     * client_order_personal.modify_time
     *
     * @param modifyTime 值对应 client_order_personal.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ClientOrderPersonal other = (ClientOrderPersonal) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClientOrderCode() == null ? other.getClientOrderCode() == null : this.getClientOrderCode().equals(other.getClientOrderCode()))
            && (this.getClientUserId() == null ? other.getClientUserId() == null : this.getClientUserId().equals(other.getClientUserId()))
            && (this.getGrandTotal() == null ? other.getGrandTotal() == null : this.getGrandTotal().equals(other.getGrandTotal()))
            && (this.getOrderStatusId() == null ? other.getOrderStatusId() == null : this.getOrderStatusId().equals(other.getOrderStatusId()))
            && (this.getPaymentMethod() == null ? other.getPaymentMethod() == null : this.getPaymentMethod().equals(other.getPaymentMethod()))
            && (this.getPaymentStatus() == null ? other.getPaymentStatus() == null : this.getPaymentStatus().equals(other.getPaymentStatus()))
            && (this.getPaymentType() == null ? other.getPaymentType() == null : this.getPaymentType().equals(other.getPaymentType()))
            && (this.getInvoiceStatus() == null ? other.getInvoiceStatus() == null : this.getInvoiceStatus().equals(other.getInvoiceStatus()))
            && (this.getOrderShippingId() == null ? other.getOrderShippingId() == null : this.getOrderShippingId().equals(other.getOrderShippingId()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getHandler() == null ? other.getHandler() == null : this.getHandler().equals(other.getHandler()))
            && (this.getHandlerTime() == null ? other.getHandlerTime() == null : this.getHandlerTime().equals(other.getHandlerTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClientOrderCode() == null) ? 0 : getClientOrderCode().hashCode());
        result = prime * result + ((getClientUserId() == null) ? 0 : getClientUserId().hashCode());
        result = prime * result + ((getGrandTotal() == null) ? 0 : getGrandTotal().hashCode());
        result = prime * result + ((getOrderStatusId() == null) ? 0 : getOrderStatusId().hashCode());
        result = prime * result + ((getPaymentMethod() == null) ? 0 : getPaymentMethod().hashCode());
        result = prime * result + ((getPaymentStatus() == null) ? 0 : getPaymentStatus().hashCode());
        result = prime * result + ((getPaymentType() == null) ? 0 : getPaymentType().hashCode());
        result = prime * result + ((getInvoiceStatus() == null) ? 0 : getInvoiceStatus().hashCode());
        result = prime * result + ((getOrderShippingId() == null) ? 0 : getOrderShippingId().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getHandler() == null) ? 0 : getHandler().hashCode());
        result = prime * result + ((getHandlerTime() == null) ? 0 : getHandlerTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", clientOrderCode=").append(clientOrderCode);
        sb.append(", clientUserId=").append(clientUserId);
        sb.append(", grandTotal=").append(grandTotal);
        sb.append(", orderStatusId=").append(orderStatusId);
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append(", paymentStatus=").append(paymentStatus);
        sb.append(", paymentType=").append(paymentType);
        sb.append(", invoiceStatus=").append(invoiceStatus);
        sb.append(", orderShippingId=").append(orderShippingId);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", handler=").append(handler);
        sb.append(", handlerTime=").append(handlerTime);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public String getOrderCreator() {
		return orderCreator;
	}

	public void setOrderCreator(String orderCreator) {
		this.orderCreator = orderCreator;
	}
    
    
}