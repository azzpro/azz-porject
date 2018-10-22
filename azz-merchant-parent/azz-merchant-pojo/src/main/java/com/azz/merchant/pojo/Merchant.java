package com.azz.merchant.pojo;

import java.io.Serializable;
import java.util.Date;

public class Merchant implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 商户编码
     *
     * @mbg.generated
     */	
    private String merchantCode;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    private String merchantName;

    /**
     * 法人姓名
     *
     * @mbg.generated
     */
    private String legalPersonName;

    /**
     * 法人身份证
     *
     * @mbg.generated
     */
    private String legalPersonIdCard;

    /**
     * 企业名称
     *
     * @mbg.generated
     */
    private String companyName;

    /**
     * 信用代码
     *
     * @mbg.generated
     */
    private String creditCode;

    /**
     * 公司电话
     *
     * @mbg.generated
     */
    private String companyTel;

    /**
     * 详细地址
     *
     * @mbg.generated
     */
    private String address;

    /**
     * 第一张营业执照文件名称
     *
     * @mbg.generated
     */
    private String tradingCertificateFirstFileName;

    /**
     * 第一张营业执照url
     *
     * @mbg.generated
     */
    private String tradingCertificateFirstFileUrl;

    /**
     * 第二张营业执照名称
     *
     * @mbg.generated
     */
    private String tradingCertificateSecondFileName;

    /**
     * 第二张营业执照url
     *
     * @mbg.generated
     */
    private String tradingCertificateSecondFileUrl;

    /**
     * 第三张营业执照名称
     *
     * @mbg.generated
     */
    private String tradingCertificateThirdFileName;

    /**
     * 第三张营业执照url
     *
     * @mbg.generated
     */
    private String tradingCertificateThirdFileUrl;

    /**
     * 公司经营执照名称
     *
     * @mbg.generated
     */
    private String businessLicenseFileName;

    /**
     * 公司经营执照url
     *
     * @mbg.generated
     */
    private String businessLicenseFileUrl;

    /**
     * 法人身份证文件名称
     *
     * @mbg.generated
     */
    private String legalPersonIdCardFileName;

    /**
     * 法人身份证文件路径
     *
     * @mbg.generated
     */
    private String legalPersonIdCardFileUrl;

    /**
     * 审核人
     *
     * @mbg.generated
     */
    private String auditor;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 merchant.id
     *
     * @return 返回值对应 merchant.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * merchant.id
     *
     * @param id 值对应 merchant.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商户编码<br/>
     * 返回值对应的表列名 merchant.merchant_code
     *
     * @return 返回值对应 merchant.merchant_code
     *
     * @mbg.generated
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /**
     * 商户编码<br/>
     * merchant.merchant_code
     *
     * @param merchantCode 值对应 merchant.merchant_code
     *
     * @mbg.generated
     */
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
    }

    /**
     * 商户名称<br/>
     * 返回值对应的表列名 merchant.merchant_name
     *
     * @return 返回值对应 merchant.merchant_name
     *
     * @mbg.generated
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称<br/>
     * merchant.merchant_name
     *
     * @param merchantName 值对应 merchant.merchant_name
     *
     * @mbg.generated
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * 法人姓名<br/>
     * 返回值对应的表列名 merchant.legal_person_name
     *
     * @return 返回值对应 merchant.legal_person_name
     *
     * @mbg.generated
     */
    public String getLegalPersonName() {
        return legalPersonName;
    }

    /**
     * 法人姓名<br/>
     * merchant.legal_person_name
     *
     * @param legalPersonName 值对应 merchant.legal_person_name
     *
     * @mbg.generated
     */
    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName == null ? null : legalPersonName.trim();
    }

    /**
     * 法人身份证<br/>
     * 返回值对应的表列名 merchant.legal_person_id_card
     *
     * @return 返回值对应 merchant.legal_person_id_card
     *
     * @mbg.generated
     */
    public String getLegalPersonIdCard() {
        return legalPersonIdCard;
    }

    /**
     * 法人身份证<br/>
     * merchant.legal_person_id_card
     *
     * @param legalPersonIdCard 值对应 merchant.legal_person_id_card
     *
     * @mbg.generated
     */
    public void setLegalPersonIdCard(String legalPersonIdCard) {
        this.legalPersonIdCard = legalPersonIdCard == null ? null : legalPersonIdCard.trim();
    }

    /**
     * 企业名称<br/>
     * 返回值对应的表列名 merchant.company_name
     *
     * @return 返回值对应 merchant.company_name
     *
     * @mbg.generated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 企业名称<br/>
     * merchant.company_name
     *
     * @param companyName 值对应 merchant.company_name
     *
     * @mbg.generated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 信用代码<br/>
     * 返回值对应的表列名 merchant.credit_code
     *
     * @return 返回值对应 merchant.credit_code
     *
     * @mbg.generated
     */
    public String getCreditCode() {
        return creditCode;
    }

    /**
     * 信用代码<br/>
     * merchant.credit_code
     *
     * @param creditCode 值对应 merchant.credit_code
     *
     * @mbg.generated
     */
    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    /**
     * 公司电话<br/>
     * 返回值对应的表列名 merchant.company_tel
     *
     * @return 返回值对应 merchant.company_tel
     *
     * @mbg.generated
     */
    public String getCompanyTel() {
        return companyTel;
    }

    /**
     * 公司电话<br/>
     * merchant.company_tel
     *
     * @param companyTel 值对应 merchant.company_tel
     *
     * @mbg.generated
     */
    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel == null ? null : companyTel.trim();
    }

    /**
     * 详细地址<br/>
     * 返回值对应的表列名 merchant.address
     *
     * @return 返回值对应 merchant.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * 详细地址<br/>
     * merchant.address
     *
     * @param address 值对应 merchant.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 第一张营业执照文件名称<br/>
     * 返回值对应的表列名 merchant.trading_certificate_first_file_name
     *
     * @return 返回值对应 merchant.trading_certificate_first_file_name
     *
     * @mbg.generated
     */
    public String getTradingCertificateFirstFileName() {
        return tradingCertificateFirstFileName;
    }

    /**
     * 第一张营业执照文件名称<br/>
     * merchant.trading_certificate_first_file_name
     *
     * @param tradingCertificateFirstFileName 值对应 merchant.trading_certificate_first_file_name
     *
     * @mbg.generated
     */
    public void setTradingCertificateFirstFileName(String tradingCertificateFirstFileName) {
        this.tradingCertificateFirstFileName = tradingCertificateFirstFileName == null ? null : tradingCertificateFirstFileName.trim();
    }

    /**
     * 第一张营业执照url<br/>
     * 返回值对应的表列名 merchant.trading_certificate_first_file_url
     *
     * @return 返回值对应 merchant.trading_certificate_first_file_url
     *
     * @mbg.generated
     */
    public String getTradingCertificateFirstFileUrl() {
        return tradingCertificateFirstFileUrl;
    }

    /**
     * 第一张营业执照url<br/>
     * merchant.trading_certificate_first_file_url
     *
     * @param tradingCertificateFirstFileUrl 值对应 merchant.trading_certificate_first_file_url
     *
     * @mbg.generated
     */
    public void setTradingCertificateFirstFileUrl(String tradingCertificateFirstFileUrl) {
        this.tradingCertificateFirstFileUrl = tradingCertificateFirstFileUrl == null ? null : tradingCertificateFirstFileUrl.trim();
    }

    /**
     * 第二张营业执照名称<br/>
     * 返回值对应的表列名 merchant.trading_certificate_second_file_name
     *
     * @return 返回值对应 merchant.trading_certificate_second_file_name
     *
     * @mbg.generated
     */
    public String getTradingCertificateSecondFileName() {
        return tradingCertificateSecondFileName;
    }

    /**
     * 第二张营业执照名称<br/>
     * merchant.trading_certificate_second_file_name
     *
     * @param tradingCertificateSecondFileName 值对应 merchant.trading_certificate_second_file_name
     *
     * @mbg.generated
     */
    public void setTradingCertificateSecondFileName(String tradingCertificateSecondFileName) {
        this.tradingCertificateSecondFileName = tradingCertificateSecondFileName == null ? null : tradingCertificateSecondFileName.trim();
    }

    /**
     * 第二张营业执照url<br/>
     * 返回值对应的表列名 merchant.trading_certificate_second_file_url
     *
     * @return 返回值对应 merchant.trading_certificate_second_file_url
     *
     * @mbg.generated
     */
    public String getTradingCertificateSecondFileUrl() {
        return tradingCertificateSecondFileUrl;
    }

    /**
     * 第二张营业执照url<br/>
     * merchant.trading_certificate_second_file_url
     *
     * @param tradingCertificateSecondFileUrl 值对应 merchant.trading_certificate_second_file_url
     *
     * @mbg.generated
     */
    public void setTradingCertificateSecondFileUrl(String tradingCertificateSecondFileUrl) {
        this.tradingCertificateSecondFileUrl = tradingCertificateSecondFileUrl == null ? null : tradingCertificateSecondFileUrl.trim();
    }

    /**
     * 第三张营业执照名称<br/>
     * 返回值对应的表列名 merchant.trading_certificate_third_file_name
     *
     * @return 返回值对应 merchant.trading_certificate_third_file_name
     *
     * @mbg.generated
     */
    public String getTradingCertificateThirdFileName() {
        return tradingCertificateThirdFileName;
    }

    /**
     * 第三张营业执照名称<br/>
     * merchant.trading_certificate_third_file_name
     *
     * @param tradingCertificateThirdFileName 值对应 merchant.trading_certificate_third_file_name
     *
     * @mbg.generated
     */
    public void setTradingCertificateThirdFileName(String tradingCertificateThirdFileName) {
        this.tradingCertificateThirdFileName = tradingCertificateThirdFileName == null ? null : tradingCertificateThirdFileName.trim();
    }

    /**
     * 第三张营业执照url<br/>
     * 返回值对应的表列名 merchant.trading_certificate_third_file_url
     *
     * @return 返回值对应 merchant.trading_certificate_third_file_url
     *
     * @mbg.generated
     */
    public String getTradingCertificateThirdFileUrl() {
        return tradingCertificateThirdFileUrl;
    }

    /**
     * 第三张营业执照url<br/>
     * merchant.trading_certificate_third_file_url
     *
     * @param tradingCertificateThirdFileUrl 值对应 merchant.trading_certificate_third_file_url
     *
     * @mbg.generated
     */
    public void setTradingCertificateThirdFileUrl(String tradingCertificateThirdFileUrl) {
        this.tradingCertificateThirdFileUrl = tradingCertificateThirdFileUrl == null ? null : tradingCertificateThirdFileUrl.trim();
    }

    /**
     * 公司经营执照名称<br/>
     * 返回值对应的表列名 merchant.business_license_file_name
     *
     * @return 返回值对应 merchant.business_license_file_name
     *
     * @mbg.generated
     */
    public String getBusinessLicenseFileName() {
        return businessLicenseFileName;
    }

    /**
     * 公司经营执照名称<br/>
     * merchant.business_license_file_name
     *
     * @param businessLicenseFileName 值对应 merchant.business_license_file_name
     *
     * @mbg.generated
     */
    public void setBusinessLicenseFileName(String businessLicenseFileName) {
        this.businessLicenseFileName = businessLicenseFileName == null ? null : businessLicenseFileName.trim();
    }

    /**
     * 公司经营执照url<br/>
     * 返回值对应的表列名 merchant.business_license_file_url
     *
     * @return 返回值对应 merchant.business_license_file_url
     *
     * @mbg.generated
     */
    public String getBusinessLicenseFileUrl() {
        return businessLicenseFileUrl;
    }

    /**
     * 公司经营执照url<br/>
     * merchant.business_license_file_url
     *
     * @param businessLicenseFileUrl 值对应 merchant.business_license_file_url
     *
     * @mbg.generated
     */
    public void setBusinessLicenseFileUrl(String businessLicenseFileUrl) {
        this.businessLicenseFileUrl = businessLicenseFileUrl == null ? null : businessLicenseFileUrl.trim();
    }

    /**
     * 法人身份证文件名称<br/>
     * 返回值对应的表列名 merchant.legal_person_id_card_file_name
     *
     * @return 返回值对应 merchant.legal_person_id_card_file_name
     *
     * @mbg.generated
     */
    public String getLegalPersonIdCardFileName() {
        return legalPersonIdCardFileName;
    }

    /**
     * 法人身份证文件名称<br/>
     * merchant.legal_person_id_card_file_name
     *
     * @param legalPersonIdCardFileName 值对应 merchant.legal_person_id_card_file_name
     *
     * @mbg.generated
     */
    public void setLegalPersonIdCardFileName(String legalPersonIdCardFileName) {
        this.legalPersonIdCardFileName = legalPersonIdCardFileName == null ? null : legalPersonIdCardFileName.trim();
    }

    /**
     * 法人身份证文件路径<br/>
     * 返回值对应的表列名 merchant.legal_person_id_card_file_url
     *
     * @return 返回值对应 merchant.legal_person_id_card_file_url
     *
     * @mbg.generated
     */
    public String getLegalPersonIdCardFileUrl() {
        return legalPersonIdCardFileUrl;
    }

    /**
     * 法人身份证文件路径<br/>
     * merchant.legal_person_id_card_file_url
     *
     * @param legalPersonIdCardFileUrl 值对应 merchant.legal_person_id_card_file_url
     *
     * @mbg.generated
     */
    public void setLegalPersonIdCardFileUrl(String legalPersonIdCardFileUrl) {
        this.legalPersonIdCardFileUrl = legalPersonIdCardFileUrl == null ? null : legalPersonIdCardFileUrl.trim();
    }

    /**
     * 审核人<br/>
     * 返回值对应的表列名 merchant.auditor
     *
     * @return 返回值对应 merchant.auditor
     *
     * @mbg.generated
     */
    public String getAuditor() {
        return auditor;
    }

    /**
     * 审核人<br/>
     * merchant.auditor
     *
     * @param auditor 值对应 merchant.auditor
     *
     * @mbg.generated
     */
    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    /**
     * 备注<br/>
     * 返回值对应的表列名 merchant.remark
     *
     * @return 返回值对应 merchant.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * merchant.remark
     *
     * @param remark 值对应 merchant.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 merchant.create_time
     *
     * @return 返回值对应 merchant.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * merchant.create_time
     *
     * @param createTime 值对应 merchant.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        Merchant other = (Merchant) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantCode() == null ? other.getMerchantCode() == null : this.getMerchantCode().equals(other.getMerchantCode()))
            && (this.getMerchantName() == null ? other.getMerchantName() == null : this.getMerchantName().equals(other.getMerchantName()))
            && (this.getLegalPersonName() == null ? other.getLegalPersonName() == null : this.getLegalPersonName().equals(other.getLegalPersonName()))
            && (this.getLegalPersonIdCard() == null ? other.getLegalPersonIdCard() == null : this.getLegalPersonIdCard().equals(other.getLegalPersonIdCard()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getCreditCode() == null ? other.getCreditCode() == null : this.getCreditCode().equals(other.getCreditCode()))
            && (this.getCompanyTel() == null ? other.getCompanyTel() == null : this.getCompanyTel().equals(other.getCompanyTel()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getTradingCertificateFirstFileName() == null ? other.getTradingCertificateFirstFileName() == null : this.getTradingCertificateFirstFileName().equals(other.getTradingCertificateFirstFileName()))
            && (this.getTradingCertificateFirstFileUrl() == null ? other.getTradingCertificateFirstFileUrl() == null : this.getTradingCertificateFirstFileUrl().equals(other.getTradingCertificateFirstFileUrl()))
            && (this.getTradingCertificateSecondFileName() == null ? other.getTradingCertificateSecondFileName() == null : this.getTradingCertificateSecondFileName().equals(other.getTradingCertificateSecondFileName()))
            && (this.getTradingCertificateSecondFileUrl() == null ? other.getTradingCertificateSecondFileUrl() == null : this.getTradingCertificateSecondFileUrl().equals(other.getTradingCertificateSecondFileUrl()))
            && (this.getTradingCertificateThirdFileName() == null ? other.getTradingCertificateThirdFileName() == null : this.getTradingCertificateThirdFileName().equals(other.getTradingCertificateThirdFileName()))
            && (this.getTradingCertificateThirdFileUrl() == null ? other.getTradingCertificateThirdFileUrl() == null : this.getTradingCertificateThirdFileUrl().equals(other.getTradingCertificateThirdFileUrl()))
            && (this.getBusinessLicenseFileName() == null ? other.getBusinessLicenseFileName() == null : this.getBusinessLicenseFileName().equals(other.getBusinessLicenseFileName()))
            && (this.getBusinessLicenseFileUrl() == null ? other.getBusinessLicenseFileUrl() == null : this.getBusinessLicenseFileUrl().equals(other.getBusinessLicenseFileUrl()))
            && (this.getLegalPersonIdCardFileName() == null ? other.getLegalPersonIdCardFileName() == null : this.getLegalPersonIdCardFileName().equals(other.getLegalPersonIdCardFileName()))
            && (this.getLegalPersonIdCardFileUrl() == null ? other.getLegalPersonIdCardFileUrl() == null : this.getLegalPersonIdCardFileUrl().equals(other.getLegalPersonIdCardFileUrl()))
            && (this.getAuditor() == null ? other.getAuditor() == null : this.getAuditor().equals(other.getAuditor()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMerchantCode() == null) ? 0 : getMerchantCode().hashCode());
        result = prime * result + ((getMerchantName() == null) ? 0 : getMerchantName().hashCode());
        result = prime * result + ((getLegalPersonName() == null) ? 0 : getLegalPersonName().hashCode());
        result = prime * result + ((getLegalPersonIdCard() == null) ? 0 : getLegalPersonIdCard().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getCreditCode() == null) ? 0 : getCreditCode().hashCode());
        result = prime * result + ((getCompanyTel() == null) ? 0 : getCompanyTel().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getTradingCertificateFirstFileName() == null) ? 0 : getTradingCertificateFirstFileName().hashCode());
        result = prime * result + ((getTradingCertificateFirstFileUrl() == null) ? 0 : getTradingCertificateFirstFileUrl().hashCode());
        result = prime * result + ((getTradingCertificateSecondFileName() == null) ? 0 : getTradingCertificateSecondFileName().hashCode());
        result = prime * result + ((getTradingCertificateSecondFileUrl() == null) ? 0 : getTradingCertificateSecondFileUrl().hashCode());
        result = prime * result + ((getTradingCertificateThirdFileName() == null) ? 0 : getTradingCertificateThirdFileName().hashCode());
        result = prime * result + ((getTradingCertificateThirdFileUrl() == null) ? 0 : getTradingCertificateThirdFileUrl().hashCode());
        result = prime * result + ((getBusinessLicenseFileName() == null) ? 0 : getBusinessLicenseFileName().hashCode());
        result = prime * result + ((getBusinessLicenseFileUrl() == null) ? 0 : getBusinessLicenseFileUrl().hashCode());
        result = prime * result + ((getLegalPersonIdCardFileName() == null) ? 0 : getLegalPersonIdCardFileName().hashCode());
        result = prime * result + ((getLegalPersonIdCardFileUrl() == null) ? 0 : getLegalPersonIdCardFileUrl().hashCode());
        result = prime * result + ((getAuditor() == null) ? 0 : getAuditor().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", merchantCode=").append(merchantCode);
        sb.append(", merchantName=").append(merchantName);
        sb.append(", legalPersonName=").append(legalPersonName);
        sb.append(", legalPersonIdCard=").append(legalPersonIdCard);
        sb.append(", companyName=").append(companyName);
        sb.append(", creditCode=").append(creditCode);
        sb.append(", companyTel=").append(companyTel);
        sb.append(", address=").append(address);
        sb.append(", tradingCertificateFirstFileName=").append(tradingCertificateFirstFileName);
        sb.append(", tradingCertificateFirstFileUrl=").append(tradingCertificateFirstFileUrl);
        sb.append(", tradingCertificateSecondFileName=").append(tradingCertificateSecondFileName);
        sb.append(", tradingCertificateSecondFileUrl=").append(tradingCertificateSecondFileUrl);
        sb.append(", tradingCertificateThirdFileName=").append(tradingCertificateThirdFileName);
        sb.append(", tradingCertificateThirdFileUrl=").append(tradingCertificateThirdFileUrl);
        sb.append(", businessLicenseFileName=").append(businessLicenseFileName);
        sb.append(", businessLicenseFileUrl=").append(businessLicenseFileUrl);
        sb.append(", legalPersonIdCardFileName=").append(legalPersonIdCardFileName);
        sb.append(", legalPersonIdCardFileUrl=").append(legalPersonIdCardFileUrl);
        sb.append(", auditor=").append(auditor);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}