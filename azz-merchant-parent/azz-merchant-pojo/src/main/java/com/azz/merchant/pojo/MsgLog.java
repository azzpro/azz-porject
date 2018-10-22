package com.azz.merchant.pojo;


import java.io.Serializable;
import java.util.Date;

public class MsgLog implements Serializable {
    /**
     * 主键ID
     *
     * @mbg.generated
     */
    private Long msgId;

    /**
     * 短信标题
     *
     * @mbg.generated
     */
    private String msgTitle;

    /**
     * 短信内容
     *
     * @mbg.generated
     */
    private String msgContent;

    /**
     * 手机号码
     *
     * @mbg.generated
     */
    private Long msgPhone;

    /**
     * 发送短信时间
     *
     * @mbg.generated
     */
    private Date msgTime;

    /**
     * 发送状态 1 成功 2 失败
     *
     * @mbg.generated
     */
    private Integer msgStatus;

    /**
     * 短信平台 1建周 2 亿美
     *
     * @mbg.generated
     */
    private Integer msgPlatform;

    /**
     * 类型   1 : 注册 2 : 找回密码  3：忘记密码 4：提现 ，5：修改登录手机号
     *
     * @mbg.generated
     */
    private Integer msgType;

    /**
     * 短信错误信息
     *
     * @mbg.generated
     */
    private String msgError;

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID<br/>
     * 返回值对应的表列名 msg_log.msg_id
     *
     * @return 返回值对应 msg_log.msg_id
     *
     * @mbg.generated
     */
    public Long getMsgId() {
        return msgId;
    }

    /**
     * 主键ID<br/>
     * msg_log.msg_id
     *
     * @param msgId 值对应 msg_log.msg_id
     *
     * @mbg.generated
     */
    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    /**
     * 短信标题<br/>
     * 返回值对应的表列名 msg_log.msg_title
     *
     * @return 返回值对应 msg_log.msg_title
     *
     * @mbg.generated
     */
    public String getMsgTitle() {
        return msgTitle;
    }

    /**
     * 短信标题<br/>
     * msg_log.msg_title
     *
     * @param msgTitle 值对应 msg_log.msg_title
     *
     * @mbg.generated
     */
    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    /**
     * 短信内容<br/>
     * 返回值对应的表列名 msg_log.msg_content
     *
     * @return 返回值对应 msg_log.msg_content
     *
     * @mbg.generated
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * 短信内容<br/>
     * msg_log.msg_content
     *
     * @param msgContent 值对应 msg_log.msg_content
     *
     * @mbg.generated
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    /**
     * 手机号码<br/>
     * 返回值对应的表列名 msg_log.msg_phone
     *
     * @return 返回值对应 msg_log.msg_phone
     *
     * @mbg.generated
     */
    public Long getMsgPhone() {
        return msgPhone;
    }

    /**
     * 手机号码<br/>
     * msg_log.msg_phone
     *
     * @param msgPhone 值对应 msg_log.msg_phone
     *
     * @mbg.generated
     */
    public void setMsgPhone(Long msgPhone) {
        this.msgPhone = msgPhone;
    }

    /**
     * 发送短信时间<br/>
     * 返回值对应的表列名 msg_log.msg_time
     *
     * @return 返回值对应 msg_log.msg_time
     *
     * @mbg.generated
     */
    public Date getMsgTime() {
        return msgTime;
    }

    /**
     * 发送短信时间<br/>
     * msg_log.msg_time
     *
     * @param msgTime 值对应 msg_log.msg_time
     *
     * @mbg.generated
     */
    public void setMsgTime(Date msgTime) {
        this.msgTime = msgTime;
    }

    /**
     * 发送状态 1 成功 2 失败<br/>
     * 返回值对应的表列名 msg_log.msg_status
     *
     * @return 返回值对应 msg_log.msg_status
     *
     * @mbg.generated
     */
    public Integer getMsgStatus() {
        return msgStatus;
    }

