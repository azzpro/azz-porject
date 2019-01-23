package com.azz.system.pojo;

import java.io.Serializable;

public class ClientWxUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1600998272739357654L;
	private Long id;
	private String openid;
	private String unionid;
	private String access_token;
	private Long expires_in;
	private String refresh_token;
	private String scope;
	private String userCode;
	private String avatarUrl;
	private String nickName;
	
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Long getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Override
	public String toString() {
		return "WxScanUser [id=" + id + ", openid=" + openid + ", unionid=" + unionid + ", access_token=" + access_token
				+ ", expires_in=" + expires_in + ", refresh_token=" + refresh_token + ", scope=" + scope + ", userCode="
				+ userCode + "]";
	}
	
}
