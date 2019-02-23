package com.azz.system.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class QQClientRegistParam {

	@NotBlank(message = "手机号不允许为空")
    private String phoneNumber;
    
    @NotBlank(message = "手机验证码不允许为空")
    private String verificationCode;

    @NotBlank(message = "密码不允许为空")
    private String password;

    @NotBlank(message = "确认密码不允许为空")    
    private String confirmPassword;
    
    @NotBlank(message = "openid不允许为空")
    private String openid;
	private String accessToken;
	private String expiresIn;
	private String avatarUrl;
	private String nickName;
}
