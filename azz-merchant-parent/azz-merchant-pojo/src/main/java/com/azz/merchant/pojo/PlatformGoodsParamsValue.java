package com.azz.merchant.pojo;

import java.io.Serializable;

public class PlatformGoodsParamsValue implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 参数 父ID
     *
     * @mbg.generated
     */
    private Long paramsParentId;

    /**
     * 参数值
     *
     * @mbg.generated
     */
    private Long paramsValue;

    /**
     * 参数单位
     *
     * @mbg.generated
     */
    private String paramsUnit;

    private static final long serialVersionUID = 1L;

    /**
     * 主键<br/>
     * 返回值对应的表列名 platform_goods_params_value.id
     *
     * @return 返回值对应 platform_goods_params_value.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * platform_goods_params_value.id
     *
     * @param id 值对应 platform_goods_params_value.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 参数 父ID<br/>
     * 返回值对应的表列名 platform_goods_params_value.params_parent_id
     *
     * @return 返回值对应 platform_goods_params_value.params_parent_id
     *
     * @mbg.generated
     */
    public Long getParamsParentId() {
        return paramsParentId;
    }

    /**
     * 参数 父ID<br/>
     * platform_goods_params_value.params_parent_id
     *
     * @param paramsParentId 值对应 platform_goods_params_value.params_parent_id
     *
     * @mbg.generated
     */
    public void setParamsParentId(Long paramsParentId) {
        this.paramsParentId = paramsParentId;
    }

    /**
     * 参数值<br/>
     * 返回值对应的表列名 platform_goods_params_value.params_value
     *
     * @return 返回值对应 platform_goods_params_value.params_value
     *
     * @mbg.generated
     */
    public Long getParamsValue() {
        return paramsValue;
    }

    /**
     * 参数值<br/>
     * platform_goods_params_value.params_value
     *
     * @param paramsValue 值对应 platform_goods_params_value.params_value
     *
     * @mbg.generated
     */
    public void setParamsValue(Long paramsValue) {
        this.paramsValue = paramsValue;
    }

    /**
     * 参数单位<br/>
     * 返回值对应的表列名 platform_goods_params_value.params_unit
     *
     * @return 返回值对应 platform_goods_params_value.params_unit
     *
     * @mbg.generated
     */
    public String getParamsUnit() {
        return paramsUnit;
    }

    /**
     * 参数单位<br/>
     * platform_goods_params_value.params_unit
     *
     * @param paramsUnit 值对应 platform_goods_params_value.params_unit
     *
     * @mbg.generated
     */
    public void setParamsUnit(String paramsUnit) {
        this.paramsUnit = paramsUnit == null ? null : paramsUnit.trim();
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
        PlatformGoodsParamsValue other = (PlatformGoodsParamsValue) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParamsParentId() == null ? other.getParamsParentId() == null : this.getParamsParentId().equals(other.getParamsParentId()))
            && (this.getParamsValue() == null ? other.getParamsValue() == null : this.getParamsValue().equals(other.getParamsValue()))
            && (this.getParamsUnit() == null ? other.getParamsUnit() == null : this.getParamsUnit().equals(other.getParamsUnit()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParamsParentId() == null) ? 0 : getParamsParentId().hashCode());
        result = prime * result + ((getParamsValue() == null) ? 0 : getParamsValue().hashCode());
        result = prime * result + ((getParamsUnit() == null) ? 0 : getParamsUnit().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", paramsParentId=").append(paramsParentId);
        sb.append(", paramsValue=").append(paramsValue);
        sb.append(", paramsUnit=").append(paramsUnit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}