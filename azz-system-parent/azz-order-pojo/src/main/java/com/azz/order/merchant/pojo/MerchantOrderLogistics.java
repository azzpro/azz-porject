package com.azz.order.merchant.pojo;


import java.io.Serializable;
import java.util.Date;

public class MerchantOrderLogistics implements Serializable {
    private Long id;

    /**
     * 关联的订单id
     *
     * @mbg.generated
     */
    private Long merchantOrderId;

    /**
     * 快递公司id
     *
     * @mbg.generated
     */
    private Integer expressCompanyId;

    /**
     * 物流公司名称
     *
     * @mbg.generated
     */
    private String logistiscCompanyName;

    /**
     * 单号
     *
     * @mbg.generated
     */
    private String number;

    /**
     * 配送方式   1快递 2物流 3自送
     *
     * @mbg.generated
     */
    private Integer deliveryType;

    /**
     * 配送人
     *
     * @mbg.generated
     */
    private String deliveryPerson;

    /**
     * 配送人电话
     *
     * @mbg.generated
     */
    private String deliveryPhoneNumber;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

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
     * 修改时间
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    private String modifier;

    /**
     * 出货文件信息   文件名、url数组的json字符串
     *
     * @mbg.generated
     */
    private String shipmentFileInfo;

    private static final long serialVersionUID = 1L;

    /**
     * <br/>
     * 返回值对应的表列名 merchant_order_logistics.id
     *
     * @return 返回值对应 merchant_order_logistics.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * <br/>
     * merchant_order_logistics.id
     *
     * @param id 值对应 merchant_order_logistics.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 关联的订单id<br/>
     * 返回值对应的表列名 merchant_order_logistics.merchant_order_id
     *
     * @return 返回值对应 merchant_order_logistics.merchant_order_id
     *
     * @mbg.generated
     */
    public Long getMerchantOrderId() {
        return merchantOrderId;
    }

