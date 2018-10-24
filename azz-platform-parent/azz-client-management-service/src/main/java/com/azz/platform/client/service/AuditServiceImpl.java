/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午4:44:51
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.platform.client.api.AuditService;
import com.azz.platform.client.mapper.ClientApplyMapper;
import com.azz.platform.client.mapper.ClientMapper;
import com.azz.platform.client.pojo.bo.AuditParam;
import com.azz.util.JSR303ValidateUtils;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月24日 下午4:44:51
 */
@Transactional(rollbackFor=Exception.class)
@RestController
public class AuditServiceImpl implements AuditService{

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    ClientApplyMapper clientApplyMapper;
    
    @Override
    public JsonResult<String> auditClient(AuditParam param) {
        JSR303ValidateUtils.validate(param);
        
        
        return null;
    }

}

