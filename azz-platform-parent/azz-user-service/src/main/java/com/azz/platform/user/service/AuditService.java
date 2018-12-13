/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月20日 下午2:54:40
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.service;

import static org.assertj.core.api.Assertions.setAllowComparingPrivateFields;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.constants.MerchantConstants.QualificationApplyStatus;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.user.common.constants.AuditConstants;
import com.azz.platform.user.mapper.MerchantApplyMapper;
import com.azz.platform.user.mapper.MerchantMapper;
import com.azz.platform.user.pojo.Merchant;
import com.azz.platform.user.pojo.MerchantApply;
import com.azz.platform.user.pojo.bo.AuditParam;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;

/**
 * <P>审核企业信息</P>
 * @version 1.0
 * @author 彭斌  2018年10月20日 下午2:54:40
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AuditService{
    
    @Autowired
    MerchantApplyMapper merchantApplyMapper;
    
    @Autowired
    MerchantMapper merchantMapper;
    
    public JsonResult<String> auditEnterprise(@RequestBody AuditParam param) {
        JSR303ValidateUtils.validate(param);
        
        MerchantApply object = merchantApplyMapper.selectMerchantByCode(param.getMerchantCode());
        
        if(ObjectUtils.isNull(object)) {
            // 商户不存在无法审核
            throw new BaseException(PlatformUserErrorCode.PLATFORM_MERCHANT_ERROR_NO_EXIST);
        }
       
        if(!AuditConstants.AuditStatus.checkStatusExist(param.getStatus())) {
            // 审核状态不存在
            throw new BaseException(PlatformUserErrorCode.PLATFORM_MERCHANT_AUDIT_STATUS_ERROR_NO_EXIST);
        }
        
        if(!object.getStatus().equals(AuditConstants.AuditStatus.PENDING.getValue())) {
            // 该公司信息不在待审核阶段
            throw new BaseException(PlatformUserErrorCode.PLATFORM_MERCHANT_AUDIT_ERROR);
        }
        
        object.setStatus(param.getStatus());
        object.setAuditor(param.getAuditor());
        object.setAuditorTime(new Date());
        merchantApplyMapper.updateByPrimaryKeySelective(object);
        
        Merchant record = merchantMapper.selectByMerchantCode(param.getMerchantCode());
        if(ObjectUtils.isNull(record)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户编码信息不存在");
        }
        
        // 审核通过该企业信息注册到商户表中,拒绝的商户信息将不注册到商户表中依旧保留在申请表中状态为拒绝
        if(param.getStatus().equals(AuditConstants.AuditStatus.PASSED.getValue())) {
            record.setQualificationApplyStatus(QualificationApplyStatus.PASSED.getValue());
        } else if(param.getStatus().equals(AuditConstants.AuditStatus.REFUSED.getValue())){
            record.setQualificationApplyStatus(QualificationApplyStatus.REFUSED.getValue());
        } else {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "审核状态异常");
        }
        record.setAuditor(param.getAuditor());
        merchantMapper.updateByPrimaryKeySelective(record);
        
        return JsonResult.successJsonResult();
    }

}

