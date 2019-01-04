package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.util.Date;

public class WxCourseParamTermValue implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

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
    private String paramValue;

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
     * 主键id<br/>
     * 返回值对应的表列名 wx_course_param_term_value.id
     *
     * @return 返回值对应 wx_course_param_term_value.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_course_param_term_value.id
     *
     * @param id 值对应 wx_course_param_term_value.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 所属参数项编码<br/>
     * 返回值对应的表列名 wx_course_param_term_value.param_term_code
     *
     * @return 返回值对应 wx_course_param_term_value.param_term_code
     *
     * @mbg.generated
     */
    public String getParamTermCode() {
        return paramTermCode;
    }

    /**
     * 所属参数项编码<br/>
     * wx_course_param_term_value.param_term_code
     *
     * @param paramTermCode 值对应 wx_course_param_term_value.param_term_code
     *
     * @mbg.generated
     */
    public void setParamTermCode(String paramTermCode) {
        this.paramTermCode = paramTermCode == null ? null : paramTermCode.trim();
    }

    /**
     * 参数值<br/>
     * 返回值对应的表列名 wx_course_param_term_value.param_value
     *
     * @return 返回值对应 wx_course_param_term_value.param_value
     *
     * @mbg.generated
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 参数值<br/>
     * wx_course_param_term_value.param_value
     *
     * @param paramValue 值对应 wx_course_param_term_value.param_value
     *
     * @mbg.generated
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course_param_term_value.creator
     *
     * @return 返回值对应 wx_course_param_term_value.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_course_param_term_value.creator
     *
     * @param creator 值对应 wx_course_param_term_value.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 wx_course_param_term_value.create_time
     *
     * @return 返回值对应 wx_course_param_term_value.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * wx_course_param_term_value.create_time
     *
     * @param createTime 值对应 wx_course_param_term_value.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_course_param_term_value.modifier
     *
     * @return 返回值对应 wx_course_param_term_value.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_course_param_term_value.modifier
     *
     * @param modifier 值对应 wx_course_param_term_value.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_course_param_term_value.modify_time
     *
     * @return 返回值对应 wx_course_param_term_value.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_course_param_term_value.modify_time
     *
     * @param modifyTime 值对应 wx_course_param_term_value.modify_time
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
        WxCourseParamTermValue other = (WxCourseParamTermValue) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParamTermCode() == null ? other.getParamTermCode() == null : this.getParamTermCode().equals(other.getParamTermCode()))
            && (this.getParamValue() == null ? other.getParamValue() == null : this.getParamValue().equals(other.getParamValue()))
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
        result = prime * result + ((getParamTermCode() == null) ? 0 : getParamTermCode().hashCode());
        result = prime * result + ((getParamValue() == null) ? 0 : getParamValue().hashCode());
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
        sb.append(", paramTermCode=").append(paramTermCode);
        sb.append(", paramValue=").append(paramValue);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}