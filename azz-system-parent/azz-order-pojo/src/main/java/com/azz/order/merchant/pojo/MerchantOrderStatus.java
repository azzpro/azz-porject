package com.azz.order.merchant.pojo;


import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantOrderStatus implements Serializable {
    private Long id;

    /**
     * 关联商户订单id
     *
     * @mbg.generated
     */
    private Long merchantOrderId;

    /**
     * 订单状态
     *
     * @mbg.generated
     */
    private Integer merchantStatusId;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

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

    private static final long serialVersionUID = 1L;

    /**
     * <br/>
     * 返回值对应的表列名 merchant_order_status.id
     *
     * @return 返回值对应 merchant_order_status.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * <br/>
     * merchant_order_status.id
     *
     * @param id 值对应 merchant_order_status.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 关联商户订单id<br/>
     * 返回值对应的表列名 merchant_order_status.merchant_order_id
     *
     * @return 返回值对应 merchant_order_status.merchant_order_id
     *
     * @mbg.generated
     */
    public Long getMerchantOrderId() {
        return merchantOrderId;
    }

    /**
     * 关联商户订单id<br/>
     * merchant_order_status.merchant_order_id
     *
     * @param merchantOrderId 值对应 merchant_order_status.merchant_order_id
     *
     * @mbg.generated
     */
    public void setMerchantOrderId(Long merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    /**
     * 订单状态<br/>
     * 返回值对应的表列名 merchant_order_status.merchant_status_id
     *
     * @return 返回值对应 merchant_order_status.merchant_status_id
     *
     * @mbg.generated
     */
    public Integer getMerchantStatusId() {
        return merchantStatusId;
    }

    /**
     * 订单状态<br/>
     * merchant_order_status.merchant_status_id
     *
     * @param merchantStatusId 值对应 merchant_order_status.merchant_status_id
     *
     * @mbg.generated
     */
    public void setMerchantStatusId(Integer merchantStatusId) {
        this.merchantStatusId = merchantStatusId;
    }

    /**
     * 备注<br/>
     * 返回值对应的表列名 merchant_order_status.remark
     *
     * @return 返回值对应 merchant_order_status.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * merchant_order_status.remark
     *
     * @param remark 值对应 merchant_order_status.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 merchant_order_status.creator
     *
     * @return 返回值对应 merchant_order_status.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * merchant_order_status.creator
     *
     * @param creator 值对应 merchant_order_status.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 merchant_order_status.create_time
     *
     * @return 返回值对应 merchant_order_status.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * merchant_order_status.create_time
     *
     * @param createTime 值对应 merchant_order_status.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        MerchantOrderStatus other = (MerchantOrderStatus) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantOrderId() == null ? other.getMerchantOrderId() == null : this.getMerchantOrderId().equals(other.getMerchantOrderId()))
            && (this.getMerchantStatusId() == null ? other.getMerchantStatusId() == null : this.getMerchantStatusId().equals(other.getMerchantStatusId()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMerchantOrderId() == null) ? 0 : getMerchantOrderId().hashCode());
        result = prime * result + ((getMerchantStatusId() == null) ? 0 : getMerchantStatusId().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
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
        sb.append(", merchantStatusId=").append(merchantStatusId);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}