package com.azz.order.client.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrderStatusPersonal implements Serializable {
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
    private Long orderId;

    /**
     * 订单状态
     *
     * @mbg.generated
     */
    private Integer orderStatusId;

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
     * 返回值对应的表列名 client_order_status_personal.id
     *
     * @return 返回值对应 client_order_status_personal.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * client_order_status_personal.id
     *
     * @param id 值对应 client_order_status_personal.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 订单id<br/>
     * 返回值对应的表列名 client_order_status_personal.order_id
     *
     * @return 返回值对应 client_order_status_personal.order_id
     *
     * @mbg.generated
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 订单id<br/>
     * client_order_status_personal.order_id
     *
     * @param orderId 值对应 client_order_status_personal.order_id
     *
     * @mbg.generated
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 订单状态<br/>
     * 返回值对应的表列名 client_order_status_personal.order_status_id
     *
     * @return 返回值对应 client_order_status_personal.order_status_id
     *
     * @mbg.generated
     */
    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    /**
     * 订单状态<br/>
     * client_order_status_personal.order_status_id
     *
     * @param orderStatusId 值对应 client_order_status_personal.order_status_id
     *
     * @mbg.generated
     */
    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    /**
     * 备注<br/>
     * 返回值对应的表列名 client_order_status_personal.remark
     *
     * @return 返回值对应 client_order_status_personal.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * client_order_status_personal.remark
     *
     * @param remark 值对应 client_order_status_personal.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 client_order_status_personal.creator
     *
     * @return 返回值对应 client_order_status_personal.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * client_order_status_personal.creator
     *
     * @param creator 值对应 client_order_status_personal.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 client_order_status_personal.create_time
     *
     * @return 返回值对应 client_order_status_personal.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * client_order_status_personal.create_time
     *
     * @param createTime 值对应 client_order_status_personal.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 client_order_status_personal.modifier
     *
     * @return 返回值对应 client_order_status_personal.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * client_order_status_personal.modifier
     *
     * @param modifier 值对应 client_order_status_personal.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 client_order_status_personal.modify_time
     *
     * @return 返回值对应 client_order_status_personal.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 最后修改时间<br/>
     * client_order_status_personal.modify_time
     *
     * @param modifyTime 值对应 client_order_status_personal.modify_time
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
        ClientOrderStatusPersonal other = (ClientOrderStatusPersonal) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderStatusId() == null ? other.getOrderStatusId() == null : this.getOrderStatusId().equals(other.getOrderStatusId()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
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
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderStatusId() == null) ? 0 : getOrderStatusId().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
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
        sb.append(", orderId=").append(orderId);
        sb.append(", orderStatusId=").append(orderStatusId);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}