package com.azz.platform.client.common.constants;


/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月19日 下午2:59:26
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 

import lombok.Getter;

/**
 * <P>客户常量</P>
 * @version 1.0
 * @author 彭斌  2018年10月20日 下午3:43:30
 */
public abstract class ClientConstants {
    
    public static final Long CLIENT_ADMIN_ROLE_ID = 1L;
    
    // 0：已拒绝 1：待审核 2：已通过
    public enum EnterpriseType {
	
        PERSONAL(0, "个人"),
    	
        ENTERPRISE(1, "企业");
    	    
    	@Getter
    	private int value;
    	
    	@Getter
    	private String desc;
    	
    	EnterpriseType(int value, String desc) {
    	    this.value = value;
    	    this.desc = desc;
    	}
    	
    }

    
}

