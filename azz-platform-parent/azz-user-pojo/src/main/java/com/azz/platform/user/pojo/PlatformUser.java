package com.azz.platform.user.pojo;

import java.io.Serializable;
import java.util.Date;

public class PlatformUser implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 用户id
     *
     * @mbg.generated
     */
    private String userCode;

    /**
     * 用户姓名
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * 用户昵称
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     * 手机号码
     *
     * @mbg.generated
     */
    private Integer phoneNumber;

    /**
     * 密码
     *
     * @mbg.generated
     */
    private String password;

    /**
     * �?
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
     * 性别(0：男 1：女 2：未�?)
     *
     * @mbg.generated
     */
    private Integer gender;

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
     * 状�?�（1：有�? 0：冻�? 2：注�?�?
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
     * 创建�?
     *
     * @mbg.generated
     */
    private String creator;

    /**
     * �?后修改时�?
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     * 修改�?
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
     * 返回值对应的表列�? platform_user.id
     *
     * @return 返回值对�? platform_user.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * platform_user.id
     *
     * @param id 值对�? platform_user.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户id<br/>
     * 返回值对应的表列�? platform_user.user_code
     *
     * @return 返回值对�? platform_user.user_code
     *
     * @mbg.generated
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 用户id<br/>
     * platform_user.user_code
     *
     * @param userCode 值对�? platform_user.user_code
     *
     * @mbg.generated
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * 用户姓名<br/>
     * 返回值对应的表列�? platform_user.user_name
     *
     * @return 返回值对�? platform_user.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户姓名<br/>
     * platform_user.user_name
     *
     * @param userName 值对�? platform_user.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 用户昵称<br/>
     * 返回值对应的表列�? platform_user.nickname
     *
     * @return 返回值对�? platform_user.nickname
     *
     * @mbg.generated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 用户昵称<br/>
     * platform_user.nickname
     *
     * @param nickname 值对�? platform_user.nickname
     *
     * @mbg.generated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 手机号码<br/>
     * 返回值对应的表列�? platform_user.phone_number
     *
     * @return 返回值对�? platform_user.phone_number
     *
     * @mbg.generated
     */
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 手机号码<br/>
     * platform_user.phone_number
     *
     * @param phoneNumber 值对�? platform_user.phone_number
     *
     * @mbg.generated
     */
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 密码<br/>
     * 返回值对应的表列�? platform_user.password
     *
     * @return 返回值对�? platform_user.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码<br/>
     * platform_user.password
     *
     * @param password 值对�? platform_user.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * �?<br/>
     * 返回值对应的表列�? platform_user.salt
     *
     * @return 返回值对�? platform_user.salt
     *
     * @mbg.generated
     */
    public String getSalt() {
        return salt;
    }

    /**
     * �?<br/>
     * platform_user.salt
     *
     * @param salt 值对�? platform_user.salt
     *
     * @mbg.generated
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 邮箱<br/>
     * 返回值对应的表列�? platform_user.email
     *
     * @return 返回值对�? platform_user.email
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱<br/>
     * platform_user.email
     *
     * @param email 值对�? platform_user.email
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 性别(0：男 1：女 2：未�?)<br/>
     * 返回值对应的表列�? platform_user.gender
     *
     * @return 返回值对�? platform_user.gender
     *
     * @mbg.generated
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 性别(0：男 1：女 2：未�?)<br/>
     * platform_user.gender
     *
     * @param gender 值对�? platform_user.gender
     *
     * @mbg.generated
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 部门id<br/>
     * 返回值对应的表列�? platform_user.dept_id
     *
     * @return 返回值对�? platform_user.dept_id
     *
     * @mbg.generated
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 部门id<br/>
     * platform_user.dept_id
     *
     * @param deptId 值对�? platform_user.dept_id
     *
     * @mbg.generated
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 岗位名称<br/>
     * 返回值对应的表列�? platform_user.post_name
     *
     * @return 返回值对�? platform_user.post_name
     *
     * @mbg.generated
     */
    public String getPostName() {
        return postName;
    }

    /**
     * 岗位名称<br/>
     * platform_user.post_name
     *
     * @param postName 值对�? platform_user.post_name
     *
     * @mbg.generated
     */
    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    /**
     * 状�?�（1：有�? 0：冻�? 2：注�?�?<br/>
     * 返回值对应的表列�? platform_user.status
     *
     * @return 返回值对�? platform_user.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状�?�（1：有�? 0：冻�? 2：注�?�?<br/>
     * platform_user.status
     *
     * @param status 值对�? platform_user.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 登录错误次数<br/>
     * 返回值对应的表列�? platform_user.login_errors_times
     *
     * @return 返回值对�? platform_user.login_errors_times
     *
     * @mbg.generated
     */
    public Integer getLoginErrorsTimes() {
        return loginErrorsTimes;
    }

    /**
     * 登录错误次数<br/>
     * platform_user.login_errors_times
     *
     * @param loginErrorsTimes 值对�? platform_user.login_errors_times
     *
     * @mbg.generated
     */
    public void setLoginErrorsTimes(Integer loginErrorsTimes) {
        this.loginErrorsTimes = loginErrorsTimes;
    }

    /**
     * 登录ip地址<br/>
     * 返回值对应的表列�? platform_user.login_ip
     *
     * @return 返回值对�? platform_user.login_ip
     *
     * @mbg.generated
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 登录ip地址<br/>
     * platform_user.login_ip
     *
     * @param loginIp 值对�? platform_user.login_ip
     *
     * @mbg.generated
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列�? platform_user.create_time
     *
     * @return 返回值对�? platform_user.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * platform_user.create_time
     *
     * @param createTime 值对�? platform_user.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建�?<br/>
     * 返回值对应的表列�? platform_user.creator
     *
     * @return 返回值对�? platform_user.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建�?<br/>
     * platform_user.creator
     *
     * @param creator 值对�? platform_user.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * �?后修改时�?<br/>
     * 返回值对应的表列�? platform_user.last_modify_time
     *
     * @return 返回值对�? platform_user.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * �?后修改时�?<br/>
     * platform_user.last_modify_time
     *
     * @param lastModifyTime 值对�? platform_user.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * 修改�?<br/>
     * 返回值对应的表列�? platform_user.modifier
     *
     * @return 返回值对�? platform_user.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改�?<br/>
     * platform_user.modifier
     *
     * @param modifier 值对�? platform_user.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 备注<br/>
     * 返回值对应的表列�? platform_user.remark
     *
     * @return 返回值对�? platform_user.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * platform_user.remark
     *
     * @param remark 值对�? platform_user.remark
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
        PlatformUser other = (PlatformUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
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
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
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
        sb.append(", userCode=").append(userCode);
        sb.append(", userName=").append(userName);
        sb.append(", nickname=").append(nickname);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", email=").append(email);
        sb.append(", gender=").append(gender);
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