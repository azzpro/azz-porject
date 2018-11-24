package com.azz.order.client.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrderItemPersonal implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 订单id
     *
     * @mbg.generated
     */
    private Long clientOrderId;

    /**
     * 所属产品编码
     *
     * @mbg.generated
     */
    private String productCode;

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
     * 模组名称
     *
     * @mbg.generated
     */
    private String moduleName;

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
     * 主键id<br/>
     * 返回值对应的表列名 client_order_item_personal.id
     *
     * @return 返回值对应 client_order_item_personal.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * client_order_item_personal.id
     *
     * @param id 值对应 client_order_item_personal.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 订单id<br/>
     * 返回值对应的表列名 client_order_item_personal.client_order_id
     *
     * @return 返回值对应 client_order_item_personal.client_order_id
     *
     * @mbg.generated
     */
    public Long getClientOrderId() {
        return clientOrderId;
    }

    /**
     * 订单id<br/>
     * client_order_item_personal.client_order_id
     *
     * @param clientOrderId 值对应 client_order_item_personal.client_order_id
     *
     * @mbg.generated
     */
    public void setClientOrderId(Long clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    /**
     * 所属产品编码<br/>
     * 返回值对应的表列名 client_order_item_personal.product_code
     *
     * @return 返回值对应 client_order_item_personal.product_code
     *
     * @mbg.generated
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 所属产品编码<br/>
     * client_order_item_personal.product_code
     *
     * @param productCode 值对应 client_order_item_personal.product_code
     *
     * @mbg.generated
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    /**
     * 产品参数名称<br/>
     * 返回值对应的表列名 client_order_item_personal.product_params_name
     *
     * @return 返回值对应 client_order_item_personal.product_params_name
     *
     * @mbg.generated
     */
    public String getProductParamsName() {
        return productParamsName;
    }

    /**
     * 产品参数名称<br/>
     * client_order_item_personal.product_params_name
     *
     * @param productParamsName 值对应 client_order_item_personal.product_params_name
     *
     * @mbg.generated
     */
    public void setProductParamsName(String productParamsName) {
        this.productParamsName = productParamsName == null ? null : productParamsName.trim();
    }

    /**
     * 分类名称<br/>
     * 返回值对应的表列名 client_order_item_personal.assortment_name
     *
     * @return 返回值对应 client_order_item_personal.assortment_name
     *
     * @mbg.generated
     */
    public String getAssortmentName() {
        return assortmentName;
    }

    /**
     * 分类名称<br/>
     * client_order_item_personal.assortment_name
     *
     * @param assortmentName 值对应 client_order_item_personal.assortment_name
     *
     * @mbg.generated
     */
    public void setAssortmentName(String assortmentName) {
        this.assortmentName = assortmentName == null ? null : assortmentName.trim();
    }

    /**
     * 品牌名称<br/>
     * 返回值对应的表列名 client_order_item_personal.brand_name
     *
     * @return 返回值对应 client_order_item_personal.brand_name
     *
     * @mbg.generated
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 品牌名称<br/>
     * client_order_item_personal.brand_name
     *
     * @param brandName 值对应 client_order_item_personal.brand_name
     *
     * @mbg.generated
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    /**
     * 交期<br/>
     * 返回值对应的表列名 client_order_item_personal.delivery_date
     *
     * @return 返回值对应 client_order_item_personal.delivery_date
     *
     * @mbg.generated
     */
    public Integer getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * 交期<br/>
     * client_order_item_personal.delivery_date
     *
     * @param deliveryDate 值对应 client_order_item_personal.delivery_date
     *
     * @mbg.generated
     */
    public void setDeliveryDate(Integer deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * 交货日期   交期+订单支付日期<br/>
     * 返回值对应的表列名 client_order_item_personal.delivery_time
     *
     * @return 返回值对应 client_order_item_personal.delivery_time
     *
     * @mbg.generated
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * 交货日期   交期+订单支付日期<br/>
     * client_order_item_personal.delivery_time
     *
     * @param deliveryTime 值对应 client_order_item_personal.delivery_time
     *
     * @mbg.generated
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * 模组名称<br/>
     * 返回值对应的表列名 client_order_item_personal.module_name
     *
     * @return 返回值对应 client_order_item_personal.module_name
     *
     * @mbg.generated
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * 模组名称<br/>
     * client_order_item_personal.module_name
     *
     * @param moduleName 值对应 client_order_item_personal.module_name
     *
     * @mbg.generated
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    /**
     * 模组图片url<br/>
     * 返回值对应的表列名 client_order_item_personal.module_pic_url
     *
     * @return 返回值对应 client_order_item_personal.module_pic_url
     *
     * @mbg.generated
     */
    public String getModulePicUrl() {
        return modulePicUrl;
    }

    /**
     * 模组图片url<br/>
     * client_order_item_personal.module_pic_url
     *
     * @param modulePicUrl 值对应 client_order_item_personal.module_pic_url
     *
     * @mbg.generated
     */
    public void setModulePicUrl(String modulePicUrl) {
        this.modulePicUrl = modulePicUrl == null ? null : modulePicUrl.trim();
    }

    /**
     * 商品价格<br/>
     * 返回值对应的表列名 client_order_item_personal.product_price
     *
     * @return 返回值对应 client_order_item_personal.product_price
     *
     * @mbg.generated
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     * 商品价格<br/>
     * client_order_item_personal.product_price
     *
     * @param productPrice 值对应 client_order_item_personal.product_price
     *
     * @mbg.generated
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 下单数量<br/>
     * 返回值对应的表列名 client_order_item_personal.quantity
     *
     * @return 返回值对应 client_order_item_personal.quantity
     *
     * @mbg.generated
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 下单数量<br/>
     * client_order_item_personal.quantity
     *
     * @param quantity 值对应 client_order_item_personal.quantity
     *
     * @mbg.generated
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 client_order_item_personal.creator
     *
     * @return 返回值对应 client_order_item_personal.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * client_order_item_personal.creator
     *
     * @param creator 值对应 client_order_item_personal.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 client_order_item_personal.create_time
     *
     * @return 返回值对应 client_order_item_personal.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * client_order_item_personal.create_time
     *
     * @param createTime 值对应 client_order_item_personal.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 client_order_item_personal.modifier
     *
     * @return 返回值对应 client_order_item_personal.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * client_order_item_personal.modifier
     *
     * @param modifier 值对应 client_order_item_personal.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 client_order_item_personal.modify_time
     *
     * @return 返回值对应 client_order_item_personal.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 最后修改时间<br/>
     * client_order_item_personal.modify_time
     *
     * @param modifyTime 值对应 client_order_item_personal.modify_time
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
        ClientOrderItemPersonal other = (ClientOrderItemPersonal) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClientOrderId() == null ? other.getClientOrderId() == null : this.getClientOrderId().equals(other.getClientOrderId()))
            && (this.getProductCode() == null ? other.getProductCode() == null : this.getProductCode().equals(other.getProductCode()))
            && (this.getProductParamsName() == null ? other.getProductParamsName() == null : this.getProductParamsName().equals(other.getProductParamsName()))
            && (this.getAssortmentName() == null ? other.getAssortmentName() == null : this.getAssortmentName().equals(other.getAssortmentName()))
            && (this.getBrandName() == null ? other.getBrandName() == null : this.getBrandName().equals(other.getBrandName()))
            && (this.getDeliveryDate() == null ? other.getDeliveryDate() == null : this.getDeliveryDate().equals(other.getDeliveryDate()))
            && (this.getDeliveryTime() == null ? other.getDeliveryTime() == null : this.getDeliveryTime().equals(other.getDeliveryTime()))
            && (this.getModuleName() == null ? other.getModuleName() == null : this.getModuleName().equals(other.getModuleName()))
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
        result = prime * result + ((getClientOrderId() == null) ? 0 : getClientOrderId().hashCode());
        result = prime * result + ((getProductCode() == null) ? 0 : getProductCode().hashCode());
        result = prime * result + ((getProductParamsName() == null) ? 0 : getProductParamsName().hashCode());
        result = prime * result + ((getAssortmentName() == null) ? 0 : getAssortmentName().hashCode());
        result = prime * result + ((getBrandName() == null) ? 0 : getBrandName().hashCode());
        result = prime * result + ((getDeliveryDate() == null) ? 0 : getDeliveryDate().hashCode());
        result = prime * result + ((getDeliveryTime() == null) ? 0 : getDeliveryTime().hashCode());
        result = prime * result + ((getModuleName() == null) ? 0 : getModuleName().hashCode());
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
        sb.append(", clientOrderId=").append(clientOrderId);
        sb.append(", productCode=").append(productCode);
        sb.append(", productParamsName=").append(productParamsName);
        sb.append(", assortmentName=").append(assortmentName);
        sb.append(", brandName=").append(brandName);
        sb.append(", deliveryDate=").append(deliveryDate);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", moduleName=").append(moduleName);
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