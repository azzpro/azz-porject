package com.azz.order.client.pojo;


import java.io.Serializable;
import java.util.Date;

public class ClientInvoiceTemplate implements Serializable {
    /**
     * id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 关联的客户id
     *
     * @mbg.generated
     */
    private Long clientUserId;

    /**
     * 发票类型（0 普通发票 1 增值税专用发票）
     *
     * @mbg.generated
     */
    private Integer invoiceType;

    /**
     * 发票抬头
     *
     * @mbg.generated
     */
    private String invoiceTitle;

    /**
     * 纳税识别号
     *
     * @mbg.generated
     */
    private String taxIdentificationNumber;

    /**
     * 信用代码
     */
    private String creditCode;
    
    /**
     * 单位名称
     *
     * @mbg.generated
     */
    private String companyName;

    /**
     * 开户银行
     *
     * @mbg.generated
     */
    private String bank;

    /**
     * 银行账号
     *
     * @mbg.generated
     */
    private String bankAccount;

    /**
     * 注册地址
     *
     * @mbg.generated
     */
    private String regAddress;

    /**
     * 注册电话
     *
     * @mbg.generated
     */
    private String regTelephone;

    /**
     * 状态（0 无效 1有效）
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

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    
    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    /**
     * id<br/>
     * 返回值对应的表列名 client_invoice_template.id
     *
     * @return 返回值对应 client_invoice_template.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * id<br/>
     * client_invoice_template.id
     *
     * @param id 值对应 client_invoice_template.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 关联的客户id<br/>
     * 返回值对应的表列名 client_invoice_template.client_user_id
     *
     * @return 返回值对应 client_invoice_template.client_user_id
     *
     * @mbg.generated
     */
    public Long getClientUserId() {
        return clientUserId;
    }

    /**
     * 关联的客户id<br/>
     * client_invoice_template.client_user_id
     *
     * @param clientUserId 值对应 client_invoice_template.client_user_id
     *
     * @mbg.generated
     */
    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    /**
     * 发票类型（0 普通发票 1 增值税专用发票）<br/>
     * 返回值对应的表列名 client_invoice_template.invoice_type
     *
     * @return 返回值对应 client_invoice_template.invoice_type
     *
     * @mbg.generated
     */
    public Integer getInvoiceType() {
        return invoiceType;
    }

    /**
     * 发票类型（0 普通发票 1 增值税专用发票）<br/>
     * client_invoice_template.invoice_type
     *
     * @param invoiceType 值对应 client_invoice_template.invoice_type
     *
     * @mbg.generated
     */
    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * 发票抬头<br/>
     * 返回值对应的表列名 client_invoice_template.invoice_title
     *
     * @return 返回值对应 client_invoice_template.invoice_title
     *
     * @mbg.generated
     */
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    /**
     * 发票抬头<br/>
     * client_invoice_template.invoice_title
     *
     * @param invoiceTitle 值对应 client_invoice_template.invoice_title
     *
     * @mbg.generated
     */
    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    /**
     * 纳税识别号<br/>
     * 返回值对应的表列名 client_invoice_template.tax_identification_number
     *
     * @return 返回值对应 client_invoice_template.tax_identification_number
     *
     * @mbg.generated
     */
    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    /**
     * 纳税识别号<br/>
     * client_invoice_template.tax_identification_number
     *
     * @param taxIdentificationNumber 值对应 client_invoice_template.tax_identification_number
     *
     * @mbg.generated
     */
    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber == null ? null : taxIdentificationNumber.trim();
    }

    /**
     * 单位名称<br/>
     * 返回值对应的表列名 client_invoice_template.company_name
     *
     * @return 返回值对应 client_invoice_template.company_name
     *
     * @mbg.generated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 单位名称<br/>
     * client_invoice_template.company_name
     *
     * @param companyName 值对应 client_invoice_template.company_name
     *
     * @mbg.generated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 开户银行<br/>
     * 返回值对应的表列名 client_invoice_template.bank
     *
     * @return 返回值对应 client_invoice_template.bank
     *
     * @mbg.generated
     */
    public String getBank() {
        return bank;
    }

    /**
     * 开户银行<br/>
     * client_invoice_template.bank
     *
     * @param bank 值对应 client_invoice_template.bank
     *
     * @mbg.generated
     */
    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    /**
     * 银行账号<br/>
     * 返回值对应的表列名 client_invoice_template.bank_account
     *
     * @return 返回值对应 client_invoice_template.bank_account
     *
     * @mbg.generated
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * 银行账号<br/>
     * client_invoice_template.bank_account
     *
     * @param bankAccount 值对应 client_invoice_template.bank_account
     *
     * @mbg.generated
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    /**
     * 注册地址<br/>
     * 返回值对应的表列名 client_invoice_template.reg_address
     *
     * @return 返回值对应 client_invoice_template.reg_address
     *
     * @mbg.generated
     */
    public String getRegAddress() {
        return regAddress;
    }

    /**
     * 注册地址<br/>
     * client_invoice_template.reg_address
     *
     * @param regAddress 值对应 client_invoice_template.reg_address
     *
     * @mbg.generated
     */
    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress == null ? null : regAddress.trim();
    }

    /**
     * 注册电话<br/>
     * 返回值对应的表列名 client_invoice_template.reg_telephone
     *
     * @return 返回值对应 client_invoice_template.reg_telephone
     *
     * @mbg.generated
     */
    public String getRegTelephone() {
        return regTelephone;
    }

    /**
     * 注册电话<br/>
     * client_invoice_template.reg_telephone
     *
     * @param regTelephone 值对应 client_invoice_template.reg_telephone
     *
     * @mbg.generated
     */
    public void setRegTelephone(String regTelephone) {
        this.regTelephone = regTelephone == null ? null : regTelephone.trim();
    }

    /**
     * 状态（0 无效 1有效）<br/>
     * 返回值对应的表列名 client_invoice_template.status
     *
     * @return 返回值对应 client_invoice_template.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0 无效 1有效）<br/>
     * client_invoice_template.status
     *
     * @param status 值对应 client_invoice_template.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 client_invoice_template.creator
     *
     * @return 返回值对应 client_invoice_template.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * client_invoice_template.creator
     *
     * @param creator 值对应 client_invoice_template.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 client_invoice_template.create_time
     *
     * @return 返回值对应 client_invoice_template.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * client_invoice_template.create_time
     *
     * @param createTime 值对应 client_invoice_template.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 client_invoice_template.modifier
     *
     * @return 返回值对应 client_invoice_template.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * client_invoice_template.modifier
     *
     * @param modifier 值对应 client_invoice_template.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 client_invoice_template.modify_time
     *
     * @return 返回值对应 client_invoice_template.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 最后修改时间<br/>
     * client_invoice_template.modify_time
     *
     * @param modifyTime 值对应 client_invoice_template.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 备注<br/>
     * 返回值对应的表列名 client_invoice_template.remark
     *
     * @return 返回值对应 client_invoice_template.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * client_invoice_template.remark
     *
     * @param remark 值对应 client_invoice_template.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

}