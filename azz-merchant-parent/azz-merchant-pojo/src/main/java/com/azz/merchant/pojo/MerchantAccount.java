package com.azz.merchant.pojo;


import java.io.Serializable;
import java.util.Date;

public class MerchantAccount implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 商户id
     *
     * @mbg.generated
     */
    private Long merchantId;

    /**
     * 开户账号
     *
     * @mbg.generated
     */
    private String accountName;

    /**
     * 开户银行
     *
     * @mbg.generated
     */
    private String accountBank;

    /**
     * 对公账号
     *
     * @mbg.generated
     */
    private String accountBankCardNumber;

    /**
     * 开户支行
     *
     * @mbg.generated
     */
    private String accountSubBranch;

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
    private Date lastModifyTime;

    private static final long serialVersionUID = 1L;

    /**
     * 主键<br/>
     * 返回值对应的表列名 merchant_account.id
     *
     * @return 返回值对应 merchant_account.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * merchant_account.id
     *
     * @param id 值对应 merchant_account.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商户id<br/>
     * 返回值对应的表列名 merchant_account.merchant_id
     *
     * @return 返回值对应 merchant_account.merchant_id
     *
     * @mbg.generated
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 商户id<br/>
     * merchant_account.merchant_id
     *
     * @param merchantId 值对应 merchant_account.merchant_id
     *
     * @mbg.generated
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 开户账号<br/>
     * 返回值对应的表列名 merchant_account.account_name
     *
     * @return 返回值对应 merchant_account.account_name
     *
     * @mbg.generated
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 开户账号<br/>
     * merchant_account.account_name
     *
     * @param accountName 值对应 merchant_account.account_name
     *
     * @mbg.generated
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     * 开户银行<br/>
     * 返回值对应的表列名 merchant_account.account_bank
     *
     * @return 返回值对应 merchant_account.account_bank
     *
     * @mbg.generated
     */
    public String getAccountBank() {
        return accountBank;
    }

    /**
     * 开户银行<br/>
     * merchant_account.account_bank
     *
     * @param accountBank 值对应 merchant_account.account_bank
     *
     * @mbg.generated
     */
    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank == null ? null : accountBank.trim();
    }

    /**
     * 对公账号<br/>
     * 返回值对应的表列名 merchant_account.account_bank_card_number
     *
     * @return 返回值对应 merchant_account.account_bank_card_number
     *
     * @mbg.generated
     */
    public String getAccountBankCardNumber() {
        return accountBankCardNumber;
    }

    /**
     * 对公账号<br/>
     * merchant_account.account_bank_card_number
     *
     * @param accountBankCardNumber 值对应 merchant_account.account_bank_card_number
     *
     * @mbg.generated
     */
    public void setAccountBankCardNumber(String accountBankCardNumber) {
        this.accountBankCardNumber = accountBankCardNumber == null ? null : accountBankCardNumber.trim();
    }

    /**
     * 开户支行<br/>
     * 返回值对应的表列名 merchant_account.account_sub_branch
     *
     * @return 返回值对应 merchant_account.account_sub_branch
     *
     * @mbg.generated
     */
    public String getAccountSubBranch() {
        return accountSubBranch;
    }

    /**
     * 开户支行<br/>
     * merchant_account.account_sub_branch
     *
     * @param accountSubBranch 值对应 merchant_account.account_sub_branch
     *
     * @mbg.generated
     */
    public void setAccountSubBranch(String accountSubBranch) {
        this.accountSubBranch = accountSubBranch == null ? null : accountSubBranch.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 merchant_account.creator
     *
     * @return 返回值对应 merchant_account.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * merchant_account.creator
     *
     * @param creator 值对应 merchant_account.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 merchant_account.create_time
     *
     * @return 返回值对应 merchant_account.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * merchant_account.create_time
     *
     * @param createTime 值对应 merchant_account.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 merchant_account.modifier
     *
     * @return 返回值对应 merchant_account.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * merchant_account.modifier
     *
     * @param modifier 值对应 merchant_account.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 merchant_account.last_modify_time
     *
     * @return 返回值对应 merchant_account.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * 最后修改时间<br/>
     * merchant_account.last_modify_time
     *
     * @param lastModifyTime 值对应 merchant_account.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
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
        MerchantAccount other = (MerchantAccount) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getAccountName() == null ? other.getAccountName() == null : this.getAccountName().equals(other.getAccountName()))
            && (this.getAccountBank() == null ? other.getAccountBank() == null : this.getAccountBank().equals(other.getAccountBank()))
            && (this.getAccountBankCardNumber() == null ? other.getAccountBankCardNumber() == null : this.getAccountBankCardNumber().equals(other.getAccountBankCardNumber()))
            && (this.getAccountSubBranch() == null ? other.getAccountSubBranch() == null : this.getAccountSubBranch().equals(other.getAccountSubBranch()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getAccountName() == null) ? 0 : getAccountName().hashCode());
        result = prime * result + ((getAccountBank() == null) ? 0 : getAccountBank().hashCode());
        result = prime * result + ((getAccountBankCardNumber() == null) ? 0 : getAccountBankCardNumber().hashCode());
        result = prime * result + ((getAccountSubBranch() == null) ? 0 : getAccountSubBranch().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", accountName=").append(accountName);
        sb.append(", accountBank=").append(accountBank);
        sb.append(", accountBankCardNumber=").append(accountBankCardNumber);
        sb.append(", accountSubBranch=").append(accountSubBranch);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}