    /**
     * 关联的订单id<br/>
     * merchant_order_logistics.merchant_order_id
     *
     * @param merchantOrderId 值对应 merchant_order_logistics.merchant_order_id
     *
     * @mbg.generated
     */
    public void setMerchantOrderId(Long merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    /**
     * 快递公司id<br/>
     * 返回值对应的表列名 merchant_order_logistics.express_company_id
     *
     * @return 返回值对应 merchant_order_logistics.express_company_id
     *
     * @mbg.generated
     */
    public Integer getExpressCompanyId() {
        return expressCompanyId;
    }

    /**
     * 快递公司id<br/>
     * merchant_order_logistics.express_company_id
     *
     * @param expressCompanyId 值对应 merchant_order_logistics.express_company_id
     *
     * @mbg.generated
     */
    public void setExpressCompanyId(Integer expressCompanyId) {
        this.expressCompanyId = expressCompanyId;
    }

    /**
     * 物流公司名称<br/>
     * 返回值对应的表列名 merchant_order_logistics.logistisc_company_name
     *
     * @return 返回值对应 merchant_order_logistics.logistisc_company_name
     *
     * @mbg.generated
     */
    public String getLogistiscCompanyName() {
        return logistiscCompanyName;
    }

    /**
     * 物流公司名称<br/>
     * merchant_order_logistics.logistisc_company_name
     *
     * @param logistiscCompanyName 值对应 merchant_order_logistics.logistisc_company_name
     *
     * @mbg.generated
     */
    public void setLogistiscCompanyName(String logistiscCompanyName) {
        this.logistiscCompanyName = logistiscCompanyName == null ? null : logistiscCompanyName.trim();
    }

    /**
     * 单号<br/>
     * 返回值对应的表列名 merchant_order_logistics.number
     *
     * @return 返回值对应 merchant_order_logistics.number
     *
     * @mbg.generated
     */
    public String getNumber() {
        return number;
    }

    /**
     * 单号<br/>
     * merchant_order_logistics.number
     *
     * @param number 值对应 merchant_order_logistics.number
     *
     * @mbg.generated
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * 配送方式   1快递 2物流 3自送<br/>
     * 返回值对应的表列名 merchant_order_logistics.delivery_type
     *
     * @return 返回值对应 merchant_order_logistics.delivery_type
     *
     * @mbg.generated
     */
    public Integer getDeliveryType() {
        return deliveryType;
    }

    /**
     * 配送方式   1快递 2物流 3自送<br/>
     * merchant_order_logistics.delivery_type
     *
     * @param deliveryType 值对应 merchant_order_logistics.delivery_type
     *
     * @mbg.generated
     */
    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    /**
     * 配送人<br/>
     * 返回值对应的表列名 merchant_order_logistics.delivery_person
     *
     * @return 返回值对应 merchant_order_logistics.delivery_person
     *
     * @mbg.generated
     */
    public String getDeliveryPerson() {
        return deliveryPerson;
    }

    /**
     * 配送人<br/>
     * merchant_order_logistics.delivery_person
     *
     * @param deliveryPerson 值对应 merchant_order_logistics.delivery_person
     *
     * @mbg.generated
     */
    public void setDeliveryPerson(String deliveryPerson) {
        this.deliveryPerson = deliveryPerson == null ? null : deliveryPerson.trim();
    }

    /**
     * 配送人电话<br/>
     * 返回值对应的表列名 merchant_order_logistics.delivery_phone_number
     *
     * @return 返回值对应 merchant_order_logistics.delivery_phone_number
     *
     * @mbg.generated
     */
    public String getDeliveryPhoneNumber() {
        return deliveryPhoneNumber;
    }

    /**
     * 配送人电话<br/>
     * merchant_order_logistics.delivery_phone_number
     *
     * @param deliveryPhoneNumber 值对应 merchant_order_logistics.delivery_phone_number
     *
     * @mbg.generated
     */
    public void setDeliveryPhoneNumber(String deliveryPhoneNumber) {
        this.deliveryPhoneNumber = deliveryPhoneNumber == null ? null : deliveryPhoneNumber.trim();
    }

    /**
     * 备注<br/>
     * 返回值对应的表列名 merchant_order_logistics.remark
     *
     * @return 返回值对应 merchant_order_logistics.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * merchant_order_logistics.remark
     *
     * @param remark 值对应 merchant_order_logistics.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 merchant_order_logistics.create_time
     *
     * @return 返回值对应 merchant_order_logistics.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * merchant_order_logistics.create_time
     *
     * @param createTime 值对应 merchant_order_logistics.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 merchant_order_logistics.creator
     *
     * @return 返回值对应 merchant_order_logistics.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * merchant_order_logistics.creator
     *
     * @param creator 值对应 merchant_order_logistics.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 merchant_order_logistics.modify_time
     *
     * @return 返回值对应 merchant_order_logistics.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * merchant_order_logistics.modify_time
     *
     * @param modifyTime 值对应 merchant_order_logistics.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 merchant_order_logistics.modifier
     *
     * @return 返回值对应 merchant_order_logistics.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * merchant_order_logistics.modifier
     *
     * @param modifier 值对应 merchant_order_logistics.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 出货文件信息   文件名、url数组的json字符串<br/>
     * 返回值对应的表列名 merchant_order_logistics.shipment_file_info
     *
     * @return 返回值对应 merchant_order_logistics.shipment_file_info
     *
     * @mbg.generated
     */
    public String getShipmentFileInfo() {
        return shipmentFileInfo;
    }

    /**
     * 出货文件信息   文件名、url数组的json字符串<br/>
     * merchant_order_logistics.shipment_file_info
     *
     * @param shipmentFileInfo 值对应 merchant_order_logistics.shipment_file_info
     *
     * @mbg.generated
     */
    public void setShipmentFileInfo(String shipmentFileInfo) {
        this.shipmentFileInfo = shipmentFileInfo == null ? null : shipmentFileInfo.trim();
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
        MerchantOrderLogistics other = (MerchantOrderLogistics) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantOrderId() == null ? other.getMerchantOrderId() == null : this.getMerchantOrderId().equals(other.getMerchantOrderId()))
            && (this.getExpressCompanyId() == null ? other.getExpressCompanyId() == null : this.getExpressCompanyId().equals(other.getExpressCompanyId()))
            && (this.getLogistiscCompanyName() == null ? other.getLogistiscCompanyName() == null : this.getLogistiscCompanyName().equals(other.getLogistiscCompanyName()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getDeliveryType() == null ? other.getDeliveryType() == null : this.getDeliveryType().equals(other.getDeliveryType()))
            && (this.getDeliveryPerson() == null ? other.getDeliveryPerson() == null : this.getDeliveryPerson().equals(other.getDeliveryPerson()))
            && (this.getDeliveryPhoneNumber() == null ? other.getDeliveryPhoneNumber() == null : this.getDeliveryPhoneNumber().equals(other.getDeliveryPhoneNumber()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getShipmentFileInfo() == null ? other.getShipmentFileInfo() == null : this.getShipmentFileInfo().equals(other.getShipmentFileInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMerchantOrderId() == null) ? 0 : getMerchantOrderId().hashCode());
        result = prime * result + ((getExpressCompanyId() == null) ? 0 : getExpressCompanyId().hashCode());
        result = prime * result + ((getLogistiscCompanyName() == null) ? 0 : getLogistiscCompanyName().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getDeliveryType() == null) ? 0 : getDeliveryType().hashCode());
        result = prime * result + ((getDeliveryPerson() == null) ? 0 : getDeliveryPerson().hashCode());
        result = prime * result + ((getDeliveryPhoneNumber() == null) ? 0 : getDeliveryPhoneNumber().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getShipmentFileInfo() == null) ? 0 : getShipmentFileInfo().hashCode());
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
        sb.append(", expressCompanyId=").append(expressCompanyId);
        sb.append(", logistiscCompanyName=").append(logistiscCompanyName);
        sb.append(", number=").append(number);
        sb.append(", deliveryType=").append(deliveryType);
        sb.append(", deliveryPerson=").append(deliveryPerson);
        sb.append(", deliveryPhoneNumber=").append(deliveryPhoneNumber);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", shipmentFileInfo=").append(shipmentFileInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}