package com.azz.platform.merchant.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlatformCombinationModule implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 组合id
     *
     * @mbg.generated
     */
    private Long combinationId;

    /**
     * 选中的模组id
     *
     * @mbg.generated
     */
    private Long moduleId;

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
     * 返回值对应的表列名 platform_combination_module.id
     *
     * @return 返回值对应 platform_combination_module.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * platform_combination_module.id
     *
     * @param id 值对应 platform_combination_module.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 组合id<br/>
     * 返回值对应的表列名 platform_combination_module.combination_id
     *
     * @return 返回值对应 platform_combination_module.combination_id
     *
     * @mbg.generated
     */
    public Long getCombinationId() {
        return combinationId;
    }

    /**
     * 组合id<br/>
     * platform_combination_module.combination_id
     *
     * @param combinationId 值对应 platform_combination_module.combination_id
     *
     * @mbg.generated
     */
    public void setCombinationId(Long combinationId) {
        this.combinationId = combinationId;
    }

    /**
     * 选中的模组id<br/>
     * 返回值对应的表列名 platform_combination_module.module_id
     *
     * @return 返回值对应 platform_combination_module.module_id
     *
     * @mbg.generated
     */
    public Long getModuleId() {
        return moduleId;
    }

    /**
     * 选中的模组id<br/>
     * platform_combination_module.module_id
     *
     * @param moduleId 值对应 platform_combination_module.module_id
     *
     * @mbg.generated
     */
    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 platform_combination_module.creator
     *
     * @return 返回值对应 platform_combination_module.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * platform_combination_module.creator
     *
     * @param creator 值对应 platform_combination_module.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 platform_combination_module.create_time
     *
     * @return 返回值对应 platform_combination_module.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * platform_combination_module.create_time
     *
     * @param createTime 值对应 platform_combination_module.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 platform_combination_module.modifier
     *
     * @return 返回值对应 platform_combination_module.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * platform_combination_module.modifier
     *
     * @param modifier 值对应 platform_combination_module.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 platform_combination_module.modify_time
     *
     * @return 返回值对应 platform_combination_module.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * platform_combination_module.modify_time
     *
     * @param modifyTime 值对应 platform_combination_module.modify_time
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
        PlatformCombinationModule other = (PlatformCombinationModule) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCombinationId() == null ? other.getCombinationId() == null : this.getCombinationId().equals(other.getCombinationId()))
            && (this.getModuleId() == null ? other.getModuleId() == null : this.getModuleId().equals(other.getModuleId()))
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
        result = prime * result + ((getCombinationId() == null) ? 0 : getCombinationId().hashCode());
        result = prime * result + ((getModuleId() == null) ? 0 : getModuleId().hashCode());
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
        sb.append(", combinationId=").append(combinationId);
        sb.append(", moduleId=").append(moduleId);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}