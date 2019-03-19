package com.azz.order.finance.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.azz.order.finance.pojo.MerchantWithdrawDepositApply.MerchantWithdrawDepositApplyBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantWithdrawDepositApplyOrder implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 申请编码
     *
     * @mbg.generated
     */
    private String applyCode;

    /**
     * 商户编码
     *
     * @mbg.generated
     */
    private String merchantOrderCode;

    /**
     * 订单金额
     *
     * @mbg.generated
     */
    private BigDecimal grandTotal;

    /**
     * 提现金额
     *
     * @mbg.generated
     */
    private BigDecimal withdrawDepositMoney;

    /**
     * 交易费用
     *
     * @mbg.generated
     */
    private BigDecimal transactionCost;

    /**
     * 下单时间
     *
     * @mbg.generated
     */
    private Date orderDate;

    /**
     * 状态  1有效  0无效
     *
     * @mbg.generated
     */
    private Byte status;

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
     * 返回值对应的表列名 merchant_withdraw_deposit_apply_order.id
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply_order.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * merchant_withdraw_deposit_apply_order.id
     *
     * @param id 值对应 merchant_withdraw_deposit_apply_order.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 申请编码<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply_order.apply_code
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply_order.apply_code
     *
     * @mbg.generated
     */
    public String getApplyCode() {
        return applyCode;
    }

    /**
     * 申请编码<br/>
     * merchant_withdraw_deposit_apply_order.apply_code
     *
     * @param applyCode 值对应 merchant_withdraw_deposit_apply_order.apply_code
     *
     * @mbg.generated
     */
    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode == null ? null : applyCode.trim();
    }

    /**
     * 商户编码<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply_order.merchant_order_code
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply_order.merchant_order_code
     *
     * @mbg.generated
     */
    public String getMerchantOrderCode() {
        return merchantOrderCode;
    }

    /**
     * 商户编码<br/>
     * merchant_withdraw_deposit_apply_order.merchant_order_code
     *
     * @param merchantOrderCode 值对应 merchant_withdraw_deposit_apply_order.merchant_order_code
     *
     * @mbg.generated
     */
    public void setMerchantOrderCode(String merchantOrderCode) {
        this.merchantOrderCode = merchantOrderCode == null ? null : merchantOrderCode.trim();
    }

    /**
     * 订单金额<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply_order.grand_total
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply_order.grand_total
     *
     * @mbg.generated
     */
    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    /**
     * 订单金额<br/>
     * merchant_withdraw_deposit_apply_order.grand_total
     *
     * @param grandTotal 值对应 merchant_withdraw_deposit_apply_order.grand_total
     *
     * @mbg.generated
     */
    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    /**
     * 提现金额<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply_order.withdraw_deposit_money
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply_order.withdraw_deposit_money
     *
     * @mbg.generated
     */
    public BigDecimal getWithdrawDepositMoney() {
        return withdrawDepositMoney;
    }

    /**
     * 提现金额<br/>
     * merchant_withdraw_deposit_apply_order.withdraw_deposit_money
     *
     * @param withdrawDepositMoney 值对应 merchant_withdraw_deposit_apply_order.withdraw_deposit_money
     *
     * @mbg.generated
     */
    public void setWithdrawDepositMoney(BigDecimal withdrawDepositMoney) {
        this.withdrawDepositMoney = withdrawDepositMoney;
    }

    /**
     * 交易费用<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply_order.transaction_cost
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply_order.transaction_cost
     *
     * @mbg.generated
     */
    public BigDecimal getTransactionCost() {
        return transactionCost;
    }

    /**
     * 交易费用<br/>
     * merchant_withdraw_deposit_apply_order.transaction_cost
     *
     * @param transactionCost 值对应 merchant_withdraw_deposit_apply_order.transaction_cost
     *
     * @mbg.generated
     */
    public void setTransactionCost(BigDecimal transactionCost) {
        this.transactionCost = transactionCost;
    }

    /**
     * 下单时间<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply_order.order_date
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply_order.order_date
     *
     * @mbg.generated
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 下单时间<br/>
     * merchant_withdraw_deposit_apply_order.order_date
     *
     * @param orderDate 值对应 merchant_withdraw_deposit_apply_order.order_date
     *
     * @mbg.generated
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 状态  1有效  0无效<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply_order.status
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply_order.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 状态  1有效  0无效<br/>
     * merchant_withdraw_deposit_apply_order.status
     *
     * @param status 值对应 merchant_withdraw_deposit_apply_order.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * <br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply_order.remark
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply_order.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <br/>
     * merchant_withdraw_deposit_apply_order.remark
     *
     * @param remark 值对应 merchant_withdraw_deposit_apply_order.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply_order.creator
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply_order.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * merchant_withdraw_deposit_apply_order.creator
     *
     * @param creator 值对应 merchant_withdraw_deposit_apply_order.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply_order.create_time
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply_order.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建人<br/>
     * merchant_withdraw_deposit_apply_order.create_time
     *
     * @param createTime 值对应 merchant_withdraw_deposit_apply_order.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply_order.modifier
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply_order.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * merchant_withdraw_deposit_apply_order.modifier
     *
     * @param modifier 值对应 merchant_withdraw_deposit_apply_order.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply_order.modify_time
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply_order.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * merchant_withdraw_deposit_apply_order.modify_time
     *
     * @param modifyTime 值对应 merchant_withdraw_deposit_apply_order.modify_time
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
        MerchantWithdrawDepositApplyOrder other = (MerchantWithdrawDepositApplyOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getApplyCode() == null ? other.getApplyCode() == null : this.getApplyCode().equals(other.getApplyCode()))
            && (this.getMerchantOrderCode() == null ? other.getMerchantOrderCode() == null : this.getMerchantOrderCode().equals(other.getMerchantOrderCode()))
            && (this.getGrandTotal() == null ? other.getGrandTotal() == null : this.getGrandTotal().equals(other.getGrandTotal()))
            && (this.getWithdrawDepositMoney() == null ? other.getWithdrawDepositMoney() == null : this.getWithdrawDepositMoney().equals(other.getWithdrawDepositMoney()))
            && (this.getTransactionCost() == null ? other.getTransactionCost() == null : this.getTransactionCost().equals(other.getTransactionCost()))
            && (this.getOrderDate() == null ? other.getOrderDate() == null : this.getOrderDate().equals(other.getOrderDate()))
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
        result = prime * result + ((getApplyCode() == null) ? 0 : getApplyCode().hashCode());
        result = prime * result + ((getMerchantOrderCode() == null) ? 0 : getMerchantOrderCode().hashCode());
        result = prime * result + ((getGrandTotal() == null) ? 0 : getGrandTotal().hashCode());
        result = prime * result + ((getWithdrawDepositMoney() == null) ? 0 : getWithdrawDepositMoney().hashCode());
        result = prime * result + ((getTransactionCost() == null) ? 0 : getTransactionCost().hashCode());
        result = prime * result + ((getOrderDate() == null) ? 0 : getOrderDate().hashCode());
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
        sb.append(", applyCode=").append(applyCode);
        sb.append(", merchantOrderCode=").append(merchantOrderCode);
        sb.append(", grandTotal=").append(grandTotal);
        sb.append(", withdrawDepositMoney=").append(withdrawDepositMoney);
        sb.append(", transactionCost=").append(transactionCost);
        sb.append(", orderDate=").append(orderDate);
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