package com.azz.platform.merchant.pojo;


import java.io.Serializable;
import java.util.Date;

public class PlatformCase implements Serializable {
    private Long id;

    /**
     * 方案编码
     *
     * @mbg.generated
     */
    private String caseCode;

    /**
     * 方案名称
     *
     * @mbg.generated
     */
    private String caseName;

    /**
     * 所属分类id
     *
     * @mbg.generated
     */
    private Long classificationId;

    /**
     * 方案状态（0下架 1上架）
     *
     * @mbg.generated
     */
    private Integer caseStatus;

    /**
     * 主图url
     *
     * @mbg.generated
     */
    private String casePicUrl;

    /**
     * 方案主图名称
     *
     * @mbg.generated
     */
    private String casePicName;

    /**
     * 创建人
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
    private Date lastModifyTime;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * <br/>
     * 返回值对应的表列名 platform_case.id
     *
     * @return 返回值对应 platform_case.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * <br/>
     * platform_case.id
     *
     * @param id 值对应 platform_case.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 方案编码<br/>
     * 返回值对应的表列名 platform_case.case_code
     *
     * @return 返回值对应 platform_case.case_code
     *
     * @mbg.generated
     */
    public String getCaseCode() {
        return caseCode;
    }

    /**
     * 方案编码<br/>
     * platform_case.case_code
     *
     * @param caseCode 值对应 platform_case.case_code
     *
     * @mbg.generated
     */
    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode == null ? null : caseCode.trim();
    }

    /**
     * 方案名称<br/>
     * 返回值对应的表列名 platform_case.case_name
     *
     * @return 返回值对应 platform_case.case_name
     *
     * @mbg.generated
     */
    public String getCaseName() {
        return caseName;
    }

    /**
     * 方案名称<br/>
     * platform_case.case_name
     *
     * @param caseName 值对应 platform_case.case_name
     *
     * @mbg.generated
     */
    public void setCaseName(String caseName) {
        this.caseName = caseName == null ? null : caseName.trim();
    }

    /**
     * 所属分类id<br/>
     * 返回值对应的表列名 platform_case.classification_id
     *
     * @return 返回值对应 platform_case.classification_id
     *
     * @mbg.generated
     */
    public Long getClassificationId() {
        return classificationId;
    }

    /**
     * 所属分类id<br/>
     * platform_case.classification_id
     *
     * @param classificationId 值对应 platform_case.classification_id
     *
     * @mbg.generated
     */
    public void setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
    }

    /**
     * 方案状态（0下架 1上架）<br/>
     * 返回值对应的表列名 platform_case.case_status
     *
     * @return 返回值对应 platform_case.case_status
     *
     * @mbg.generated
     */
    public Integer getCaseStatus() {
        return caseStatus;
    }

    /**
     * 方案状态（0下架 1上架）<br/>
     * platform_case.case_status
     *
     * @param caseStatus 值对应 platform_case.case_status
     *
     * @mbg.generated
     */
    public void setCaseStatus(Integer caseStatus) {
        this.caseStatus = caseStatus;
    }

    /**
     * 主图url<br/>
     * 返回值对应的表列名 platform_case.case_pic_url
     *
     * @return 返回值对应 platform_case.case_pic_url
     *
     * @mbg.generated
     */
    public String getCasePicUrl() {
        return casePicUrl;
    }

    /**
     * 主图url<br/>
     * platform_case.case_pic_url
     *
     * @param casePicUrl 值对应 platform_case.case_pic_url
     *
     * @mbg.generated
     */
    public void setCasePicUrl(String casePicUrl) {
        this.casePicUrl = casePicUrl == null ? null : casePicUrl.trim();
    }

    /**
     * 方案主图名称<br/>
     * 返回值对应的表列名 platform_case.case_pic_name
     *
     * @return 返回值对应 platform_case.case_pic_name
     *
     * @mbg.generated
     */
    public String getCasePicName() {
        return casePicName;
    }

    /**
     * 方案主图名称<br/>
     * platform_case.case_pic_name
     *
     * @param casePicName 值对应 platform_case.case_pic_name
     *
     * @mbg.generated
     */
    public void setCasePicName(String casePicName) {
        this.casePicName = casePicName == null ? null : casePicName.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 platform_case.create_time
     *
     * @return 返回值对应 platform_case.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建人<br/>
     * platform_case.create_time
     *
     * @param createTime 值对应 platform_case.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 platform_case.creator
     *
     * @return 返回值对应 platform_case.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * platform_case.creator
     *
     * @param creator 值对应 platform_case.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 platform_case.modifier
     *
     * @return 返回值对应 platform_case.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * platform_case.modifier
     *
     * @param modifier 值对应 platform_case.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 platform_case.last_modify_time
     *
     * @return 返回值对应 platform_case.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * 修改时间<br/>
     * platform_case.last_modify_time
     *
     * @param lastModifyTime 值对应 platform_case.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * 备注<br/>
     * 返回值对应的表列名 platform_case.remark
     *
     * @return 返回值对应 platform_case.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * platform_case.remark
     *
     * @param remark 值对应 platform_case.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        PlatformCase other = (PlatformCase) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCaseCode() == null ? other.getCaseCode() == null : this.getCaseCode().equals(other.getCaseCode()))
            && (this.getCaseName() == null ? other.getCaseName() == null : this.getCaseName().equals(other.getCaseName()))
            && (this.getClassificationId() == null ? other.getClassificationId() == null : this.getClassificationId().equals(other.getClassificationId()))
            && (this.getCaseStatus() == null ? other.getCaseStatus() == null : this.getCaseStatus().equals(other.getCaseStatus()))
            && (this.getCasePicUrl() == null ? other.getCasePicUrl() == null : this.getCasePicUrl().equals(other.getCasePicUrl()))
            && (this.getCasePicName() == null ? other.getCasePicName() == null : this.getCasePicName().equals(other.getCasePicName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCaseCode() == null) ? 0 : getCaseCode().hashCode());
        result = prime * result + ((getCaseName() == null) ? 0 : getCaseName().hashCode());
        result = prime * result + ((getClassificationId() == null) ? 0 : getClassificationId().hashCode());
        result = prime * result + ((getCaseStatus() == null) ? 0 : getCaseStatus().hashCode());
        result = prime * result + ((getCasePicUrl() == null) ? 0 : getCasePicUrl().hashCode());
        result = prime * result + ((getCasePicName() == null) ? 0 : getCasePicName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", caseCode=").append(caseCode);
        sb.append(", caseName=").append(caseName);
        sb.append(", classificationId=").append(classificationId);
        sb.append(", caseStatus=").append(caseStatus);
        sb.append(", casePicUrl=").append(casePicUrl);
        sb.append(", casePicName=").append(casePicName);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", modifier=").append(modifier);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}