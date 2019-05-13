package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WxSolicitContribution implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 征稿编码
     *
     * @mbg.generated
     */
    private String solicitContributionCode;

    /**
     * 征稿名称
     *
     * @mbg.generated
     */
    private String solicitContributionName;

    /**
     * 征稿主图名称
     *
     * @mbg.generated
     */
    private String solicitContributionPicName;

    /**
     * 征稿主图url
     *
     * @mbg.generated
     */
    private String solicitContributionPicUrl;

    /**
     * 征稿状态 0删除 1上架  2下架 
     *
     * @mbg.generated
     */
    private Byte solicitContributionStatus;

    /**
     * 投票数
     *
     * @mbg.generated
     */
    private Integer voteCount;

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
     * 征稿内容
     *
     * @mbg.generated
     */
    private String solicitContributionContent;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 wx_solicit_contribution.id
     *
     * @return 返回值对应 wx_solicit_contribution.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_solicit_contribution.id
     *
     * @param id 值对应 wx_solicit_contribution.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 征稿编码<br/>
     * 返回值对应的表列名 wx_solicit_contribution.solicit_contribution_code
     *
     * @return 返回值对应 wx_solicit_contribution.solicit_contribution_code
     *
     * @mbg.generated
     */
    public String getSolicitContributionCode() {
        return solicitContributionCode;
    }

    /**
     * 征稿编码<br/>
     * wx_solicit_contribution.solicit_contribution_code
     *
     * @param solicitContributionCode 值对应 wx_solicit_contribution.solicit_contribution_code
     *
     * @mbg.generated
     */
    public void setSolicitContributionCode(String solicitContributionCode) {
        this.solicitContributionCode = solicitContributionCode == null ? null : solicitContributionCode.trim();
    }

    /**
     * 征稿名称<br/>
     * 返回值对应的表列名 wx_solicit_contribution.solicit_contribution_name
     *
     * @return 返回值对应 wx_solicit_contribution.solicit_contribution_name
     *
     * @mbg.generated
     */
    public String getSolicitContributionName() {
        return solicitContributionName;
    }

    /**
     * 征稿名称<br/>
     * wx_solicit_contribution.solicit_contribution_name
     *
     * @param solicitContributionName 值对应 wx_solicit_contribution.solicit_contribution_name
     *
     * @mbg.generated
     */
    public void setSolicitContributionName(String solicitContributionName) {
        this.solicitContributionName = solicitContributionName == null ? null : solicitContributionName.trim();
    }

    /**
     * 征稿主图名称<br/>
     * 返回值对应的表列名 wx_solicit_contribution.solicit_contribution_pic_name
     *
     * @return 返回值对应 wx_solicit_contribution.solicit_contribution_pic_name
     *
     * @mbg.generated
     */
    public String getSolicitContributionPicName() {
        return solicitContributionPicName;
    }

    /**
     * 征稿主图名称<br/>
     * wx_solicit_contribution.solicit_contribution_pic_name
     *
     * @param solicitContributionPicName 值对应 wx_solicit_contribution.solicit_contribution_pic_name
     *
     * @mbg.generated
     */
    public void setSolicitContributionPicName(String solicitContributionPicName) {
        this.solicitContributionPicName = solicitContributionPicName == null ? null : solicitContributionPicName.trim();
    }

    /**
     * 征稿主图url<br/>
     * 返回值对应的表列名 wx_solicit_contribution.solicit_contribution_pic_url
     *
     * @return 返回值对应 wx_solicit_contribution.solicit_contribution_pic_url
     *
     * @mbg.generated
     */
    public String getSolicitContributionPicUrl() {
        return solicitContributionPicUrl;
    }

    /**
     * 征稿主图url<br/>
     * wx_solicit_contribution.solicit_contribution_pic_url
     *
     * @param solicitContributionPicUrl 值对应 wx_solicit_contribution.solicit_contribution_pic_url
     *
     * @mbg.generated
     */
    public void setSolicitContributionPicUrl(String solicitContributionPicUrl) {
        this.solicitContributionPicUrl = solicitContributionPicUrl == null ? null : solicitContributionPicUrl.trim();
    }

    /**
     * 征稿状态 0删除 1上架  2下架 <br/>
     * 返回值对应的表列名 wx_solicit_contribution.solicit_contribution_status
     *
     * @return 返回值对应 wx_solicit_contribution.solicit_contribution_status
     *
     * @mbg.generated
     */
    public Byte getSolicitContributionStatus() {
        return solicitContributionStatus;
    }

    /**
     * 征稿状态 0删除 1上架  2下架 <br/>
     * wx_solicit_contribution.solicit_contribution_status
     *
     * @param solicitContributionStatus 值对应 wx_solicit_contribution.solicit_contribution_status
     *
     * @mbg.generated
     */
    public void setSolicitContributionStatus(Byte solicitContributionStatus) {
        this.solicitContributionStatus = solicitContributionStatus;
    }

    /**
     * 投票数<br/>
     * 返回值对应的表列名 wx_solicit_contribution.vote_count
     *
     * @return 返回值对应 wx_solicit_contribution.vote_count
     *
     * @mbg.generated
     */
    public Integer getVoteCount() {
        return voteCount;
    }

    /**
     * 投票数<br/>
     * wx_solicit_contribution.vote_count
     *
     * @param voteCount 值对应 wx_solicit_contribution.vote_count
     *
     * @mbg.generated
     */
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    /**
     * 备注<br/>
     * 返回值对应的表列名 wx_solicit_contribution.remark
     *
     * @return 返回值对应 wx_solicit_contribution.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注<br/>
     * wx_solicit_contribution.remark
     *
     * @param remark 值对应 wx_solicit_contribution.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 wx_solicit_contribution.create_time
     *
     * @return 返回值对应 wx_solicit_contribution.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * wx_solicit_contribution.create_time
     *
     * @param createTime 值对应 wx_solicit_contribution.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_solicit_contribution.creator
     *
     * @return 返回值对应 wx_solicit_contribution.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_solicit_contribution.creator
     *
     * @param creator 值对应 wx_solicit_contribution.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_solicit_contribution.modify_time
     *
     * @return 返回值对应 wx_solicit_contribution.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_solicit_contribution.modify_time
     *
     * @param modifyTime 值对应 wx_solicit_contribution.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_solicit_contribution.modifier
     *
     * @return 返回值对应 wx_solicit_contribution.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_solicit_contribution.modifier
     *
     * @param modifier 值对应 wx_solicit_contribution.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 征稿内容<br/>
     * 返回值对应的表列名 wx_solicit_contribution.solicit_contribution_content
     *
     * @return 返回值对应 wx_solicit_contribution.solicit_contribution_content
     *
     * @mbg.generated
     */
    public String getSolicitContributionContent() {
        return solicitContributionContent;
    }

    /**
     * 征稿内容<br/>
     * wx_solicit_contribution.solicit_contribution_content
     *
     * @param solicitContributionContent 值对应 wx_solicit_contribution.solicit_contribution_content
     *
     * @mbg.generated
     */
    public void setSolicitContributionContent(String solicitContributionContent) {
        this.solicitContributionContent = solicitContributionContent == null ? null : solicitContributionContent.trim();
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
        WxSolicitContribution other = (WxSolicitContribution) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSolicitContributionCode() == null ? other.getSolicitContributionCode() == null : this.getSolicitContributionCode().equals(other.getSolicitContributionCode()))
            && (this.getSolicitContributionName() == null ? other.getSolicitContributionName() == null : this.getSolicitContributionName().equals(other.getSolicitContributionName()))
            && (this.getSolicitContributionPicName() == null ? other.getSolicitContributionPicName() == null : this.getSolicitContributionPicName().equals(other.getSolicitContributionPicName()))
            && (this.getSolicitContributionPicUrl() == null ? other.getSolicitContributionPicUrl() == null : this.getSolicitContributionPicUrl().equals(other.getSolicitContributionPicUrl()))
            && (this.getSolicitContributionStatus() == null ? other.getSolicitContributionStatus() == null : this.getSolicitContributionStatus().equals(other.getSolicitContributionStatus()))
            && (this.getVoteCount() == null ? other.getVoteCount() == null : this.getVoteCount().equals(other.getVoteCount()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getSolicitContributionContent() == null ? other.getSolicitContributionContent() == null : this.getSolicitContributionContent().equals(other.getSolicitContributionContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSolicitContributionCode() == null) ? 0 : getSolicitContributionCode().hashCode());
        result = prime * result + ((getSolicitContributionName() == null) ? 0 : getSolicitContributionName().hashCode());
        result = prime * result + ((getSolicitContributionPicName() == null) ? 0 : getSolicitContributionPicName().hashCode());
        result = prime * result + ((getSolicitContributionPicUrl() == null) ? 0 : getSolicitContributionPicUrl().hashCode());
        result = prime * result + ((getSolicitContributionStatus() == null) ? 0 : getSolicitContributionStatus().hashCode());
        result = prime * result + ((getVoteCount() == null) ? 0 : getVoteCount().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getSolicitContributionContent() == null) ? 0 : getSolicitContributionContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", solicitContributionCode=").append(solicitContributionCode);
        sb.append(", solicitContributionName=").append(solicitContributionName);
        sb.append(", solicitContributionPicName=").append(solicitContributionPicName);
        sb.append(", solicitContributionPicUrl=").append(solicitContributionPicUrl);
        sb.append(", solicitContributionStatus=").append(solicitContributionStatus);
        sb.append(", voteCount=").append(voteCount);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", solicitContributionContent=").append(solicitContributionContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}