package com.azz.merchant.pojo;


import java.io.Serializable;

public class MerchantGoodsProductParams implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 参数项名称
     *
     * @mbg.generated
     */
    private String paramsName;

    /**
     * 参数项ID
     *
     * @mbg.generated
     */
    private Integer paramsId;

    /**
     * 参数值
     *
     * @mbg.generated
     */
    private String paramsValue;

    /**
     * 产品ID
     *
     * @mbg.generated
     */
    private Long productId;

    private static final long serialVersionUID = 1L;

    /**
     * 主键<br/>
     * 返回值对应的表列名 merchant_goods_product_params.id
     *
     * @return 返回值对应 merchant_goods_product_params.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * merchant_goods_product_params.id
     *
     * @param id 值对应 merchant_goods_product_params.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 参数项名称<br/>
     * 返回值对应的表列名 merchant_goods_product_params.params_name
     *
     * @return 返回值对应 merchant_goods_product_params.params_name
     *
     * @mbg.generated
     */
    public String getParamsName() {
        return paramsName;
    }

    /**
     * 参数项名称<br/>
     * merchant_goods_product_params.params_name
     *
     * @param paramsName 值对应 merchant_goods_product_params.params_name
     *
     * @mbg.generated
     */
    public void setParamsName(String paramsName) {
        this.paramsName = paramsName == null ? null : paramsName.trim();
    }

    /**
     * 参数项ID<br/>
     * 返回值对应的表列名 merchant_goods_product_params.params_id
     *
     * @return 返回值对应 merchant_goods_product_params.params_id
     *
     * @mbg.generated
     */
    public Integer getParamsId() {
        return paramsId;
    }

    /**
     * 参数项ID<br/>
     * merchant_goods_product_params.params_id
     *
     * @param paramsId 值对应 merchant_goods_product_params.params_id
     *
     * @mbg.generated
     */
    public void setParamsId(Integer paramsId) {
        this.paramsId = paramsId;
    }

    /**
     * 参数值<br/>
     * 返回值对应的表列名 merchant_goods_product_params.params_value
     *
     * @return 返回值对应 merchant_goods_product_params.params_value
     *
     * @mbg.generated
     */
    public String getParamsValue() {
        return paramsValue;
    }

    /**
     * 参数值<br/>
     * merchant_goods_product_params.params_value
     *
     * @param paramsValue 值对应 merchant_goods_product_params.params_value
     *
     * @mbg.generated
     */
    public void setParamsValue(String paramsValue) {
        this.paramsValue = paramsValue == null ? null : paramsValue.trim();
    }

    /**
     * 产品ID<br/>
     * 返回值对应的表列名 merchant_goods_product_params.product_id
     *
     * @return 返回值对应 merchant_goods_product_params.product_id
     *
     * @mbg.generated
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 产品ID<br/>
     * merchant_goods_product_params.product_id
     *
     * @param productId 值对应 merchant_goods_product_params.product_id
     *
     * @mbg.generated
     */
    public void setProductId(Long productId) {
        this.productId = productId;
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
        MerchantGoodsProductParams other = (MerchantGoodsProductParams) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParamsName() == null ? other.getParamsName() == null : this.getParamsName().equals(other.getParamsName()))
            && (this.getParamsId() == null ? other.getParamsId() == null : this.getParamsId().equals(other.getParamsId()))
            && (this.getParamsValue() == null ? other.getParamsValue() == null : this.getParamsValue().equals(other.getParamsValue()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParamsName() == null) ? 0 : getParamsName().hashCode());
        result = prime * result + ((getParamsId() == null) ? 0 : getParamsId().hashCode());
        result = prime * result + ((getParamsValue() == null) ? 0 : getParamsValue().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", paramsName=").append(paramsName);
        sb.append(", paramsId=").append(paramsId);
        sb.append(", paramsValue=").append(paramsValue);
        sb.append(", productId=").append(productId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}