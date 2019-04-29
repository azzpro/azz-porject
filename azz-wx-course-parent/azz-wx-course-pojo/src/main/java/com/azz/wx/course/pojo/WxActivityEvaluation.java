package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxActivityEvaluation implements Serializable {
	 /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 评价编码
     *
     * @mbg.generated
     */
    private String evaluationCode;

    /**
     * 微信openid
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
     * 所属活动编码
     *
     * @mbg.generated
     */
    private String activityCode;

    /**
     * 评分 1-5的整数
     *
     * @mbg.generated
     */
    private Byte grade;

    /**
     * 评价内容
     *
     * @mbg.generated
     */
    private String evaluationContent;

    /**
     * 是否置顶  1是 0否
     *
     * @mbg.generated
     */
    private Byte isShowOnTop;

    /**
     * 是否屏蔽 0否 1是
     *
     * @mbg.generated
     */
    private Byte isShield;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String creator;

    /**
     * 创建人
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
     * 修改时间
     *
     * @mbg.generated
     */
    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 wx_activity_evaluation.id
     *
     * @return 返回值对应 wx_activity_evaluation.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_activity_evaluation.id
     *
     * @param id 值对应 wx_activity_evaluation.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 评价编码<br/>
     * 返回值对应的表列名 wx_activity_evaluation.evaluation_code
     *
     * @return 返回值对应 wx_activity_evaluation.evaluation_code
     *
     * @mbg.generated
     */
    public String getEvaluationCode() {
        return evaluationCode;
    }

    /**
     * 评价编码<br/>
     * wx_activity_evaluation.evaluation_code
     *
     * @param evaluationCode 值对应 wx_activity_evaluation.evaluation_code
     *
     * @mbg.generated
     */
    public void setEvaluationCode(String evaluationCode) {
        this.evaluationCode = evaluationCode == null ? null : evaluationCode.trim();
    }

    /**
     * 微信openid<br/>
     * 返回值对应的表列名 wx_activity_evaluation.openid
     *
     * @return 返回值对应 wx_activity_evaluation.openid
     *
     * @mbg.generated
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 微信openid<br/>
     * wx_activity_evaluation.openid
     *
     * @param openid 值对应 wx_activity_evaluation.openid
     *
     * @mbg.generated
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 微信昵称<br/>
     * 返回值对应的表列名 wx_activity_evaluation.nickname
     *
     * @return 返回值对应 wx_activity_evaluation.nickname
     *
     * @mbg.generated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 微信昵称<br/>
     * wx_activity_evaluation.nickname
     *
     * @param nickname 值对应 wx_activity_evaluation.nickname
     *
     * @mbg.generated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 微信头像地址<br/>
     * 返回值对应的表列名 wx_activity_evaluation.head_image_url
     *
     * @return 返回值对应 wx_activity_evaluation.head_image_url
     *
     * @mbg.generated
     */
    public String getHeadImageUrl() {
        return headImageUrl;
    }

    /**
     * 微信头像地址<br/>
     * wx_activity_evaluation.head_image_url
     *
     * @param headImageUrl 值对应 wx_activity_evaluation.head_image_url
     *
     * @mbg.generated
     */
    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl == null ? null : headImageUrl.trim();
    }

    /**
     * 所属活动编码<br/>
     * 返回值对应的表列名 wx_activity_evaluation.activity_code
     *
     * @return 返回值对应 wx_activity_evaluation.activity_code
     *
     * @mbg.generated
     */
    public String getActivityCode() {
        return activityCode;
    }

    /**
     * 所属活动编码<br/>
     * wx_activity_evaluation.activity_code
     *
     * @param activityCode 值对应 wx_activity_evaluation.activity_code
     *
     * @mbg.generated
     */
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode == null ? null : activityCode.trim();
    }

    /**
     * 评分 1-5的整数<br/>
     * 返回值对应的表列名 wx_activity_evaluation.grade
     *
     * @return 返回值对应 wx_activity_evaluation.grade
     *
     * @mbg.generated
     */
    public Byte getGrade() {
        return grade;
    }

    /**
     * 评分 1-5的整数<br/>
     * wx_activity_evaluation.grade
     *
     * @param grade 值对应 wx_activity_evaluation.grade
     *
     * @mbg.generated
     */
    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    /**
     * 评价内容<br/>
     * 返回值对应的表列名 wx_activity_evaluation.evaluation_content
     *
     * @return 返回值对应 wx_activity_evaluation.evaluation_content
     *
     * @mbg.generated
     */
    public String getEvaluationContent() {
        return evaluationContent;
    }

    /**
     * 评价内容<br/>
     * wx_activity_evaluation.evaluation_content
     *
     * @param evaluationContent 值对应 wx_activity_evaluation.evaluation_content
     *
     * @mbg.generated
     */
    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent == null ? null : evaluationContent.trim();
    }

    /**
     * 是否置顶  1是 0否<br/>
     * 返回值对应的表列名 wx_activity_evaluation.is_show_on_top
     *
     * @return 返回值对应 wx_activity_evaluation.is_show_on_top
     *
     * @mbg.generated
     */
    public Byte getIsShowOnTop() {
        return isShowOnTop;
    }

    /**
     * 是否置顶  1是 0否<br/>
     * wx_activity_evaluation.is_show_on_top
     *
     * @param isShowOnTop 值对应 wx_activity_evaluation.is_show_on_top
     *
     * @mbg.generated
     */
    public void setIsShowOnTop(Byte isShowOnTop) {
        this.isShowOnTop = isShowOnTop;
    }

    /**
     * 是否屏蔽 0否 1是<br/>
     * 返回值对应的表列名 wx_activity_evaluation.is_shield
     *
     * @return 返回值对应 wx_activity_evaluation.is_shield
     *
     * @mbg.generated
     */
    public Byte getIsShield() {
        return isShield;
    }

    /**
     * 是否屏蔽 0否 1是<br/>
     * wx_activity_evaluation.is_shield
     *
     * @param isShield 值对应 wx_activity_evaluation.is_shield
     *
     * @mbg.generated
     */
    public void setIsShield(Byte isShield) {
        this.isShield = isShield;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_activity_evaluation.creator
     *
     * @return 返回值对应 wx_activity_evaluation.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_activity_evaluation.creator
     *
     * @param creator 值对应 wx_activity_evaluation.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_activity_evaluation.create_time
     *
     * @return 返回值对应 wx_activity_evaluation.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建人<br/>
     * wx_activity_evaluation.create_time
     *
     * @param createTime 值对应 wx_activity_evaluation.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_activity_evaluation.modifier
     *
     * @return 返回值对应 wx_activity_evaluation.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_activity_evaluation.modifier
     *
     * @param modifier 值对应 wx_activity_evaluation.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_activity_evaluation.modify_time
     *
     * @return 返回值对应 wx_activity_evaluation.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_activity_evaluation.modify_time
     *
     * @param modifyTime 值对应 wx_activity_evaluation.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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
        WxActivityEvaluation other = (WxActivityEvaluation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEvaluationCode() == null ? other.getEvaluationCode() == null : this.getEvaluationCode().equals(other.getEvaluationCode()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getHeadImageUrl() == null ? other.getHeadImageUrl() == null : this.getHeadImageUrl().equals(other.getHeadImageUrl()))
            && (this.getActivityCode() == null ? other.getActivityCode() == null : this.getActivityCode().equals(other.getActivityCode()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getEvaluationContent() == null ? other.getEvaluationContent() == null : this.getEvaluationContent().equals(other.getEvaluationContent()))
            && (this.getIsShowOnTop() == null ? other.getIsShowOnTop() == null : this.getIsShowOnTop().equals(other.getIsShowOnTop()))
            && (this.getIsShield() == null ? other.getIsShield() == null : this.getIsShield().equals(other.getIsShield()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEvaluationCode() == null) ? 0 : getEvaluationCode().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getHeadImageUrl() == null) ? 0 : getHeadImageUrl().hashCode());
        result = prime * result + ((getActivityCode() == null) ? 0 : getActivityCode().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getEvaluationContent() == null) ? 0 : getEvaluationContent().hashCode());
        result = prime * result + ((getIsShowOnTop() == null) ? 0 : getIsShowOnTop().hashCode());
        result = prime * result + ((getIsShield() == null) ? 0 : getIsShield().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", evaluationCode=").append(evaluationCode);
        sb.append(", openid=").append(openid);
        sb.append(", nickname=").append(nickname);
        sb.append(", headImageUrl=").append(headImageUrl);
        sb.append(", activityCode=").append(activityCode);
        sb.append(", grade=").append(grade);
        sb.append(", evaluationContent=").append(evaluationContent);
        sb.append(", isShowOnTop=").append(isShowOnTop);
        sb.append(", isShield=").append(isShield);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}