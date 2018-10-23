package com.azz.platform.client.common.constants;


/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月19日 下午2:59:26
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 

import lombok.Getter;

/**
 * <P>审核常量</P>
 * @version 1.0
 * @author 彭斌  2018年10月20日 下午3:43:30
 */
public abstract class AuditConstants {
    // 0：已拒绝 1：待审核 2：已通过
    public enum AuditStatus {
	
        REFUSED(0, "已拒绝"),
    	
        PENDING(1, "待审核"),
    	
        PASSED(2, "已通过");
    	
        
        
    	@Getter
    	private int value;
    	
    	@Getter
    	private String desc;
    	
    	AuditStatus(int value, String desc) {
    	    this.value = value;
    	    this.desc = desc;
    	}
    	
    	public static boolean checkStatusExist(int value) {
            AuditStatus[] values = AuditStatus.values();
            for (AuditStatus auditStatus : values) {
                if (auditStatus.getValue() == value) {
                return true;
                }
            }
            return false;
        }
    }

    
}

