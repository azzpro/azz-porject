package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxActivityOrderItem implements Serializable {
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
    private String orderCode;

    /**
     * 所属产品编码
     *
     * @mbg.generated
     */
    private String activityCode;

    private String activityName;

    /**
     * 价格
     *
     * @mbg.generated
     */
    private BigDecimal price;

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
     * 返回值对应的表列名 wx_activity_order_item.id
     *
     * @return 返回值对应 wx_activity_order_item.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_activity_order_item.id
     *
     * @param id 值对应 wx_activity_order_item.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 订单id<br/>
     * 返回值对应的表列名 wx_activity_order_item.order_code
     *
     * @return 返回值对应 wx_activity_order_item.order_code
     *
     * @mbg.generated
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 订单id<br/>
     * wx_activity_order_item.order_code
     *
     * @param orderCode 值对应 wx_activity_order_item.order_code
     *
     * @mbg.generated
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * 所属产品编码<br/>
     * 返回值对应的表列名 wx_activity_order_item.activity_code
     *
     * @return 返回值对应 wx_activity_order_item.activity_code
     *
     * @mbg.generated
     */
    public String getActivityCode() {
        return activityCode;
    }

    /**
     * 所属产品编码<br/>
     * wx_activity_order_item.activity_code
     *
     * @param activityCode 值对应 wx_activity_order_item.activity_code
     *
     * @mbg.generated
     */
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode == null ? null : activityCode.trim();
    }

    /**
     * <br/>
     * 返回值对应的表列名 wx_activity_order_item.activity_name
     *
     * @return 返回值对应 wx_activity_order_item.activity_name
     *
     * @mbg.generated
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * <br/>
     * wx_activity_order_item.activity_name
     *
     * @param activityName 值对应 wx_activity_order_item.activity_name
     *
     * @mbg.generated
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    /**
     * 价格<br/>
     * 返回值对应的表列名 wx_activity_order_item.price
     *
     * @return 返回值对应 wx_activity_order_item.price
     *
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 价格<br/>
     * wx_activity_order_item.price
     *
     * @param price 值对应 wx_activity_order_item.price
     *
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 下单数量<br/>
     * 返回值对应的表列名 wx_activity_order_item.quantity
     *
     * @return 返回值对应 wx_activity_order_item.quantity
     *
     * @mbg.generated
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 下单数量<br/>
     * wx_activity_order_item.quantity
     *
     * @param quantity 值对应 wx_activity_order_item.quantity
     *
     * @mbg.generated
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_activity_order_item.creator
     *
     * @return 返回值对应 wx_activity_order_item.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_activity_order_item.creator
     *
     * @param creator 值对应 wx_activity_order_item.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 wx_activity_order_item.create_time
     *
     * @return 返回值对应 wx_activity_order_item.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * wx_activity_order_item.create_time
     *
     * @param createTime 值对应 wx_activity_order_item.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_activity_order_item.modifier
     *
     * @return 返回值对应 wx_activity_order_item.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_activity_order_item.modifier
     *
     * @param modifier 值对应 wx_activity_order_item.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 wx_activity_order_item.modify_time
     *
     * @return 返回值对应 wx_activity_order_item.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 最后修改时间<br/>
     * wx_activity_order_item.modify_time
     *
     * @param modifyTime 值对应 wx_activity_order_item.modify_time
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
        WxActivityOrderItem other = (WxActivityOrderItem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderCode() == null ? other.getOrderCode() == null : this.getOrderCode().equals(other.getOrderCode()))
            && (this.getActivityCode() == null ? other.getActivityCode() == null : this.getActivityCode().equals(other.getActivityCode()))
            && (this.getActivityName() == null ? other.getActivityName() == null : this.getActivityName().equals(other.getActivityName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
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
        result = prime * result + ((getOrderCode() == null) ? 0 : getOrderCode().hashCode());
        result = prime * result + ((getActivityCode() == null) ? 0 : getActivityCode().hashCode());
        result = prime * result + ((getActivityName() == null) ? 0 : getActivityName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
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
        sb.append(", orderCode=").append(orderCode);
        sb.append(", activityCode=").append(activityCode);
        sb.append(", activityName=").append(activityName);
        sb.append(", price=").append(price);
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