package com.azz.platform.merchant.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlatformCombination implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 组合编码
     *
     * @mbg.generated
     */
    private String combinationCode;

    /**
     * 组合名称
     *
     * @mbg.generated
     */
    private String combinationName;

    /**
     * 所属方案id
     *
     * @mbg.generated
     */
    private Long caseId;

    /**
     * 状态 (0删除 1上架  2下架)
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 推荐理由
     *
     * @mbg.generated
     */
    private String recommendReason;

    /**
     * 主图文件名
     *
     * @mbg.generated
     */
    private String combinationPicName;

    /**
     * 主图文件路径
     *
     * @mbg.generated
     */
    private String combinationPicUrl;

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
     * 返回值对应的表列名 platform_combination.id
     *
     * @return 返回值对应 platform_combination.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * platform_combination.id
     *
     * @param id 值对应 platform_combination.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 组合编码<br/>
     * 返回值对应的表列名 platform_combination.combination_code
     *
     * @return 返回值对应 platform_combination.combination_code
     *
     * @mbg.generated
     */
    public String getCombinationCode() {
        return combinationCode;
    }

    /**
     * 组合编码<br/>
     * platform_combination.combination_code
     *
     * @param combinationCode 值对应 platform_combination.combination_code
     *
     * @mbg.generated
     */
    public void setCombinationCode(String combinationCode) {
        this.combinationCode = combinationCode == null ? null : combinationCode.trim();
    }

    /**
     * 组合名称<br/>
     * 返回值对应的表列名 platform_combination.combination_name
     *
     * @return 返回值对应 platform_combination.combination_name
     *
     * @mbg.generated
     */
    public String getCombinationName() {
        return combinationName;
    }

    /**
     * 组合名称<br/>
     * platform_combination.combination_name
     *
     * @param combinationName 值对应 platform_combination.combination_name
     *
     * @mbg.generated
     */
    public void setCombinationName(String combinationName) {
        this.combinationName = combinationName == null ? null : combinationName.trim();
    }

    /**
     * 所属方案id<br/>
     * 返回值对应的表列名 platform_combination.case_id
     *
     * @return 返回值对应 platform_combination.case_id
     *
     * @mbg.generated
     */
    public Long getCaseId() {
        return caseId;
    }

    /**
     * 所属方案id<br/>
     * platform_combination.case_id
     *
     * @param caseId 值对应 platform_combination.case_id
     *
     * @mbg.generated
     */
    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    /**
     * 状态 (0删除 1上架  2下架)<br/>
     * 返回值对应的表列名 platform_combination.status
     *
     * @return 返回值对应 platform_combination.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态 (0删除 1上架  2下架)<br/>
     * platform_combination.status
     *
     * @param status 值对应 platform_combination.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 推荐理由<br/>
     * 返回值对应的表列名 platform_combination.recommend_reason
     *
     * @return 返回值对应 platform_combination.recommend_reason
     *
     * @mbg.generated
     */
    public String getRecommendReason() {
        return recommendReason;
    }

    /**
     * 推荐理由<br/>
     * platform_combination.recommend_reason
     *
     * @param recommendReason 值对应 platform_combination.recommend_reason
     *
     * @mbg.generated
     */
    public void setRecommendReason(String recommendReason) {
        this.recommendReason = recommendReason == null ? null : recommendReason.trim();
    }

    /**
     * 主图文件名<br/>
     * 返回值对应的表列名 platform_combination.combination_pic_name
     *
     * @return 返回值对应 platform_combination.combination_pic_name
     *
     * @mbg.generated
     */
    public String getCombinationPicName() {
        return combinationPicName;
    }

    /**
     * 主图文件名<br/>
     * platform_combination.combination_pic_name
     *
     * @param combinationPicName 值对应 platform_combination.combination_pic_name
     *
     * @mbg.generated
     */
    public void setCombinationPicName(String combinationPicName) {
        this.combinationPicName = combinationPicName == null ? null : combinationPicName.trim();
    }

    /**
     * 主图文件路径<br/>
     * 返回值对应的表列名 platform_combination.combination_pic_url
     *
     * @return 返回值对应 platform_combination.combination_pic_url
     *
     * @mbg.generated
     */
    public String getCombinationPicUrl() {
        return combinationPicUrl;
    }

    /**
     * 主图文件路径<br/>
     * platform_combination.combination_pic_url
     *
     * @param combinationPicUrl 值对应 platform_combination.combination_pic_url
     *
     * @mbg.generated
     */
    public void setCombinationPicUrl(String combinationPicUrl) {
        this.combinationPicUrl = combinationPicUrl == null ? null : combinationPicUrl.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 platform_combination.creator
     *
     * @return 返回值对应 platform_combination.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * platform_combination.creator
     *
     * @param creator 值对应 platform_combination.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 platform_combination.create_time
     *
     * @return 返回值对应 platform_combination.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * platform_combination.create_time
     *
     * @param createTime 值对应 platform_combination.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 platform_combination.modifier
     *
     * @return 返回值对应 platform_combination.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * platform_combination.modifier
     *
     * @param modifier 值对应 platform_combination.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 platform_combination.modify_time
     *
     * @return 返回值对应 platform_combination.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * platform_combination.modify_time
     *
     * @param modifyTime 值对应 platform_combination.modify_time
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
        PlatformCombination other = (PlatformCombination) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCombinationCode() == null ? other.getCombinationCode() == null : this.getCombinationCode().equals(other.getCombinationCode()))
            && (this.getCombinationName() == null ? other.getCombinationName() == null : this.getCombinationName().equals(other.getCombinationName()))
            && (this.getCaseId() == null ? other.getCaseId() == null : this.getCaseId().equals(other.getCaseId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRecommendReason() == null ? other.getRecommendReason() == null : this.getRecommendReason().equals(other.getRecommendReason()))
            && (this.getCombinationPicName() == null ? other.getCombinationPicName() == null : this.getCombinationPicName().equals(other.getCombinationPicName()))
            && (this.getCombinationPicUrl() == null ? other.getCombinationPicUrl() == null : this.getCombinationPicUrl().equals(other.getCombinationPicUrl()))
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
        result = prime * result + ((getCombinationCode() == null) ? 0 : getCombinationCode().hashCode());
        result = prime * result + ((getCombinationName() == null) ? 0 : getCombinationName().hashCode());
        result = prime * result + ((getCaseId() == null) ? 0 : getCaseId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRecommendReason() == null) ? 0 : getRecommendReason().hashCode());
        result = prime * result + ((getCombinationPicName() == null) ? 0 : getCombinationPicName().hashCode());
        result = prime * result + ((getCombinationPicUrl() == null) ? 0 : getCombinationPicUrl().hashCode());
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
        sb.append(", combinationCode=").append(combinationCode);
        sb.append(", combinationName=").append(combinationName);
        sb.append(", caseId=").append(caseId);
        sb.append(", status=").append(status);
        sb.append(", recommendReason=").append(recommendReason);
        sb.append(", combinationPicName=").append(combinationPicName);
        sb.append(", combinationPicUrl=").append(combinationPicUrl);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}