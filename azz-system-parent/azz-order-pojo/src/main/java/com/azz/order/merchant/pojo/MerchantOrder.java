package com.azz.order.merchant.pojo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MerchantOrder implements Serializable {
    /**
     * 自增id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    private String merchantOrderCode;

    /**
     * 所属商户id
     *
     * @mbg.generated
     */
    private Long merchantId;

    /**
     * 客户订单id
     *
     * @mbg.generated
     */
    private Long clientOrderId;

    /**
     * 订单累计金额
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
     * 开票状态
     *
     * @mbg.generated
     */
    private Integer invoiceStatus;

    /**
     * 订单类型（ 0个人  1企业）
     *
     * @mbg.generated
     */
    private Integer orderType;

    /**
     * 订单状态显示 0无效 1有效
     *
     * @mbg.generated
     */
    private Integer status;

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
     * 修改时间
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * 自增id<br/>
     * 返回值对应的表列名 merchant_order.id
     *
     * @return 返回值对应 merchant_order.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 自增id<br/>
     * merchant_order.id
     *
     * @param id 值对应 merchant_order.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 订单编号<br/>
     * 返回值对应的表列名 merchant_order.merchant_order_code
     *
     * @return 返回值对应 merchant_order.merchant_order_code
     *
     * @mbg.generated
     */
    public String getMerchantOrderCode() {
        return merchantOrderCode;
    }

    /**
     * 订单编号<br/>
     * merchant_order.merchant_order_code
     *
     * @param merchantOrderCode 值对应 merchant_order.merchant_order_code
     *
     * @mbg.generated
     */
    public void setMerchantOrderCode(String merchantOrderCode) {
        this.merchantOrderCode = merchantOrderCode == null ? null : merchantOrderCode.trim();
    }

    /**
     * 所属商户id<br/>
     * 返回值对应的表列名 merchant_order.merchant_id
     *
     * @return 返回值对应 merchant_order.merchant_id
     *
     * @mbg.generated
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 所属商户id<br/>
     * merchant_order.merchant_id
     *
     * @param merchantId 值对应 merchant_order.merchant_id
     *
     * @mbg.generated
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 客户订单id<br/>
     * 返回值对应的表列名 merchant_order.client_order_id
     *
     * @return 返回值对应 merchant_order.client_order_id
     *
     * @mbg.generated
     */
    public Long getClientOrderId() {
        return clientOrderId;
    }

    /**
     * 客户订单id<br/>
     * merchant_order.client_order_id
     *
     * @param clientOrderId 值对应 merchant_order.client_order_id
     *
     * @mbg.generated
     */
    public void setClientOrderId(Long clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    /**
     * 订单累计金额<br/>
     * 返回值对应的表列名 merchant_order.grand_total
     *
     * @return 返回值对应 merchant_order.grand_total
     *
     * @mbg.generated
     */
    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    /**
     * 订单累计金额<br/>
     * merchant_order.grand_total
     *
     * @param grandTotal 值对应 merchant_order.grand_total
     *
     * @mbg.generated
     */
    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    /**
     * 订单状态id<br/>
     * 返回值对应的表列名 merchant_order.order_status_id
     *
     * @return 返回值对应 merchant_order.order_status_id
     *
     * @mbg.generated
     */
    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    /**
     * 订单状态id<br/>
     * merchant_order.order_status_id
     *
     * @param orderStatusId 值对应 merchant_order.order_status_id
     *
     * @mbg.generated
     */
    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    /**
     * 开票状态<br/>
     * 返回值对应的表列名 merchant_order.invoice_status
     *
     * @return 返回值对应 merchant_order.invoice_status
     *
     * @mbg.generated
     */
    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * 开票状态<br/>
     * merchant_order.invoice_status
     *
     * @param invoiceStatus 值对应 merchant_order.invoice_status
     *
     * @mbg.generated
     */
    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    /**
     * 订单类型（ 0个人  1企业）<br/>
     * 返回值对应的表列名 merchant_order.order_type
     *
     * @return 返回值对应 merchant_order.order_type
     *
     * @mbg.generated
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 订单类型（ 0个人  1企业）<br/>
     * merchant_order.order_type
     *
     * @param orderType 值对应 merchant_order.order_type
     *
     * @mbg.generated
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 订单状态显示 0无效 1有效<br/>
     * 返回值对应的表列名 merchant_order.status
     *
     * @return 返回值对应 merchant_order.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 订单状态显示 0无效 1有效<br/>
     * merchant_order.status
     *
     * @param status 值对应 merchant_order.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 merchant_order.create_time
     *
     * @return 返回值对应 merchant_order.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * merchant_order.create_time
     *
     * @param createTime 值对应 merchant_order.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 merchant_order.modifier
     *
     * @return 返回值对应 merchant_order.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * merchant_order.modifier
     *
     * @param modifier 值对应 merchant_order.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 merchant_order.modify_time
     *
     * @return 返回值对应 merchant_order.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * merchant_order.modify_time
     *
     * @param modifyTime 值对应 merchant_order.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 备注<br/>
     * 返回值对应的表列名 merchant_order.remark
     *
     * @return 返回值对应 merchant_order.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * merchant_order.remark
     *
     * @param remark 值对应 merchant_order.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        MerchantOrder other = (MerchantOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantOrderCode() == null ? other.getMerchantOrderCode() == null : this.getMerchantOrderCode().equals(other.getMerchantOrderCode()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getClientOrderId() == null ? other.getClientOrderId() == null : this.getClientOrderId().equals(other.getClientOrderId()))
            && (this.getGrandTotal() == null ? other.getGrandTotal() == null : this.getGrandTotal().equals(other.getGrandTotal()))
            && (this.getOrderStatusId() == null ? other.getOrderStatusId() == null : this.getOrderStatusId().equals(other.getOrderStatusId()))
            && (this.getInvoiceStatus() == null ? other.getInvoiceStatus() == null : this.getInvoiceStatus().equals(other.getInvoiceStatus()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMerchantOrderCode() == null) ? 0 : getMerchantOrderCode().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getClientOrderId() == null) ? 0 : getClientOrderId().hashCode());
        result = prime * result + ((getGrandTotal() == null) ? 0 : getGrandTotal().hashCode());
        result = prime * result + ((getOrderStatusId() == null) ? 0 : getOrderStatusId().hashCode());
        result = prime * result + ((getInvoiceStatus() == null) ? 0 : getInvoiceStatus().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", merchantOrderCode=").append(merchantOrderCode);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", clientOrderId=").append(clientOrderId);
        sb.append(", grandTotal=").append(grandTotal);
        sb.append(", orderStatusId=").append(orderStatusId);
        sb.append(", invoiceStatus=").append(invoiceStatus);
        sb.append(", orderType=").append(orderType);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}