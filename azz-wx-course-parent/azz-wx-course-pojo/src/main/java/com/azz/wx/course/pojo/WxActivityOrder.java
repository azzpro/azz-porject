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
public class WxActivityOrder implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    private String orderCode;

    /**
     * 微信openid
     *
     * @mbg.generated
     */
    private String openid;

    /**
     * 微信昵称
     *
     * @mbg.generated
     */
    private String nickName;

    /**
     * 微信头像url
     *
     * @mbg.generated
     */
    private String headImageUrl;

    /**
     * 订单状态 1待支付 2已支付 3已关闭 
     *
     * @mbg.generated
     */
    private Byte orderStatus;

    /**
     * 订单金额
     *
     * @mbg.generated
     */
    private BigDecimal grandTotal;

    /**
     * 支付方式 1在线支付  2线下支付
     *
     * @mbg.generated
     */
    private Byte paymentMethod;

    /**
     * 支付状态 1待支付    2支付成功 3支付关闭 4支付失败
     *
     * @mbg.generated
     */
    private Byte paymentStatus;

    /**
     * 支付类型  1微信   2支付宝  3其他 
     *
     * @mbg.generated
     */
    private Byte paymentType;

    /**
     * 状态 0无效 1有效
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * 订单备注
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * 创建人 即下单人的client_user_code
     *
     * @mbg.generated
     */
    private String creator;

    /**
     * 创建人
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
     * 主键id<br/>
     * 返回值对应的表列名 wx_activity_order.id
     *
     * @return 返回值对应 wx_activity_order.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_activity_order.id
     *
     * @param id 值对应 wx_activity_order.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <br/>
     * 返回值对应的表列名 wx_activity_order.order_code
     *
     * @return 返回值对应 wx_activity_order.order_code
     *
     * @mbg.generated
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * <br/>
     * wx_activity_order.order_code
     *
     * @param orderCode 值对应 wx_activity_order.order_code
     *
     * @mbg.generated
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * 微信openid<br/>
     * 返回值对应的表列名 wx_activity_order.openid
     *
     * @return 返回值对应 wx_activity_order.openid
     *
     * @mbg.generated
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 微信openid<br/>
     * wx_activity_order.openid
     *
     * @param openid 值对应 wx_activity_order.openid
     *
     * @mbg.generated
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 微信昵称<br/>
     * 返回值对应的表列名 wx_activity_order.nick_name
     *
     * @return 返回值对应 wx_activity_order.nick_name
     *
     * @mbg.generated
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 微信昵称<br/>
     * wx_activity_order.nick_name
     *
     * @param nickName 值对应 wx_activity_order.nick_name
     *
     * @mbg.generated
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 微信头像url<br/>
     * 返回值对应的表列名 wx_activity_order.head_image_url
     *
     * @return 返回值对应 wx_activity_order.head_image_url
     *
     * @mbg.generated
     */
    public String getHeadImageUrl() {
        return headImageUrl;
    }

    /**
     * 微信头像url<br/>
     * wx_activity_order.head_image_url
     *
     * @param headImageUrl 值对应 wx_activity_order.head_image_url
     *
     * @mbg.generated
     */
    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl == null ? null : headImageUrl.trim();
    }

    /**
     * 订单状态 1待支付 2已支付 3已关闭 <br/>
     * 返回值对应的表列名 wx_activity_order.order_status
     *
     * @return 返回值对应 wx_activity_order.order_status
     *
     * @mbg.generated
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * 订单状态 1待支付 2已支付 3已关闭 <br/>
     * wx_activity_order.order_status
     *
     * @param orderStatus 值对应 wx_activity_order.order_status
     *
     * @mbg.generated
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 订单金额<br/>
     * 返回值对应的表列名 wx_activity_order.grand_total
     *
     * @return 返回值对应 wx_activity_order.grand_total
     *
     * @mbg.generated
     */
    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    /**
     * 订单金额<br/>
     * wx_activity_order.grand_total
     *
     * @param grandTotal 值对应 wx_activity_order.grand_total
     *
     * @mbg.generated
     */
    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    /**
     * 支付方式 1在线支付  2线下支付<br/>
     * 返回值对应的表列名 wx_activity_order.payment_method
     *
     * @return 返回值对应 wx_activity_order.payment_method
     *
     * @mbg.generated
     */
    public Byte getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * 支付方式 1在线支付  2线下支付<br/>
     * wx_activity_order.payment_method
     *
     * @param paymentMethod 值对应 wx_activity_order.payment_method
     *
     * @mbg.generated
     */
    public void setPaymentMethod(Byte paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * 支付状态 1待支付    2支付成功 3支付关闭 4支付失败<br/>
     * 返回值对应的表列名 wx_activity_order.payment_status
     *
     * @return 返回值对应 wx_activity_order.payment_status
     *
     * @mbg.generated
     */
    public Byte getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * 支付状态 1待支付    2支付成功 3支付关闭 4支付失败<br/>
     * wx_activity_order.payment_status
     *
     * @param paymentStatus 值对应 wx_activity_order.payment_status
     *
     * @mbg.generated
     */
    public void setPaymentStatus(Byte paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * 支付类型  1微信   2支付宝  3其他 <br/>
     * 返回值对应的表列名 wx_activity_order.payment_type
     *
     * @return 返回值对应 wx_activity_order.payment_type
     *
     * @mbg.generated
     */
    public Byte getPaymentType() {
        return paymentType;
    }

    /**
     * 支付类型  1微信   2支付宝  3其他 <br/>
     * wx_activity_order.payment_type
     *
     * @param paymentType 值对应 wx_activity_order.payment_type
     *
     * @mbg.generated
     */
    public void setPaymentType(Byte paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 状态 0无效 1有效<br/>
     * 返回值对应的表列名 wx_activity_order.status
     *
     * @return 返回值对应 wx_activity_order.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 状态 0无效 1有效<br/>
     * wx_activity_order.status
     *
     * @param status 值对应 wx_activity_order.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 订单备注<br/>
     * 返回值对应的表列名 wx_activity_order.remark
     *
     * @return 返回值对应 wx_activity_order.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 订单备注<br/>
     * wx_activity_order.remark
     *
     * @param remark 值对应 wx_activity_order.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人 即下单人的client_user_code<br/>
     * 返回值对应的表列名 wx_activity_order.creator
     *
     * @return 返回值对应 wx_activity_order.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人 即下单人的client_user_code<br/>
     * wx_activity_order.creator
     *
     * @param creator 值对应 wx_activity_order.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_activity_order.create_time
     *
     * @return 返回值对应 wx_activity_order.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建人<br/>
     * wx_activity_order.create_time
     *
     * @param createTime 值对应 wx_activity_order.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_activity_order.modifier
     *
     * @return 返回值对应 wx_activity_order.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_activity_order.modifier
     *
     * @param modifier 值对应 wx_activity_order.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_activity_order.modify_time
     *
     * @return 返回值对应 wx_activity_order.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_activity_order.modify_time
     *
     * @param modifyTime 值对应 wx_activity_order.modify_time
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
        WxActivityOrder other = (WxActivityOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderCode() == null ? other.getOrderCode() == null : this.getOrderCode().equals(other.getOrderCode()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getHeadImageUrl() == null ? other.getHeadImageUrl() == null : this.getHeadImageUrl().equals(other.getHeadImageUrl()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getGrandTotal() == null ? other.getGrandTotal() == null : this.getGrandTotal().equals(other.getGrandTotal()))
            && (this.getPaymentMethod() == null ? other.getPaymentMethod() == null : this.getPaymentMethod().equals(other.getPaymentMethod()))
            && (this.getPaymentStatus() == null ? other.getPaymentStatus() == null : this.getPaymentStatus().equals(other.getPaymentStatus()))
            && (this.getPaymentType() == null ? other.getPaymentType() == null : this.getPaymentType().equals(other.getPaymentType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
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
        result = prime * result + ((getOrderCode() == null) ? 0 : getOrderCode().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getHeadImageUrl() == null) ? 0 : getHeadImageUrl().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getGrandTotal() == null) ? 0 : getGrandTotal().hashCode());
        result = prime * result + ((getPaymentMethod() == null) ? 0 : getPaymentMethod().hashCode());
        result = prime * result + ((getPaymentStatus() == null) ? 0 : getPaymentStatus().hashCode());
        result = prime * result + ((getPaymentType() == null) ? 0 : getPaymentType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", orderCode=").append(orderCode);
        sb.append(", openid=").append(openid);
        sb.append(", nickName=").append(nickName);
        sb.append(", headImageUrl=").append(headImageUrl);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", grandTotal=").append(grandTotal);
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append(", paymentStatus=").append(paymentStatus);
        sb.append(", paymentType=").append(paymentType);
        sb.append(", status=").append(status);
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