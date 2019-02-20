package com.azz.wx.course.pojo;


import java.io.Serializable;
import java.util.Date;

public class WxCourseSuggestions implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 用户编码
     *
     * @mbg.generated
     */
    private String userCode;

    /**
     * 微信openid
     *
     * @mbg.generated
     */
    private String openid;

    /**
     * 问题类型
     *
     * @mbg.generated
     */
    private String questionType;

    /**
     * 联系方式
     *
     * @mbg.generated
     */
    private String contact;

    /**
     * 问题描述
     *
     * @mbg.generated
     */
    private String questionDescription;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 状态（1：有效 0：无效）
     *
     * @mbg.generated
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 wx_course_suggestions.id
     *
     * @return 返回值对应 wx_course_suggestions.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_course_suggestions.id
     *
     * @param id 值对应 wx_course_suggestions.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户编码<br/>
     * 返回值对应的表列名 wx_course_suggestions.user_code
     *
     * @return 返回值对应 wx_course_suggestions.user_code
     *
     * @mbg.generated
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 用户编码<br/>
     * wx_course_suggestions.user_code
     *
     * @param userCode 值对应 wx_course_suggestions.user_code
     *
     * @mbg.generated
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * 微信openid<br/>
     * 返回值对应的表列名 wx_course_suggestions.openid
     *
     * @return 返回值对应 wx_course_suggestions.openid
     *
     * @mbg.generated
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 微信openid<br/>
     * wx_course_suggestions.openid
     *
     * @param openid 值对应 wx_course_suggestions.openid
     *
     * @mbg.generated
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 问题类型<br/>
     * 返回值对应的表列名 wx_course_suggestions.question_type
     *
     * @return 返回值对应 wx_course_suggestions.question_type
     *
     * @mbg.generated
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * 问题类型<br/>
     * wx_course_suggestions.question_type
     *
     * @param questionType 值对应 wx_course_suggestions.question_type
     *
     * @mbg.generated
     */
    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }

    /**
     * 联系方式<br/>
     * 返回值对应的表列名 wx_course_suggestions.contact
     *
     * @return 返回值对应 wx_course_suggestions.contact
     *
     * @mbg.generated
     */
    public String getContact() {
        return contact;
    }

    /**
     * 联系方式<br/>
     * wx_course_suggestions.contact
     *
     * @param contact 值对应 wx_course_suggestions.contact
     *
     * @mbg.generated
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * 问题描述<br/>
     * 返回值对应的表列名 wx_course_suggestions.question_description
     *
     * @return 返回值对应 wx_course_suggestions.question_description
     *
     * @mbg.generated
     */
    public String getQuestionDescription() {
        return questionDescription;
    }

    /**
     * 问题描述<br/>
     * wx_course_suggestions.question_description
     *
     * @param questionDescription 值对应 wx_course_suggestions.question_description
     *
     * @mbg.generated
     */
    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription == null ? null : questionDescription.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 wx_course_suggestions.create_time
     *
     * @return 返回值对应 wx_course_suggestions.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * wx_course_suggestions.create_time
     *
     * @param createTime 值对应 wx_course_suggestions.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 状态（1：有效 0：无效）<br/>
     * 返回值对应的表列名 wx_course_suggestions.status
     *
     * @return 返回值对应 wx_course_suggestions.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 状态（1：有效 0：无效）<br/>
     * wx_course_suggestions.status
     *
     * @param status 值对应 wx_course_suggestions.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
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
        WxCourseSuggestions other = (WxCourseSuggestions) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getQuestionType() == null ? other.getQuestionType() == null : this.getQuestionType().equals(other.getQuestionType()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getQuestionDescription() == null ? other.getQuestionDescription() == null : this.getQuestionDescription().equals(other.getQuestionDescription()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getQuestionType() == null) ? 0 : getQuestionType().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getQuestionDescription() == null) ? 0 : getQuestionDescription().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", openid=").append(openid);
        sb.append(", questionType=").append(questionType);
        sb.append(", contact=").append(contact);
        sb.append(", questionDescription=").append(questionDescription);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}