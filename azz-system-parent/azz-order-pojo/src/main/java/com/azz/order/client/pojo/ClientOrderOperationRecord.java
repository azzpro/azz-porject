package com.azz.order.client.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrderOperationRecord implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 客户订单id
     *
     * @mbg.generated
     */
    private Long clientOrderId;

    /**
     * 操作类型 1订单拆单 2重新拆单 3取消派单
     *
     * @mbg.generated
     */
    private Integer optType;

    /**
     * 操作人
     *
     * @mbg.generated
     */
    private String operator;

    /**
     * 操作备注
     *
     * @mbg.generated
     */
    private String optRemark;

    /**
     * 订单类型（ 1个人  2企业）
     *
     * @mbg.generated
     */
    private Integer orderType;

    /**
     * 操作时间
     *
     * @mbg.generated
     */
    private Date optTime;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 client_order_operation_record.id
     *
     * @return 返回值对应 client_order_operation_record.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * client_order_operation_record.id
     *
     * @param id 值对应 client_order_operation_record.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 客户订单id<br/>
     * 返回值对应的表列名 client_order_operation_record.client_order_id
     *
     * @return 返回值对应 client_order_operation_record.client_order_id
     *
     * @mbg.generated
     */
    public Long getClientOrderId() {
        return clientOrderId;
    }

    /**
     * 客户订单id<br/>
     * client_order_operation_record.client_order_id
     *
     * @param clientOrderId 值对应 client_order_operation_record.client_order_id
     *
     * @mbg.generated
     */
    public void setClientOrderId(Long clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    /**
     * 操作类型 1订单拆单 2重新拆单 3取消派单<br/>
     * 返回值对应的表列名 client_order_operation_record.opt_type
     *
     * @return 返回值对应 client_order_operation_record.opt_type
     *
     * @mbg.generated
     */
    public Integer getOptType() {
        return optType;
    }

    /**
     * 操作类型 1订单拆单 2重新拆单 3取消派单<br/>
     * client_order_operation_record.opt_type
     *
     * @param optType 值对应 client_order_operation_record.opt_type
     *
     * @mbg.generated
     */
    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    /**
     * 操作人<br/>
     * 返回值对应的表列名 client_order_operation_record.operator
     *
     * @return 返回值对应 client_order_operation_record.operator
     *
     * @mbg.generated
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 操作人<br/>
     * client_order_operation_record.operator
     *
     * @param operator 值对应 client_order_operation_record.operator
     *
     * @mbg.generated
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * 操作备注<br/>
     * 返回值对应的表列名 client_order_operation_record.opt_remark
     *
     * @return 返回值对应 client_order_operation_record.opt_remark
     *
     * @mbg.generated
     */
    public String getOptRemark() {
        return optRemark;
    }

    /**
     * 操作备注<br/>
     * client_order_operation_record.opt_remark
     *
     * @param optRemark 值对应 client_order_operation_record.opt_remark
     *
     * @mbg.generated
     */
    public void setOptRemark(String optRemark) {
        this.optRemark = optRemark == null ? null : optRemark.trim();
    }

    /**
     * 订单类型（ 1个人  2企业）<br/>
     * 返回值对应的表列名 client_order_operation_record.order_type
     *
     * @return 返回值对应 client_order_operation_record.order_type
     *
     * @mbg.generated
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 订单类型（ 1个人  2企业）<br/>
     * client_order_operation_record.order_type
     *
     * @param orderType 值对应 client_order_operation_record.order_type
     *
     * @mbg.generated
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 操作时间<br/>
     * 返回值对应的表列名 client_order_operation_record.opt_time
     *
     * @return 返回值对应 client_order_operation_record.opt_time
     *
     * @mbg.generated
     */
    public Date getOptTime() {
        return optTime;
    }

    /**
     * 操作时间<br/>
     * client_order_operation_record.opt_time
     *
     * @param optTime 值对应 client_order_operation_record.opt_time
     *
     * @mbg.generated
     */
    public void setOptTime(Date optTime) {
        this.optTime = optTime;
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
        ClientOrderOperationRecord other = (ClientOrderOperationRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClientOrderId() == null ? other.getClientOrderId() == null : this.getClientOrderId().equals(other.getClientOrderId()))
            && (this.getOptType() == null ? other.getOptType() == null : this.getOptType().equals(other.getOptType()))
            && (this.getOperator() == null ? other.getOperator() == null : this.getOperator().equals(other.getOperator()))
            && (this.getOptRemark() == null ? other.getOptRemark() == null : this.getOptRemark().equals(other.getOptRemark()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getOptTime() == null ? other.getOptTime() == null : this.getOptTime().equals(other.getOptTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClientOrderId() == null) ? 0 : getClientOrderId().hashCode());
        result = prime * result + ((getOptType() == null) ? 0 : getOptType().hashCode());
        result = prime * result + ((getOperator() == null) ? 0 : getOperator().hashCode());
        result = prime * result + ((getOptRemark() == null) ? 0 : getOptRemark().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getOptTime() == null) ? 0 : getOptTime().hashCode());
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
        sb.append(", optType=").append(optType);
        sb.append(", operator=").append(operator);
        sb.append(", optRemark=").append(optRemark);
        sb.append(", orderType=").append(orderType);
        sb.append(", optTime=").append(optTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}