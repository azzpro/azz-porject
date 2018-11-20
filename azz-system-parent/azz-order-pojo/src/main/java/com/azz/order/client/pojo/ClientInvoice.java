package com.azz.order.client.pojo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ClientInvoice implements Serializable {
    /**
     * id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 申请编码
     *
     * @mbg.generated
     */
    private String clientApplyCode;

    /**
     * 关联客户id
     *
     * @mbg.generated
     */
    private Long clientUserId;

    /**
     * 开票金额
     *
     * @mbg.generated
     */
    private BigDecimal amount;

    /**
     * 开票数量
     *
     * @mbg.generated
     */
    private Integer quantity;

    /**
     * 关联客户订单id
     *
     * @mbg.generated
     */
    private Long clientOrderId;

    /**
     * 关联发票模板id
     *
     * @mbg.generated
     */
    private Long invoiceTemplateId;

    /**
     * 关联收货地址id
     *
     * @mbg.generated
     */
    private Long shippingAddressId;

    /**
     * 状态（0 待审批 1 待开票 2 待签收 3 已拒绝 4 已完成 5 已取消）
     *
     * @mbg.generated
     */
    private Integer status;

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
     * id<br/>
     * 返回值对应的表列名 client_invoice.id
     *
     * @return 返回值对应 client_invoice.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * id<br/>
     * client_invoice.id
     *
     * @param id 值对应 client_invoice.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 申请编码<br/>
     * 返回值对应的表列名 client_invoice.client_apply_code
     *
     * @return 返回值对应 client_invoice.client_apply_code
     *
     * @mbg.generated
     */
    public String getClientApplyCode() {
        return clientApplyCode;
    }

    /**
     * 申请编码<br/>
     * client_invoice.client_apply_code
     *
     * @param clientApplyCode 值对应 client_invoice.client_apply_code
     *
     * @mbg.generated
     */
    public void setClientApplyCode(String clientApplyCode) {
        this.clientApplyCode = clientApplyCode == null ? null : clientApplyCode.trim();
    }

    /**
     * 关联客户id<br/>
     * 返回值对应的表列名 client_invoice.client_user_id
     *
     * @return 返回值对应 client_invoice.client_user_id
     *
     * @mbg.generated
     */
    public Long getClientUserId() {
        return clientUserId;
    }

    /**
     * 关联客户id<br/>
     * client_invoice.client_user_id
     *
     * @param clientUserId 值对应 client_invoice.client_user_id
     *
     * @mbg.generated
     */
    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    /**
     * 开票金额<br/>
     * 返回值对应的表列名 client_invoice.amount
     *
     * @return 返回值对应 client_invoice.amount
     *
     * @mbg.generated
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 开票金额<br/>
     * client_invoice.amount
     *
     * @param amount 值对应 client_invoice.amount
     *
     * @mbg.generated
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 开票数量<br/>
     * 返回值对应的表列名 client_invoice.quantity
     *
     * @return 返回值对应 client_invoice.quantity
     *
     * @mbg.generated
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 开票数量<br/>
     * client_invoice.quantity
     *
     * @param quantity 值对应 client_invoice.quantity
     *
     * @mbg.generated
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 关联客户订单id<br/>
     * 返回值对应的表列名 client_invoice.client_order_id
     *
     * @return 返回值对应 client_invoice.client_order_id
     *
     * @mbg.generated
     */
    public Long getClientOrderId() {
        return clientOrderId;
    }

    /**
     * 关联客户订单id<br/>
     * client_invoice.client_order_id
     *
     * @param clientOrderId 值对应 client_invoice.client_order_id
     *
     * @mbg.generated
     */
    public void setClientOrderId(Long clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    /**
     * 关联发票模板id<br/>
     * 返回值对应的表列名 client_invoice.invoice_template_id
     *
     * @return 返回值对应 client_invoice.invoice_template_id
     *
     * @mbg.generated
     */
    public Long getInvoiceTemplateId() {
        return invoiceTemplateId;
    }

    /**
     * 关联发票模板id<br/>
     * client_invoice.invoice_template_id
     *
     * @param invoiceTemplateId 值对应 client_invoice.invoice_template_id
     *
     * @mbg.generated
     */
    public void setInvoiceTemplateId(Long invoiceTemplateId) {
        this.invoiceTemplateId = invoiceTemplateId;
    }

    /**
     * 关联收货地址id<br/>
     * 返回值对应的表列名 client_invoice.shipping_address_id
     *
     * @return 返回值对应 client_invoice.shipping_address_id
     *
     * @mbg.generated
     */
    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    /**
     * 关联收货地址id<br/>
     * client_invoice.shipping_address_id
     *
     * @param shippingAddressId 值对应 client_invoice.shipping_address_id
     *
     * @mbg.generated
     */
    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    /**
     * 状态（0 待审批 1 待开票 2 待签收 3 已拒绝 4 已完成 5 已取消）<br/>
     * 返回值对应的表列名 client_invoice.status
     *
     * @return 返回值对应 client_invoice.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0 待审批 1 待开票 2 待签收 3 已拒绝 4 已完成 5 已取消）<br/>
     * client_invoice.status
     *
     * @param status 值对应 client_invoice.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 client_invoice.creator
     *
     * @return 返回值对应 client_invoice.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * client_invoice.creator
     *
     * @param creator 值对应 client_invoice.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 client_invoice.create_time
     *
     * @return 返回值对应 client_invoice.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * client_invoice.create_time
     *
     * @param createTime 值对应 client_invoice.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 client_invoice.modifier
     *
     * @return 返回值对应 client_invoice.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * client_invoice.modifier
     *
     * @param modifier 值对应 client_invoice.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 client_invoice.modify_time
     *
     * @return 返回值对应 client_invoice.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 最后修改时间<br/>
     * client_invoice.modify_time
     *
     * @param modifyTime 值对应 client_invoice.modify_time
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
        ClientInvoice other = (ClientInvoice) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClientApplyCode() == null ? other.getClientApplyCode() == null : this.getClientApplyCode().equals(other.getClientApplyCode()))
            && (this.getClientUserId() == null ? other.getClientUserId() == null : this.getClientUserId().equals(other.getClientUserId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getClientOrderId() == null ? other.getClientOrderId() == null : this.getClientOrderId().equals(other.getClientOrderId()))
            && (this.getInvoiceTemplateId() == null ? other.getInvoiceTemplateId() == null : this.getInvoiceTemplateId().equals(other.getInvoiceTemplateId()))
            && (this.getShippingAddressId() == null ? other.getShippingAddressId() == null : this.getShippingAddressId().equals(other.getShippingAddressId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
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
        result = prime * result + ((getClientApplyCode() == null) ? 0 : getClientApplyCode().hashCode());
        result = prime * result + ((getClientUserId() == null) ? 0 : getClientUserId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getClientOrderId() == null) ? 0 : getClientOrderId().hashCode());
        result = prime * result + ((getInvoiceTemplateId() == null) ? 0 : getInvoiceTemplateId().hashCode());
        result = prime * result + ((getShippingAddressId() == null) ? 0 : getShippingAddressId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", clientApplyCode=").append(clientApplyCode);
        sb.append(", clientUserId=").append(clientUserId);
        sb.append(", amount=").append(amount);
        sb.append(", quantity=").append(quantity);
        sb.append(", clientOrderId=").append(clientOrderId);
        sb.append(", invoiceTemplateId=").append(invoiceTemplateId);
        sb.append(", shippingAddressId=").append(shippingAddressId);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}