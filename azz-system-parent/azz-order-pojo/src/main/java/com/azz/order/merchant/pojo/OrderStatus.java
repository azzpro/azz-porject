package com.azz.order.merchant.pojo;


import java.io.Serializable;
import java.util.Date;

public class OrderStatus implements Serializable {
    /**
     * id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 状态编码
     *
     * @mbg.generated
     */
    private String statusCode;

    /**
     * 状态名称
     *
     * @mbg.generated
     */
    private String statusName;

    /**
     * 状态类型 1客户订单  2商户订单
     *
     * @mbg.generated
     */
    private Integer statusType;

    /**
     * 状态说明
     *
     * @mbg.generated
     */
    private String statusDescription;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * id<br/>
     * 返回值对应的表列名 order_status.id
     *
     * @return 返回值对应 order_status.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * id<br/>
     * order_status.id
     *
     * @param id 值对应 order_status.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 状态编码<br/>
     * 返回值对应的表列名 order_status.status_code
     *
     * @return 返回值对应 order_status.status_code
     *
     * @mbg.generated
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * 状态编码<br/>
     * order_status.status_code
     *
     * @param statusCode 值对应 order_status.status_code
     *
     * @mbg.generated
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode == null ? null : statusCode.trim();
    }

    /**
     * 状态名称<br/>
     * 返回值对应的表列名 order_status.status_name
     *
     * @return 返回值对应 order_status.status_name
     *
     * @mbg.generated
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * 状态名称<br/>
     * order_status.status_name
     *
     * @param statusName 值对应 order_status.status_name
     *
     * @mbg.generated
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName == null ? null : statusName.trim();
    }

    /**
     * 状态类型 1客户订单  2商户订单<br/>
     * 返回值对应的表列名 order_status.status_type
     *
     * @return 返回值对应 order_status.status_type
     *
     * @mbg.generated
     */
    public Integer getStatusType() {
        return statusType;
    }

    /**
     * 状态类型 1客户订单  2商户订单<br/>
     * order_status.status_type
     *
     * @param statusType 值对应 order_status.status_type
     *
     * @mbg.generated
     */
    public void setStatusType(Integer statusType) {
        this.statusType = statusType;
    }

    /**
     * 状态说明<br/>
     * 返回值对应的表列名 order_status.status_description
     *
     * @return 返回值对应 order_status.status_description
     *
     * @mbg.generated
     */
    public String getStatusDescription() {
        return statusDescription;
    }

    /**
     * 状态说明<br/>
     * order_status.status_description
     *
     * @param statusDescription 值对应 order_status.status_description
     *
     * @mbg.generated
     */
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription == null ? null : statusDescription.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 order_status.create_time
     *
     * @return 返回值对应 order_status.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * order_status.create_time
     *
     * @param createTime 值对应 order_status.create_time
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
        OrderStatus other = (OrderStatus) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStatusCode() == null ? other.getStatusCode() == null : this.getStatusCode().equals(other.getStatusCode()))
            && (this.getStatusName() == null ? other.getStatusName() == null : this.getStatusName().equals(other.getStatusName()))
            && (this.getStatusType() == null ? other.getStatusType() == null : this.getStatusType().equals(other.getStatusType()))
            && (this.getStatusDescription() == null ? other.getStatusDescription() == null : this.getStatusDescription().equals(other.getStatusDescription()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStatusCode() == null) ? 0 : getStatusCode().hashCode());
        result = prime * result + ((getStatusName() == null) ? 0 : getStatusName().hashCode());
        result = prime * result + ((getStatusType() == null) ? 0 : getStatusType().hashCode());
        result = prime * result + ((getStatusDescription() == null) ? 0 : getStatusDescription().hashCode());
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
        sb.append(", statusCode=").append(statusCode);
        sb.append(", statusName=").append(statusName);
        sb.append(", statusType=").append(statusType);
        sb.append(", statusDescription=").append(statusDescription);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}