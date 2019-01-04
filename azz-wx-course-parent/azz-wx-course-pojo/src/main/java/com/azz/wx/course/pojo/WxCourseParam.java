package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.util.Date;

public class WxCourseParam implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 参数编号
     *
     * @mbg.generated
     */
    private String paramCode;

    /**
     * 所属分类编码
     *
     * @mbg.generated
     */
    private String classificationCode;

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
     * 返回值对应的表列名 wx_course_param.id
     *
     * @return 返回值对应 wx_course_param.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_course_param.id
     *
     * @param id 值对应 wx_course_param.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 参数编号<br/>
     * 返回值对应的表列名 wx_course_param.param_code
     *
     * @return 返回值对应 wx_course_param.param_code
     *
     * @mbg.generated
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 参数编号<br/>
     * wx_course_param.param_code
     *
     * @param paramCode 值对应 wx_course_param.param_code
     *
     * @mbg.generated
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    /**
     * 所属分类编码<br/>
     * 返回值对应的表列名 wx_course_param.classification_code
     *
     * @return 返回值对应 wx_course_param.classification_code
     *
     * @mbg.generated
     */
    public String getClassificationCode() {
        return classificationCode;
    }

    /**
     * 所属分类编码<br/>
     * wx_course_param.classification_code
     *
     * @param classificationCode 值对应 wx_course_param.classification_code
     *
     * @mbg.generated
     */
    public void setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode == null ? null : classificationCode.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course_param.creator
     *
     * @return 返回值对应 wx_course_param.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_course_param.creator
     *
     * @param creator 值对应 wx_course_param.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 wx_course_param.create_time
     *
     * @return 返回值对应 wx_course_param.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * wx_course_param.create_time
     *
     * @param createTime 值对应 wx_course_param.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_course_param.modifier
     *
     * @return 返回值对应 wx_course_param.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_course_param.modifier
     *
     * @param modifier 值对应 wx_course_param.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_course_param.modify_time
     *
     * @return 返回值对应 wx_course_param.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_course_param.modify_time
     *
     * @param modifyTime 值对应 wx_course_param.modify_time
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
        WxCourseParam other = (WxCourseParam) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParamCode() == null ? other.getParamCode() == null : this.getParamCode().equals(other.getParamCode()))
            && (this.getClassificationCode() == null ? other.getClassificationCode() == null : this.getClassificationCode().equals(other.getClassificationCode()))
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
        result = prime * result + ((getParamCode() == null) ? 0 : getParamCode().hashCode());
        result = prime * result + ((getClassificationCode() == null) ? 0 : getClassificationCode().hashCode());
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
        sb.append(", paramCode=").append(paramCode);
        sb.append(", classificationCode=").append(classificationCode);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}