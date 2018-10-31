package com.azz.platform.merchant.pojo;


import java.io.Serializable;

public class PlatformGoodsParams implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 参数编号
     *
     * @mbg.generated
     */
    private String paramsCode;

    private static final long serialVersionUID = 1L;

    /**
     * 主键<br/>
     * 返回值对应的表列名 platform_goods_params.id
     *
     * @return 返回值对应 platform_goods_params.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * platform_goods_params.id
     *
     * @param id 值对应 platform_goods_params.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 参数编号<br/>
     * 返回值对应的表列名 platform_goods_params.params_code
     *
     * @return 返回值对应 platform_goods_params.params_code
     *
     * @mbg.generated
     */
    public String getParamsCode() {
        return paramsCode;
    }

    /**
     * 参数编号<br/>
     * platform_goods_params.params_code
     *
     * @param paramsCode 值对应 platform_goods_params.params_code
     *
     * @mbg.generated
     */
    public void setParamsCode(String paramsCode) {
        this.paramsCode = paramsCode == null ? null : paramsCode.trim();
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
        PlatformGoodsParams other = (PlatformGoodsParams) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParamsCode() == null ? other.getParamsCode() == null : this.getParamsCode().equals(other.getParamsCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParamsCode() == null) ? 0 : getParamsCode().hashCode());
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}