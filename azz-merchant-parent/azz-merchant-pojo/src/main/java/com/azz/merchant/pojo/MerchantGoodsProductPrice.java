package com.azz.merchant.pojo;


import java.io.Serializable;
import java.math.BigDecimal;

public class MerchantGoodsProductPrice implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 交期(单位：天)
     *
     * @mbg.generated
     */
    private Integer deliveryDate;

    /**
     * 价格
     *
     * @mbg.generated
     */
    private BigDecimal price;

    /**
     * 产品ID
     *
     * @mbg.generated
     */
    private Long productId;

    private static final long serialVersionUID = 1L;

    /**
     * 主键<br/>
     * 返回值对应的表列名 merchant_goods_product_price.id
     *
     * @return 返回值对应 merchant_goods_product_price.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * merchant_goods_product_price.id
     *
     * @param id 值对应 merchant_goods_product_price.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 交期(单位：天)<br/>
     * 返回值对应的表列名 merchant_goods_product_price.delivery_date
     *
     * @return 返回值对应 merchant_goods_product_price.delivery_date
     *
     * @mbg.generated
     */
    public Integer getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * 交期(单位：天)<br/>
     * merchant_goods_product_price.delivery_date
     *
     * @param deliveryDate 值对应 merchant_goods_product_price.delivery_date
     *
     * @mbg.generated
     */
    public void setDeliveryDate(Integer deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * 价格<br/>
     * 返回值对应的表列名 merchant_goods_product_price.price
     *
     * @return 返回值对应 merchant_goods_product_price.price
     *
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 价格<br/>
     * merchant_goods_product_price.price
     *
     * @param price 值对应 merchant_goods_product_price.price
     *
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 产品ID<br/>
     * 返回值对应的表列名 merchant_goods_product_price.product_id
     *
     * @return 返回值对应 merchant_goods_product_price.product_id
     *
     * @mbg.generated
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 产品ID<br/>
     * merchant_goods_product_price.product_id
     *
     * @param productId 值对应 merchant_goods_product_price.product_id
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
        MerchantGoodsProductPrice other = (MerchantGoodsProductPrice) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDeliveryDate() == null ? other.getDeliveryDate() == null : this.getDeliveryDate().equals(other.getDeliveryDate()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDeliveryDate() == null) ? 0 : getDeliveryDate().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
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
        sb.append(", deliveryDate=").append(deliveryDate);
        sb.append(", price=").append(price);
        sb.append(", productId=").append(productId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}