package com.azz.merchant.pojo;

import java.io.Serializable;
import java.util.Date;

public class MerchantRoleRef implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 用户id
     *
     * @mbg.generated
     */
    private Long merchantId;

    /**
     * 角色id
     *
     * @mbg.generated
     */
    private Long roleId;

    private Date createTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String creator;

    /**
     * 最后修改时间
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    private String modifier;

    private static final long serialVersionUID = 1L;

    /**
     * 主键<br/>
     * 返回值对应的表列名 merchant_role_ref.id
     *
     * @return 返回值对应 merchant_role_ref.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * merchant_role_ref.id
     *
     * @param id 值对应 merchant_role_ref.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户id<br/>
     * 返回值对应的表列名 merchant_role_ref.merchant_id
     *
     * @return 返回值对应 merchant_role_ref.merchant_id
     *
     * @mbg.generated
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 用户id<br/>
     * merchant_role_ref.merchant_id
     *
     * @param merchantId 值对应 merchant_role_ref.merchant_id
     *
     * @mbg.generated
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 角色id<br/>
     * 返回值对应的表列名 merchant_role_ref.role_id
     *
     * @return 返回值对应 merchant_role_ref.role_id
     *
     * @mbg.generated
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 角色id<br/>
     * merchant_role_ref.role_id
     *
     * @param roleId 值对应 merchant_role_ref.role_id
     *
     * @mbg.generated
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * <br/>
     * 返回值对应的表列名 merchant_role_ref.create_time
     *
     * @return 返回值对应 merchant_role_ref.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * <br/>
     * merchant_role_ref.create_time
     *
     * @param createTime 值对应 merchant_role_ref.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 merchant_role_ref.creator
     *
     * @return 返回值对应 merchant_role_ref.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * merchant_role_ref.creator
     *
     * @param creator 值对应 merchant_role_ref.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 merchant_role_ref.last_modify_time
     *
     * @return 返回值对应 merchant_role_ref.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * 最后修改时间<br/>
     * merchant_role_ref.last_modify_time
     *
     * @param lastModifyTime 值对应 merchant_role_ref.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 merchant_role_ref.modifier
     *
     * @return 返回值对应 merchant_role_ref.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * merchant_role_ref.modifier
     *
     * @param modifier 值对应 merchant_role_ref.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
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
        MerchantRoleRef other = (MerchantRoleRef) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", roleId=").append(roleId);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}