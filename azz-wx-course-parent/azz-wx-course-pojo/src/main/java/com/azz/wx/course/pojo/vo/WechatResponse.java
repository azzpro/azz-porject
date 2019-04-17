/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年4月16日 下午3:09:29
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.vo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年4月16日 下午3:09:29
 */
public class WechatResponse {
	private Integer  errcode;
	
	private String errmsg;
	
	private String msgid;
 
	public Integer getErrcode() {
		return errcode;
	}
 
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
 
	public String getErrmsg() {
		return errmsg;
	}
 
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
 
	public String getMsgid() {
		return msgid;
	}
 
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
}

