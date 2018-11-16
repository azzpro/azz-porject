package com.azz.order.merchant.pojo;


import java.io.Serializable;
import java.util.Date;

public class ExpressCompany implements Serializable {
    private Integer id;

    /**
     * 快递公司名称
     *
     * @mbg.generated
     */
    private String companyName;

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
    private Date modifyTime;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * <br/>
     * 返回值对应的表列名 express_company.id
     *
     * @return 返回值对应 express_company.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * <br/>
     * express_company.id
     *
     * @param id 值对应 express_company.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 快递公司名称<br/>
     * 返回值对应的表列名 express_company.company_name
     *
     * @return 返回值对应 express_company.company_name
     *
     * @mbg.generated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 快递公司名称<br/>
     * express_company.company_name
     *
     * @param companyName 值对应 express_company.company_name
     *
     * @mbg.generated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 express_company.create_time
     *
     * @return 返回值对应 express_company.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * express_company.create_time
     *
     * @param createTime 值对应 express_company.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 express_company.creator
     *
     * @return 返回值对应 express_company.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * express_company.creator
     *
     * @param creator 值对应 express_company.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 express_company.modifier
     *
     * @return 返回值对应 express_company.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * express_company.modifier
     *
     * @param modifier 值对应 express_company.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 express_company.modify_time
     *
     * @return 返回值对应 express_company.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * express_company.modify_time
     *
     * @param modifyTime 值对应 express_company.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 备注<br/>
     * 返回值对应的表列名 express_company.remark
     *
     * @return 返回值对应 express_company.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * express_company.remark
     *
     * @param remark 值对应 express_company.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        ExpressCompany other = (ExpressCompany) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyName=").append(companyName);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}