package com.azz.login.pojo;

import java.io.Serializable;
import java.util.Date;

public class PlatformUser implements Serializable {
    /**
     * ����id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * �û�id
     *
     * @mbg.generated
     */
    private String userCode;

    /**
     * �û�����
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * �û��ǳ�
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     * �ֻ�����
     *
     * @mbg.generated
     */
    private Integer phoneNumber;

    /**
     * ����
     *
     * @mbg.generated
     */
    private String password;

    /**
     * ��
     *
     * @mbg.generated
     */
    private String salt;

    /**
     * ����
     *
     * @mbg.generated
     */
    private String email;

    /**
     * �Ա�(0���� 1��Ů 2��δ֪)
     *
     * @mbg.generated
     */
    private Integer gender;

    /**
     * ����id
     *
     * @mbg.generated
     */
    private Long deptId;

    /**
     * ��λ����
     *
     * @mbg.generated
     */
    private String postName;

    /**
     * ״̬��1����Ч 0������ 2��ע����
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * ��¼�������
     *
     * @mbg.generated
     */
    private Integer loginErrorsTimes;

    /**
     * ��¼ip��ַ
     *
     * @mbg.generated
     */
    private String loginIp;

    /**
     * ����ʱ��
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * ����޸�ʱ��
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     * �޸���
     *
     * @mbg.generated
     */
    private String modifier;

    /**
     * ��ע
     *
     * @mbg.generated
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * ����id<br/>
     * ����ֵ��Ӧ�ı����� platform_user.id
     *
     * @return ����ֵ��Ӧ platform_user.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * ����id<br/>
     * platform_user.id
     *
     * @param id ֵ��Ӧ platform_user.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * �û�id<br/>
     * ����ֵ��Ӧ�ı����� platform_user.user_code
     *
     * @return ����ֵ��Ӧ platform_user.user_code
     *
     * @mbg.generated
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * �û�id<br/>
     * platform_user.user_code
     *
     * @param userCode ֵ��Ӧ platform_user.user_code
     *
     * @mbg.generated
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * �û�����<br/>
     * ����ֵ��Ӧ�ı����� platform_user.user_name
     *
     * @return ����ֵ��Ӧ platform_user.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * �û�����<br/>
     * platform_user.user_name
     *
     * @param userName ֵ��Ӧ platform_user.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * �û��ǳ�<br/>
     * ����ֵ��Ӧ�ı����� platform_user.nickname
     *
     * @return ����ֵ��Ӧ platform_user.nickname
     *
     * @mbg.generated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * �û��ǳ�<br/>
     * platform_user.nickname
     *
     * @param nickname ֵ��Ӧ platform_user.nickname
     *
     * @mbg.generated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * �ֻ�����<br/>
     * ����ֵ��Ӧ�ı����� platform_user.phone_number
     *
     * @return ����ֵ��Ӧ platform_user.phone_number
     *
     * @mbg.generated
     */
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * �ֻ�����<br/>
     * platform_user.phone_number
     *
     * @param phoneNumber ֵ��Ӧ platform_user.phone_number
     *
     * @mbg.generated
     */
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * ����<br/>
     * ����ֵ��Ӧ�ı����� platform_user.password
     *
     * @return ����ֵ��Ӧ platform_user.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * ����<br/>
     * platform_user.password
     *
     * @param password ֵ��Ӧ platform_user.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * ��<br/>
     * ����ֵ��Ӧ�ı����� platform_user.salt
     *
     * @return ����ֵ��Ӧ platform_user.salt
     *
     * @mbg.generated
     */
    public String getSalt() {
        return salt;
    }

