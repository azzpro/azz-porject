package com.azz.system.vo;

public class QQCallBackInfo {
	private String msg;
	private String code;
	private String nickname;
	private String headimgurl;
	private String openid;
	private String accessToken;
	private String expiresIn;
	private String phone;
	private String password;
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	@Override
	public String toString() {
		return "QQCallBackInfo [msg=" + msg + ", code=" + code + ", nickname=" + nickname + ", headimgurl=" + headimgurl
				+ ", openid=" + openid + ", accessToken=" + accessToken + ", expiresIn=" + expiresIn + ", phone="
				+ phone + ", password=" + password + "]";
	}
	
}
