package com.azz.platform.merchant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.platform.merchant.api.AuditService;
import com.azz.platform.merchant.mapper.MerchantApplyMapper;
import com.azz.platform.merchant.mapper.MerchantMapper;
import com.azz.platform.merchant.pojo.bo.AuditParam;
import com.azz.util.JSR303ValidateUtils;

/**
 * <P>审核企业信息</P>
 * @version 1.0
 * @author 彭斌  2018年10月20日 下午2:54:40
 */
@RestController
public class AuditServiceImpl implements AuditService{
    
    @Autowired
    MerchantApplyMapper merchantApplyMapper;
    
    @Autowired
    MerchantMapper merchantMapper;
    
    @Override
    public JsonResult<String> auditEnterprise(@RequestBody AuditParam param) {
        JSR303ValidateUtils.validate(param);
        
        
        
        return JsonResult.successJsonResult();
    }


}

