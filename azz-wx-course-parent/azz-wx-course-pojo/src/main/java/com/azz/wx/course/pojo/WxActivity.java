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
public class WxActivity implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 活动编码
     *
     * @mbg.generated
     */
    private String activityCode;

    /**
     * 活动名称
     *
     * @mbg.generated
     */
    private String activityName;

    /**
     * 活动状态  1  2   3
     *
     * @mbg.generated
     */
    private Byte activityStatus;

    /**
     * 活动地点
     *
     * @mbg.generated
     */
    private String activityAddress;

    /**
     * 活动时间
     *
     * @mbg.generated
     */
    private Date activityTime;

    /**
     * 活动主图名称
     *
     * @mbg.generated
     */
    private String activityPicName;

    /**
     * 活动主图url
     *
     * @mbg.generated
     */
    private String activityPicUrl;

    /**
     * 报名人数限制
     *
     * @mbg.generated
     */
    private Integer signUpLimit;

    /**
     * 报名人数
     *
     * @mbg.generated
     */
    private Integer signUpCount;

    /**
     * 活动价格
     *
     * @mbg.generated
     */
    private BigDecimal price;

    /**
     * 备注 0删除 1上架 2下架
     *
     * @mbg.generated
     */
    private Byte status;

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
     * 活动内容
     *
     * @mbg.generated
     */
    private String activityContent;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 wx_activity.id
     *
     * @return 返回值对应 wx_activity.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_activity.id
     *
     * @param id 值对应 wx_activity.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 活动编码<br/>
     * 返回值对应的表列名 wx_activity.activity_code
     *
     * @return 返回值对应 wx_activity.activity_code
     *
     * @mbg.generated
     */
    public String getActivityCode() {
        return activityCode;
    }

    /**
     * 活动编码<br/>
     * wx_activity.activity_code
     *
     * @param activityCode 值对应 wx_activity.activity_code
     *
     * @mbg.generated
     */
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode == null ? null : activityCode.trim();
    }

    /**
     * 活动名称<br/>
     * 返回值对应的表列名 wx_activity.activity_name
     *
     * @return 返回值对应 wx_activity.activity_name
     *
     * @mbg.generated
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * 活动名称<br/>
     * wx_activity.activity_name
     *
     * @param activityName 值对应 wx_activity.activity_name
     *
     * @mbg.generated
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    /**
     * 活动状态  1  2   3<br/>
     * 返回值对应的表列名 wx_activity.activity_status
     *
     * @return 返回值对应 wx_activity.activity_status
     *
     * @mbg.generated
     */
    public Byte getActivityStatus() {
        return activityStatus;
    }

    /**
     * 活动状态  1  2   3<br/>
     * wx_activity.activity_status
     *
     * @param activityStatus 值对应 wx_activity.activity_status
     *
     * @mbg.generated
     */
    public void setActivityStatus(Byte activityStatus) {
        this.activityStatus = activityStatus;
    }

    /**
     * 活动地点<br/>
     * 返回值对应的表列名 wx_activity.activity_address
     *
     * @return 返回值对应 wx_activity.activity_address
     *
     * @mbg.generated
     */
    public String getActivityAddress() {
        return activityAddress;
    }

    /**
     * 活动地点<br/>
     * wx_activity.activity_address
     *
     * @param activityAddress 值对应 wx_activity.activity_address
     *
     * @mbg.generated
     */
    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress == null ? null : activityAddress.trim();
    }

    /**
     * 活动时间<br/>
     * 返回值对应的表列名 wx_activity.activity_time
     *
     * @return 返回值对应 wx_activity.activity_time
     *
     * @mbg.generated
     */
    public Date getActivityTime() {
        return activityTime;
    }

    /**
     * 活动时间<br/>
     * wx_activity.activity_time
     *
     * @param activityTime 值对应 wx_activity.activity_time
     *
     * @mbg.generated
     */
    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    /**
     * 活动主图名称<br/>
     * 返回值对应的表列名 wx_activity.activity_pic_name
     *
     * @return 返回值对应 wx_activity.activity_pic_name
     *
     * @mbg.generated
     */
    public String getActivityPicName() {
        return activityPicName;
    }

    /**
     * 活动主图名称<br/>
     * wx_activity.activity_pic_name
     *
     * @param activityPicName 值对应 wx_activity.activity_pic_name
     *
     * @mbg.generated
     */
    public void setActivityPicName(String activityPicName) {
        this.activityPicName = activityPicName == null ? null : activityPicName.trim();
    }

    /**
     * 活动主图url<br/>
     * 返回值对应的表列名 wx_activity.activity_pic_url
     *
     * @return 返回值对应 wx_activity.activity_pic_url
     *
     * @mbg.generated
     */
    public String getActivityPicUrl() {
        return activityPicUrl;
    }

    /**
     * 活动主图url<br/>
     * wx_activity.activity_pic_url
     *
     * @param activityPicUrl 值对应 wx_activity.activity_pic_url
     *
     * @mbg.generated
     */
    public void setActivityPicUrl(String activityPicUrl) {
        this.activityPicUrl = activityPicUrl == null ? null : activityPicUrl.trim();
    }

    /**
     * 报名人数限制<br/>
     * 返回值对应的表列名 wx_activity.sign_up_limit
     *
     * @return 返回值对应 wx_activity.sign_up_limit
     *
     * @mbg.generated
     */
    public Integer getSignUpLimit() {
        return signUpLimit;
    }

    /**
     * 报名人数限制<br/>
     * wx_activity.sign_up_limit
     *
     * @param signUpLimit 值对应 wx_activity.sign_up_limit
     *
     * @mbg.generated
     */
    public void setSignUpLimit(Integer signUpLimit) {
        this.signUpLimit = signUpLimit;
    }

    /**
     * 报名人数<br/>
     * 返回值对应的表列名 wx_activity.sign_up_count
     *
     * @return 返回值对应 wx_activity.sign_up_count
     *
     * @mbg.generated
     */
    public Integer getSignUpCount() {
        return signUpCount;
    }

    /**
     * 报名人数<br/>
     * wx_activity.sign_up_count
     *
     * @param signUpCount 值对应 wx_activity.sign_up_count
     *
     * @mbg.generated
     */
    public void setSignUpCount(Integer signUpCount) {
        this.signUpCount = signUpCount;
    }

    /**
     * 活动价格<br/>
     * 返回值对应的表列名 wx_activity.price
     *
     * @return 返回值对应 wx_activity.price
     *
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 活动价格<br/>
     * wx_activity.price
     *
     * @param price 值对应 wx_activity.price
     *
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 备注 0删除 1上架 2下架<br/>
     * 返回值对应的表列名 wx_activity.status
     *
     * @return 返回值对应 wx_activity.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 备注 0删除 1上架 2下架<br/>
     * wx_activity.status
     *
     * @param status 值对应 wx_activity.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 备注<br/>
     * 返回值对应的表列名 wx_activity.remark
     *
     * @return 返回值对应 wx_activity.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * wx_activity.remark
     *
     * @param remark 值对应 wx_activity.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 wx_activity.create_time
     *
     * @return 返回值对应 wx_activity.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * wx_activity.create_time
     *
     * @param createTime 值对应 wx_activity.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_activity.creator
     *
     * @return 返回值对应 wx_activity.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_activity.creator
     *
     * @param creator 值对应 wx_activity.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_activity.modify_time
     *
     * @return 返回值对应 wx_activity.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_activity.modify_time
     *
     * @param modifyTime 值对应 wx_activity.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_activity.modifier
     *
     * @return 返回值对应 wx_activity.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_activity.modifier
     *
     * @param modifier 值对应 wx_activity.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 活动内容<br/>
     * 返回值对应的表列名 wx_activity.activity_content
     *
     * @return 返回值对应 wx_activity.activity_content
     *
     * @mbg.generated
     */
    public String getActivityContent() {
        return activityContent;
    }

    /**
     * 活动内容<br/>
     * wx_activity.activity_content
     *
     * @param activityContent 值对应 wx_activity.activity_content
     *
     * @mbg.generated
     */
    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent == null ? null : activityContent.trim();
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
        WxActivity other = (WxActivity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getActivityCode() == null ? other.getActivityCode() == null : this.getActivityCode().equals(other.getActivityCode()))
            && (this.getActivityName() == null ? other.getActivityName() == null : this.getActivityName().equals(other.getActivityName()))
            && (this.getActivityStatus() == null ? other.getActivityStatus() == null : this.getActivityStatus().equals(other.getActivityStatus()))
            && (this.getActivityAddress() == null ? other.getActivityAddress() == null : this.getActivityAddress().equals(other.getActivityAddress()))
            && (this.getActivityTime() == null ? other.getActivityTime() == null : this.getActivityTime().equals(other.getActivityTime()))
            && (this.getActivityPicName() == null ? other.getActivityPicName() == null : this.getActivityPicName().equals(other.getActivityPicName()))
            && (this.getActivityPicUrl() == null ? other.getActivityPicUrl() == null : this.getActivityPicUrl().equals(other.getActivityPicUrl()))
            && (this.getSignUpLimit() == null ? other.getSignUpLimit() == null : this.getSignUpLimit().equals(other.getSignUpLimit()))
            && (this.getSignUpCount() == null ? other.getSignUpCount() == null : this.getSignUpCount().equals(other.getSignUpCount()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getActivityContent() == null ? other.getActivityContent() == null : this.getActivityContent().equals(other.getActivityContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getActivityCode() == null) ? 0 : getActivityCode().hashCode());
        result = prime * result + ((getActivityName() == null) ? 0 : getActivityName().hashCode());
        result = prime * result + ((getActivityStatus() == null) ? 0 : getActivityStatus().hashCode());
        result = prime * result + ((getActivityAddress() == null) ? 0 : getActivityAddress().hashCode());
        result = prime * result + ((getActivityTime() == null) ? 0 : getActivityTime().hashCode());
        result = prime * result + ((getActivityPicName() == null) ? 0 : getActivityPicName().hashCode());
        result = prime * result + ((getActivityPicUrl() == null) ? 0 : getActivityPicUrl().hashCode());
        result = prime * result + ((getSignUpLimit() == null) ? 0 : getSignUpLimit().hashCode());
        result = prime * result + ((getSignUpCount() == null) ? 0 : getSignUpCount().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getActivityContent() == null) ? 0 : getActivityContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", activityCode=").append(activityCode);
        sb.append(", activityName=").append(activityName);
        sb.append(", activityStatus=").append(activityStatus);
        sb.append(", activityAddress=").append(activityAddress);
        sb.append(", activityTime=").append(activityTime);
        sb.append(", activityPicName=").append(activityPicName);
        sb.append(", activityPicUrl=").append(activityPicUrl);
        sb.append(", signUpLimit=").append(signUpLimit);
        sb.append(", signUpCount=").append(signUpCount);
        sb.append(", price=").append(price);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", activityContent=").append(activityContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}