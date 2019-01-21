package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.util.Date;

public class WxCourseAapplyInfo implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 报名信息编码
     *
     * @mbg.generated
     */
    private String applyInfoCode;

    /**
     * 所属微信用户编码
     *
     * @mbg.generated
     */
    private String wxUserCode;

    /**
     * 报名人姓名
     *
     * @mbg.generated
     */
    private String personName;

    /**
     * 联系电话
     *
     * @mbg.generated
     */
    private String phoneNumber;

    /**
     * 邮箱
     *
     * @mbg.generated
     */
    private String email;

    /**
     * 在职公司
     *
     * @mbg.generated
     */
    private String company;

    /**
     * 毕业院校
     *
     * @mbg.generated
     */
    private String graduateSchool;

    /**
     * 是否默认  是1 否0
     *
     * @mbg.generated
     */
    private Byte isDefault;

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
     * 返回值对应的表列名 wx_course_apply_info.id
     *
     * @return 返回值对应 wx_course_apply_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_course_apply_info.id
     *
     * @param id 值对应 wx_course_apply_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 报名信息编码<br/>
     * 返回值对应的表列名 wx_course_apply_info.apply_info_code
     *
     * @return 返回值对应 wx_course_apply_info.apply_info_code
     *
     * @mbg.generated
     */
    public String getApplyInfoCode() {
        return applyInfoCode;
    }

    /**
     * 报名信息编码<br/>
     * wx_course_apply_info.apply_info_code
     *
     * @param applyInfoCode 值对应 wx_course_apply_info.apply_info_code
     *
     * @mbg.generated
     */
    public void setApplyInfoCode(String applyInfoCode) {
        this.applyInfoCode = applyInfoCode == null ? null : applyInfoCode.trim();
    }

    /**
     * 所属微信用户编码<br/>
     * 返回值对应的表列名 wx_course_apply_info.wx_user_code
     *
     * @return 返回值对应 wx_course_apply_info.wx_user_code
     *
     * @mbg.generated
     */
    public String getWxUserCode() {
        return wxUserCode;
    }

    /**
     * 所属微信用户编码<br/>
     * wx_course_apply_info.wx_user_code
     *
     * @param wxUserCode 值对应 wx_course_apply_info.wx_user_code
     *
     * @mbg.generated
     */
    public void setWxUserCode(String wxUserCode) {
        this.wxUserCode = wxUserCode == null ? null : wxUserCode.trim();
    }

    /**
     * 报名人姓名<br/>
     * 返回值对应的表列名 wx_course_apply_info.person_name
     *
     * @return 返回值对应 wx_course_apply_info.person_name
     *
     * @mbg.generated
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 报名人姓名<br/>
     * wx_course_apply_info.person_name
     *
     * @param personName 值对应 wx_course_apply_info.person_name
     *
     * @mbg.generated
     */
    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    /**
     * 联系电话<br/>
     * 返回值对应的表列名 wx_course_apply_info.phone_number
     *
     * @return 返回值对应 wx_course_apply_info.phone_number
     *
     * @mbg.generated
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 联系电话<br/>
     * wx_course_apply_info.phone_number
     *
     * @param phoneNumber 值对应 wx_course_apply_info.phone_number
     *
     * @mbg.generated
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * 邮箱<br/>
     * 返回值对应的表列名 wx_course_apply_info.email
     *
     * @return 返回值对应 wx_course_apply_info.email
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱<br/>
     * wx_course_apply_info.email
     *
     * @param email 值对应 wx_course_apply_info.email
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 在职公司<br/>
     * 返回值对应的表列名 wx_course_apply_info.company
     *
     * @return 返回值对应 wx_course_apply_info.company
     *
     * @mbg.generated
     */
    public String getCompany() {
        return company;
    }

    /**
     * 在职公司<br/>
     * wx_course_apply_info.company
     *
     * @param company 值对应 wx_course_apply_info.company
     *
     * @mbg.generated
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * 毕业院校<br/>
     * 返回值对应的表列名 wx_course_apply_info.graduate_school
     *
     * @return 返回值对应 wx_course_apply_info.graduate_school
     *
     * @mbg.generated
     */
    public String getGraduateSchool() {
        return graduateSchool;
    }

    /**
     * 毕业院校<br/>
     * wx_course_apply_info.graduate_school
     *
     * @param graduateSchool 值对应 wx_course_apply_info.graduate_school
     *
     * @mbg.generated
     */
    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool == null ? null : graduateSchool.trim();
    }

    /**
     * 是否默认  是1 否0<br/>
     * 返回值对应的表列名 wx_course_apply_info.is_default
     *
     * @return 返回值对应 wx_course_apply_info.is_default
     *
     * @mbg.generated
     */
    public Byte getIsDefault() {
        return isDefault;
    }

    /**
     * 是否默认  是1 否0<br/>
     * wx_course_apply_info.is_default
     *
     * @param isDefault 值对应 wx_course_apply_info.is_default
     *
     * @mbg.generated
     */
    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course_apply_info.creator
     *
     * @return 返回值对应 wx_course_apply_info.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_course_apply_info.creator
     *
     * @param creator 值对应 wx_course_apply_info.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course_apply_info.create_time
     *
     * @return 返回值对应 wx_course_apply_info.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建人<br/>
     * wx_course_apply_info.create_time
     *
     * @param createTime 值对应 wx_course_apply_info.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_course_apply_info.modifier
     *
     * @return 返回值对应 wx_course_apply_info.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_course_apply_info.modifier
     *
     * @param modifier 值对应 wx_course_apply_info.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_course_apply_info.modify_time
     *
     * @return 返回值对应 wx_course_apply_info.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_course_apply_info.modify_time
     *
     * @param modifyTime 值对应 wx_course_apply_info.modify_time
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
        WxCourseAapplyInfo other = (WxCourseAapplyInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getApplyInfoCode() == null ? other.getApplyInfoCode() == null : this.getApplyInfoCode().equals(other.getApplyInfoCode()))
            && (this.getWxUserCode() == null ? other.getWxUserCode() == null : this.getWxUserCode().equals(other.getWxUserCode()))
            && (this.getPersonName() == null ? other.getPersonName() == null : this.getPersonName().equals(other.getPersonName()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getCompany() == null ? other.getCompany() == null : this.getCompany().equals(other.getCompany()))
            && (this.getGraduateSchool() == null ? other.getGraduateSchool() == null : this.getGraduateSchool().equals(other.getGraduateSchool()))
            && (this.getIsDefault() == null ? other.getIsDefault() == null : this.getIsDefault().equals(other.getIsDefault()))
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
        result = prime * result + ((getApplyInfoCode() == null) ? 0 : getApplyInfoCode().hashCode());
        result = prime * result + ((getWxUserCode() == null) ? 0 : getWxUserCode().hashCode());
        result = prime * result + ((getPersonName() == null) ? 0 : getPersonName().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getCompany() == null) ? 0 : getCompany().hashCode());
        result = prime * result + ((getGraduateSchool() == null) ? 0 : getGraduateSchool().hashCode());
        result = prime * result + ((getIsDefault() == null) ? 0 : getIsDefault().hashCode());
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
        sb.append(", applyInfoCode=").append(applyInfoCode);
        sb.append(", wxUserCode=").append(wxUserCode);
        sb.append(", personName=").append(personName);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", email=").append(email);
        sb.append(", company=").append(company);
        sb.append(", graduateSchool=").append(graduateSchool);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}