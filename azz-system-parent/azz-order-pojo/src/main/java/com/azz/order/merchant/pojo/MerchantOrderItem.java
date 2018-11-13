package com.azz.order.merchant.pojo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MerchantOrderItem implements Serializable {
    /**
     * 自增id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 关联商户订单自增id
     *
     * @mbg.generated
     */
    private Long merchantOrderId;

    /**
     * 产品id
     *
     * @mbg.generated
     */
    private Long productId;

    /**
     * 模组名称
     *
     * @mbg.generated
     */
    private String moduleName;

    /**
     * 产品参数名称
     *
     * @mbg.generated
     */
    private String productParamsName;

    /**
     * 分类名称
     *
     * @mbg.generated
     */
    private String assortmentName;

    /**
     * 品牌名称
     *
     * @mbg.generated
     */
    private String brandName;

    /**
     * 交期
     *
     * @mbg.generated
     */
    private Integer deliveryDate;

    /**
     * 交货日期   交期+订单支付日期
     *
     * @mbg.generated
     */
    private Date deliveryTime;

    /**
     * 模组图片url
     *
     * @mbg.generated
     */
    private String modulePicUrl;

    /**
     * 商品价格
     *
     * @mbg.generated
     */
    private BigDecimal productPrice;

    /**
     * 下单数量
     *
     * @mbg.generated
     */
    private Integer quantity;

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
     * 最后修改时间
     *
     * @mbg.generated
     */
    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    /**
     * 自增id<br/>
     * 返回值对应的表列名 merchant_order_item.id
     *
     * @return 返回值对应 merchant_order_item.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 自增id<br/>
     * merchant_order_item.id
     *
     * @param id 值对应 merchant_order_item.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 关联商户订单自增id<br/>
     * 返回值对应的表列名 merchant_order_item.merchant_order_id
     *
     * @return 返回值对应 merchant_order_item.merchant_order_id
     *
     * @mbg.generated
     */
    public Long getMerchantOrderId() {
        return merchantOrderId;
    }

    /**
     * 关联商户订单自增id<br/>
     * merchant_order_item.merchant_order_id
     *
     * @param merchantOrderId 值对应 merchant_order_item.merchant_order_id
     *
     * @mbg.generated
     */
    public void setMerchantOrderId(Long merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    /**
     * 产品id<br/>
     * 返回值对应的表列名 merchant_order_item.product_id
     *
     * @return 返回值对应 merchant_order_item.product_id
     *
     * @mbg.generated
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 产品id<br/>
     * merchant_order_item.product_id
     *
     * @param productId 值对应 merchant_order_item.product_id
     *
     * @mbg.generated
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 模组名称<br/>
     * 返回值对应的表列名 merchant_order_item.module_name
     *
     * @return 返回值对应 merchant_order_item.module_name
     *
     * @mbg.generated
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * 模组名称<br/>
     * merchant_order_item.module_name
     *
     * @param moduleName 值对应 merchant_order_item.module_name
     *
     * @mbg.generated
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    /**
     * 产品参数名称<br/>
     * 返回值对应的表列名 merchant_order_item.product_params_name
     *
     * @return 返回值对应 merchant_order_item.product_params_name
     *
     * @mbg.generated
     */
    public String getProductParamsName() {
        return productParamsName;
    }

    /**
     * 产品参数名称<br/>
     * merchant_order_item.product_params_name
     *
     * @param productParamsName 值对应 merchant_order_item.product_params_name
     *
     * @mbg.generated
     */
    public void setProductParamsName(String productParamsName) {
        this.productParamsName = productParamsName == null ? null : productParamsName.trim();
    }

    /**
     * 分类名称<br/>
     * 返回值对应的表列名 merchant_order_item.assortment_name
     *
     * @return 返回值对应 merchant_order_item.assortment_name
     *
     * @mbg.generated
     */
    public String getAssortmentName() {
        return assortmentName;
    }

    /**
     * 分类名称<br/>
     * merchant_order_item.assortment_name
     *
     * @param assortmentName 值对应 merchant_order_item.assortment_name
     *
     * @mbg.generated
     */
    public void setAssortmentName(String assortmentName) {
        this.assortmentName = assortmentName == null ? null : assortmentName.trim();
    }

    /**
     * 品牌名称<br/>
     * 返回值对应的表列名 merchant_order_item.brand_name
     *
     * @return 返回值对应 merchant_order_item.brand_name
     *
     * @mbg.generated
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 品牌名称<br/>
     * merchant_order_item.brand_name
     *
     * @param brandName 值对应 merchant_order_item.brand_name
     *
     * @mbg.generated
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    /**
     * 交期<br/>
     * 返回值对应的表列名 merchant_order_item.delivery_date
     *
     * @return 返回值对应 merchant_order_item.delivery_date
     *
     * @mbg.generated
     */
    public Integer getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * 交期<br/>
     * merchant_order_item.delivery_date
     *
     * @param deliveryDate 值对应 merchant_order_item.delivery_date
     *
     * @mbg.generated
     */
    public void setDeliveryDate(Integer deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * 交货日期   交期+订单支付日期<br/>
     * 返回值对应的表列名 merchant_order_item.delivery_time
     *
     * @return 返回值对应 merchant_order_item.delivery_time
     *
     * @mbg.generated
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * 交货日期   交期+订单支付日期<br/>
     * merchant_order_item.delivery_time
     *
     * @param deliveryTime 值对应 merchant_order_item.delivery_time
     *
     * @mbg.generated
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * 模组图片url<br/>
     * 返回值对应的表列名 merchant_order_item.module_pic_url
     *
     * @return 返回值对应 merchant_order_item.module_pic_url
     *
     * @mbg.generated
     */
    public String getModulePicUrl() {
        return modulePicUrl;
    }

    /**
     * 模组图片url<br/>
     * merchant_order_item.module_pic_url
     *
     * @param modulePicUrl 值对应 merchant_order_item.module_pic_url
     *
     * @mbg.generated
     */
    public void setModulePicUrl(String modulePicUrl) {
        this.modulePicUrl = modulePicUrl == null ? null : modulePicUrl.trim();
    }

    /**
     * 商品价格<br/>
     * 返回值对应的表列名 merchant_order_item.product_price
     *
     * @return 返回值对应 merchant_order_item.product_price
     *
     * @mbg.generated
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     * 商品价格<br/>
     * merchant_order_item.product_price
     *
     * @param productPrice 值对应 merchant_order_item.product_price
     *
     * @mbg.generated
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 下单数量<br/>
     * 返回值对应的表列名 merchant_order_item.quantity
     *
     * @return 返回值对应 merchant_order_item.quantity
     *
     * @mbg.generated
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 下单数量<br/>
     * merchant_order_item.quantity
     *
     * @param quantity 值对应 merchant_order_item.quantity
     *
     * @mbg.generated
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 merchant_order_item.creator
     *
     * @return 返回值对应 merchant_order_item.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * merchant_order_item.creator
     *
     * @param creator 值对应 merchant_order_item.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 merchant_order_item.create_time
     *
     * @return 返回值对应 merchant_order_item.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * merchant_order_item.create_time
     *
     * @param createTime 值对应 merchant_order_item.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 merchant_order_item.modifier
     *
     * @return 返回值对应 merchant_order_item.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * merchant_order_item.modifier
     *
     * @param modifier 值对应 merchant_order_item.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 merchant_order_item.modify_time
     *
     * @return 返回值对应 merchant_order_item.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 最后修改时间<br/>
     * merchant_order_item.modify_time
     *
     * @param modifyTime 值对应 merchant_order_item.modify_time
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
        MerchantOrderItem other = (MerchantOrderItem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantOrderId() == null ? other.getMerchantOrderId() == null : this.getMerchantOrderId().equals(other.getMerchantOrderId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getModuleName() == null ? other.getModuleName() == null : this.getModuleName().equals(other.getModuleName()))
            && (this.getProductParamsName() == null ? other.getProductParamsName() == null : this.getProductParamsName().equals(other.getProductParamsName()))
            && (this.getAssortmentName() == null ? other.getAssortmentName() == null : this.getAssortmentName().equals(other.getAssortmentName()))
            && (this.getBrandName() == null ? other.getBrandName() == null : this.getBrandName().equals(other.getBrandName()))
            && (this.getDeliveryDate() == null ? other.getDeliveryDate() == null : this.getDeliveryDate().equals(other.getDeliveryDate()))
            && (this.getDeliveryTime() == null ? other.getDeliveryTime() == null : this.getDeliveryTime().equals(other.getDeliveryTime()))
            && (this.getModulePicUrl() == null ? other.getModulePicUrl() == null : this.getModulePicUrl().equals(other.getModulePicUrl()))
            && (this.getProductPrice() == null ? other.getProductPrice() == null : this.getProductPrice().equals(other.getProductPrice()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
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
        result = prime * result + ((getMerchantOrderId() == null) ? 0 : getMerchantOrderId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getModuleName() == null) ? 0 : getModuleName().hashCode());
        result = prime * result + ((getProductParamsName() == null) ? 0 : getProductParamsName().hashCode());
        result = prime * result + ((getAssortmentName() == null) ? 0 : getAssortmentName().hashCode());
        result = prime * result + ((getBrandName() == null) ? 0 : getBrandName().hashCode());
        result = prime * result + ((getDeliveryDate() == null) ? 0 : getDeliveryDate().hashCode());
        result = prime * result + ((getDeliveryTime() == null) ? 0 : getDeliveryTime().hashCode());
        result = prime * result + ((getModulePicUrl() == null) ? 0 : getModulePicUrl().hashCode());
        result = prime * result + ((getProductPrice() == null) ? 0 : getProductPrice().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
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
        sb.append(", merchantOrderId=").append(merchantOrderId);
        sb.append(", productId=").append(productId);
        sb.append(", moduleName=").append(moduleName);
        sb.append(", productParamsName=").append(productParamsName);
        sb.append(", assortmentName=").append(assortmentName);
        sb.append(", brandName=").append(brandName);
        sb.append(", deliveryDate=").append(deliveryDate);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", modulePicUrl=").append(modulePicUrl);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", quantity=").append(quantity);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}