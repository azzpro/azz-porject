package com.azz.merchant.pojo;

import java.io.Serializable;
import java.util.Date;

public class MerchantGoodsProduct implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 所属分类ID
     *
     * @mbg.generated
     */
    private Long assortmentId;

    /**
     * 产品编号
     *
     * @mbg.generated
     */
    private String productCode;

    /**
     * 产品系统编号
     *
     * @mbg.generated
     */
    private String productSystemCode;

    /**
     * 所属品牌ID
     *
     * @mbg.generated
     */
    private Long brandId;

    /**
     * 产品状态(1 上架，2下架)
     *
     * @mbg.generated
     */
    private Byte productStatus;

    /**
     * 模组ID(某分类下的模组)
     *
     * @mbg.generated
     */
    private Long moduleId;

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
    
    private Long merchantId;

    private static final long serialVersionUID = 1L;

    
    public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	/**
     * 主键<br/>
     * 返回值对应的表列名 merchant_goods_product.id
     *
     * @return 返回值对应 merchant_goods_product.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * merchant_goods_product.id
     *
     * @param id 值对应 merchant_goods_product.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 所属分类ID<br/>
     * 返回值对应的表列名 merchant_goods_product.assortment_id
     *
     * @return 返回值对应 merchant_goods_product.assortment_id
     *
     * @mbg.generated
     */
    public Long getAssortmentId() {
        return assortmentId;
    }

    /**
     * 所属分类ID<br/>
     * merchant_goods_product.assortment_id
     *
     * @param assortmentId 值对应 merchant_goods_product.assortment_id
     *
     * @mbg.generated
     */
    public void setAssortmentId(Long assortmentId) {
        this.assortmentId = assortmentId;
    }

    /**
     * 产品编号<br/>
     * 返回值对应的表列名 merchant_goods_product.product _code
     *
     * @return 返回值对应 merchant_goods_product.product _code
     *
     * @mbg.generated
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 产品编号<br/>
     * merchant_goods_product.product _code
     *
     * @param productCode 值对应 merchant_goods_product.product _code
     *
     * @mbg.generated
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    /**
     * 产品系统编号<br/>
     * 返回值对应的表列名 merchant_goods_product.product_system_code
     *
     * @return 返回值对应 merchant_goods_product.product_system_code
     *
     * @mbg.generated
     */
    public String getProductSystemCode() {
        return productSystemCode;
    }

    /**
     * 产品系统编号<br/>
     * merchant_goods_product.product_system_code
     *
     * @param productSystemCode 值对应 merchant_goods_product.product_system_code
     *
     * @mbg.generated
     */
    public void setProductSystemCode(String productSystemCode) {
        this.productSystemCode = productSystemCode == null ? null : productSystemCode.trim();
    }

    /**
     * 所属品牌ID<br/>
     * 返回值对应的表列名 merchant_goods_product.brand_id
     *
     * @return 返回值对应 merchant_goods_product.brand_id
     *
     * @mbg.generated
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 所属品牌ID<br/>
     * merchant_goods_product.brand_id
     *
     * @param brandId 值对应 merchant_goods_product.brand_id
     *
     * @mbg.generated
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 产品状态(1 上架，2下架)<br/>
     * 返回值对应的表列名 merchant_goods_product.product_status
     *
     * @return 返回值对应 merchant_goods_product.product_status
     *
     * @mbg.generated
     */
    public Byte getProductStatus() {
        return productStatus;
    }

    /**
     * 产品状态(1 上架，2下架)<br/>
     * merchant_goods_product.product_status
     *
     * @param productStatus 值对应 merchant_goods_product.product_status
     *
     * @mbg.generated
     */
    public void setProductStatus(Byte productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * 模组ID(某分类下的模组)<br/>
     * 返回值对应的表列名 merchant_goods_product.module_id
     *
     * @return 返回值对应 merchant_goods_product.module_id
     *
     * @mbg.generated
     */
    public Long getModuleId() {
        return moduleId;
    }

    /**
     * 模组ID(某分类下的模组)<br/>
     * merchant_goods_product.module_id
     *
     * @param moduleId 值对应 merchant_goods_product.module_id
     *
     * @mbg.generated
     */
    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 merchant_goods_product.creator
     *
     * @return 返回值对应 merchant_goods_product.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * merchant_goods_product.creator
     *
     * @param creator 值对应 merchant_goods_product.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 merchant_goods_product.create_time
     *
     * @return 返回值对应 merchant_goods_product.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * merchant_goods_product.create_time
     *
     * @param createTime 值对应 merchant_goods_product.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 merchant_goods_product.modifier
     *
     * @return 返回值对应 merchant_goods_product.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * merchant_goods_product.modifier
     *
     * @param modifier 值对应 merchant_goods_product.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 merchant_goods_product.modify_time
     *
     * @return 返回值对应 merchant_goods_product.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * merchant_goods_product.modify_time
     *
     * @param modifyTime 值对应 merchant_goods_product.modify_time
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
        MerchantGoodsProduct other = (MerchantGoodsProduct) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAssortmentId() == null ? other.getAssortmentId() == null : this.getAssortmentId().equals(other.getAssortmentId()))
            && (this.getProductCode() == null ? other.getProductCode() == null : this.getProductCode().equals(other.getProductCode()))
            && (this.getProductSystemCode() == null ? other.getProductSystemCode() == null : this.getProductSystemCode().equals(other.getProductSystemCode()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getProductStatus() == null ? other.getProductStatus() == null : this.getProductStatus().equals(other.getProductStatus()))
            && (this.getModuleId() == null ? other.getModuleId() == null : this.getModuleId().equals(other.getModuleId()))
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
        result = prime * result + ((getAssortmentId() == null) ? 0 : getAssortmentId().hashCode());
        result = prime * result + ((getProductCode() == null) ? 0 : getProductCode().hashCode());
        result = prime * result + ((getProductSystemCode() == null) ? 0 : getProductSystemCode().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getProductStatus() == null) ? 0 : getProductStatus().hashCode());
        result = prime * result + ((getModuleId() == null) ? 0 : getModuleId().hashCode());
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
        sb.append(", assortmentId=").append(assortmentId);
        sb.append(", productCode=").append(productCode);
        sb.append(", productSystemCode=").append(productSystemCode);
        sb.append(", brandId=").append(brandId);
        sb.append(", productStatus=").append(productStatus);
        sb.append(", moduleId=").append(moduleId);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}