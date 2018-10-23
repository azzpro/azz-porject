package com.azz.system.pojo;

import java.io.Serializable;
import java.util.Date;

public class SystemMsgTemplate implements Serializable {
    /**
	 * TODO
	 */
	private static final long serialVersionUID = -6612453293036073168L;

	/**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 短信模板主题
     *
     * @mbg.generated
     */	
    private String msgTitle;

    /**
     * 短信模板内容
     *
     * @mbg.generated
     */
    private String msgContent;

    /**
     * 短信模板创建人
     *
     * @mbg.generated
     */
    private String msgCreater;

    /**
     * 短信模板创建时间
     *
     * @mbg.generated
     */
    private Date msgCreatetime;

    /**
     * 短信模板ID
     *
     * @mbg.generated
     */
    private String msgTempid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgCreater() {
		return msgCreater;
	}

	public void setMsgCreater(String msgCreater) {
		this.msgCreater = msgCreater;
	}

	public Date getMsgCreatetime() {
		return msgCreatetime;
	}

	public void setMsgCreatetime(Date msgCreatetime) {
		this.msgCreatetime = msgCreatetime;
	}

	public String getMsgTempid() {
		return msgTempid;
	}

	public void setMsgTempid(String msgTempid) {
		this.msgTempid = msgTempid;
	}

	@Override
	public String toString() {
		return "SystemMsgTemplate [id=" + id + ", msgTitle=" + msgTitle + ", msgContent=" + msgContent + ", msgCreater="
				+ msgCreater + ", msgCreatetime=" + msgCreatetime + ", msgTempid=" + msgTempid + "]";
	}

    
    
}