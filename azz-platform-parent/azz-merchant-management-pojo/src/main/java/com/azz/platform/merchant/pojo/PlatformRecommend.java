package com.azz.platform.merchant.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlatformRecommend implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 推荐编码
     *
     * @mbg.generated
     */
    private String recommendCode;

    /**
     * 推荐名称
     *
     * @mbg.generated
     */
    private String recommendName;

    /**
     * 所属专场编码
     *
     * @mbg.generated
     */
    private String specialPerformanceCode;

    /**
     * 状态  删除0  上架1  下架2  
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
     * 主键id<br/>
     * 返回值对应的表列名 platform_recommend.id
     *
     * @return 返回值对应 platform_recommend.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * platform_recommend.id
     *
     * @param id 值对应 platform_recommend.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 推荐编码<br/>
     * 返回值对应的表列名 platform_recommend.recommend_code
     *
     * @return 返回值对应 platform_recommend.recommend_code
     *
     * @mbg.generated
     */
    public String getRecommendCode() {
        return recommendCode;
    }

    /**
     * 推荐编码<br/>
     * platform_recommend.recommend_code
     *
     * @param recommendCode 值对应 platform_recommend.recommend_code
     *
     * @mbg.generated
     */
    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode == null ? null : recommendCode.trim();
    }

    /**
     * 推荐名称<br/>
     * 返回值对应的表列名 platform_recommend.recommend_name
     *
     * @return 返回值对应 platform_recommend.recommend_name
     *
     * @mbg.generated
     */
    public String getRecommendName() {
        return recommendName;
    }

    /**
     * 推荐名称<br/>
     * platform_recommend.recommend_name
     *
     * @param recommendName 值对应 platform_recommend.recommend_name
     *
     * @mbg.generated
     */
    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName == null ? null : recommendName.trim();
    }

    /**
     * 所属专场编码<br/>
     * 返回值对应的表列名 platform_recommend.special_performance_code
     *
     * @return 返回值对应 platform_recommend.special_performance_code
     *
     * @mbg.generated
     */
    public String getSpecialPerformanceCode() {
        return specialPerformanceCode;
    }

    /**
     * 所属专场编码<br/>
     * platform_recommend.special_performance_code
     *
     * @param specialPerformanceCode 值对应 platform_recommend.special_performance_code
     *
     * @mbg.generated
     */
    public void setSpecialPerformanceCode(String specialPerformanceCode) {
        this.specialPerformanceCode = specialPerformanceCode == null ? null : specialPerformanceCode.trim();
    }

    /**
     * 状态  删除0  上架1  下架2  <br/>
     * 返回值对应的表列名 platform_recommend.status
     *
     * @return 返回值对应 platform_recommend.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 状态  删除0  上架1  下架2  <br/>
     * platform_recommend.status
     *
     * @param status 值对应 platform_recommend.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 platform_recommend.creator
     *
     * @return 返回值对应 platform_recommend.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * platform_recommend.creator
     *
     * @param creator 值对应 platform_recommend.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 platform_recommend.create_time
     *
     * @return 返回值对应 platform_recommend.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * platform_recommend.create_time
     *
     * @param createTime 值对应 platform_recommend.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 platform_recommend.modifier
     *
     * @return 返回值对应 platform_recommend.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * platform_recommend.modifier
     *
     * @param modifier 值对应 platform_recommend.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 platform_recommend.modify_time
     *
     * @return 返回值对应 platform_recommend.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * platform_recommend.modify_time
     *
     * @param modifyTime 值对应 platform_recommend.modify_time
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
        PlatformRecommend other = (PlatformRecommend) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRecommendCode() == null ? other.getRecommendCode() == null : this.getRecommendCode().equals(other.getRecommendCode()))
            && (this.getRecommendName() == null ? other.getRecommendName() == null : this.getRecommendName().equals(other.getRecommendName()))
            && (this.getSpecialPerformanceCode() == null ? other.getSpecialPerformanceCode() == null : this.getSpecialPerformanceCode().equals(other.getSpecialPerformanceCode()))
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
        result = prime * result + ((getRecommendCode() == null) ? 0 : getRecommendCode().hashCode());
        result = prime * result + ((getRecommendName() == null) ? 0 : getRecommendName().hashCode());
        result = prime * result + ((getSpecialPerformanceCode() == null) ? 0 : getSpecialPerformanceCode().hashCode());
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
        sb.append(", recommendCode=").append(recommendCode);
        sb.append(", recommendName=").append(recommendName);
        sb.append(", specialPerformanceCode=").append(specialPerformanceCode);
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