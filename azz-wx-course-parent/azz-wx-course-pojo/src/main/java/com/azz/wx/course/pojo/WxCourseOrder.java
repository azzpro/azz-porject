package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WxCourseOrder implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    private String orderCode;

    /**
     * 下单人名称
     *
     * @mbg.generated
     */
    private String orderCreator;

    /**
     * 订单状态id
     *
     * @mbg.generated
     */
    private Integer orderStatusId;

    /**
     * 报名信息编码
     *
     * @mbg.generated
     */
    private String applyInfoCode;

    /**
     * 订单金额
     *
     * @mbg.generated
     */
    private BigDecimal grandTotal;

    /**
     * 支付方式 1在线支付  2线下支付
     *
     * @mbg.generated
     */
    private Integer paymentMethod;

    /**
     * 支付状态 1待支付    2支付成功 3支付关闭 4支付失败
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
     * 确认订单的人
     *
     * @mbg.generated
     */
    private String confirmPerson;

    /**
     * 状态 0无效 1有效
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 订单备注
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String creator;

    /**
     * 创建人
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
     * 修改时间
     *
     * @mbg.generated
     */
    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 wx_course_order.id
     *
     * @return 返回值对应 wx_course_order.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_course_order.id
     *
     * @param id 值对应 wx_course_order.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <br/>
     * 返回值对应的表列名 wx_course_order.order_code
     *
     * @return 返回值对应 wx_course_order.order_code
     *
     * @mbg.generated
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * <br/>
     * wx_course_order.order_code
     *
     * @param orderCode 值对应 wx_course_order.order_code
     *
     * @mbg.generated
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * 下单人名称<br/>
     * 返回值对应的表列名 wx_course_order.order_creator
     *
     * @return 返回值对应 wx_course_order.order_creator
     *
     * @mbg.generated
     */
    public String getOrderCreator() {
        return orderCreator;
    }

    /**
     * 下单人名称<br/>
     * wx_course_order.order_creator
     *
     * @param orderCreator 值对应 wx_course_order.order_creator
     *
     * @mbg.generated
     */
    public void setOrderCreator(String orderCreator) {
        this.orderCreator = orderCreator == null ? null : orderCreator.trim();
    }

    /**
     * 订单状态id<br/>
     * 返回值对应的表列名 wx_course_order.order_status_id
     *
     * @return 返回值对应 wx_course_order.order_status_id
     *
     * @mbg.generated
     */
    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    /**
     * 订单状态id<br/>
     * wx_course_order.order_status_id
     *
     * @param orderStatusId 值对应 wx_course_order.order_status_id
     *
     * @mbg.generated
     */
    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    /**
     * 报名信息编码<br/>
     * 返回值对应的表列名 wx_course_order.apply_info_code
     *
     * @return 返回值对应 wx_course_order.apply_info_code
     *
     * @mbg.generated
     */
    public String getApplyInfoCode() {
        return applyInfoCode;
    }

    /**
     * 报名信息编码<br/>
     * wx_course_order.apply_info_code
     *
     * @param applyInfoCode 值对应 wx_course_order.apply_info_code
     *
     * @mbg.generated
     */
    public void setApplyInfoCode(String applyInfoCode) {
        this.applyInfoCode = applyInfoCode == null ? null : applyInfoCode.trim();
    }

    /**
     * 订单金额<br/>
     * 返回值对应的表列名 wx_course_order.grand_total
     *
     * @return 返回值对应 wx_course_order.grand_total
     *
     * @mbg.generated
     */
    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    /**
     * 订单金额<br/>
     * wx_course_order.grand_total
     *
     * @param grandTotal 值对应 wx_course_order.grand_total
     *
     * @mbg.generated
     */
    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    /**
     * 支付方式 1在线支付  2线下支付<br/>
     * 返回值对应的表列名 wx_course_order.payment_method
     *
     * @return 返回值对应 wx_course_order.payment_method
     *
     * @mbg.generated
     */
    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * 支付方式 1在线支付  2线下支付<br/>
     * wx_course_order.payment_method
     *
     * @param paymentMethod 值对应 wx_course_order.payment_method
     *
     * @mbg.generated
     */
    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * 支付状态 1待支付    2支付成功 3支付关闭 4支付失败<br/>
     * 返回值对应的表列名 wx_course_order.payment_status
     *
     * @return 返回值对应 wx_course_order.payment_status
     *
     * @mbg.generated
     */
    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * 支付状态 1待支付    2支付成功 3支付关闭 4支付失败<br/>
     * wx_course_order.payment_status
     *
     * @param paymentStatus 值对应 wx_course_order.payment_status
     *
     * @mbg.generated
     */
    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * 支付类型  1微信   2支付宝  3其他 <br/>
     * 返回值对应的表列名 wx_course_order.payment_type
     *
     * @return 返回值对应 wx_course_order.payment_type
     *
     * @mbg.generated
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * 支付类型  1微信   2支付宝  3其他 <br/>
     * wx_course_order.payment_type
     *
     * @param paymentType 值对应 wx_course_order.payment_type
     *
     * @mbg.generated
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 开票状态 0未开票  1已开票<br/>
     * 返回值对应的表列名 wx_course_order.invoice_status
     *
     * @return 返回值对应 wx_course_order.invoice_status
     *
     * @mbg.generated
     */
    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * 开票状态 0未开票  1已开票<br/>
     * wx_course_order.invoice_status
     *
     * @param invoiceStatus 值对应 wx_course_order.invoice_status
     *
     * @mbg.generated
     */
    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    /**
     * 确认订单的人<br/>
     * 返回值对应的表列名 wx_course_order.confirm_person
     *
     * @return 返回值对应 wx_course_order.confirm_person
     *
     * @mbg.generated
     */
    public String getConfirmPerson() {
        return confirmPerson;
    }

    /**
     * 确认订单的人<br/>
     * wx_course_order.confirm_person
     *
     * @param confirmPerson 值对应 wx_course_order.confirm_person
     *
     * @mbg.generated
     */
    public void setConfirmPerson(String confirmPerson) {
        this.confirmPerson = confirmPerson == null ? null : confirmPerson.trim();
    }

    /**
     * 状态 0无效 1有效<br/>
     * 返回值对应的表列名 wx_course_order.status
     *
     * @return 返回值对应 wx_course_order.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态 0无效 1有效<br/>
     * wx_course_order.status
     *
     * @param status 值对应 wx_course_order.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 订单备注<br/>
     * 返回值对应的表列名 wx_course_order.remark
     *
     * @return 返回值对应 wx_course_order.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 订单备注<br/>
     * wx_course_order.remark
     *
     * @param remark 值对应 wx_course_order.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course_order.creator
     *
     * @return 返回值对应 wx_course_order.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_course_order.creator
     *
     * @param creator 值对应 wx_course_order.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course_order.create_time
     *
     * @return 返回值对应 wx_course_order.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建人<br/>
     * wx_course_order.create_time
     *
     * @param createTime 值对应 wx_course_order.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_course_order.modifier
     *
     * @return 返回值对应 wx_course_order.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_course_order.modifier
     *
     * @param modifier 值对应 wx_course_order.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_course_order.modify_time
     *
     * @return 返回值对应 wx_course_order.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_course_order.modify_time
     *
     * @param modifyTime 值对应 wx_course_order.modify_time
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
        WxCourseOrder other = (WxCourseOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderCode() == null ? other.getOrderCode() == null : this.getOrderCode().equals(other.getOrderCode()))
            && (this.getOrderCreator() == null ? other.getOrderCreator() == null : this.getOrderCreator().equals(other.getOrderCreator()))
            && (this.getOrderStatusId() == null ? other.getOrderStatusId() == null : this.getOrderStatusId().equals(other.getOrderStatusId()))
            && (this.getApplyInfoCode() == null ? other.getApplyInfoCode() == null : this.getApplyInfoCode().equals(other.getApplyInfoCode()))
            && (this.getGrandTotal() == null ? other.getGrandTotal() == null : this.getGrandTotal().equals(other.getGrandTotal()))
            && (this.getPaymentMethod() == null ? other.getPaymentMethod() == null : this.getPaymentMethod().equals(other.getPaymentMethod()))
            && (this.getPaymentStatus() == null ? other.getPaymentStatus() == null : this.getPaymentStatus().equals(other.getPaymentStatus()))
            && (this.getPaymentType() == null ? other.getPaymentType() == null : this.getPaymentType().equals(other.getPaymentType()))
            && (this.getInvoiceStatus() == null ? other.getInvoiceStatus() == null : this.getInvoiceStatus().equals(other.getInvoiceStatus()))
            && (this.getConfirmPerson() == null ? other.getConfirmPerson() == null : this.getConfirmPerson().equals(other.getConfirmPerson()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
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
        result = prime * result + ((getOrderCode() == null) ? 0 : getOrderCode().hashCode());
        result = prime * result + ((getOrderCreator() == null) ? 0 : getOrderCreator().hashCode());
        result = prime * result + ((getOrderStatusId() == null) ? 0 : getOrderStatusId().hashCode());
        result = prime * result + ((getApplyInfoCode() == null) ? 0 : getApplyInfoCode().hashCode());
        result = prime * result + ((getGrandTotal() == null) ? 0 : getGrandTotal().hashCode());
        result = prime * result + ((getPaymentMethod() == null) ? 0 : getPaymentMethod().hashCode());
        result = prime * result + ((getPaymentStatus() == null) ? 0 : getPaymentStatus().hashCode());
        result = prime * result + ((getPaymentType() == null) ? 0 : getPaymentType().hashCode());
        result = prime * result + ((getInvoiceStatus() == null) ? 0 : getInvoiceStatus().hashCode());
        result = prime * result + ((getConfirmPerson() == null) ? 0 : getConfirmPerson().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
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
        sb.append(", orderCode=").append(orderCode);
        sb.append(", orderCreator=").append(orderCreator);
        sb.append(", orderStatusId=").append(orderStatusId);
        sb.append(", applyInfoCode=").append(applyInfoCode);
        sb.append(", grandTotal=").append(grandTotal);
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append(", paymentStatus=").append(paymentStatus);
        sb.append(", paymentType=").append(paymentType);
        sb.append(", invoiceStatus=").append(invoiceStatus);
        sb.append(", confirmPerson=").append(confirmPerson);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}