    /**
     * ��<br/>
     * platform_user.salt
     *
     * @param salt ֵ��Ӧ platform_user.salt
     *
     * @mbg.generated
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * ����<br/>
     * ����ֵ��Ӧ�ı����� platform_user.email
     *
     * @return ����ֵ��Ӧ platform_user.email
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * ����<br/>
     * platform_user.email
     *
     * @param email ֵ��Ӧ platform_user.email
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * �Ա�(0���� 1��Ů 2��δ֪)<br/>
     * ����ֵ��Ӧ�ı����� platform_user.gender
     *
     * @return ����ֵ��Ӧ platform_user.gender
     *
     * @mbg.generated
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * �Ա�(0���� 1��Ů 2��δ֪)<br/>
     * platform_user.gender
     *
     * @param gender ֵ��Ӧ platform_user.gender
     *
     * @mbg.generated
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * ����id<br/>
     * ����ֵ��Ӧ�ı����� platform_user.dept_id
     *
     * @return ����ֵ��Ӧ platform_user.dept_id
     *
     * @mbg.generated
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * ����id<br/>
     * platform_user.dept_id
     *
     * @param deptId ֵ��Ӧ platform_user.dept_id
     *
     * @mbg.generated
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * ��λ����<br/>
     * ����ֵ��Ӧ�ı����� platform_user.post_name
     *
     * @return ����ֵ��Ӧ platform_user.post_name
     *
     * @mbg.generated
     */
    public String getPostName() {
        return postName;
    }

    /**
     * ��λ����<br/>
     * platform_user.post_name
     *
     * @param postName ֵ��Ӧ platform_user.post_name
     *
     * @mbg.generated
     */
    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    /**
     * ״̬��1����Ч 0������ 2��ע����<br/>
     * ����ֵ��Ӧ�ı����� platform_user.status
     *
     * @return ����ֵ��Ӧ platform_user.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * ״̬��1����Ч 0������ 2��ע����<br/>
     * platform_user.status
     *
     * @param status ֵ��Ӧ platform_user.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * ��¼�������<br/>
     * ����ֵ��Ӧ�ı����� platform_user.login_errors_times
     *
     * @return ����ֵ��Ӧ platform_user.login_errors_times
     *
     * @mbg.generated
     */
    public Integer getLoginErrorsTimes() {
        return loginErrorsTimes;
    }

    /**
     * ��¼�������<br/>
     * platform_user.login_errors_times
     *
     * @param loginErrorsTimes ֵ��Ӧ platform_user.login_errors_times
     *
     * @mbg.generated
     */
    public void setLoginErrorsTimes(Integer loginErrorsTimes) {
        this.loginErrorsTimes = loginErrorsTimes;
    }

    /**
     * ��¼ip��ַ<br/>
     * ����ֵ��Ӧ�ı����� platform_user.login_ip
     *
     * @return ����ֵ��Ӧ platform_user.login_ip
     *
     * @mbg.generated
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * ��¼ip��ַ<br/>
     * platform_user.login_ip
     *
     * @param loginIp ֵ��Ӧ platform_user.login_ip
     *
     * @mbg.generated
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    /**
     * ����ʱ��<br/>
     * ����ֵ��Ӧ�ı����� platform_user.create_time
     *
     * @return ����ֵ��Ӧ platform_user.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * ����ʱ��<br/>
     * platform_user.create_time
     *
     * @param createTime ֵ��Ӧ platform_user.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * ����޸�ʱ��<br/>
     * ����ֵ��Ӧ�ı����� platform_user.last_modify_time
     *
     * @return ����ֵ��Ӧ platform_user.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * ����޸�ʱ��<br/>
     * platform_user.last_modify_time
     *
     * @param lastModifyTime ֵ��Ӧ platform_user.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * �޸���<br/>
     * ����ֵ��Ӧ�ı����� platform_user.modifier
     *
     * @return ����ֵ��Ӧ platform_user.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * �޸���<br/>
     * platform_user.modifier
     *
     * @param modifier ֵ��Ӧ platform_user.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * ��ע<br/>
     * ����ֵ��Ӧ�ı����� platform_user.remark
     *
     * @return ����ֵ��Ӧ platform_user.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * ��ע<br/>
     * platform_user.remark
     *
     * @param remark ֵ��Ӧ platform_user.remark
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
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}