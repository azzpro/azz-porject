/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月16日 下午7:33:31
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.platform.user.pojo.bo.AddDeptParam;

/**
 * <P>部门服务管理</P>
 * @version 1.0
 * @author 彭斌  2018年10月16日 下午7:33:31
 */
@FeignClient("azz-login-service")
public interface DeptService {
    
    @PostMapping("addDeptInfo")
    JsonResult<String> addDeptInfo(@RequestBody AddDeptParam param);
}

