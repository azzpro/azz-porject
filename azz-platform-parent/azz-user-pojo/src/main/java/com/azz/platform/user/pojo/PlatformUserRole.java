package com.azz.platform.user.pojo;

import java.io.Serializable;
import java.util.Date;

public class PlatformUserRole implements Serializable {
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
    private Long userId;

    /**
     * 角色id
     *
     * @mbg.generated
     */
    private Long roleId;

    private Date createTime;

    /**
     * 创建�?
     *
     * @mbg.generated
     */
    private String creator;

    /**
     * �?后修改时�?
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     * 修改�?
     *
     * @mbg.generated
     */
    private String modifier;

    private static final long serialVersionUID = 1L;

    /**
     * 主键<br/>
     * 返回值对应的表列�? platform_user_role.id
     *
     * @return 返回值对�? platform_user_role.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * platform_user_role.id
     *
     * @param id 值对�? platform_user_role.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户id<br/>
     * 返回值对应的表列�? platform_user_role.user_id
     *
     * @return 返回值对�? platform_user_role.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户id<br/>
     * platform_user_role.user_id
     *
     * @param userId 值对�? platform_user_role.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 角色id<br/>
     * 返回值对应的表列�? platform_user_role.role_id
     *
     * @return 返回值对�? platform_user_role.role_id
     *
     * @mbg.generated
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 角色id<br/>
     * platform_user_role.role_id
     *
     * @param roleId 值对�? platform_user_role.role_id
     *
     * @mbg.generated
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * <br/>
     * 返回值对应的表列�? platform_user_role.create_time
     *
     * @return 返回值对�? platform_user_role.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * <br/>
     * platform_user_role.create_time
     *
     * @param createTime 值对�? platform_user_role.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建�?<br/>
     * 返回值对应的表列�? platform_user_role.creator
     *
     * @return 返回值对�? platform_user_role.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建�?<br/>
     * platform_user_role.creator
     *
     * @param creator 值对�? platform_user_role.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * �?后修改时�?<br/>
     * 返回值对应的表列�? platform_user_role.last_modify_time
     *
     * @return 返回值对�? platform_user_role.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * �?后修改时�?<br/>
     * platform_user_role.last_modify_time
     *
     * @param lastModifyTime 值对�? platform_user_role.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * 修改�?<br/>
     * 返回值对应的表列�? platform_user_role.modifier
     *
     * @return 返回值对�? platform_user_role.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改�?<br/>
     * platform_user_role.modifier
     *
     * @param modifier 值对�? platform_user_role.modifier
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
        PlatformUserRole other = (PlatformUserRole) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
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
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
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
        sb.append(", userId=").append(userId);
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