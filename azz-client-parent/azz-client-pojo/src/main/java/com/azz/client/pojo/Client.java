package com.azz.client.pojo;

import java.io.Serializable;
import java.util.Date;

public class Client implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    private String clientCode;

    private String phoneNumber;

    private String password;

    private String salt;

    /**
     * 客户类型   0个人  1企业
     *
     * @mbg.generated
     */
    private Integer clientType;

    /**
     * 状态  0无效  1有效 2 禁用
     *
     * @mbg.generated
     */
    private Integer status;

    private Date createTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String creator;

    /**
     * 最后修改时间
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    private String modifier;

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
     * 头像路径
     *
     * @mbg.generated
     */
    private String headImageUrl;

    private String email;

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

    private static final long serialVersionUID = 1L;

    /**
     * 主键<br/>
     * 返回值对应的表列名 client.id
     *
     * @return 返回值对应 client.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * client.id
     *
     * @param id 值对应 client.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <br/>
     * 返回值对应的表列名 client.client_code
     *
     * @return 返回值对应 client.client_code
     *
     * @mbg.generated
     */
    public String getClientCode() {
        return clientCode;
    }

    /**
     * <br/>
     * client.client_code
     *
     * @param clientCode 值对应 client.client_code
     *
     * @mbg.generated
     */
    public void setClientCode(String clientCode) {
        this.clientCode = clientCode == null ? null : clientCode.trim();
    }

    /**
     * <br/>
     * 返回值对应的表列名 client.phone_number
     *
     * @return 返回值对应 client.phone_number
     *
     * @mbg.generated
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * <br/>
     * client.phone_number
     *
     * @param phoneNumber 值对应 client.phone_number
     *
     * @mbg.generated
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * <br/>
     * 返回值对应的表列名 client.password
     *
     * @return 返回值对应 client.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * <br/>
     * client.password
     *
     * @param password 值对应 client.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * <br/>
     * 返回值对应的表列名 client.salt
     *
     * @return 返回值对应 client.salt
     *
     * @mbg.generated
     */
    public String getSalt() {
        return salt;
    }

    /**
     * <br/>
     * client.salt
     *
     * @param salt 值对应 client.salt
     *
     * @mbg.generated
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 客户类型   0个人  1企业<br/>
     * 返回值对应的表列名 client.client_type
     *
     * @return 返回值对应 client.client_type
     *
     * @mbg.generated
     */
    public Integer getClientType() {
        return clientType;
    }

    /**
     * 客户类型   0个人  1企业<br/>
     * client.client_type
     *
     * @param clientType 值对应 client.client_type
     *
     * @mbg.generated
     */
    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    /**
     * 状态  0无效  1有效 2 禁用<br/>
     * 返回值对应的表列名 client.status
     *
     * @return 返回值对应 client.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态  0无效  1有效 2 禁用<br/>
     * client.status
     *
     * @param status 值对应 client.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <br/>
     * 返回值对应的表列名 client.create_time
     *
     * @return 返回值对应 client.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * <br/>
     * client.create_time
     *
     * @param createTime 值对应 client.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 client.creator
     *
     * @return 返回值对应 client.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * client.creator
     *
     * @param creator 值对应 client.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 client.last_modify_time
     *
     * @return 返回值对应 client.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * 最后修改时间<br/>
     * client.last_modify_time
     *
     * @param lastModifyTime 值对应 client.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 client.modifier
     *
     * @return 返回值对应 client.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * client.modifier
     *
     * @param modifier 值对应 client.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 部门名称<br/>
     * 返回值对应的表列名 client.dept_name
     *
     * @return 返回值对应 client.dept_name
     *
     * @mbg.generated
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 部门名称<br/>
     * client.dept_name
     *
     * @param deptName 值对应 client.dept_name
     *
     * @mbg.generated
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * 企业名称<br/>
     * 返回值对应的表列名 client.company_name
     *
     * @return 返回值对应 client.company_name
     *
     * @mbg.generated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 企业名称<br/>
     * client.company_name
     *
     * @param companyName 值对应 client.company_name
     *
     * @mbg.generated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 公司电话<br/>
     * 返回值对应的表列名 client.company_tel
     *
     * @return 返回值对应 client.company_tel
     *
     * @mbg.generated
     */
    public String getCompanyTel() {
        return companyTel;
    }

    /**
     * 公司电话<br/>
     * client.company_tel
     *
     * @param companyTel 值对应 client.company_tel
     *
     * @mbg.generated
     */
    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel == null ? null : companyTel.trim();
    }

    /**
     * 头像路径<br/>
     * 返回值对应的表列名 client.head_image_url
     *
     * @return 返回值对应 client.head_image_url
     *
     * @mbg.generated
     */
    public String getHeadImageUrl() {
        return headImageUrl;
    }

    /**
     * 头像路径<br/>
     * client.head_image_url
     *
     * @param headImageUrl 值对应 client.head_image_url
     *
     * @mbg.generated
     */
    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl == null ? null : headImageUrl.trim();
    }

    /**
     * <br/>
     * 返回值对应的表列名 client.email
     *
     * @return 返回值对应 client.email
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * <br/>
     * client.email
     *
     * @param email 值对应 client.email
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 详细地址<br/>
     * 返回值对应的表列名 client.address
     *
     * @return 返回值对应 client.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * 详细地址<br/>
     * client.address
     *
     * @param address 值对应 client.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 信用代码<br/>
     * 返回值对应的表列名 client.credit_code
     *
     * @return 返回值对应 client.credit_code
     *
     * @mbg.generated
     */
    public String getCreditCode() {
        return creditCode;
    }

    /**
     * 信用代码<br/>
     * client.credit_code
     *
     * @param creditCode 值对应 client.credit_code
     *
     * @mbg.generated
     */
    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
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
        Client other = (Client) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClientCode() == null ? other.getClientCode() == null : this.getClientCode().equals(other.getClientCode()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
            && (this.getClientType() == null ? other.getClientType() == null : this.getClientType().equals(other.getClientType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getDeptName() == null ? other.getDeptName() == null : this.getDeptName().equals(other.getDeptName()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getCompanyTel() == null ? other.getCompanyTel() == null : this.getCompanyTel().equals(other.getCompanyTel()))
            && (this.getHeadImageUrl() == null ? other.getHeadImageUrl() == null : this.getHeadImageUrl().equals(other.getHeadImageUrl()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getCreditCode() == null ? other.getCreditCode() == null : this.getCreditCode().equals(other.getCreditCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClientCode() == null) ? 0 : getClientCode().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getClientType() == null) ? 0 : getClientType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getDeptName() == null) ? 0 : getDeptName().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getCompanyTel() == null) ? 0 : getCompanyTel().hashCode());
        result = prime * result + ((getHeadImageUrl() == null) ? 0 : getHeadImageUrl().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getCreditCode() == null) ? 0 : getCreditCode().hashCode());
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
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", clientType=").append(clientType);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", deptName=").append(deptName);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyTel=").append(companyTel);
        sb.append(", headImageUrl=").append(headImageUrl);
        sb.append(", email=").append(email);
        sb.append(", address=").append(address);
        sb.append(", creditCode=").append(creditCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}