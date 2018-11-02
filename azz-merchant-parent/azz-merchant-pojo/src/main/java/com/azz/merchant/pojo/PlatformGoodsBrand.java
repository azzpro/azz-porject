package com.azz.merchant.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlatformGoodsBrand implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 品牌编码
     *
     * @mbg.generated
     */
    private String brandCode;

    /**
     * 品牌名称
     *
     * @mbg.generated
     */
    private String brandName;

    /**
     * 品牌英文名称
     *
     * @mbg.generated
     */
    private String brandEnglishName;

    /**
     * 品牌图片URL
     *
     * @mbg.generated
     */
    private String brandPicUrl;

    /**
     * 品牌图片名称
     *
     * @mbg.generated
     */
    private String brandPicName;

    /**
     * 品牌简介
     *
     * @mbg.generated
     */
    private String brandDescription;
    
    private Integer status;

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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
     * 返回值对应的表列名 platform_goods_brand.id
     *
     * @return 返回值对应 platform_goods_brand.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * platform_goods_brand.id
     *
     * @param id 值对应 platform_goods_brand.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 品牌编码<br/>
     * 返回值对应的表列名 platform_goods_brand.brand_code
     *
     * @return 返回值对应 platform_goods_brand.brand_code
     *
     * @mbg.generated
     */
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * 品牌编码<br/>
     * platform_goods_brand.brand_code
     *
     * @param brandCode 值对应 platform_goods_brand.brand_code
     *
     * @mbg.generated
     */
    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    /**
     * 品牌名称<br/>
     * 返回值对应的表列名 platform_goods_brand.brand_name
     *
     * @return 返回值对应 platform_goods_brand.brand_name
     *
     * @mbg.generated
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 品牌名称<br/>
     * platform_goods_brand.brand_name
     *
     * @param brandName 值对应 platform_goods_brand.brand_name
     *
     * @mbg.generated
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    /**
     * 品牌英文名称<br/>
     * 返回值对应的表列名 platform_goods_brand.brand_english_name
     *
     * @return 返回值对应 platform_goods_brand.brand_english_name
     *
     * @mbg.generated
     */
    public String getBrandEnglishName() {
        return brandEnglishName;
    }

    /**
     * 品牌英文名称<br/>
     * platform_goods_brand.brand_english_name
     *
     * @param brandEnglishName 值对应 platform_goods_brand.brand_english_name
     *
     * @mbg.generated
     */
    public void setBrandEnglishName(String brandEnglishName) {
        this.brandEnglishName = brandEnglishName == null ? null : brandEnglishName.trim();
    }

    /**
     * 品牌图片URL<br/>
     * 返回值对应的表列名 platform_goods_brand.brand_pic_url
     *
     * @return 返回值对应 platform_goods_brand.brand_pic_url
     *
     * @mbg.generated
     */
    public String getBrandPicUrl() {
        return brandPicUrl;
    }

    /**
     * 品牌图片URL<br/>
     * platform_goods_brand.brand_pic_url
     *
     * @param brandPicUrl 值对应 platform_goods_brand.brand_pic_url
     *
     * @mbg.generated
     */
    public void setBrandPicUrl(String brandPicUrl) {
        this.brandPicUrl = brandPicUrl == null ? null : brandPicUrl.trim();
    }

    /**
     * 品牌图片名称<br/>
     * 返回值对应的表列名 platform_goods_brand.brand_pic_name
     *
     * @return 返回值对应 platform_goods_brand.brand_pic_name
     *
     * @mbg.generated
     */
    public String getBrandPicName() {
        return brandPicName;
    }

    /**
     * 品牌图片名称<br/>
     * platform_goods_brand.brand_pic_name
     *
     * @param brandPicName 值对应 platform_goods_brand.brand_pic_name
     *
     * @mbg.generated
     */
    public void setBrandPicName(String brandPicName) {
        this.brandPicName = brandPicName == null ? null : brandPicName.trim();
    }

    /**
     * 品牌简介<br/>
     * 返回值对应的表列名 platform_goods_brand.brand_description
     *
     * @return 返回值对应 platform_goods_brand.brand_description
     *
     * @mbg.generated
     */
    public String getBrandDescription() {
        return brandDescription;
    }

    /**
     * 品牌简介<br/>
     * platform_goods_brand.brand_description
     *
     * @param brandDescription 值对应 platform_goods_brand.brand_description
     *
     * @mbg.generated
     */
    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription == null ? null : brandDescription.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 platform_goods_brand.creator
     *
     * @return 返回值对应 platform_goods_brand.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * platform_goods_brand.creator
     *
     * @param creator 值对应 platform_goods_brand.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 platform_goods_brand.create_time
     *
     * @return 返回值对应 platform_goods_brand.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * platform_goods_brand.create_time
     *
     * @param createTime 值对应 platform_goods_brand.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 platform_goods_brand.modifier
     *
     * @return 返回值对应 platform_goods_brand.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * platform_goods_brand.modifier
     *
     * @param modifier 值对应 platform_goods_brand.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 platform_goods_brand.modify_time
     *
     * @return 返回值对应 platform_goods_brand.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * platform_goods_brand.modify_time
     *
     * @param modifyTime 值对应 platform_goods_brand.modify_time
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
        PlatformGoodsBrand other = (PlatformGoodsBrand) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBrandCode() == null ? other.getBrandCode() == null : this.getBrandCode().equals(other.getBrandCode()))
            && (this.getBrandName() == null ? other.getBrandName() == null : this.getBrandName().equals(other.getBrandName()))
            && (this.getBrandEnglishName() == null ? other.getBrandEnglishName() == null : this.getBrandEnglishName().equals(other.getBrandEnglishName()))
            && (this.getBrandPicUrl() == null ? other.getBrandPicUrl() == null : this.getBrandPicUrl().equals(other.getBrandPicUrl()))
            && (this.getBrandPicName() == null ? other.getBrandPicName() == null : this.getBrandPicName().equals(other.getBrandPicName()))
            && (this.getBrandDescription() == null ? other.getBrandDescription() == null : this.getBrandDescription().equals(other.getBrandDescription()))
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
        result = prime * result + ((getBrandCode() == null) ? 0 : getBrandCode().hashCode());
        result = prime * result + ((getBrandName() == null) ? 0 : getBrandName().hashCode());
        result = prime * result + ((getBrandEnglishName() == null) ? 0 : getBrandEnglishName().hashCode());
        result = prime * result + ((getBrandPicUrl() == null) ? 0 : getBrandPicUrl().hashCode());
        result = prime * result + ((getBrandPicName() == null) ? 0 : getBrandPicName().hashCode());
        result = prime * result + ((getBrandDescription() == null) ? 0 : getBrandDescription().hashCode());
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
        sb.append(", brandCode=").append(brandCode);
        sb.append(", brandName=").append(brandName);
        sb.append(", brandEnglishName=").append(brandEnglishName);
        sb.append(", brandPicUrl=").append(brandPicUrl);
        sb.append(", brandPicName=").append(brandPicName);
        sb.append(", brandDescription=").append(brandDescription);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}