    /**
     * 发送状态 1 成功 2 失败<br/>
     * msg_log.msg_status
     *
     * @param msgStatus 值对应 msg_log.msg_status
     *
     * @mbg.generated
     */
    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }

    /**
     * 短信平台 1建周 2 亿美<br/>
     * 返回值对应的表列名 msg_log.msg_platform
     *
     * @return 返回值对应 msg_log.msg_platform
     *
     * @mbg.generated
     */
    public Integer getMsgPlatform() {
        return msgPlatform;
    }

    /**
     * 短信平台 1建周 2 亿美<br/>
     * msg_log.msg_platform
     *
     * @param msgPlatform 值对应 msg_log.msg_platform
     *
     * @mbg.generated
     */
    public void setMsgPlatform(Integer msgPlatform) {
        this.msgPlatform = msgPlatform;
    }

    /**
     * 类型   1 : 注册 2 : 找回密码  3：忘记密码 4：提现 ，5：修改登录手机号<br/>
     * 返回值对应的表列名 msg_log.msg_type
     *
     * @return 返回值对应 msg_log.msg_type
     *
     * @mbg.generated
     */
    public Integer getMsgType() {
        return msgType;
    }

    /**
     * 类型   1 : 注册 2 : 找回密码  3：忘记密码 4：提现 ，5：修改登录手机号<br/>
     * msg_log.msg_type
     *
     * @param msgType 值对应 msg_log.msg_type
     *
     * @mbg.generated
     */
    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    /**
     * 短信错误信息<br/>
     * 返回值对应的表列名 msg_log.msg_error
     *
     * @return 返回值对应 msg_log.msg_error
     *
     * @mbg.generated
     */
    public String getMsgError() {
        return msgError;
    }

    /**
     * 短信错误信息<br/>
     * msg_log.msg_error
     *
     * @param msgError 值对应 msg_log.msg_error
     *
     * @mbg.generated
     */
    public void setMsgError(String msgError) {
        this.msgError = msgError == null ? null : msgError.trim();
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
        MsgLog other = (MsgLog) that;
        return (this.getMsgId() == null ? other.getMsgId() == null : this.getMsgId().equals(other.getMsgId()))
            && (this.getMsgTitle() == null ? other.getMsgTitle() == null : this.getMsgTitle().equals(other.getMsgTitle()))
            && (this.getMsgContent() == null ? other.getMsgContent() == null : this.getMsgContent().equals(other.getMsgContent()))
            && (this.getMsgPhone() == null ? other.getMsgPhone() == null : this.getMsgPhone().equals(other.getMsgPhone()))
            && (this.getMsgTime() == null ? other.getMsgTime() == null : this.getMsgTime().equals(other.getMsgTime()))
            && (this.getMsgStatus() == null ? other.getMsgStatus() == null : this.getMsgStatus().equals(other.getMsgStatus()))
            && (this.getMsgPlatform() == null ? other.getMsgPlatform() == null : this.getMsgPlatform().equals(other.getMsgPlatform()))
            && (this.getMsgType() == null ? other.getMsgType() == null : this.getMsgType().equals(other.getMsgType()))
            && (this.getMsgError() == null ? other.getMsgError() == null : this.getMsgError().equals(other.getMsgError()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMsgId() == null) ? 0 : getMsgId().hashCode());
        result = prime * result + ((getMsgTitle() == null) ? 0 : getMsgTitle().hashCode());
        result = prime * result + ((getMsgContent() == null) ? 0 : getMsgContent().hashCode());
        result = prime * result + ((getMsgPhone() == null) ? 0 : getMsgPhone().hashCode());
        result = prime * result + ((getMsgTime() == null) ? 0 : getMsgTime().hashCode());
        result = prime * result + ((getMsgStatus() == null) ? 0 : getMsgStatus().hashCode());
        result = prime * result + ((getMsgPlatform() == null) ? 0 : getMsgPlatform().hashCode());
        result = prime * result + ((getMsgType() == null) ? 0 : getMsgType().hashCode());
        result = prime * result + ((getMsgError() == null) ? 0 : getMsgError().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", msgId=").append(msgId);
        sb.append(", msgTitle=").append(msgTitle);
        sb.append(", msgContent=").append(msgContent);
        sb.append(", msgPhone=").append(msgPhone);
        sb.append(", msgTime=").append(msgTime);
        sb.append(", msgStatus=").append(msgStatus);
        sb.append(", msgPlatform=").append(msgPlatform);
        sb.append(", msgType=").append(msgType);
        sb.append(", msgError=").append(msgError);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}