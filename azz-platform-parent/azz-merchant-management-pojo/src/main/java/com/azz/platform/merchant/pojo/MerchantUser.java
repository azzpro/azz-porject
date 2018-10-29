package com.azz.platform.merchant.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantUser implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 商户成员编码
     *
     * @mbg.generated
     */
    private String merchantUserCode;

    /**
     * 商户编码
     *
     * @mbg.generated
     */
    private String merchantCode;

    /**
     * 商户成员姓名
     *
     * @mbg.generated
     */
    private String merchantUserName;

    /**
     * 手机号码
     *
     * @mbg.generated
     */
    private String phoneNumber;

    /**
     * 密码
     *
     * @mbg.generated
     */
    private String password;

    /**
     * 盐
     *
     * @mbg.generated
     */
    private String salt;

    /**
     * 邮箱
     *
     * @mbg.generated
     */
    private String email;

    /**
     * 部门id
     *
     * @mbg.generated
     */
    private Long deptId;

    /**
     * 岗位名称
     *
     * @mbg.generated
     */
    private String postName;

    /**
     * 状态（1：有效 0：冻结 2：注销）
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 登录错误次数
     *
     * @mbg.generated
     */
    private Integer loginErrorsTimes;

    /**
     * 登录ip地址
     *
     * @mbg.generated
     */
    private String loginIp;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
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
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 merchant_user.id
     *
     * @return 返回值对应 merchant_user.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * merchant_user.id
     *
     * @param id 值对应 merchant_user.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商户成员编码<br/>
     * 返回值对应的表列名 merchant_user.merchant_user_code
     *
     * @return 返回值对应 merchant_user.merchant_user_code
     *
     * @mbg.generated
     */
    public String getMerchantUserCode() {
        return merchantUserCode;
    }

    /**
     * 商户成员编码<br/>
     * merchant_user.merchant_user_code
     *
     * @param merchantUserCode 值对应 merchant_user.merchant_user_code
     *
     * @mbg.generated
     */
    public void setMerchantUserCode(String merchantUserCode) {
        this.merchantUserCode = merchantUserCode == null ? null : merchantUserCode.trim();
    }

    /**
     * 商户编码<br/>
     * 返回值对应的表列名 merchant_user.merchant_code
     *
     * @return 返回值对应 merchant_user.merchant_code
     *
     * @mbg.generated
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /**
     * 商户编码<br/>
     * merchant_user.merchant_code
     *
     * @param merchantCode 值对应 merchant_user.merchant_code
     *
     * @mbg.generated
     */
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
    }

    /**
     * 商户成员姓名<br/>
     * 返回值对应的表列名 merchant_user.merchant_user_name
     *
     * @return 返回值对应 merchant_user.merchant_user_name
     *
     * @mbg.generated
     */
    public String getMerchantUserName() {
        return merchantUserName;
    }

    /**
     * 商户成员姓名<br/>
     * merchant_user.merchant_user_name
     *
     * @param merchantUserName 值对应 merchant_user.merchant_user_name
     *
     * @mbg.generated
     */
    public void setMerchantUserName(String merchantUserName) {
        this.merchantUserName = merchantUserName == null ? null : merchantUserName.trim();
    }

    /**
     * 手机号码<br/>
     * 返回值对应的表列名 merchant_user.phone_number
     *
     * @return 返回值对应 merchant_user.phone_number
     *
     * @mbg.generated
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 手机号码<br/>
     * merchant_user.phone_number
     *
     * @param phoneNumber 值对应 merchant_user.phone_number
     *
     * @mbg.generated
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * 密码<br/>
     * 返回值对应的表列名 merchant_user.password
     *
     * @return 返回值对应 merchant_user.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码<br/>
     * merchant_user.password
     *
     * @param password 值对应 merchant_user.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 盐<br/>
     * 返回值对应的表列名 merchant_user.salt
     *
     * @return 返回值对应 merchant_user.salt
     *
     * @mbg.generated
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 盐<br/>
     * merchant_user.salt
     *
     * @param salt 值对应 merchant_user.salt
     *
     * @mbg.generated
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 邮箱<br/>
     * 返回值对应的表列名 merchant_user.email
     *
     * @return 返回值对应 merchant_user.email
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱<br/>
     * merchant_user.email
     *
     * @param email 值对应 merchant_user.email
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 部门id<br/>
     * 返回值对应的表列名 merchant_user.dept_id
     *
     * @return 返回值对应 merchant_user.dept_id
     *
     * @mbg.generated
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 部门id<br/>
     * merchant_user.dept_id
     *
     * @param deptId 值对应 merchant_user.dept_id
     *
     * @mbg.generated
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 岗位名称<br/>
     * 返回值对应的表列名 merchant_user.post_name
     *
     * @return 返回值对应 merchant_user.post_name
     *
     * @mbg.generated
     */
    public String getPostName() {
        return postName;
    }

    /**
     * 岗位名称<br/>
     * merchant_user.post_name
     *
     * @param postName 值对应 merchant_user.post_name
     *
     * @mbg.generated
     */
    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    /**
     * 状态（1：有效 0：冻结 2：注销）<br/>
     * 返回值对应的表列名 merchant_user.status
     *
     * @return 返回值对应 merchant_user.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（1：有效 0：冻结 2：注销）<br/>
     * merchant_user.status
     *
     * @param status 值对应 merchant_user.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 登录错误次数<br/>
     * 返回值对应的表列名 merchant_user.login_errors_times
     *
     * @return 返回值对应 merchant_user.login_errors_times
     *
     * @mbg.generated
     */
    public Integer getLoginErrorsTimes() {
        return loginErrorsTimes;
    }

    /**
     * 登录错误次数<br/>
     * merchant_user.login_errors_times
     *
     * @param loginErrorsTimes 值对应 merchant_user.login_errors_times
     *
     * @mbg.generated
     */
    public void setLoginErrorsTimes(Integer loginErrorsTimes) {
        this.loginErrorsTimes = loginErrorsTimes;
    }

    /**
     * 登录ip地址<br/>
     * 返回值对应的表列名 merchant_user.login_ip
     *
     * @return 返回值对应 merchant_user.login_ip
     *
     * @mbg.generated
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 登录ip地址<br/>
     * merchant_user.login_ip
     *
     * @param loginIp 值对应 merchant_user.login_ip
     *
     * @mbg.generated
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 merchant_user.create_time
     *
     * @return 返回值对应 merchant_user.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * merchant_user.create_time
     *
     * @param createTime 值对应 merchant_user.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 merchant_user.creator
     *
     * @return 返回值对应 merchant_user.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * merchant_user.creator
     *
     * @param creator 值对应 merchant_user.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 merchant_user.last_modify_time
     *
     * @return 返回值对应 merchant_user.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * 最后修改时间<br/>
     * merchant_user.last_modify_time
     *
     * @param lastModifyTime 值对应 merchant_user.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 merchant_user.modifier
     *
     * @return 返回值对应 merchant_user.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * merchant_user.modifier
     *
     * @param modifier 值对应 merchant_user.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 备注<br/>
     * 返回值对应的表列名 merchant_user.remark
     *
     * @return 返回值对应 merchant_user.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * merchant_user.remark
     *
     * @param remark 值对应 merchant_user.remark
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
        MerchantUser other = (MerchantUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantUserCode() == null ? other.getMerchantUserCode() == null : this.getMerchantUserCode().equals(other.getMerchantUserCode()))
            && (this.getMerchantCode() == null ? other.getMerchantCode() == null : this.getMerchantCode().equals(other.getMerchantCode()))
            && (this.getMerchantUserName() == null ? other.getMerchantUserName() == null : this.getMerchantUserName().equals(other.getMerchantUserName()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
            && (this.getPostName() == null ? other.getPostName() == null : this.getPostName().equals(other.getPostName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getLoginErrorsTimes() == null ? other.getLoginErrorsTimes() == null : this.getLoginErrorsTimes().equals(other.getLoginErrorsTimes()))
            && (this.getLoginIp() == null ? other.getLoginIp() == null : this.getLoginIp().equals(other.getLoginIp()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMerchantUserCode() == null) ? 0 : getMerchantUserCode().hashCode());
        result = prime * result + ((getMerchantCode() == null) ? 0 : getMerchantCode().hashCode());
        result = prime * result + ((getMerchantUserName() == null) ? 0 : getMerchantUserName().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getPostName() == null) ? 0 : getPostName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getLoginErrorsTimes() == null) ? 0 : getLoginErrorsTimes().hashCode());
        result = prime * result + ((getLoginIp() == null) ? 0 : getLoginIp().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
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
        sb.append(", merchantUserCode=").append(merchantUserCode);
        sb.append(", merchantCode=").append(merchantCode);
        sb.append(", merchantUserName=").append(merchantUserName);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", email=").append(email);
        sb.append(", deptId=").append(deptId);
        sb.append(", postName=").append(postName);
        sb.append(", status=").append(status);
        sb.append(", loginErrorsTimes=").append(loginErrorsTimes);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}