package com.azz.order.merchant.pojo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MerchantInvoice implements Serializable {
    /**
     * id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 发票申请编码
     *
     * @mbg.generated
     */
    private String merchantApplyCode;

    /**
     * 所属商户id
     *
     * @mbg.generated
     */
    private Long merchantId;

    /**
     * 关联商户订单id
     *
     * @mbg.generated
     */
    private Long merchantOrderId;

    /**
     * 发票类型（0 普通发票 1 增值专用发票）
     *
     * @mbg.generated
     */
    private Integer invoiceType;

    /**
     * 申请金额
     *
     * @mbg.generated
     */
    private BigDecimal applyAmount;

    /**
     * 申请状态（0 待确认 1 待开票 2 待签收 3 已完成）
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 创建人 为平台端创建商户开票申请的创建人与创建时间
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

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * id<br/>
     * 返回值对应的表列名 merchant_invoice.id
     *
     * @return 返回值对应 merchant_invoice.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * id<br/>
     * merchant_invoice.id
     *
     * @param id 值对应 merchant_invoice.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 发票申请编码<br/>
     * 返回值对应的表列名 merchant_invoice.merchant_apply_code
     *
     * @return 返回值对应 merchant_invoice.merchant_apply_code
     *
     * @mbg.generated
     */
    public String getMerchantApplyCode() {
        return merchantApplyCode;
    }

    /**
     * 发票申请编码<br/>
     * merchant_invoice.merchant_apply_code
     *
     * @param merchantApplyCode 值对应 merchant_invoice.merchant_apply_code
     *
     * @mbg.generated
     */
    public void setMerchantApplyCode(String merchantApplyCode) {
        this.merchantApplyCode = merchantApplyCode == null ? null : merchantApplyCode.trim();
    }

    /**
     * 所属商户id<br/>
     * 返回值对应的表列名 merchant_invoice.merchant_id
     *
     * @return 返回值对应 merchant_invoice.merchant_id
     *
     * @mbg.generated
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 所属商户id<br/>
     * merchant_invoice.merchant_id
     *
     * @param merchantId 值对应 merchant_invoice.merchant_id
     *
     * @mbg.generated
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 关联商户订单id<br/>
     * 返回值对应的表列名 merchant_invoice.merchant_order_id
     *
     * @return 返回值对应 merchant_invoice.merchant_order_id
     *
     * @mbg.generated
     */
    public Long getMerchantOrderId() {
        return merchantOrderId;
    }

    /**
     * 关联商户订单id<br/>
     * merchant_invoice.merchant_order_id
     *
     * @param merchantOrderId 值对应 merchant_invoice.merchant_order_id
     *
     * @mbg.generated
     */
    public void setMerchantOrderId(Long merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    /**
     * 发票类型（0 普通发票 1 增值专用发票）<br/>
     * 返回值对应的表列名 merchant_invoice.invoice_type
     *
     * @return 返回值对应 merchant_invoice.invoice_type
     *
     * @mbg.generated
     */
    public Integer getInvoiceType() {
        return invoiceType;
    }

    /**
     * 发票类型（0 普通发票 1 增值专用发票）<br/>
     * merchant_invoice.invoice_type
     *
     * @param invoiceType 值对应 merchant_invoice.invoice_type
     *
     * @mbg.generated
     */
    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * 申请金额<br/>
     * 返回值对应的表列名 merchant_invoice.apply_amount
     *
     * @return 返回值对应 merchant_invoice.apply_amount
     *
     * @mbg.generated
     */
    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    /**
     * 申请金额<br/>
     * merchant_invoice.apply_amount
     *
     * @param applyAmount 值对应 merchant_invoice.apply_amount
     *
     * @mbg.generated
     */
    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    /**
     * 申请状态（0 待确认 1 待开票 2 待签收 3 已完成）<br/>
     * 返回值对应的表列名 merchant_invoice.status
     *
     * @return 返回值对应 merchant_invoice.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 申请状态（0 待确认 1 待开票 2 待签收 3 已完成）<br/>
     * merchant_invoice.status
     *
     * @param status 值对应 merchant_invoice.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建人 为平台端创建商户开票申请的创建人与创建时间<br/>
     * 返回值对应的表列名 merchant_invoice.creator
     *
     * @return 返回值对应 merchant_invoice.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人 为平台端创建商户开票申请的创建人与创建时间<br/>
     * merchant_invoice.creator
     *
     * @param creator 值对应 merchant_invoice.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 merchant_invoice.create_time
     *
     * @return 返回值对应 merchant_invoice.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * merchant_invoice.create_time
     *
     * @param createTime 值对应 merchant_invoice.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 merchant_invoice.modifier
     *
     * @return 返回值对应 merchant_invoice.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * merchant_invoice.modifier
     *
     * @param modifier 值对应 merchant_invoice.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 merchant_invoice.modify_time
     *
     * @return 返回值对应 merchant_invoice.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 最后修改时间<br/>
     * merchant_invoice.modify_time
     *
     * @param modifyTime 值对应 merchant_invoice.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 备注<br/>
     * 返回值对应的表列名 merchant_invoice.remark
     *
     * @return 返回值对应 merchant_invoice.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * merchant_invoice.remark
     *
     * @param remark 值对应 merchant_invoice.remark
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
        MerchantInvoice other = (MerchantInvoice) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantApplyCode() == null ? other.getMerchantApplyCode() == null : this.getMerchantApplyCode().equals(other.getMerchantApplyCode()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getMerchantOrderId() == null ? other.getMerchantOrderId() == null : this.getMerchantOrderId().equals(other.getMerchantOrderId()))
            && (this.getInvoiceType() == null ? other.getInvoiceType() == null : this.getInvoiceType().equals(other.getInvoiceType()))
            && (this.getApplyAmount() == null ? other.getApplyAmount() == null : this.getApplyAmount().equals(other.getApplyAmount()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
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
        result = prime * result + ((getMerchantApplyCode() == null) ? 0 : getMerchantApplyCode().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getMerchantOrderId() == null) ? 0 : getMerchantOrderId().hashCode());
        result = prime * result + ((getInvoiceType() == null) ? 0 : getInvoiceType().hashCode());
        result = prime * result + ((getApplyAmount() == null) ? 0 : getApplyAmount().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
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
        sb.append(", merchantApplyCode=").append(merchantApplyCode);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", merchantOrderId=").append(merchantOrderId);
        sb.append(", invoiceType=").append(invoiceType);
        sb.append(", applyAmount=").append(applyAmount);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}