/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月20日 下午2:54:40
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.platform.user.api.AuditService;
import com.azz.platform.user.mapper.PlatformMerchantMapper;
import com.azz.platform.user.pojo.PlatformMerchant;
import com.azz.platform.user.pojo.bo.AuditParam;
import com.azz.util.JSR303ValidateUtils;

/**
 * <P>审核企业信息</P>
 * @version 1.0
 * @author 彭斌  2018年10月20日 下午2:54:40
 */
@RestController
public class AuditServiceImpl implements AuditService{
    
    @Autowired
    PlatformMerchantMapper merchantMapper;
    
    @Override
    public JsonResult<String> auditEnterprise(@RequestBody AuditParam param) {
        JSR303ValidateUtils.validate(param);
        
        PlatformMerchant object = merchantMapper.selectMerchantByCode(param.getMerchantCode());
        
        return JsonResult.successJsonResult();
    }

}

