package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.util.Date;

public class WxCourseParamRel implements Serializable {
    /**
     *  主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 所属课程编码
     *
     * @mbg.generated
     */
    private String courseCode;

    /**
     * 所属参数编码
     *
     * @mbg.generated
     */
    private String paramCode;

    /**
     * 参数项名称
     *
     * @mbg.generated
     */
    private String paramName;

    /**
     * 所属参数项编码
     *
     * @mbg.generated
     */
    private String paramTermCode;

    /**
     * 参数值
     *
     * @mbg.generated
     */
    private String paramTermValue;

    /**
     * 参数项类型 1 下拉 2填写
     *
     * @mbg.generated
     */
    private Boolean paramType;

    /**
     * 是否必选 1必选  2非必选
     *
     * @mbg.generated
     */
    private Boolean paramChoice;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String creator;

    /**
     * 创建时间
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
     *  主键id<br/>
     * 返回值对应的表列名 wx_course_param_rel.id
     *
     * @return 返回值对应 wx_course_param_rel.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     *  主键id<br/>
     * wx_course_param_rel.id
     *
     * @param id 值对应 wx_course_param_rel.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 所属课程编码<br/>
     * 返回值对应的表列名 wx_course_param_rel.course_code
     *
     * @return 返回值对应 wx_course_param_rel.course_code
     *
     * @mbg.generated
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * 所属课程编码<br/>
     * wx_course_param_rel.course_code
     *
     * @param courseCode 值对应 wx_course_param_rel.course_code
     *
     * @mbg.generated
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }

    /**
     * 所属参数编码<br/>
     * 返回值对应的表列名 wx_course_param_rel.param_code
     *
     * @return 返回值对应 wx_course_param_rel.param_code
     *
     * @mbg.generated
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 所属参数编码<br/>
     * wx_course_param_rel.param_code
     *
     * @param paramCode 值对应 wx_course_param_rel.param_code
     *
     * @mbg.generated
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    /**
     * 参数项名称<br/>
     * 返回值对应的表列名 wx_course_param_rel.param_name
     *
     * @return 返回值对应 wx_course_param_rel.param_name
     *
     * @mbg.generated
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * 参数项名称<br/>
     * wx_course_param_rel.param_name
     *
     * @param paramName 值对应 wx_course_param_rel.param_name
     *
     * @mbg.generated
     */
    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    /**
     * 所属参数项编码<br/>
     * 返回值对应的表列名 wx_course_param_rel.param_term_code
     *
     * @return 返回值对应 wx_course_param_rel.param_term_code
     *
     * @mbg.generated
     */
    public String getParamTermCode() {
        return paramTermCode;
    }

    /**
     * 所属参数项编码<br/>
     * wx_course_param_rel.param_term_code
     *
     * @param paramTermCode 值对应 wx_course_param_rel.param_term_code
     *
     * @mbg.generated
     */
    public void setParamTermCode(String paramTermCode) {
        this.paramTermCode = paramTermCode == null ? null : paramTermCode.trim();
    }

    /**
     * 参数值<br/>
     * 返回值对应的表列名 wx_course_param_rel.param_term_value
     *
     * @return 返回值对应 wx_course_param_rel.param_term_value
     *
     * @mbg.generated
     */
    public String getParamTermValue() {
        return paramTermValue;
    }

    /**
     * 参数值<br/>
     * wx_course_param_rel.param_term_value
     *
     * @param paramTermValue 值对应 wx_course_param_rel.param_term_value
     *
     * @mbg.generated
     */
    public void setParamTermValue(String paramTermValue) {
        this.paramTermValue = paramTermValue == null ? null : paramTermValue.trim();
    }

    /**
     * 参数项类型 1 下拉 2填写<br/>
     * 返回值对应的表列名 wx_course_param_rel.param_type
     *
     * @return 返回值对应 wx_course_param_rel.param_type
     *
     * @mbg.generated
     */
    public Boolean getParamType() {
        return paramType;
    }

    /**
     * 参数项类型 1 下拉 2填写<br/>
     * wx_course_param_rel.param_type
     *
     * @param paramType 值对应 wx_course_param_rel.param_type
     *
     * @mbg.generated
     */
    public void setParamType(Boolean paramType) {
        this.paramType = paramType;
    }

    /**
     * 是否必选 1必选  2非必选<br/>
     * 返回值对应的表列名 wx_course_param_rel.param_choice
     *
     * @return 返回值对应 wx_course_param_rel.param_choice
     *
     * @mbg.generated
     */
    public Boolean getParamChoice() {
        return paramChoice;
    }

    /**
     * 是否必选 1必选  2非必选<br/>
     * wx_course_param_rel.param_choice
     *
     * @param paramChoice 值对应 wx_course_param_rel.param_choice
     *
     * @mbg.generated
     */
    public void setParamChoice(Boolean paramChoice) {
        this.paramChoice = paramChoice;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course_param_rel.creator
     *
     * @return 返回值对应 wx_course_param_rel.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_course_param_rel.creator
     *
     * @param creator 值对应 wx_course_param_rel.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 wx_course_param_rel.create_time
     *
     * @return 返回值对应 wx_course_param_rel.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * wx_course_param_rel.create_time
     *
     * @param createTime 值对应 wx_course_param_rel.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_course_param_rel.modifier
     *
     * @return 返回值对应 wx_course_param_rel.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_course_param_rel.modifier
     *
     * @param modifier 值对应 wx_course_param_rel.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_course_param_rel.modify_time
     *
     * @return 返回值对应 wx_course_param_rel.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_course_param_rel.modify_time
     *
     * @param modifyTime 值对应 wx_course_param_rel.modify_time
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
        WxCourseParamRel other = (WxCourseParamRel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCourseCode() == null ? other.getCourseCode() == null : this.getCourseCode().equals(other.getCourseCode()))
            && (this.getParamCode() == null ? other.getParamCode() == null : this.getParamCode().equals(other.getParamCode()))
            && (this.getParamName() == null ? other.getParamName() == null : this.getParamName().equals(other.getParamName()))
            && (this.getParamTermCode() == null ? other.getParamTermCode() == null : this.getParamTermCode().equals(other.getParamTermCode()))
            && (this.getParamTermValue() == null ? other.getParamTermValue() == null : this.getParamTermValue().equals(other.getParamTermValue()))
            && (this.getParamType() == null ? other.getParamType() == null : this.getParamType().equals(other.getParamType()))
            && (this.getParamChoice() == null ? other.getParamChoice() == null : this.getParamChoice().equals(other.getParamChoice()))
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
        result = prime * result + ((getCourseCode() == null) ? 0 : getCourseCode().hashCode());
        result = prime * result + ((getParamCode() == null) ? 0 : getParamCode().hashCode());
        result = prime * result + ((getParamName() == null) ? 0 : getParamName().hashCode());
        result = prime * result + ((getParamTermCode() == null) ? 0 : getParamTermCode().hashCode());
        result = prime * result + ((getParamTermValue() == null) ? 0 : getParamTermValue().hashCode());
        result = prime * result + ((getParamType() == null) ? 0 : getParamType().hashCode());
        result = prime * result + ((getParamChoice() == null) ? 0 : getParamChoice().hashCode());
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
        sb.append(", courseCode=").append(courseCode);
        sb.append(", paramCode=").append(paramCode);
        sb.append(", paramName=").append(paramName);
        sb.append(", paramTermCode=").append(paramTermCode);
        sb.append(", paramTermValue=").append(paramTermValue);
        sb.append(", paramType=").append(paramType);
        sb.append(", paramChoice=").append(paramChoice);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}