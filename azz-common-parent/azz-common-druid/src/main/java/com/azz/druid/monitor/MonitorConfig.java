package com.azz.druid.monitor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月13日 上午11:08:57
 */
@Component
@ConfigurationProperties(prefix = "azz.datasource.monitorConfig")
public class MonitorConfig {
	
    private String available;
    
    private String allow;
    
    private String deny;
    
    private String loginUsername;
    
    private String loginPassword;
    
    private String resetEnable;

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getAllow() {
        return allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    public String getDeny() {
        return deny;
    }

    public void setDeny(String deny) {
        this.deny = deny;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getResetEnable() {
        return resetEnable;
    }

    public void setResetEnable(String resetEnable) {
        this.resetEnable = resetEnable;
    }
}
