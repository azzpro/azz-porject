package com.azz.core.wx.constants;

public interface WxConstants {
	
	public static final String WXURL = "https://open.weixin.qq.com/connect/qrconnect";
	
	public static final String SCOPE = "snsapi_login";
	
	public static final String CALLBACKURL = "http://c.izz2025.com/azz/api/wechat/callback";
	
	public static final String ACCESSTOKENURL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	public static final String AUTHURL = "https://api.weixin.qq.com/sns/auth";
	
	public static final String USERINFOURL = "https://api.weixin.qq.com/sns/userinfo";
	
	public static final String STATEMSG = "回调有误";
	
	public static final String STATECODE = "1213";
	
	public static final String NOACCESS = "用户未授权";
	
	public static final String NOACCESSCODE = "1214";
	
	public static final String ACCESSTOKENERRORMSG = "access_token无效";
	
	public static final String ACCESSTOKENERRORCODE = "1215";
	
	public static final String HTTPERRORCODE = "2222";
	
	public static final String HTTPERRORMSG = "请求失败";
	
	public static final String SUCCESSMSG = "回调成功";
	
	public static final String SUCCESSCODE = "0000";
}
