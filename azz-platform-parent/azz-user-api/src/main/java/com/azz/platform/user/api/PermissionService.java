/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月17日 下午5:59:05
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.api;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.azz.core.common.JsonResult;

/**
 * <P>权限服务相关接口</P>
 * @version 1.0
 * @author 黄智聪  2018年10月17日 下午5:59:05
 */
@FeignClient("azz-user-service")
public interface PermissionService {
    
    //JsonResult<String> addRole();
    
    

}

