/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午3:54:14
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.platform.merchant.pojo.bo.AuditParam;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月22日 下午3:54:14
 */
@FeignClient("azz-merchant-management-service")
public interface AuditService {
    /**
     * <p>审核企业信息</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月20日 下午2:53:27
     */
    @PostMapping("auditEnterprise")
    JsonResult<String> auditEnterprise(@RequestBody AuditParam param);
}

