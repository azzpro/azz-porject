package com.azz.order.selection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientSelectionRecord implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 客户id
     *
     * @mbg.generated
     */
    private Long clientUserId;

    /**
     * 产品编码
     *
     * @mbg.generated
     */
    private String productCode;
    
    private Long productPriceId;

    /**
     * 参考单价
     *
     * @mbg.generated
     */
    private BigDecimal price;

    /**
     * 交期
     *
     * @mbg.generated
     */
    private Integer deliveryDate;

    /**
     * 所属模组
     *
     * @mbg.generated
     */
    private String moduleName;

    /**
     * 状态   0无效  1有效
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String creator;

    /**
     * 最后修改时间
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    private String modifier;

    /**
     * 参数值，多个以逗号隔开存放
     *
     * @mbg.generated
     */
    private String paramsValue;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 client_selection_record.id
     *
     * @return 返回值对应 client_selection_record.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * client_selection_record.id
     *
     * @param id 值对应 client_selection_record.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 客户id<br/>
     * 返回值对应的表列名 client_selection_record.client_user_id
     *
     * @return 返回值对应 client_selection_record.client_user_id
     *
     * @mbg.generated
     */
    public Long getClientUserId() {
        return clientUserId;
    }

    /**
     * 客户id<br/>
     * client_selection_record.client_user_id
     *
     * @param clientUserId 值对应 client_selection_record.client_user_id
     *
     * @mbg.generated
     */
    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    /**
     * 产品编码<br/>
     * 返回值对应的表列名 client_selection_record.product_code
     *
     * @return 返回值对应 client_selection_record.product_code
     *
     * @mbg.generated
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 产品编码<br/>
     * client_selection_record.product_code
     *
     * @param productCode 值对应 client_selection_record.product_code
     *
     * @mbg.generated
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 参考单价<br/>
     * 返回值对应的表列名 client_selection_record.price
     *
     * @return 返回值对应 client_selection_record.price
     *
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 参考单价<br/>
     * client_selection_record.price
     *
     * @param price 值对应 client_selection_record.price
     *
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 交期<br/>
     * 返回值对应的表列名 client_selection_record.delivery_date
     *
     * @return 返回值对应 client_selection_record.delivery_date
     *
     * @mbg.generated
     */
    public Integer getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * 交期<br/>
     * client_selection_record.delivery_date
     *
     * @param deliveryDate 值对应 client_selection_record.delivery_date
     *
     * @mbg.generated
     */
    public void setDeliveryDate(Integer deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * 所属模组<br/>
     * 返回值对应的表列名 client_selection_record.module_name
     *
     * @return 返回值对应 client_selection_record.module_name
     *
     * @mbg.generated
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * 所属模组<br/>
     * client_selection_record.module_name
     *
     * @param moduleName 值对应 client_selection_record.module_name
     *
     * @mbg.generated
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    /**
     * 状态   0无效  1有效<br/>
     * 返回值对应的表列名 client_selection_record.status
     *
     * @return 返回值对应 client_selection_record.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态   0无效  1有效<br/>
     * client_selection_record.status
     *
     * @param status 值对应 client_selection_record.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 client_selection_record.create_time
     *
     * @return 返回值对应 client_selection_record.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * client_selection_record.create_time
     *
     * @param createTime 值对应 client_selection_record.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 client_selection_record.creator
     *
     * @return 返回值对应 client_selection_record.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * client_selection_record.creator
     *
     * @param creator 值对应 client_selection_record.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 client_selection_record.last_modify_time
     *
     * @return 返回值对应 client_selection_record.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * 最后修改时间<br/>
     * client_selection_record.last_modify_time
     *
     * @param lastModifyTime 值对应 client_selection_record.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 client_selection_record.modifier
     *
     * @return 返回值对应 client_selection_record.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * client_selection_record.modifier
     *
     * @param modifier 值对应 client_selection_record.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 参数值，多个以逗号隔开存放<br/>
     * 返回值对应的表列名 client_selection_record.params_value
     *
     * @return 返回值对应 client_selection_record.params_value
     *
     * @mbg.generated
     */
    public String getParamsValue() {
        return paramsValue;
    }

    /**
     * 参数值，多个以逗号隔开存放<br/>
     * client_selection_record.params_value
     *
     * @param paramsValue 值对应 client_selection_record.params_value
     *
     * @mbg.generated
     */
    public void setParamsValue(String paramsValue) {
        this.paramsValue = paramsValue == null ? null : paramsValue.trim();
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
        ClientSelectionRecord other = (ClientSelectionRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClientUserId() == null ? other.getClientUserId() == null : this.getClientUserId().equals(other.getClientUserId()))
            && (this.getProductCode() == null ? other.getProductCode() == null : this.getProductCode().equals(other.getProductCode()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getDeliveryDate() == null ? other.getDeliveryDate() == null : this.getDeliveryDate().equals(other.getDeliveryDate()))
            && (this.getModuleName() == null ? other.getModuleName() == null : this.getModuleName().equals(other.getModuleName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getParamsValue() == null ? other.getParamsValue() == null : this.getParamsValue().equals(other.getParamsValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClientUserId() == null) ? 0 : getClientUserId().hashCode());
        result = prime * result + ((getProductCode() == null) ? 0 : getProductCode().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getDeliveryDate() == null) ? 0 : getDeliveryDate().hashCode());
        result = prime * result + ((getModuleName() == null) ? 0 : getModuleName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getParamsValue() == null) ? 0 : getParamsValue().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", clientUserId=").append(clientUserId);
        sb.append(", productCode=").append(productCode);
        sb.append(", price=").append(price);
        sb.append(", deliveryDate=").append(deliveryDate);
        sb.append(", moduleName=").append(moduleName);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", paramsValue=").append(paramsValue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public Long getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(Long productPriceId) {
		this.productPriceId = productPriceId;
	}
}