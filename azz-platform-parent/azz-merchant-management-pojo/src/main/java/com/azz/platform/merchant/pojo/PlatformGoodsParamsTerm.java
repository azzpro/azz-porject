package com.azz.platform.merchant.pojo;


import java.io.Serializable;
import java.util.Date;

public class PlatformGoodsParamsTerm implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 参数项编号
     *
     * @mbg.generated
     */
    private String paramsCode;

    /**
     * 参数项名称
     *
     * @mbg.generated
     */
    private String paramsName;

    /**
     * 参数主键
     *
     * @mbg.generated
     */
    private Long paramsId;

    /**
     * 参数项类型(1,下拉2参数填写)
     *
     * @mbg.generated
     */
    private Boolean paramsType;

    /**
     * 是否必选(1 必选 2非必选)
     *
     * @mbg.generated
     */
    private Boolean paramsChoice;

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
     * 主键<br/>
     * 返回值对应的表列名 platform_goods_params_term.id
     *
     * @return 返回值对应 platform_goods_params_term.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * platform_goods_params_term.id
     *
     * @param id 值对应 platform_goods_params_term.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 参数项编号<br/>
     * 返回值对应的表列名 platform_goods_params_term.params_code
     *
     * @return 返回值对应 platform_goods_params_term.params_code
     *
     * @mbg.generated
     */
    public String getParamsCode() {
        return paramsCode;
    }

    /**
     * 参数项编号<br/>
     * platform_goods_params_term.params_code
     *
     * @param paramsCode 值对应 platform_goods_params_term.params_code
     *
     * @mbg.generated
     */
    public void setParamsCode(String paramsCode) {
        this.paramsCode = paramsCode == null ? null : paramsCode.trim();
    }

    /**
     * 参数项名称<br/>
     * 返回值对应的表列名 platform_goods_params_term.params_name
     *
     * @return 返回值对应 platform_goods_params_term.params_name
     *
     * @mbg.generated
     */
    public String getParamsName() {
        return paramsName;
    }

    /**
     * 参数项名称<br/>
     * platform_goods_params_term.params_name
     *
     * @param paramsName 值对应 platform_goods_params_term.params_name
     *
     * @mbg.generated
     */
    public void setParamsName(String paramsName) {
        this.paramsName = paramsName == null ? null : paramsName.trim();
    }

    /**
     * 参数主键<br/>
     * 返回值对应的表列名 platform_goods_params_term.params_id
     *
     * @return 返回值对应 platform_goods_params_term.params_id
     *
     * @mbg.generated
     */
    public Long getParamsId() {
        return paramsId;
    }

    /**
     * 参数主键<br/>
     * platform_goods_params_term.params_id
     *
     * @param paramsId 值对应 platform_goods_params_term.params_id
     *
     * @mbg.generated
     */
    public void setParamsId(Long paramsId) {
        this.paramsId = paramsId;
    }

    /**
     * 参数项类型(1,下拉2参数填写)<br/>
     * 返回值对应的表列名 platform_goods_params_term.params_type
     *
     * @return 返回值对应 platform_goods_params_term.params_type
     *
     * @mbg.generated
     */
    public Boolean getParamsType() {
        return paramsType;
    }

    /**
     * 参数项类型(1,下拉2参数填写)<br/>
     * platform_goods_params_term.params_type
     *
     * @param paramsType 值对应 platform_goods_params_term.params_type
     *
     * @mbg.generated
     */
    public void setParamsType(Boolean paramsType) {
        this.paramsType = paramsType;
    }

    /**
     * 是否必选(1 必选 2非必选)<br/>
     * 返回值对应的表列名 platform_goods_params_term.params_choice
     *
     * @return 返回值对应 platform_goods_params_term.params_choice
     *
     * @mbg.generated
     */
    public Boolean getParamsChoice() {
        return paramsChoice;
    }

    /**
     * 是否必选(1 必选 2非必选)<br/>
     * platform_goods_params_term.params_choice
     *
     * @param paramsChoice 值对应 platform_goods_params_term.params_choice
     *
     * @mbg.generated
     */
    public void setParamsChoice(Boolean paramsChoice) {
        this.paramsChoice = paramsChoice;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 platform_goods_params_term.creator
     *
     * @return 返回值对应 platform_goods_params_term.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * platform_goods_params_term.creator
     *
     * @param creator 值对应 platform_goods_params_term.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 platform_goods_params_term.create_time
     *
     * @return 返回值对应 platform_goods_params_term.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * platform_goods_params_term.create_time
     *
     * @param createTime 值对应 platform_goods_params_term.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 platform_goods_params_term.modifier
     *
     * @return 返回值对应 platform_goods_params_term.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * platform_goods_params_term.modifier
     *
     * @param modifier 值对应 platform_goods_params_term.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 platform_goods_params_term.modify_time
     *
     * @return 返回值对应 platform_goods_params_term.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * platform_goods_params_term.modify_time
     *
     * @param modifyTime 值对应 platform_goods_params_term.modify_time
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
        PlatformGoodsParamsTerm other = (PlatformGoodsParamsTerm) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParamsCode() == null ? other.getParamsCode() == null : this.getParamsCode().equals(other.getParamsCode()))
            && (this.getParamsName() == null ? other.getParamsName() == null : this.getParamsName().equals(other.getParamsName()))
            && (this.getParamsId() == null ? other.getParamsId() == null : this.getParamsId().equals(other.getParamsId()))
            && (this.getParamsType() == null ? other.getParamsType() == null : this.getParamsType().equals(other.getParamsType()))
            && (this.getParamsChoice() == null ? other.getParamsChoice() == null : this.getParamsChoice().equals(other.getParamsChoice()))
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
        result = prime * result + ((getParamsCode() == null) ? 0 : getParamsCode().hashCode());
        result = prime * result + ((getParamsName() == null) ? 0 : getParamsName().hashCode());
        result = prime * result + ((getParamsId() == null) ? 0 : getParamsId().hashCode());
        result = prime * result + ((getParamsType() == null) ? 0 : getParamsType().hashCode());
        result = prime * result + ((getParamsChoice() == null) ? 0 : getParamsChoice().hashCode());
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
        sb.append(", paramsCode=").append(paramsCode);
        sb.append(", paramsName=").append(paramsName);
        sb.append(", paramsId=").append(paramsId);
        sb.append(", paramsType=").append(paramsType);
        sb.append(", paramsChoice=").append(paramsChoice);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}