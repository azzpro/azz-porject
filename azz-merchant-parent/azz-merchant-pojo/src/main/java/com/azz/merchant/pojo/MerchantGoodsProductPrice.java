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


   
}