package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCourse implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 所属分类编码
     *
     * @mbg.generated
     */
    private String classificationCode;
    
    private String brandCode;
    
    /**
     * 课程编码
     *
     * @mbg.generated
     */
    private String courseCode;

    /**
     * 课程名称
     *
     * @mbg.generated
     */
    private String courseName;

    /**
     * 课程简介
     *
     * @mbg.generated
     */
    private String courseDescription;

    /**
     * 课程主图名称
     *
     * @mbg.generated
     */
    private String coursePicName;

    /**
     * 课程主图文件路径
     *
     * @mbg.generated
     */
    private String coursePicUrl;

    /**
     * 课程状态   删除0  上架1  下架2  
     *
     * @mbg.generated
     */
    private Byte status;

    private String remark;

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

    /**
     * 课程详情
     *
     * @mbg.generated
     */
    private String courseInfo;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 wx_course.id
     *
     * @return 返回值对应 wx_course.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_course.id
     *
     * @param id 值对应 wx_course.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 所属分类编码<br/>
     * 返回值对应的表列名 wx_course.classification_code
     *
     * @return 返回值对应 wx_course.classification_code
     *
     * @mbg.generated
     */
    public String getClassificationCode() {
        return classificationCode;
    }

    /**
     * 所属分类编码<br/>
     * wx_course.classification_code
     *
     * @param classificationCode 值对应 wx_course.classification_code
     *
     * @mbg.generated
     */
    public void setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode == null ? null : classificationCode.trim();
    }

    /**
     * 课程编码<br/>
     * 返回值对应的表列名 wx_course.course_code
     *
     * @return 返回值对应 wx_course.course_code
     *
     * @mbg.generated
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * 课程编码<br/>
     * wx_course.course_code
     *
     * @param courseCode 值对应 wx_course.course_code
     *
     * @mbg.generated
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }

    /**
     * 课程名称<br/>
     * 返回值对应的表列名 wx_course.course_name
     *
     * @return 返回值对应 wx_course.course_name
     *
     * @mbg.generated
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 课程名称<br/>
     * wx_course.course_name
     *
     * @param courseName 值对应 wx_course.course_name
     *
     * @mbg.generated
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    /**
     * 课程简介<br/>
     * 返回值对应的表列名 wx_course.course_description
     *
     * @return 返回值对应 wx_course.course_description
     *
     * @mbg.generated
     */
    public String getCourseDescription() {
        return courseDescription;
    }

    /**
     * 课程简介<br/>
     * wx_course.course_description
     *
     * @param courseDescription 值对应 wx_course.course_description
     *
     * @mbg.generated
     */
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription == null ? null : courseDescription.trim();
    }

    /**
     * 课程主图名称<br/>
     * 返回值对应的表列名 wx_course.course_pic_name
     *
     * @return 返回值对应 wx_course.course_pic_name
     *
     * @mbg.generated
     */
    public String getCoursePicName() {
        return coursePicName;
    }

    /**
     * 课程主图名称<br/>
     * wx_course.course_pic_name
     *
     * @param coursePicName 值对应 wx_course.course_pic_name
     *
     * @mbg.generated
     */
    public void setCoursePicName(String coursePicName) {
        this.coursePicName = coursePicName == null ? null : coursePicName.trim();
    }

    /**
     * 课程主图文件路径<br/>
     * 返回值对应的表列名 wx_course.course_pic_url
     *
     * @return 返回值对应 wx_course.course_pic_url
     *
     * @mbg.generated
     */
    public String getCoursePicUrl() {
        return coursePicUrl;
    }

    /**
     * 课程主图文件路径<br/>
     * wx_course.course_pic_url
     *
     * @param coursePicUrl 值对应 wx_course.course_pic_url
     *
     * @mbg.generated
     */
    public void setCoursePicUrl(String coursePicUrl) {
        this.coursePicUrl = coursePicUrl == null ? null : coursePicUrl.trim();
    }

    /**
     * 课程状态   删除0  上架1  下架2  <br/>
     * 返回值对应的表列名 wx_course.status
     *
     * @return 返回值对应 wx_course.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 课程状态   删除0  上架1  下架2  <br/>
     * wx_course.status
     *
     * @param status 值对应 wx_course.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * <br/>
     * 返回值对应的表列名 wx_course.remark
     *
     * @return 返回值对应 wx_course.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <br/>
     * wx_course.remark
     *
     * @param remark 值对应 wx_course.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course.creator
     *
     * @return 返回值对应 wx_course.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_course.creator
     *
     * @param creator 值对应 wx_course.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course.create_time
     *
     * @return 返回值对应 wx_course.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建人<br/>
     * wx_course.create_time
     *
     * @param createTime 值对应 wx_course.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_course.modifier
     *
     * @return 返回值对应 wx_course.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_course.modifier
     *
     * @param modifier 值对应 wx_course.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_course.modify_time
     *
     * @return 返回值对应 wx_course.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_course.modify_time
     *
     * @param modifyTime 值对应 wx_course.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 课程详情<br/>
     * 返回值对应的表列名 wx_course.course_info
     *
     * @return 返回值对应 wx_course.course_info
     *
     * @mbg.generated
     */
    public String getCourseInfo() {
        return courseInfo;
    }

    /**
     * 课程详情<br/>
     * wx_course.course_info
     *
     * @param courseInfo 值对应 wx_course.course_info
     *
     * @mbg.generated
     */
    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo == null ? null : courseInfo.trim();
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
        WxCourse other = (WxCourse) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClassificationCode() == null ? other.getClassificationCode() == null : this.getClassificationCode().equals(other.getClassificationCode()))
            && (this.getCourseCode() == null ? other.getCourseCode() == null : this.getCourseCode().equals(other.getCourseCode()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
            && (this.getCourseDescription() == null ? other.getCourseDescription() == null : this.getCourseDescription().equals(other.getCourseDescription()))
            && (this.getCoursePicName() == null ? other.getCoursePicName() == null : this.getCoursePicName().equals(other.getCoursePicName()))
            && (this.getCoursePicUrl() == null ? other.getCoursePicUrl() == null : this.getCoursePicUrl().equals(other.getCoursePicUrl()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getCourseInfo() == null ? other.getCourseInfo() == null : this.getCourseInfo().equals(other.getCourseInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClassificationCode() == null) ? 0 : getClassificationCode().hashCode());
        result = prime * result + ((getCourseCode() == null) ? 0 : getCourseCode().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getCourseDescription() == null) ? 0 : getCourseDescription().hashCode());
        result = prime * result + ((getCoursePicName() == null) ? 0 : getCoursePicName().hashCode());
        result = prime * result + ((getCoursePicUrl() == null) ? 0 : getCoursePicUrl().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getCourseInfo() == null) ? 0 : getCourseInfo().hashCode());
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
        sb.append(", courseCode=").append(courseCode);
        sb.append(", courseName=").append(courseName);
        sb.append(", courseDescription=").append(courseDescription);
        sb.append(", coursePicName=").append(coursePicName);
        sb.append(", coursePicUrl=").append(coursePicUrl);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", courseInfo=").append(courseInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
}