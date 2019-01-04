package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.util.Date;

public class WxCourseClassification implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 分类编码
     *
     * @mbg.generated
     */
    private String classificationCode;

    /**
     * 上级分类编号
     *
     * @mbg.generated
     */
    private String classificationParentCode;

    /**
     * 分类名称
     *
     * @mbg.generated
     */
    private String classificationName;

    /**
     * 分类层级  0 1 2
     *
     * @mbg.generated
     */
    private Boolean classificationTop;

    /**
     * 分类图片URL
     *
     * @mbg.generated
     */
    private String classificationPicUrl;

    /**
     * 分类图片名称
     *
     * @mbg.generated
     */
    private String classificationPicName;

    /**
     * 分类排序
     *
     * @mbg.generated
     */
    private Byte sort;

    /**
     * 状态(0:无效 1：有效)
     *
     * @mbg.generated
     */
    private Byte status;

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
     * 主键<br/>
     * 返回值对应的表列名 wx_course_classification.id
     *
     * @return 返回值对应 wx_course_classification.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * wx_course_classification.id
     *
     * @param id 值对应 wx_course_classification.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 分类编码<br/>
     * 返回值对应的表列名 wx_course_classification.classification_code
     *
     * @return 返回值对应 wx_course_classification.classification_code
     *
     * @mbg.generated
     */
    public String getClassificationCode() {
        return classificationCode;
    }

    /**
     * 分类编码<br/>
     * wx_course_classification.classification_code
     *
     * @param classificationCode 值对应 wx_course_classification.classification_code
     *
     * @mbg.generated
     */
    public void setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode == null ? null : classificationCode.trim();
    }

    /**
     * 上级分类编号<br/>
     * 返回值对应的表列名 wx_course_classification.classification_parent_code
     *
     * @return 返回值对应 wx_course_classification.classification_parent_code
     *
     * @mbg.generated
     */
    public String getClassificationParentCode() {
        return classificationParentCode;
    }

    /**
     * 上级分类编号<br/>
     * wx_course_classification.classification_parent_code
     *
     * @param classificationParentCode 值对应 wx_course_classification.classification_parent_code
     *
     * @mbg.generated
     */
    public void setClassificationParentCode(String classificationParentCode) {
        this.classificationParentCode = classificationParentCode == null ? null : classificationParentCode.trim();
    }

    /**
     * 分类名称<br/>
     * 返回值对应的表列名 wx_course_classification.classification_name
     *
     * @return 返回值对应 wx_course_classification.classification_name
     *
     * @mbg.generated
     */
    public String getClassificationName() {
        return classificationName;
    }

    /**
     * 分类名称<br/>
     * wx_course_classification.classification_name
     *
     * @param classificationName 值对应 wx_course_classification.classification_name
     *
     * @mbg.generated
     */
    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName == null ? null : classificationName.trim();
    }

    /**
     * 分类层级  0 1 2<br/>
     * 返回值对应的表列名 wx_course_classification.classification_top
     *
     * @return 返回值对应 wx_course_classification.classification_top
     *
     * @mbg.generated
     */
    public Boolean getClassificationTop() {
        return classificationTop;
    }

    /**
     * 分类层级  0 1 2<br/>
     * wx_course_classification.classification_top
     *
     * @param classificationTop 值对应 wx_course_classification.classification_top
     *
     * @mbg.generated
     */
    public void setClassificationTop(Boolean classificationTop) {
        this.classificationTop = classificationTop;
    }

    /**
     * 分类图片URL<br/>
     * 返回值对应的表列名 wx_course_classification.classification_pic_url
     *
     * @return 返回值对应 wx_course_classification.classification_pic_url
     *
     * @mbg.generated
     */
    public String getClassificationPicUrl() {
        return classificationPicUrl;
    }

    /**
     * 分类图片URL<br/>
     * wx_course_classification.classification_pic_url
     *
     * @param classificationPicUrl 值对应 wx_course_classification.classification_pic_url
     *
     * @mbg.generated
     */
    public void setClassificationPicUrl(String classificationPicUrl) {
        this.classificationPicUrl = classificationPicUrl == null ? null : classificationPicUrl.trim();
    }

    /**
     * 分类图片名称<br/>
     * 返回值对应的表列名 wx_course_classification.classification_pic_name
     *
     * @return 返回值对应 wx_course_classification.classification_pic_name
     *
     * @mbg.generated
     */
    public String getClassificationPicName() {
        return classificationPicName;
    }

    /**
     * 分类图片名称<br/>
     * wx_course_classification.classification_pic_name
     *
     * @param classificationPicName 值对应 wx_course_classification.classification_pic_name
     *
     * @mbg.generated
     */
    public void setClassificationPicName(String classificationPicName) {
        this.classificationPicName = classificationPicName == null ? null : classificationPicName.trim();
    }

    /**
     * 分类排序<br/>
     * 返回值对应的表列名 wx_course_classification.sort
     *
     * @return 返回值对应 wx_course_classification.sort
     *
     * @mbg.generated
     */
    public Byte getSort() {
        return sort;
    }

    /**
     * 分类排序<br/>
     * wx_course_classification.sort
     *
     * @param sort 值对应 wx_course_classification.sort
     *
     * @mbg.generated
     */
    public void setSort(Byte sort) {
        this.sort = sort;
    }

    /**
     * 状态(0:无效 1：有效)<br/>
     * 返回值对应的表列名 wx_course_classification.status
     *
     * @return 返回值对应 wx_course_classification.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 状态(0:无效 1：有效)<br/>
     * wx_course_classification.status
     *
     * @param status 值对应 wx_course_classification.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course_classification.creator
     *
     * @return 返回值对应 wx_course_classification.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_course_classification.creator
     *
     * @param creator 值对应 wx_course_classification.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 wx_course_classification.create_time
     *
     * @return 返回值对应 wx_course_classification.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * wx_course_classification.create_time
     *
     * @param createTime 值对应 wx_course_classification.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_course_classification.modifier
     *
     * @return 返回值对应 wx_course_classification.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_course_classification.modifier
     *
     * @param modifier 值对应 wx_course_classification.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_course_classification.modify_time
     *
     * @return 返回值对应 wx_course_classification.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_course_classification.modify_time
     *
     * @param modifyTime 值对应 wx_course_classification.modify_time
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
        WxCourseClassification other = (WxCourseClassification) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClassificationCode() == null ? other.getClassificationCode() == null : this.getClassificationCode().equals(other.getClassificationCode()))
            && (this.getClassificationParentCode() == null ? other.getClassificationParentCode() == null : this.getClassificationParentCode().equals(other.getClassificationParentCode()))
            && (this.getClassificationName() == null ? other.getClassificationName() == null : this.getClassificationName().equals(other.getClassificationName()))
            && (this.getClassificationTop() == null ? other.getClassificationTop() == null : this.getClassificationTop().equals(other.getClassificationTop()))
            && (this.getClassificationPicUrl() == null ? other.getClassificationPicUrl() == null : this.getClassificationPicUrl().equals(other.getClassificationPicUrl()))
            && (this.getClassificationPicName() == null ? other.getClassificationPicName() == null : this.getClassificationPicName().equals(other.getClassificationPicName()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
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
        result = prime * result + ((getClassificationCode() == null) ? 0 : getClassificationCode().hashCode());
        result = prime * result + ((getClassificationParentCode() == null) ? 0 : getClassificationParentCode().hashCode());
        result = prime * result + ((getClassificationName() == null) ? 0 : getClassificationName().hashCode());
        result = prime * result + ((getClassificationTop() == null) ? 0 : getClassificationTop().hashCode());
        result = prime * result + ((getClassificationPicUrl() == null) ? 0 : getClassificationPicUrl().hashCode());
        result = prime * result + ((getClassificationPicName() == null) ? 0 : getClassificationPicName().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", classificationCode=").append(classificationCode);
        sb.append(", classificationParentCode=").append(classificationParentCode);
        sb.append(", classificationName=").append(classificationName);
        sb.append(", classificationTop=").append(classificationTop);
        sb.append(", classificationPicUrl=").append(classificationPicUrl);
        sb.append(", classificationPicName=").append(classificationPicName);
        sb.append(", sort=").append(sort);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}