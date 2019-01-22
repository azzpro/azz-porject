package com.azz.system.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class WxLoginParam {
	
		@NotBlank(message = "手机号不允许为空")
	    private String phoneNumber;
	    
	    @NotBlank(message = "密码不允许为空")
	    private String password;
	    @NotBlank(message = "openid不允许为空")
	    private String openid;
		private String accessToken;
		private String expiresIn;
		private String refreshToken;
		private String scope;
		private String unionid;
		private String avatarUrl;
		private String nickName;
}
