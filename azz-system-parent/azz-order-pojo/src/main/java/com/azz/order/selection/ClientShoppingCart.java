package com.azz.order.selection;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientShoppingCart implements Serializable {
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
     * 选型记录id
     *
     * @mbg.generated
     */
    private Long selectionRecordId;

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

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 client_shopping_cart.id
     *
     * @return 返回值对应 client_shopping_cart.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * client_shopping_cart.id
     *
     * @param id 值对应 client_shopping_cart.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 客户id<br/>
     * 返回值对应的表列名 client_shopping_cart.client_user_id
     *
     * @return 返回值对应 client_shopping_cart.client_user_id
     *
     * @mbg.generated
     */
    public Long getClientUserId() {
        return clientUserId;
    }

    /**
     * 客户id<br/>
     * client_shopping_cart.client_user_id
     *
     * @param clientUserId 值对应 client_shopping_cart.client_user_id
     *
     * @mbg.generated
     */
    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    /**
     * 选型记录id<br/>
     * 返回值对应的表列名 client_shopping_cart.selection_record_id
     *
     * @return 返回值对应 client_shopping_cart.selection_record_id
     *
     * @mbg.generated
     */
    public Long getSelectionRecordId() {
        return selectionRecordId;
    }

    /**
     * 选型记录id<br/>
     * client_shopping_cart.selection_record_id
     *
     * @param selectionRecordId 值对应 client_shopping_cart.selection_record_id
     *
     * @mbg.generated
     */
    public void setSelectionRecordId(Long selectionRecordId) {
        this.selectionRecordId = selectionRecordId;
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 client_shopping_cart.create_time
     *
     * @return 返回值对应 client_shopping_cart.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * client_shopping_cart.create_time
     *
     * @param createTime 值对应 client_shopping_cart.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 client_shopping_cart.creator
     *
     * @return 返回值对应 client_shopping_cart.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * client_shopping_cart.creator
     *
     * @param creator 值对应 client_shopping_cart.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 client_shopping_cart.last_modify_time
     *
     * @return 返回值对应 client_shopping_cart.last_modify_time
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * 最后修改时间<br/>
     * client_shopping_cart.last_modify_time
     *
     * @param lastModifyTime 值对应 client_shopping_cart.last_modify_time
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 client_shopping_cart.modifier
     *
     * @return 返回值对应 client_shopping_cart.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * client_shopping_cart.modifier
     *
     * @param modifier 值对应 client_shopping_cart.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
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
        ClientShoppingCart other = (ClientShoppingCart) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClientUserId() == null ? other.getClientUserId() == null : this.getClientUserId().equals(other.getClientUserId()))
            && (this.getSelectionRecordId() == null ? other.getSelectionRecordId() == null : this.getSelectionRecordId().equals(other.getSelectionRecordId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClientUserId() == null) ? 0 : getClientUserId().hashCode());
        result = prime * result + ((getSelectionRecordId() == null) ? 0 : getSelectionRecordId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
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
        sb.append(", selectionRecordId=").append(selectionRecordId);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}