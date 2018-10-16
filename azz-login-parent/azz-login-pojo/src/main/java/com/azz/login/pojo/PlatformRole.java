package com.azz.login.pojo;

import java.io.Serializable;
import java.util.Date;

public class PlatformRole implements Serializable {
    /**
     * ����
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * ��ɫ����
     *
     * @mbg.generated
     */
    private String roleName;

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
     * ����ֵ��Ӧ�ı����� platform_role.id
     *
     * @return ����ֵ��Ӧ platform_role.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * ����<br/>
     * platform_role.id
     *
     * @param id ֵ��Ӧ platform_role.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * ��ɫ����<br/>
     * ����ֵ��Ӧ�ı����� platform_role.role_name
     *
     * @return ����ֵ��Ӧ platform_role.role_name
     *
     * @mbg.generated
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * ��ɫ����<br/>
     * platform_role.role_name
     *
     * @param roleName ֵ��Ӧ platform_role.role_name
     *
     * @mbg.generated
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * ��ע<br/>
     * ����ֵ��Ӧ�ı����� platform_role.remark
     *
     * @return ����ֵ��Ӧ platform_role.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * ��ע<br/>
     * platform_role.remark
     *
     * @param remark ֵ��Ӧ platform_role.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * ״̬  0��Ч  1��Ч<br/>
     * ����ֵ��Ӧ�ı����� platform_role.status
     *
     * @return ����ֵ��Ӧ platform_role.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * ״̬  0��Ч  1��Ч<br/>
     * platform_role.status
     *
     * @param status ֵ��Ӧ platform_role.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <br/>
     * ����ֵ��Ӧ�ı����� platform_role.create_time
     *
     * @return ����ֵ��Ӧ platform_role.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * <br/>
     * platform_role.create_time
     *
     * @param createTime ֵ��Ӧ platform_role.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * ������<br/>
     * ����ֵ��Ӧ�ı����� platform_role.creator
     *
     * @return ����ֵ��Ӧ platform_role.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * ������<br/>
     * platform_role.creator
     *
     * @param creator ֵ��Ӧ platform_role.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * ����޸�ʱ��<br/>
     * ����ֵ��Ӧ�ı����� platform_role.last_modify_time
     *
     * @return ����ֵ��Ӧ platform_role.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * ����޸�ʱ��<br/>
     * platform_role.last_modify_time
     *
     * @param lastModifyTime ֵ��Ӧ platform_role.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * �޸���<br/>
     * ����ֵ��Ӧ�ı����� platform_role.modifier
     *
     * @return ����ֵ��Ӧ platform_role.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * �޸���<br/>
     * platform_role.modifier
     *
     * @param modifier ֵ��Ӧ platform_role.modifier
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
        PlatformRole other = (PlatformRole) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
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
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
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
        sb.append(", roleName=").append(roleName);
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