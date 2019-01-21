package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.util.Date;

public class WxCourseEvaluation implements Serializable {
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
     * 微信用户编码
     *
     * @mbg.generated
     */
    private String wxUserCode;

    /**
     * 所属课程编码
     *
     * @mbg.generated
     */
    private String courseCode;

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
     * 返回值对应的表列名 wx_course_evaluation.id
     *
     * @return 返回值对应 wx_course_evaluation.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_course_evaluation.id
     *
     * @param id 值对应 wx_course_evaluation.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 评价编码<br/>
     * 返回值对应的表列名 wx_course_evaluation.evaluation_code
     *
     * @return 返回值对应 wx_course_evaluation.evaluation_code
     *
     * @mbg.generated
     */
    public String getEvaluationCode() {
        return evaluationCode;
    }

    /**
     * 评价编码<br/>
     * wx_course_evaluation.evaluation_code
     *
     * @param evaluationCode 值对应 wx_course_evaluation.evaluation_code
     *
     * @mbg.generated
     */
    public void setEvaluationCode(String evaluationCode) {
        this.evaluationCode = evaluationCode == null ? null : evaluationCode.trim();
    }

    /**
     * 微信用户编码<br/>
     * 返回值对应的表列名 wx_course_evaluation.wx_user_code
     *
     * @return 返回值对应 wx_course_evaluation.wx_user_code
     *
     * @mbg.generated
     */
    public String getWxUserCode() {
        return wxUserCode;
    }

    /**
     * 微信用户编码<br/>
     * wx_course_evaluation.wx_user_code
     *
     * @param wxUserCode 值对应 wx_course_evaluation.wx_user_code
     *
     * @mbg.generated
     */
    public void setWxUserCode(String wxUserCode) {
        this.wxUserCode = wxUserCode == null ? null : wxUserCode.trim();
    }

    /**
     * 所属课程编码<br/>
     * 返回值对应的表列名 wx_course_evaluation.course_code
     *
     * @return 返回值对应 wx_course_evaluation.course_code
     *
     * @mbg.generated
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * 所属课程编码<br/>
     * wx_course_evaluation.course_code
     *
     * @param courseCode 值对应 wx_course_evaluation.course_code
     *
     * @mbg.generated
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }

    /**
     * 评分 1-5的整数<br/>
     * 返回值对应的表列名 wx_course_evaluation.grade
     *
     * @return 返回值对应 wx_course_evaluation.grade
     *
     * @mbg.generated
     */
    public Byte getGrade() {
        return grade;
    }

    /**
     * 评分 1-5的整数<br/>
     * wx_course_evaluation.grade
     *
     * @param grade 值对应 wx_course_evaluation.grade
     *
     * @mbg.generated
     */
    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    /**
     * 评价内容<br/>
     * 返回值对应的表列名 wx_course_evaluation.evaluation_content
     *
     * @return 返回值对应 wx_course_evaluation.evaluation_content
     *
     * @mbg.generated
     */
    public String getEvaluationContent() {
        return evaluationContent;
    }

    /**
     * 评价内容<br/>
     * wx_course_evaluation.evaluation_content
     *
     * @param evaluationContent 值对应 wx_course_evaluation.evaluation_content
     *
     * @mbg.generated
     */
    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent == null ? null : evaluationContent.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course_evaluation.creator
     *
     * @return 返回值对应 wx_course_evaluation.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_course_evaluation.creator
     *
     * @param creator 值对应 wx_course_evaluation.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course_evaluation.create_time
     *
     * @return 返回值对应 wx_course_evaluation.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建人<br/>
     * wx_course_evaluation.create_time
     *
     * @param createTime 值对应 wx_course_evaluation.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_course_evaluation.modifier
     *
     * @return 返回值对应 wx_course_evaluation.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_course_evaluation.modifier
     *
     * @param modifier 值对应 wx_course_evaluation.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_course_evaluation.modify_time
     *
     * @return 返回值对应 wx_course_evaluation.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_course_evaluation.modify_time
     *
     * @param modifyTime 值对应 wx_course_evaluation.modify_time
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
        WxCourseEvaluation other = (WxCourseEvaluation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEvaluationCode() == null ? other.getEvaluationCode() == null : this.getEvaluationCode().equals(other.getEvaluationCode()))
            && (this.getWxUserCode() == null ? other.getWxUserCode() == null : this.getWxUserCode().equals(other.getWxUserCode()))
            && (this.getCourseCode() == null ? other.getCourseCode() == null : this.getCourseCode().equals(other.getCourseCode()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getEvaluationContent() == null ? other.getEvaluationContent() == null : this.getEvaluationContent().equals(other.getEvaluationContent()))
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
        result = prime * result + ((getWxUserCode() == null) ? 0 : getWxUserCode().hashCode());
        result = prime * result + ((getCourseCode() == null) ? 0 : getCourseCode().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getEvaluationContent() == null) ? 0 : getEvaluationContent().hashCode());
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
        sb.append(", wxUserCode=").append(wxUserCode);
        sb.append(", courseCode=").append(courseCode);
        sb.append(", grade=").append(grade);
        sb.append(", evaluationContent=").append(evaluationContent);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}