package com.azz.platform.client.pojo;


import java.io.Serializable;
import java.util.Date;

public class ClientApply implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 客户编码
     *
     * @mbg.generated
     */
    private String clientCode;

    /**
     * 状态  0无效  1有效 2 禁用
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
     * 审核时间
     *
     * @mbg.generated
     */
    private Date auditorTime;

    /**
     * 审核人
     *
     * @mbg.generated
     */
    private String auditor;

    /**
     * 部门名称
     *
     * @mbg.generated
     */
    private String deptName;

    /**
     * 企业名称
     *
     * @mbg.generated
     */
    private String companyName;

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
     * 信用代码
     *
     * @mbg.generated
     */
    private String creditCode;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String creator;

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

    private static final long serialVersionUID = 1L;

    /**
     * 主键<br/>
     * 返回值对应的表列名 client_apply.id
     *
     * @return 返回值对应 client_apply.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * client_apply.id
     *
     * @param id 值对应 client_apply.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 客户编码<br/>
     * 返回值对应的表列名 client_apply.client_code
     *
     * @return 返回值对应 client_apply.client_code
     *
     * @mbg.generated
     */
    public String getClientCode() {
        return clientCode;
    }

    /**
     * 客户编码<br/>
     * client_apply.client_code
     *
     * @param clientCode 值对应 client_apply.client_code
     *
     * @mbg.generated
     */
    public void setClientCode(String clientCode) {
        this.clientCode = clientCode == null ? null : clientCode.trim();
    }

    /**
     * 状态  0无效  1有效 2 禁用<br/>
     * 返回值对应的表列名 client_apply.status
     *
     * @return 返回值对应 client_apply.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态  0无效  1有效 2 禁用<br/>
     * client_apply.status
     *
     * @param status 值对应 client_apply.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 client_apply.create_time
     *
     * @return 返回值对应 client_apply.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * client_apply.create_time
     *
     * @param createTime 值对应 client_apply.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 审核时间<br/>
     * 返回值对应的表列名 client_apply.auditor_time
     *
     * @return 返回值对应 client_apply.auditor_time
     *
     * @mbg.generated
     */
    public Date getAuditorTime() {
        return auditorTime;
    }

    /**
     * 审核时间<br/>
     * client_apply.auditor_time
     *
     * @param auditorTime 值对应 client_apply.auditor_time
     *
     * @mbg.generated
     */
    public void setAuditorTime(Date auditorTime) {
        this.auditorTime = auditorTime;
    }

    /**
     * 审核人<br/>
     * 返回值对应的表列名 client_apply.auditor
     *
     * @return 返回值对应 client_apply.auditor
     *
     * @mbg.generated
     */
    public String getAuditor() {
        return auditor;
    }

    /**
     * 审核人<br/>
     * client_apply.auditor
     *
     * @param auditor 值对应 client_apply.auditor
     *
     * @mbg.generated
     */
    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    /**
     * 部门名称<br/>
     * 返回值对应的表列名 client_apply.dept_name
     *
     * @return 返回值对应 client_apply.dept_name
     *
     * @mbg.generated
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 部门名称<br/>
     * client_apply.dept_name
     *
     * @param deptName 值对应 client_apply.dept_name
     *
     * @mbg.generated
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * 企业名称<br/>
     * 返回值对应的表列名 client_apply.company_name
     *
     * @return 返回值对应 client_apply.company_name
     *
     * @mbg.generated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 企业名称<br/>
     * client_apply.company_name
     *
     * @param companyName 值对应 client_apply.company_name
     *
     * @mbg.generated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 公司电话<br/>
     * 返回值对应的表列名 client_apply.company_tel
     *
     * @return 返回值对应 client_apply.company_tel
     *
     * @mbg.generated
     */
    public String getCompanyTel() {
        return companyTel;
    }

    /**
     * 公司电话<br/>
     * client_apply.company_tel
     *
     * @param companyTel 值对应 client_apply.company_tel
     *
     * @mbg.generated
     */
    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel == null ? null : companyTel.trim();
    }

    /**
     * 详细地址<br/>
     * 返回值对应的表列名 client_apply.address
     *
     * @return 返回值对应 client_apply.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * 详细地址<br/>
     * client_apply.address
     *
     * @param address 值对应 client_apply.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 信用代码<br/>
     * 返回值对应的表列名 client_apply.credit_code
     *
     * @return 返回值对应 client_apply.credit_code
     *
     * @mbg.generated
     */
    public String getCreditCode() {
        return creditCode;
    }

    /**
     * 信用代码<br/>
     * client_apply.credit_code
     *
     * @param creditCode 值对应 client_apply.credit_code
     *
     * @mbg.generated
     */
    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 client_apply.creator
     *
     * @return 返回值对应 client_apply.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * client_apply.creator
     *
     * @param creator 值对应 client_apply.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 第一张营业执照文件名称<br/>
     * 返回值对应的表列名 client_apply.trading_certificate_first_file_name
     *
     * @return 返回值对应 client_apply.trading_certificate_first_file_name
     *
     * @mbg.generated
     */
    public String getTradingCertificateFirstFileName() {
        return tradingCertificateFirstFileName;
    }

    /**
     * 第一张营业执照文件名称<br/>
     * client_apply.trading_certificate_first_file_name
     *
     * @param tradingCertificateFirstFileName 值对应 client_apply.trading_certificate_first_file_name
     *
     * @mbg.generated
     */
    public void setTradingCertificateFirstFileName(String tradingCertificateFirstFileName) {
        this.tradingCertificateFirstFileName = tradingCertificateFirstFileName == null ? null : tradingCertificateFirstFileName.trim();
    }

    /**
     * 第一张营业执照url<br/>
     * 返回值对应的表列名 client_apply.trading_certificate_first_file_url
     *
     * @return 返回值对应 client_apply.trading_certificate_first_file_url
     *
     * @mbg.generated
     */
    public String getTradingCertificateFirstFileUrl() {
        return tradingCertificateFirstFileUrl;
    }

    /**
     * 第一张营业执照url<br/>
     * client_apply.trading_certificate_first_file_url
     *
     * @param tradingCertificateFirstFileUrl 值对应 client_apply.trading_certificate_first_file_url
     *
     * @mbg.generated
     */
    public void setTradingCertificateFirstFileUrl(String tradingCertificateFirstFileUrl) {
        this.tradingCertificateFirstFileUrl = tradingCertificateFirstFileUrl == null ? null : tradingCertificateFirstFileUrl.trim();
    }

    /**
     * 第二张营业执照名称<br/>
     * 返回值对应的表列名 client_apply.trading_certificate_second_file_name
     *
     * @return 返回值对应 client_apply.trading_certificate_second_file_name
     *
     * @mbg.generated
     */
    public String getTradingCertificateSecondFileName() {
        return tradingCertificateSecondFileName;
    }

    /**
     * 第二张营业执照名称<br/>
     * client_apply.trading_certificate_second_file_name
     *
     * @param tradingCertificateSecondFileName 值对应 client_apply.trading_certificate_second_file_name
     *
     * @mbg.generated
     */
    public void setTradingCertificateSecondFileName(String tradingCertificateSecondFileName) {
        this.tradingCertificateSecondFileName = tradingCertificateSecondFileName == null ? null : tradingCertificateSecondFileName.trim();
    }

    /**
     * 第二张营业执照url<br/>
     * 返回值对应的表列名 client_apply.trading_certificate_second_file_url
     *
     * @return 返回值对应 client_apply.trading_certificate_second_file_url
     *
     * @mbg.generated
     */
    public String getTradingCertificateSecondFileUrl() {
        return tradingCertificateSecondFileUrl;
    }

    /**
     * 第二张营业执照url<br/>
     * client_apply.trading_certificate_second_file_url
     *
     * @param tradingCertificateSecondFileUrl 值对应 client_apply.trading_certificate_second_file_url
     *
     * @mbg.generated
     */
    public void setTradingCertificateSecondFileUrl(String tradingCertificateSecondFileUrl) {
        this.tradingCertificateSecondFileUrl = tradingCertificateSecondFileUrl == null ? null : tradingCertificateSecondFileUrl.trim();
    }

    /**
     * 第三张营业执照名称<br/>
     * 返回值对应的表列名 client_apply.trading_certificate_third_file_name
     *
     * @return 返回值对应 client_apply.trading_certificate_third_file_name
     *
     * @mbg.generated
     */
    public String getTradingCertificateThirdFileName() {
        return tradingCertificateThirdFileName;
    }

    /**
     * 第三张营业执照名称<br/>
     * client_apply.trading_certificate_third_file_name
     *
     * @param tradingCertificateThirdFileName 值对应 client_apply.trading_certificate_third_file_name
     *
     * @mbg.generated
     */
    public void setTradingCertificateThirdFileName(String tradingCertificateThirdFileName) {
        this.tradingCertificateThirdFileName = tradingCertificateThirdFileName == null ? null : tradingCertificateThirdFileName.trim();
    }

    /**
     * 第三张营业执照url<br/>
     * 返回值对应的表列名 client_apply.trading_certificate_third_file_url
     *
     * @return 返回值对应 client_apply.trading_certificate_third_file_url
     *
     * @mbg.generated
     */
    public String getTradingCertificateThirdFileUrl() {
        return tradingCertificateThirdFileUrl;
    }

    /**
     * 第三张营业执照url<br/>
     * client_apply.trading_certificate_third_file_url
     *
     * @param tradingCertificateThirdFileUrl 值对应 client_apply.trading_certificate_third_file_url
     *
     * @mbg.generated
     */
    public void setTradingCertificateThirdFileUrl(String tradingCertificateThirdFileUrl) {
        this.tradingCertificateThirdFileUrl = tradingCertificateThirdFileUrl == null ? null : tradingCertificateThirdFileUrl.trim();
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
        ClientApply other = (ClientApply) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClientCode() == null ? other.getClientCode() == null : this.getClientCode().equals(other.getClientCode()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getAuditorTime() == null ? other.getAuditorTime() == null : this.getAuditorTime().equals(other.getAuditorTime()))
            && (this.getAuditor() == null ? other.getAuditor() == null : this.getAuditor().equals(other.getAuditor()))
            && (this.getDeptName() == null ? other.getDeptName() == null : this.getDeptName().equals(other.getDeptName()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getCompanyTel() == null ? other.getCompanyTel() == null : this.getCompanyTel().equals(other.getCompanyTel()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getCreditCode() == null ? other.getCreditCode() == null : this.getCreditCode().equals(other.getCreditCode()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getTradingCertificateFirstFileName() == null ? other.getTradingCertificateFirstFileName() == null : this.getTradingCertificateFirstFileName().equals(other.getTradingCertificateFirstFileName()))
            && (this.getTradingCertificateFirstFileUrl() == null ? other.getTradingCertificateFirstFileUrl() == null : this.getTradingCertificateFirstFileUrl().equals(other.getTradingCertificateFirstFileUrl()))
            && (this.getTradingCertificateSecondFileName() == null ? other.getTradingCertificateSecondFileName() == null : this.getTradingCertificateSecondFileName().equals(other.getTradingCertificateSecondFileName()))
            && (this.getTradingCertificateSecondFileUrl() == null ? other.getTradingCertificateSecondFileUrl() == null : this.getTradingCertificateSecondFileUrl().equals(other.getTradingCertificateSecondFileUrl()))
            && (this.getTradingCertificateThirdFileName() == null ? other.getTradingCertificateThirdFileName() == null : this.getTradingCertificateThirdFileName().equals(other.getTradingCertificateThirdFileName()))
            && (this.getTradingCertificateThirdFileUrl() == null ? other.getTradingCertificateThirdFileUrl() == null : this.getTradingCertificateThirdFileUrl().equals(other.getTradingCertificateThirdFileUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClientCode() == null) ? 0 : getClientCode().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getAuditorTime() == null) ? 0 : getAuditorTime().hashCode());
        result = prime * result + ((getAuditor() == null) ? 0 : getAuditor().hashCode());
        result = prime * result + ((getDeptName() == null) ? 0 : getDeptName().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getCompanyTel() == null) ? 0 : getCompanyTel().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getCreditCode() == null) ? 0 : getCreditCode().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getTradingCertificateFirstFileName() == null) ? 0 : getTradingCertificateFirstFileName().hashCode());
        result = prime * result + ((getTradingCertificateFirstFileUrl() == null) ? 0 : getTradingCertificateFirstFileUrl().hashCode());
        result = prime * result + ((getTradingCertificateSecondFileName() == null) ? 0 : getTradingCertificateSecondFileName().hashCode());
        result = prime * result + ((getTradingCertificateSecondFileUrl() == null) ? 0 : getTradingCertificateSecondFileUrl().hashCode());
        result = prime * result + ((getTradingCertificateThirdFileName() == null) ? 0 : getTradingCertificateThirdFileName().hashCode());
        result = prime * result + ((getTradingCertificateThirdFileUrl() == null) ? 0 : getTradingCertificateThirdFileUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", clientCode=").append(clientCode);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", auditorTime=").append(auditorTime);
        sb.append(", auditor=").append(auditor);
        sb.append(", deptName=").append(deptName);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyTel=").append(companyTel);
        sb.append(", address=").append(address);
        sb.append(", creditCode=").append(creditCode);
        sb.append(", creator=").append(creator);
        sb.append(", tradingCertificateFirstFileName=").append(tradingCertificateFirstFileName);
        sb.append(", tradingCertificateFirstFileUrl=").append(tradingCertificateFirstFileUrl);
        sb.append(", tradingCertificateSecondFileName=").append(tradingCertificateSecondFileName);
        sb.append(", tradingCertificateSecondFileUrl=").append(tradingCertificateSecondFileUrl);
        sb.append(", tradingCertificateThirdFileName=").append(tradingCertificateThirdFileName);
        sb.append(", tradingCertificateThirdFileUrl=").append(tradingCertificateThirdFileUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}