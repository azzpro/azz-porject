package com.azz.platform.merchant.pojo;

import java.io.Serializable;
import java.util.Date;

public class PlatformCaseClassificationParams implements Serializable {
    private Long id;

    /**
     * 方案id
     *
     * @mbg.generated
     */
    private Long caseId;

    /**
     * 产品参数id
     *
     * @mbg.generated
     */
    private Long paramsId;

    /**
     * 创建时间
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

    private static final long serialVersionUID = 1L;

    /**
     * <br/>
     * 返回值对应的表列名 platform_case_classification_params.id
     *
     * @return 返回值对应 platform_case_classification_params.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * <br/>
     * platform_case_classification_params.id
     *
     * @param id 值对应 platform_case_classification_params.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 方案id<br/>
     * 返回值对应的表列名 platform_case_classification_params.case_id
     *
     * @return 返回值对应 platform_case_classification_params.case_id
     *
     * @mbg.generated
     */
    public Long getCaseId() {
        return caseId;
    }

    /**
     * 方案id<br/>
     * platform_case_classification_params.case_id
     *
     * @param caseId 值对应 platform_case_classification_params.case_id
     *
     * @mbg.generated
     */
    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    /**
     * 产品参数id<br/>
     * 返回值对应的表列名 platform_case_classification_params.params_id
     *
     * @return 返回值对应 platform_case_classification_params.params_id
     *
     * @mbg.generated
     */
    public Long getParamsId() {
        return paramsId;
    }

    /**
     * 产品参数id<br/>
     * platform_case_classification_params.params_id
     *
     * @param paramsId 值对应 platform_case_classification_params.params_id
     *
     * @mbg.generated
     */
    public void setParamsId(Long paramsId) {
        this.paramsId = paramsId;
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 platform_case_classification_params.create_time
     *
     * @return 返回值对应 platform_case_classification_params.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * platform_case_classification_params.create_time
     *
     * @param createTime 值对应 platform_case_classification_params.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 platform_case_classification_params.creator
     *
     * @return 返回值对应 platform_case_classification_params.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * platform_case_classification_params.creator
     *
     * @param creator 值对应 platform_case_classification_params.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 platform_case_classification_params.modifier
     *
     * @return 返回值对应 platform_case_classification_params.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * platform_case_classification_params.modifier
     *
     * @param modifier 值对应 platform_case_classification_params.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 platform_case_classification_params.last_modify_time
     *
     * @return 返回值对应 platform_case_classification_params.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * 修改时间<br/>
     * platform_case_classification_params.last_modify_time
     *
     * @param lastModifyTime 值对应 platform_case_classification_params.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
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
        PlatformCaseClassificationParams other = (PlatformCaseClassificationParams) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCaseId() == null ? other.getCaseId() == null : this.getCaseId().equals(other.getCaseId()))
            && (this.getParamsId() == null ? other.getParamsId() == null : this.getParamsId().equals(other.getParamsId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCaseId() == null) ? 0 : getCaseId().hashCode());
        result = prime * result + ((getParamsId() == null) ? 0 : getParamsId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", caseId=").append(caseId);
        sb.append(", paramsId=").append(paramsId);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", modifier=").append(modifier);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}