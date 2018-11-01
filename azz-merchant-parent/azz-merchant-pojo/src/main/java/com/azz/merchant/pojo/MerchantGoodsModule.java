package com.azz.merchant.pojo;

import java.io.Serializable;
import java.util.Date;

public class MerchantGoodsModule implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    private String moduleCode;

    /**
     * 模组名称
     *
     * @mbg.generated
     */
    private String moduleName;

    /**
     * 模组状态（1上架 2下架）
     *
     * @mbg.generated
     */
    private Boolean moduleStatus;

    /**
     * 模组图片URL
     *
     * @mbg.generated
     */
    private String modulePicUrl;

    /**
     * 模组图片名称
     *
     * @mbg.generated
     */
    private String modulePicName;

    /**
     * 所属商户id
     *
     * @mbg.generated
     */
    private Long merchantId;

    /**
     * 所属分类ID
     *
     * @mbg.generated
     */
    private Long classificationId;

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
     * 模组详情
     *
     * @mbg.generated
     */
    private String moduleInfo;

    private static final long serialVersionUID = 1L;

    /**
     * 主键<br/>
     * 返回值对应的表列名 merchant_goods_module.id
     *
     * @return 返回值对应 merchant_goods_module.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * merchant_goods_module.id
     *
     * @param id 值对应 merchant_goods_module.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <br/>
     * 返回值对应的表列名 merchant_goods_module.module_code
     *
     * @return 返回值对应 merchant_goods_module.module_code
     *
     * @mbg.generated
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * <br/>
     * merchant_goods_module.module_code
     *
     * @param moduleCode 值对应 merchant_goods_module.module_code
     *
     * @mbg.generated
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }

    /**
     * 模组名称<br/>
     * 返回值对应的表列名 merchant_goods_module.module_name
     *
     * @return 返回值对应 merchant_goods_module.module_name
     *
     * @mbg.generated
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * 模组名称<br/>
     * merchant_goods_module.module_name
     *
     * @param moduleName 值对应 merchant_goods_module.module_name
     *
     * @mbg.generated
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    /**
     * 模组状态（1上架 2下架）<br/>
     * 返回值对应的表列名 merchant_goods_module.module_status
     *
     * @return 返回值对应 merchant_goods_module.module_status
     *
     * @mbg.generated
     */
    public Boolean getModuleStatus() {
        return moduleStatus;
    }

    /**
     * 模组状态（1上架 2下架）<br/>
     * merchant_goods_module.module_status
     *
     * @param moduleStatus 值对应 merchant_goods_module.module_status
     *
     * @mbg.generated
     */
    public void setModuleStatus(Boolean moduleStatus) {
        this.moduleStatus = moduleStatus;
    }

    /**
     * 模组图片URL<br/>
     * 返回值对应的表列名 merchant_goods_module.module_pic_url
     *
     * @return 返回值对应 merchant_goods_module.module_pic_url
     *
     * @mbg.generated
     */
    public String getModulePicUrl() {
        return modulePicUrl;
    }

    /**
     * 模组图片URL<br/>
     * merchant_goods_module.module_pic_url
     *
     * @param modulePicUrl 值对应 merchant_goods_module.module_pic_url
     *
     * @mbg.generated
     */
    public void setModulePicUrl(String modulePicUrl) {
        this.modulePicUrl = modulePicUrl == null ? null : modulePicUrl.trim();
    }

    /**
     * 模组图片名称<br/>
     * 返回值对应的表列名 merchant_goods_module.module_pic_name
     *
     * @return 返回值对应 merchant_goods_module.module_pic_name
     *
     * @mbg.generated
     */
    public String getModulePicName() {
        return modulePicName;
    }

    /**
     * 模组图片名称<br/>
     * merchant_goods_module.module_pic_name
     *
     * @param modulePicName 值对应 merchant_goods_module.module_pic_name
     *
     * @mbg.generated
     */
    public void setModulePicName(String modulePicName) {
        this.modulePicName = modulePicName == null ? null : modulePicName.trim();
    }

    /**
     * 所属商户id<br/>
     * 返回值对应的表列名 merchant_goods_module.merchant_id
     *
     * @return 返回值对应 merchant_goods_module.merchant_id
     *
     * @mbg.generated
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 所属商户id<br/>
     * merchant_goods_module.merchant_id
     *
     * @param merchantId 值对应 merchant_goods_module.merchant_id
     *
     * @mbg.generated
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 所属分类ID<br/>
     * 返回值对应的表列名 merchant_goods_module.classification_id
     *
     * @return 返回值对应 merchant_goods_module.classification_id
     *
     * @mbg.generated
     */
    public Long getClassificationId() {
        return classificationId;
    }

    /**
     * 所属分类ID<br/>
     * merchant_goods_module.classification_id
     *
     * @param classificationId 值对应 merchant_goods_module.classification_id
     *
     * @mbg.generated
     */
    public void setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 merchant_goods_module.creator
     *
     * @return 返回值对应 merchant_goods_module.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * merchant_goods_module.creator
     *
     * @param creator 值对应 merchant_goods_module.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 merchant_goods_module.create_time
     *
     * @return 返回值对应 merchant_goods_module.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * merchant_goods_module.create_time
     *
     * @param createTime 值对应 merchant_goods_module.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 merchant_goods_module.modifier
     *
     * @return 返回值对应 merchant_goods_module.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * merchant_goods_module.modifier
     *
     * @param modifier 值对应 merchant_goods_module.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 merchant_goods_module.modify_time
     *
     * @return 返回值对应 merchant_goods_module.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * merchant_goods_module.modify_time
     *
     * @param modifyTime 值对应 merchant_goods_module.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 模组详情<br/>
     * 返回值对应的表列名 merchant_goods_module.module_info
     *
     * @return 返回值对应 merchant_goods_module.module_info
     *
     * @mbg.generated
     */
    public String getModuleInfo() {
        return moduleInfo;
    }

    /**
     * 模组详情<br/>
     * merchant_goods_module.module_info
     *
     * @param moduleInfo 值对应 merchant_goods_module.module_info
     *
     * @mbg.generated
     */
    public void setModuleInfo(String moduleInfo) {
        this.moduleInfo = moduleInfo == null ? null : moduleInfo.trim();
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
        MerchantGoodsModule other = (MerchantGoodsModule) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getModuleCode() == null ? other.getModuleCode() == null : this.getModuleCode().equals(other.getModuleCode()))
            && (this.getModuleName() == null ? other.getModuleName() == null : this.getModuleName().equals(other.getModuleName()))
            && (this.getModuleStatus() == null ? other.getModuleStatus() == null : this.getModuleStatus().equals(other.getModuleStatus()))
            && (this.getModulePicUrl() == null ? other.getModulePicUrl() == null : this.getModulePicUrl().equals(other.getModulePicUrl()))
            && (this.getModulePicName() == null ? other.getModulePicName() == null : this.getModulePicName().equals(other.getModulePicName()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getClassificationId() == null ? other.getClassificationId() == null : this.getClassificationId().equals(other.getClassificationId()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getModuleInfo() == null ? other.getModuleInfo() == null : this.getModuleInfo().equals(other.getModuleInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getModuleCode() == null) ? 0 : getModuleCode().hashCode());
        result = prime * result + ((getModuleName() == null) ? 0 : getModuleName().hashCode());
        result = prime * result + ((getModuleStatus() == null) ? 0 : getModuleStatus().hashCode());
        result = prime * result + ((getModulePicUrl() == null) ? 0 : getModulePicUrl().hashCode());
        result = prime * result + ((getModulePicName() == null) ? 0 : getModulePicName().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getClassificationId() == null) ? 0 : getClassificationId().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getModuleInfo() == null) ? 0 : getModuleInfo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", moduleCode=").append(moduleCode);
        sb.append(", moduleName=").append(moduleName);
        sb.append(", moduleStatus=").append(moduleStatus);
        sb.append(", modulePicUrl=").append(modulePicUrl);
        sb.append(", modulePicName=").append(modulePicName);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", classificationId=").append(classificationId);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", moduleInfo=").append(moduleInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}