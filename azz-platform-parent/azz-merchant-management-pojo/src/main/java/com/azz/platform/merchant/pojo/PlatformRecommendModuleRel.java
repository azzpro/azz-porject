package com.azz.platform.merchant.pojo;

import java.io.Serializable;
import java.util.Date;

public class PlatformRecommendModuleRel implements Serializable {
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
     * 模组编码
     *
     * @mbg.generated
     */
    private String moduleCode;

    /**
     * 产品数量
     *
     * @mbg.generated
     */
    private Integer productNumber;

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

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 platform_recommend_module_rel.id
     *
     * @return 返回值对应 platform_recommend_module_rel.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * platform_recommend_module_rel.id
     *
     * @param id 值对应 platform_recommend_module_rel.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 推荐编码<br/>
     * 返回值对应的表列名 platform_recommend_module_rel.recommend_code
     *
     * @return 返回值对应 platform_recommend_module_rel.recommend_code
     *
     * @mbg.generated
     */
    public String getRecommendCode() {
        return recommendCode;
    }

    /**
     * 推荐编码<br/>
     * platform_recommend_module_rel.recommend_code
     *
     * @param recommendCode 值对应 platform_recommend_module_rel.recommend_code
     *
     * @mbg.generated
     */
    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode == null ? null : recommendCode.trim();
    }

    /**
     * 模组编码<br/>
     * 返回值对应的表列名 platform_recommend_module_rel.module_code
     *
     * @return 返回值对应 platform_recommend_module_rel.module_code
     *
     * @mbg.generated
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * 模组编码<br/>
     * platform_recommend_module_rel.module_code
     *
     * @param moduleCode 值对应 platform_recommend_module_rel.module_code
     *
     * @mbg.generated
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }

    /**
     * 产品数量<br/>
     * 返回值对应的表列名 platform_recommend_module_rel.product_number
     *
     * @return 返回值对应 platform_recommend_module_rel.product_number
     *
     * @mbg.generated
     */
    public Integer getProductNumber() {
        return productNumber;
    }

    /**
     * 产品数量<br/>
     * platform_recommend_module_rel.product_number
     *
     * @param productNumber 值对应 platform_recommend_module_rel.product_number
     *
     * @mbg.generated
     */
    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 platform_recommend_module_rel.creator
     *
     * @return 返回值对应 platform_recommend_module_rel.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * platform_recommend_module_rel.creator
     *
     * @param creator 值对应 platform_recommend_module_rel.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 platform_recommend_module_rel.create_time
     *
     * @return 返回值对应 platform_recommend_module_rel.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * platform_recommend_module_rel.create_time
     *
     * @param createTime 值对应 platform_recommend_module_rel.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        PlatformRecommendModuleRel other = (PlatformRecommendModuleRel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRecommendCode() == null ? other.getRecommendCode() == null : this.getRecommendCode().equals(other.getRecommendCode()))
            && (this.getModuleCode() == null ? other.getModuleCode() == null : this.getModuleCode().equals(other.getModuleCode()))
            && (this.getProductNumber() == null ? other.getProductNumber() == null : this.getProductNumber().equals(other.getProductNumber()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRecommendCode() == null) ? 0 : getRecommendCode().hashCode());
        result = prime * result + ((getModuleCode() == null) ? 0 : getModuleCode().hashCode());
        result = prime * result + ((getProductNumber() == null) ? 0 : getProductNumber().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
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
        sb.append(", moduleCode=").append(moduleCode);
        sb.append(", productNumber=").append(productNumber);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}