package com.azz.login.pojo;


import java.io.Serializable;
import java.util.Date;

public class PlatformDept implements Serializable {
    /**
     * ����
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * ���ű���
     *
     * @mbg.generated
     */
    private String deptCode;

    /**
     * ��������
     *
     * @mbg.generated
     */
    private String deptName;

    /**
     * ����
     *
     * @mbg.generated
     */
    private String description;

    /**
     * ״̬   0��Ч  1��Ч
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
     * ����ֵ��Ӧ�ı����� platform_dept.id
     *
     * @return ����ֵ��Ӧ platform_dept.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * ����<br/>
     * platform_dept.id
     *
     * @param id ֵ��Ӧ platform_dept.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * ���ű���<br/>
     * ����ֵ��Ӧ�ı����� platform_dept.dept_code
     *
     * @return ����ֵ��Ӧ platform_dept.dept_code
     *
     * @mbg.generated
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * ���ű���<br/>
     * platform_dept.dept_code
     *
     * @param deptCode ֵ��Ӧ platform_dept.dept_code
     *
     * @mbg.generated
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * ��������<br/>
     * ����ֵ��Ӧ�ı����� platform_dept.dept_name
     *
     * @return ����ֵ��Ӧ platform_dept.dept_name
     *
     * @mbg.generated
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * ��������<br/>
     * platform_dept.dept_name
     *
     * @param deptName ֵ��Ӧ platform_dept.dept_name
     *
     * @mbg.generated
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * ����<br/>
     * ����ֵ��Ӧ�ı����� platform_dept.description
     *
     * @return ����ֵ��Ӧ platform_dept.description
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * ����<br/>
     * platform_dept.description
     *
     * @param description ֵ��Ӧ platform_dept.description
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * ״̬   0��Ч  1��Ч<br/>
     * ����ֵ��Ӧ�ı����� platform_dept.status
     *
     * @return ����ֵ��Ӧ platform_dept.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * ״̬   0��Ч  1��Ч<br/>
     * platform_dept.status
     *
     * @param status ֵ��Ӧ platform_dept.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <br/>
     * ����ֵ��Ӧ�ı����� platform_dept.create_time
     *
     * @return ����ֵ��Ӧ platform_dept.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * <br/>
     * platform_dept.create_time
     *
     * @param createTime ֵ��Ӧ platform_dept.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * ������<br/>
     * ����ֵ��Ӧ�ı����� platform_dept.creator
     *
     * @return ����ֵ��Ӧ platform_dept.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * ������<br/>
     * platform_dept.creator
     *
     * @param creator ֵ��Ӧ platform_dept.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * ����޸�ʱ��<br/>
     * ����ֵ��Ӧ�ı����� platform_dept.last_modify_time
     *
     * @return ����ֵ��Ӧ platform_dept.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * ����޸�ʱ��<br/>
     * platform_dept.last_modify_time
     *
     * @param lastModifyTime ֵ��Ӧ platform_dept.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * �޸���<br/>
     * ����ֵ��Ӧ�ı����� platform_dept.modifier
     *
     * @return ����ֵ��Ӧ platform_dept.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * �޸���<br/>
     * platform_dept.modifier
     *
     * @param modifier ֵ��Ӧ platform_dept.modifier
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
        PlatformDept other = (PlatformDept) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDeptCode() == null ? other.getDeptCode() == null : this.getDeptCode().equals(other.getDeptCode()))
            && (this.getDeptName() == null ? other.getDeptName() == null : this.getDeptName().equals(other.getDeptName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
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
        result = prime * result + ((getDeptCode() == null) ? 0 : getDeptCode().hashCode());
        result = prime * result + ((getDeptName() == null) ? 0 : getDeptName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
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
        sb.append(", deptCode=").append(deptCode);
        sb.append(", deptName=").append(deptName);
        sb.append(", description=").append(description);
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