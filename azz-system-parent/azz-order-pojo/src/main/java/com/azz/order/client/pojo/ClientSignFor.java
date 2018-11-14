package com.azz.order.client.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientSignFor implements Serializable {
    private Long id;

    /**
     * 商户订单id
     *
     * @mbg.generated
     */
    private Long clientOrderId;

    /**
     * 签收人
     *
     * @mbg.generated
     */
    private String consignee;

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
     * 签收单文件信息  包含文件名、url的数组json字符串
     *
     * @mbg.generated
     */
    private String signFileInfo;

    private static final long serialVersionUID = 1L;

    /**
     * <br/>
     * 返回值对应的表列名 client_sign_for.id
     *
     * @return 返回值对应 client_sign_for.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * <br/>
     * client_sign_for.id
     *
     * @param id 值对应 client_sign_for.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商户订单id<br/>
     * 返回值对应的表列名 client_sign_for.client_order_id
     *
     * @return 返回值对应 client_sign_for.client_order_id
     *
     * @mbg.generated
     */
    public Long getClientOrderId() {
        return clientOrderId;
    }

    /**
     * 商户订单id<br/>
     * client_sign_for.client_order_id
     *
     * @param clientOrderId 值对应 client_sign_for.client_order_id
     *
     * @mbg.generated
     */
    public void setClientOrderId(Long clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    /**
     * 签收人<br/>
     * 返回值对应的表列名 client_sign_for.consignee
     *
     * @return 返回值对应 client_sign_for.consignee
     *
     * @mbg.generated
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * 签收人<br/>
     * client_sign_for.consignee
     *
     * @param consignee 值对应 client_sign_for.consignee
     *
     * @mbg.generated
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 client_sign_for.creator
     *
     * @return 返回值对应 client_sign_for.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * client_sign_for.creator
     *
     * @param creator 值对应 client_sign_for.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 client_sign_for.create_time
     *
     * @return 返回值对应 client_sign_for.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * client_sign_for.create_time
     *
     * @param createTime 值对应 client_sign_for.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 client_sign_for.modifier
     *
     * @return 返回值对应 client_sign_for.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * client_sign_for.modifier
     *
     * @param modifier 值对应 client_sign_for.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 client_sign_for.modify_time
     *
     * @return 返回值对应 client_sign_for.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * client_sign_for.modify_time
     *
     * @param modifyTime 值对应 client_sign_for.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 签收单文件信息  包含文件名、url的数组json字符串<br/>
     * 返回值对应的表列名 client_sign_for.sign_file_info
     *
     * @return 返回值对应 client_sign_for.sign_file_info
     *
     * @mbg.generated
     */
    public String getSignFileInfo() {
        return signFileInfo;
    }

    /**
     * 签收单文件信息  包含文件名、url的数组json字符串<br/>
     * client_sign_for.sign_file_info
     *
     * @param signFileInfo 值对应 client_sign_for.sign_file_info
     *
     * @mbg.generated
     */
    public void setSignFileInfo(String signFileInfo) {
        this.signFileInfo = signFileInfo == null ? null : signFileInfo.trim();
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
        ClientSignFor other = (ClientSignFor) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClientOrderId() == null ? other.getClientOrderId() == null : this.getClientOrderId().equals(other.getClientOrderId()))
            && (this.getConsignee() == null ? other.getConsignee() == null : this.getConsignee().equals(other.getConsignee()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getSignFileInfo() == null ? other.getSignFileInfo() == null : this.getSignFileInfo().equals(other.getSignFileInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClientOrderId() == null) ? 0 : getClientOrderId().hashCode());
        result = prime * result + ((getConsignee() == null) ? 0 : getConsignee().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getSignFileInfo() == null) ? 0 : getSignFileInfo().hashCode());
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
        sb.append(", consignee=").append(consignee);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", signFileInfo=").append(signFileInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}