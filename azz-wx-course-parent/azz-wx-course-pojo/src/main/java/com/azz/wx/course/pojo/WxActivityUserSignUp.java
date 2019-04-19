package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WxActivityUserSignUp implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 活动编码
     *
     * @mbg.generated
     */
    private String activityCode;

    /**
     * 微信用户的openid
     *
     * @mbg.generated
     */
    private String openid;

    /**
     * 微信昵称
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     * 微信头像地址
     *
     * @mbg.generated
     */
    private String headImageUrl;

    /**
     * 姓名
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * 手机号
     *
     * @mbg.generated
     */
    private String phoneNumber;

    /**
     * 公司名称
     *
     * @mbg.generated
     */
    private String companyName;

    /**
     * 职位
     *
     * @mbg.generated
     */
    private String position;

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

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 wx_activity_user_sign_up.id
     *
     * @return 返回值对应 wx_activity_user_sign_up.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_activity_user_sign_up.id
     *
     * @param id 值对应 wx_activity_user_sign_up.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 活动编码<br/>
     * 返回值对应的表列名 wx_activity_user_sign_up.activity_code
     *
     * @return 返回值对应 wx_activity_user_sign_up.activity_code
     *
     * @mbg.generated
     */
    public String getActivityCode() {
        return activityCode;
    }

    /**
     * 活动编码<br/>
     * wx_activity_user_sign_up.activity_code
     *
     * @param activityCode 值对应 wx_activity_user_sign_up.activity_code
     *
     * @mbg.generated
     */
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode == null ? null : activityCode.trim();
    }

    /**
     * 微信用户的openid<br/>
     * 返回值对应的表列名 wx_activity_user_sign_up.openid
     *
     * @return 返回值对应 wx_activity_user_sign_up.openid
     *
     * @mbg.generated
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 微信用户的openid<br/>
     * wx_activity_user_sign_up.openid
     *
     * @param openid 值对应 wx_activity_user_sign_up.openid
     *
     * @mbg.generated
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 微信昵称<br/>
     * 返回值对应的表列名 wx_activity_user_sign_up.nickname
     *
     * @return 返回值对应 wx_activity_user_sign_up.nickname
     *
     * @mbg.generated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 微信昵称<br/>
     * wx_activity_user_sign_up.nickname
     *
     * @param nickname 值对应 wx_activity_user_sign_up.nickname
     *
     * @mbg.generated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 微信头像地址<br/>
     * 返回值对应的表列名 wx_activity_user_sign_up.head_image_url
     *
     * @return 返回值对应 wx_activity_user_sign_up.head_image_url
     *
     * @mbg.generated
     */
    public String getHeadImageUrl() {
        return headImageUrl;
    }

    /**
     * 微信头像地址<br/>
     * wx_activity_user_sign_up.head_image_url
     *
     * @param headImageUrl 值对应 wx_activity_user_sign_up.head_image_url
     *
     * @mbg.generated
     */
    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl == null ? null : headImageUrl.trim();
    }

    /**
     * 姓名<br/>
     * 返回值对应的表列名 wx_activity_user_sign_up.user_name
     *
     * @return 返回值对应 wx_activity_user_sign_up.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 姓名<br/>
     * wx_activity_user_sign_up.user_name
     *
     * @param userName 值对应 wx_activity_user_sign_up.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 手机号<br/>
     * 返回值对应的表列名 wx_activity_user_sign_up.phone_number
     *
     * @return 返回值对应 wx_activity_user_sign_up.phone_number
     *
     * @mbg.generated
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 手机号<br/>
     * wx_activity_user_sign_up.phone_number
     *
     * @param phoneNumber 值对应 wx_activity_user_sign_up.phone_number
     *
     * @mbg.generated
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * 公司名称<br/>
     * 返回值对应的表列名 wx_activity_user_sign_up.company_name
     *
     * @return 返回值对应 wx_activity_user_sign_up.company_name
     *
     * @mbg.generated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 公司名称<br/>
     * wx_activity_user_sign_up.company_name
     *
     * @param companyName 值对应 wx_activity_user_sign_up.company_name
     *
     * @mbg.generated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 职位<br/>
     * 返回值对应的表列名 wx_activity_user_sign_up.position
     *
     * @return 返回值对应 wx_activity_user_sign_up.position
     *
     * @mbg.generated
     */
    public String getPosition() {
        return position;
    }

    /**
     * 职位<br/>
     * wx_activity_user_sign_up.position
     *
     * @param position 值对应 wx_activity_user_sign_up.position
     *
     * @mbg.generated
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 wx_activity_user_sign_up.create_time
     *
     * @return 返回值对应 wx_activity_user_sign_up.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * wx_activity_user_sign_up.create_time
     *
     * @param createTime 值对应 wx_activity_user_sign_up.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_activity_user_sign_up.creator
     *
     * @return 返回值对应 wx_activity_user_sign_up.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_activity_user_sign_up.creator
     *
     * @param creator 值对应 wx_activity_user_sign_up.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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
        WxActivityUserSignUp other = (WxActivityUserSignUp) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getActivityCode() == null ? other.getActivityCode() == null : this.getActivityCode().equals(other.getActivityCode()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getHeadImageUrl() == null ? other.getHeadImageUrl() == null : this.getHeadImageUrl().equals(other.getHeadImageUrl()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getActivityCode() == null) ? 0 : getActivityCode().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getHeadImageUrl() == null) ? 0 : getHeadImageUrl().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", activityCode=").append(activityCode);
        sb.append(", openid=").append(openid);
        sb.append(", nickname=").append(nickname);
        sb.append(", headImageUrl=").append(headImageUrl);
        sb.append(", userName=").append(userName);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", companyName=").append(companyName);
        sb.append(", position=").append(position);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}