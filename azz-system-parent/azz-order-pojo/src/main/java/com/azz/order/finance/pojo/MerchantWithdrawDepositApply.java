package com.azz.order.finance.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantWithdrawDepositApply implements Serializable {
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
    private String merchantCode;

    /**
     * 提现账户
     *
     * @mbg.generated
     */
    private String withdrawDepositAccount;

    /**
     * 订单总量
     *
     * @mbg.generated
     */
    private Integer totalOrderCount;

    /**
     * 订单总金额
     *
     * @mbg.generated
     */
    private BigDecimal totalOrderMoney;

    /**
     * 提现总金额   订单总金额-总手续费
     *
     * @mbg.generated
     */
    private BigDecimal totalWithdrawDepositMoney;

    /**
     * 三方信息--单号
     *
     * @mbg.generated
     */
    private String thirdInfoCode;

    /**
     * 三方信息--三方状态
     *
     * @mbg.generated
     */
    private String thirdInfoStatus;

    /**
     * 三方信息--提现金额
     *
     * @mbg.generated
     */
    private BigDecimal thirdInfoWithdrawDepositMoney;

    /**
     * 状态  1待审核  2待打款   3已打款   4已关闭
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * 备注
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
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.id
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * merchant_withdraw_deposit_apply.id
     *
     * @param id 值对应 merchant_withdraw_deposit_apply.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 申请编码<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.apply_code
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.apply_code
     *
     * @mbg.generated
     */
    public String getApplyCode() {
        return applyCode;
    }

    /**
     * 申请编码<br/>
     * merchant_withdraw_deposit_apply.apply_code
     *
     * @param applyCode 值对应 merchant_withdraw_deposit_apply.apply_code
     *
     * @mbg.generated
     */
    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode == null ? null : applyCode.trim();
    }

    /**
     * 商户编码<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.merchant_code
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.merchant_code
     *
     * @mbg.generated
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /**
     * 商户编码<br/>
     * merchant_withdraw_deposit_apply.merchant_code
     *
     * @param merchantCode 值对应 merchant_withdraw_deposit_apply.merchant_code
     *
     * @mbg.generated
     */
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
    }

    /**
     * 提现账户<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.withdraw_deposit_account
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.withdraw_deposit_account
     *
     * @mbg.generated
     */
    public String getWithdrawDepositAccount() {
        return withdrawDepositAccount;
    }

    /**
     * 提现账户<br/>
     * merchant_withdraw_deposit_apply.withdraw_deposit_account
     *
     * @param withdrawDepositAccount 值对应 merchant_withdraw_deposit_apply.withdraw_deposit_account
     *
     * @mbg.generated
     */
    public void setWithdrawDepositAccount(String withdrawDepositAccount) {
        this.withdrawDepositAccount = withdrawDepositAccount == null ? null : withdrawDepositAccount.trim();
    }

    /**
     * 订单总量<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.total_order_count
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.total_order_count
     *
     * @mbg.generated
     */
    public Integer getTotalOrderCount() {
        return totalOrderCount;
    }

    /**
     * 订单总量<br/>
     * merchant_withdraw_deposit_apply.total_order_count
     *
     * @param totalOrderCount 值对应 merchant_withdraw_deposit_apply.total_order_count
     *
     * @mbg.generated
     */
    public void setTotalOrderCount(Integer totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    /**
     * 订单总金额<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.total_order_money
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.total_order_money
     *
     * @mbg.generated
     */
    public BigDecimal getTotalOrderMoney() {
        return totalOrderMoney;
    }

    /**
     * 订单总金额<br/>
     * merchant_withdraw_deposit_apply.total_order_money
     *
     * @param totalOrderMoney 值对应 merchant_withdraw_deposit_apply.total_order_money
     *
     * @mbg.generated
     */
    public void setTotalOrderMoney(BigDecimal totalOrderMoney) {
        this.totalOrderMoney = totalOrderMoney;
    }

    /**
     * 提现总金额   订单总金额-总手续费<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.total_withdraw_deposit_money
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.total_withdraw_deposit_money
     *
     * @mbg.generated
     */
    public BigDecimal getTotalWithdrawDepositMoney() {
        return totalWithdrawDepositMoney;
    }

    /**
     * 提现总金额   订单总金额-总手续费<br/>
     * merchant_withdraw_deposit_apply.total_withdraw_deposit_money
     *
     * @param totalWithdrawDepositMoney 值对应 merchant_withdraw_deposit_apply.total_withdraw_deposit_money
     *
     * @mbg.generated
     */
    public void setTotalWithdrawDepositMoney(BigDecimal totalWithdrawDepositMoney) {
        this.totalWithdrawDepositMoney = totalWithdrawDepositMoney;
    }

    /**
     * 三方信息--单号<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.third_info_code
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.third_info_code
     *
     * @mbg.generated
     */
    public String getThirdInfoCode() {
        return thirdInfoCode;
    }

    /**
     * 三方信息--单号<br/>
     * merchant_withdraw_deposit_apply.third_info_code
     *
     * @param thirdInfoCode 值对应 merchant_withdraw_deposit_apply.third_info_code
     *
     * @mbg.generated
     */
    public void setThirdInfoCode(String thirdInfoCode) {
        this.thirdInfoCode = thirdInfoCode == null ? null : thirdInfoCode.trim();
    }

    /**
     * 三方信息--三方状态<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.third_info_status
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.third_info_status
     *
     * @mbg.generated
     */
    public String getThirdInfoStatus() {
        return thirdInfoStatus;
    }

    /**
     * 三方信息--三方状态<br/>
     * merchant_withdraw_deposit_apply.third_info_status
     *
     * @param thirdInfoStatus 值对应 merchant_withdraw_deposit_apply.third_info_status
     *
     * @mbg.generated
     */
    public void setThirdInfoStatus(String thirdInfoStatus) {
        this.thirdInfoStatus = thirdInfoStatus == null ? null : thirdInfoStatus.trim();
    }

    /**
     * 三方信息--提现金额<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.third_info_withdraw_deposit_money
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.third_info_withdraw_deposit_money
     *
     * @mbg.generated
     */
    public BigDecimal getThirdInfoWithdrawDepositMoney() {
        return thirdInfoWithdrawDepositMoney;
    }

    /**
     * 三方信息--提现金额<br/>
     * merchant_withdraw_deposit_apply.third_info_withdraw_deposit_money
     *
     * @param thirdInfoWithdrawDepositMoney 值对应 merchant_withdraw_deposit_apply.third_info_withdraw_deposit_money
     *
     * @mbg.generated
     */
    public void setThirdInfoWithdrawDepositMoney(BigDecimal thirdInfoWithdrawDepositMoney) {
        this.thirdInfoWithdrawDepositMoney = thirdInfoWithdrawDepositMoney;
    }

    /**
     * 状态  1待审核  2待打款   3已打款   4已关闭<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.status
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 状态  1待审核  2待打款   3已打款   4已关闭<br/>
     * merchant_withdraw_deposit_apply.status
     *
     * @param status 值对应 merchant_withdraw_deposit_apply.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 备注<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.remark
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * merchant_withdraw_deposit_apply.remark
     *
     * @param remark 值对应 merchant_withdraw_deposit_apply.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.creator
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * merchant_withdraw_deposit_apply.creator
     *
     * @param creator 值对应 merchant_withdraw_deposit_apply.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.create_time
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建人<br/>
     * merchant_withdraw_deposit_apply.create_time
     *
     * @param createTime 值对应 merchant_withdraw_deposit_apply.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.modifier
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * merchant_withdraw_deposit_apply.modifier
     *
     * @param modifier 值对应 merchant_withdraw_deposit_apply.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 merchant_withdraw_deposit_apply.modify_time
     *
     * @return 返回值对应 merchant_withdraw_deposit_apply.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * merchant_withdraw_deposit_apply.modify_time
     *
     * @param modifyTime 值对应 merchant_withdraw_deposit_apply.modify_time
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
        MerchantWithdrawDepositApply other = (MerchantWithdrawDepositApply) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getApplyCode() == null ? other.getApplyCode() == null : this.getApplyCode().equals(other.getApplyCode()))
            && (this.getMerchantCode() == null ? other.getMerchantCode() == null : this.getMerchantCode().equals(other.getMerchantCode()))
            && (this.getWithdrawDepositAccount() == null ? other.getWithdrawDepositAccount() == null : this.getWithdrawDepositAccount().equals(other.getWithdrawDepositAccount()))
            && (this.getTotalOrderCount() == null ? other.getTotalOrderCount() == null : this.getTotalOrderCount().equals(other.getTotalOrderCount()))
            && (this.getTotalOrderMoney() == null ? other.getTotalOrderMoney() == null : this.getTotalOrderMoney().equals(other.getTotalOrderMoney()))
            && (this.getTotalWithdrawDepositMoney() == null ? other.getTotalWithdrawDepositMoney() == null : this.getTotalWithdrawDepositMoney().equals(other.getTotalWithdrawDepositMoney()))
            && (this.getThirdInfoCode() == null ? other.getThirdInfoCode() == null : this.getThirdInfoCode().equals(other.getThirdInfoCode()))
            && (this.getThirdInfoStatus() == null ? other.getThirdInfoStatus() == null : this.getThirdInfoStatus().equals(other.getThirdInfoStatus()))
            && (this.getThirdInfoWithdrawDepositMoney() == null ? other.getThirdInfoWithdrawDepositMoney() == null : this.getThirdInfoWithdrawDepositMoney().equals(other.getThirdInfoWithdrawDepositMoney()))
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
        result = prime * result + ((getMerchantCode() == null) ? 0 : getMerchantCode().hashCode());
        result = prime * result + ((getWithdrawDepositAccount() == null) ? 0 : getWithdrawDepositAccount().hashCode());
        result = prime * result + ((getTotalOrderCount() == null) ? 0 : getTotalOrderCount().hashCode());
        result = prime * result + ((getTotalOrderMoney() == null) ? 0 : getTotalOrderMoney().hashCode());
        result = prime * result + ((getTotalWithdrawDepositMoney() == null) ? 0 : getTotalWithdrawDepositMoney().hashCode());
        result = prime * result + ((getThirdInfoCode() == null) ? 0 : getThirdInfoCode().hashCode());
        result = prime * result + ((getThirdInfoStatus() == null) ? 0 : getThirdInfoStatus().hashCode());
        result = prime * result + ((getThirdInfoWithdrawDepositMoney() == null) ? 0 : getThirdInfoWithdrawDepositMoney().hashCode());
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
        sb.append(", merchantCode=").append(merchantCode);
        sb.append(", withdrawDepositAccount=").append(withdrawDepositAccount);
        sb.append(", totalOrderCount=").append(totalOrderCount);
        sb.append(", totalOrderMoney=").append(totalOrderMoney);
        sb.append(", totalWithdrawDepositMoney=").append(totalWithdrawDepositMoney);
        sb.append(", thirdInfoCode=").append(thirdInfoCode);
        sb.append(", thirdInfoStatus=").append(thirdInfoStatus);
        sb.append(", thirdInfoWithdrawDepositMoney=").append(thirdInfoWithdrawDepositMoney);
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