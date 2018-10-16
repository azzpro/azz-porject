package com.azz.login.pojo;

import java.io.Serializable;
import java.util.Date;

public class PlatformPermission implements Serializable {
    /**
     * ����
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Ȩ�ޱ��
     *
     * @mbg.generated
     */
    private String permissionCode;

    /**
     * Ȩ������
     *
     * @mbg.generated
     */
    private String permissionName;

    /**
     * �ϼ�Ȩ�ޱ��
     *
     * @mbg.generated
     */
    private String parentPermissionCode;

    /**
     * ����
     *
     * @mbg.generated
     */
    private Integer sort;

    /**
     * �ȼ�
     *
     * @mbg.generated
     */
    private Integer level;

    /**
     * Ȩ�޶�Ӧ��url
     *
     * @mbg.generated
     */
    private String pageUrl;

    /**
     * ��ע
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * ״̬  0��Ч  1��Ч
     *
     * @mbg.generated
     */
    private Integer status;

    private Date createTime;

    /**
     * ������
     *
     * @mbg.generated
     */
    private String creator;

    /**
     * ����޸�ʱ��
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     * �޸���
     *
     * @mbg.generated
     */
    private String modifier;

    private static final long serialVersionUID = 1L;

    /**
     * ����<br/>
     * ����ֵ��Ӧ�ı����� platform_permission.id
     *
     * @return ����ֵ��Ӧ platform_permission.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * ����<br/>
     * platform_permission.id
     *
     * @param id ֵ��Ӧ platform_permission.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Ȩ�ޱ��<br/>
     * ����ֵ��Ӧ�ı����� platform_permission.permission_code
     *
     * @return ����ֵ��Ӧ platform_permission.permission_code
     *
     * @mbg.generated
     */
    public String getPermissionCode() {
        return permissionCode;
    }

    /**
     * Ȩ�ޱ��<br/>
     * platform_permission.permission_code
     *
     * @param permissionCode ֵ��Ӧ platform_permission.permission_code
     *
     * @mbg.generated
     */
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode == null ? null : permissionCode.trim();
    }

    /**
     * Ȩ������<br/>
     * ����ֵ��Ӧ�ı����� platform_permission.permission_name
     *
     * @return ����ֵ��Ӧ platform_permission.permission_name
     *
     * @mbg.generated
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * Ȩ������<br/>
     * platform_permission.permission_name
     *
     * @param permissionName ֵ��Ӧ platform_permission.permission_name
     *
     * @mbg.generated
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    /**
     * �ϼ�Ȩ�ޱ��<br/>
     * ����ֵ��Ӧ�ı����� platform_permission.parent_permission_code
     *
     * @return ����ֵ��Ӧ platform_permission.parent_permission_code
     *
     * @mbg.generated
     */
    public String getParentPermissionCode() {
        return parentPermissionCode;
    }

    /**
     * �ϼ�Ȩ�ޱ��<br/>
     * platform_permission.parent_permission_code
     *
     * @param parentPermissionCode ֵ��Ӧ platform_permission.parent_permission_code
     *
     * @mbg.generated
     */
    public void setParentPermissionCode(String parentPermissionCode) {
        this.parentPermissionCode = parentPermissionCode == null ? null : parentPermissionCode.trim();
    }

    /**
     * ����<br/>
     * ����ֵ��Ӧ�ı����� platform_permission.sort
     *
     * @return ����ֵ��Ӧ platform_permission.sort
     *
     * @mbg.generated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * ����<br/>
     * platform_permission.sort
     *
     * @param sort ֵ��Ӧ platform_permission.sort
     *
     * @mbg.generated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * �ȼ�<br/>
     * ����ֵ��Ӧ�ı����� platform_permission.level
     *
     * @return ����ֵ��Ӧ platform_permission.level
     *
     * @mbg.generated
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * �ȼ�<br/>
     * platform_permission.level
     *
     * @param level ֵ��Ӧ platform_permission.level
     *
     * @mbg.generated
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * Ȩ�޶�Ӧ��url<br/>
     * ����ֵ��Ӧ�ı����� platform_permission.page_url
     *
     * @return ����ֵ��Ӧ platform_permission.page_url
     *
     * @mbg.generated
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * Ȩ�޶�Ӧ��url<br/>
     * platform_permission.page_url
     *
     * @param pageUrl ֵ��Ӧ platform_permission.page_url
     *
     * @mbg.generated
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    /**
     * ��ע<br/>
     * ����ֵ��Ӧ�ı����� platform_permission.remark
     *
     * @return ����ֵ��Ӧ platform_permission.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * ��ע<br/>
     * platform_permission.remark
     *
     * @param remark ֵ��Ӧ platform_permission.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * ״̬  0��Ч  1��Ч<br/>
     * ����ֵ��Ӧ�ı����� platform_permission.status
     *
     * @return ����ֵ��Ӧ platform_permission.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * ״̬  0��Ч  1��Ч<br/>
     * platform_permission.status
     *
     * @param status ֵ��Ӧ platform_permission.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <br/>
     * ����ֵ��Ӧ�ı����� platform_permission.create_time
     *
     * @return ����ֵ��Ӧ platform_permission.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * <br/>
     * platform_permission.create_time
     *
     * @param createTime ֵ��Ӧ platform_permission.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * ������<br/>
     * ����ֵ��Ӧ�ı����� platform_permission.creator
     *
     * @return ����ֵ��Ӧ platform_permission.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * ������<br/>
     * platform_permission.creator
     *
     * @param creator ֵ��Ӧ platform_permission.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * ����޸�ʱ��<br/>
     * ����ֵ��Ӧ�ı����� platform_permission.last_modify_time
     *
     * @return ����ֵ��Ӧ platform_permission.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * ����޸�ʱ��<br/>
     * platform_permission.last_modify_time
     *
     * @param lastModifyTime ֵ��Ӧ platform_permission.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * �޸���<br/>
     * ����ֵ��Ӧ�ı����� platform_permission.modifier
     *
     * @return ����ֵ��Ӧ platform_permission.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * �޸���<br/>
     * platform_permission.modifier
     *
     * @param modifier ֵ��Ӧ platform_permission.modifier
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
        PlatformPermission other = (PlatformPermission) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPermissionCode() == null ? other.getPermissionCode() == null : this.getPermissionCode().equals(other.getPermissionCode()))
            && (this.getPermissionName() == null ? other.getPermissionName() == null : this.getPermissionName().equals(other.getPermissionName()))
            && (this.getParentPermissionCode() == null ? other.getParentPermissionCode() == null : this.getParentPermissionCode().equals(other.getParentPermissionCode()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getPageUrl() == null ? other.getPageUrl() == null : this.getPageUrl().equals(other.getPageUrl()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
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
        result = prime * result + ((getPermissionCode() == null) ? 0 : getPermissionCode().hashCode());
        result = prime * result + ((getPermissionName() == null) ? 0 : getPermissionName().hashCode());
        result = prime * result + ((getParentPermissionCode() == null) ? 0 : getParentPermissionCode().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getPageUrl() == null) ? 0 : getPageUrl().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", permissionCode=").append(permissionCode);
        sb.append(", permissionName=").append(permissionName);
        sb.append(", parentPermissionCode=").append(parentPermissionCode);
        sb.append(", sort=").append(sort);
        sb.append(", level=").append(level);
        sb.append(", pageUrl=").append(pageUrl);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}