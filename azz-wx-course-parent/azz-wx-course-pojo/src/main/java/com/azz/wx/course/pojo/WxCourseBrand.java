package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.util.Date;

public class WxCourseBrand implements Serializable {
    /**
     * 主键id
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
     * 品牌图片名称
     *
     * @mbg.generated
     */
    private String brandPicName;

    /**
     * 品牌图片URL
     *
     * @mbg.generated
     */
    private String brandPicUrl;

    /**
     * 品牌简介
     *
     * @mbg.generated
     */
    private String brandDescription;

    /**
     * 状态  0无效 1有效 
     *
     * @mbg.generated
     */
    private Byte status;

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

    /**
     * 品牌详情
     *
     * @mbg.generated
     */
    private String brandInfo;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 wx_course_brand.id
     *
     * @return 返回值对应 wx_course_brand.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_course_brand.id
     *
     * @param id 值对应 wx_course_brand.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 品牌编码<br/>
     * 返回值对应的表列名 wx_course_brand.brand_code
     *
     * @return 返回值对应 wx_course_brand.brand_code
     *
     * @mbg.generated
     */
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * 品牌编码<br/>
     * wx_course_brand.brand_code
     *
     * @param brandCode 值对应 wx_course_brand.brand_code
     *
     * @mbg.generated
     */
    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    /**
     * 品牌名称<br/>
     * 返回值对应的表列名 wx_course_brand.brand_name
     *
     * @return 返回值对应 wx_course_brand.brand_name
     *
     * @mbg.generated
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 品牌名称<br/>
     * wx_course_brand.brand_name
     *
     * @param brandName 值对应 wx_course_brand.brand_name
     *
     * @mbg.generated
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    /**
     * 品牌图片名称<br/>
     * 返回值对应的表列名 wx_course_brand.brand_pic_name
     *
     * @return 返回值对应 wx_course_brand.brand_pic_name
     *
     * @mbg.generated
     */
    public String getBrandPicName() {
        return brandPicName;
    }

    /**
     * 品牌图片名称<br/>
     * wx_course_brand.brand_pic_name
     *
     * @param brandPicName 值对应 wx_course_brand.brand_pic_name
     *
     * @mbg.generated
     */
    public void setBrandPicName(String brandPicName) {
        this.brandPicName = brandPicName == null ? null : brandPicName.trim();
    }

    /**
     * 品牌图片URL<br/>
     * 返回值对应的表列名 wx_course_brand.brand_pic_url
     *
     * @return 返回值对应 wx_course_brand.brand_pic_url
     *
     * @mbg.generated
     */
    public String getBrandPicUrl() {
        return brandPicUrl;
    }

    /**
     * 品牌图片URL<br/>
     * wx_course_brand.brand_pic_url
     *
     * @param brandPicUrl 值对应 wx_course_brand.brand_pic_url
     *
     * @mbg.generated
     */
    public void setBrandPicUrl(String brandPicUrl) {
        this.brandPicUrl = brandPicUrl == null ? null : brandPicUrl.trim();
    }

    /**
     * 品牌简介<br/>
     * 返回值对应的表列名 wx_course_brand.brand_description
     *
     * @return 返回值对应 wx_course_brand.brand_description
     *
     * @mbg.generated
     */
    public String getBrandDescription() {
        return brandDescription;
    }

    /**
     * 品牌简介<br/>
     * wx_course_brand.brand_description
     *
     * @param brandDescription 值对应 wx_course_brand.brand_description
     *
     * @mbg.generated
     */
    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription == null ? null : brandDescription.trim();
    }

    /**
     * 状态  0无效 1有效 <br/>
     * 返回值对应的表列名 wx_course_brand.status
     *
     * @return 返回值对应 wx_course_brand.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 状态  0无效 1有效 <br/>
     * wx_course_brand.status
     *
     * @param status 值对应 wx_course_brand.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course_brand.creator
     *
     * @return 返回值对应 wx_course_brand.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_course_brand.creator
     *
     * @param creator 值对应 wx_course_brand.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 wx_course_brand.create_time
     *
     * @return 返回值对应 wx_course_brand.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * wx_course_brand.create_time
     *
     * @param createTime 值对应 wx_course_brand.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_course_brand.modifier
     *
     * @return 返回值对应 wx_course_brand.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_course_brand.modifier
     *
     * @param modifier 值对应 wx_course_brand.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_course_brand.modify_time
     *
     * @return 返回值对应 wx_course_brand.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_course_brand.modify_time
     *
     * @param modifyTime 值对应 wx_course_brand.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 品牌详情<br/>
     * 返回值对应的表列名 wx_course_brand.brand_info
     *
     * @return 返回值对应 wx_course_brand.brand_info
     *
     * @mbg.generated
     */
    public String getBrandInfo() {
        return brandInfo;
    }

    /**
     * 品牌详情<br/>
     * wx_course_brand.brand_info
     *
     * @param brandInfo 值对应 wx_course_brand.brand_info
     *
     * @mbg.generated
     */
    public void setBrandInfo(String brandInfo) {
        this.brandInfo = brandInfo == null ? null : brandInfo.trim();
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
        WxCourseBrand other = (WxCourseBrand) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBrandCode() == null ? other.getBrandCode() == null : this.getBrandCode().equals(other.getBrandCode()))
            && (this.getBrandName() == null ? other.getBrandName() == null : this.getBrandName().equals(other.getBrandName()))
            && (this.getBrandPicName() == null ? other.getBrandPicName() == null : this.getBrandPicName().equals(other.getBrandPicName()))
            && (this.getBrandPicUrl() == null ? other.getBrandPicUrl() == null : this.getBrandPicUrl().equals(other.getBrandPicUrl()))
            && (this.getBrandDescription() == null ? other.getBrandDescription() == null : this.getBrandDescription().equals(other.getBrandDescription()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getBrandInfo() == null ? other.getBrandInfo() == null : this.getBrandInfo().equals(other.getBrandInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBrandCode() == null) ? 0 : getBrandCode().hashCode());
        result = prime * result + ((getBrandName() == null) ? 0 : getBrandName().hashCode());
        result = prime * result + ((getBrandPicName() == null) ? 0 : getBrandPicName().hashCode());
        result = prime * result + ((getBrandPicUrl() == null) ? 0 : getBrandPicUrl().hashCode());
        result = prime * result + ((getBrandDescription() == null) ? 0 : getBrandDescription().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getBrandInfo() == null) ? 0 : getBrandInfo().hashCode());
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
        sb.append(", brandPicName=").append(brandPicName);
        sb.append(", brandPicUrl=").append(brandPicUrl);
        sb.append(", brandDescription=").append(brandDescription);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", brandInfo=").append(brandInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}