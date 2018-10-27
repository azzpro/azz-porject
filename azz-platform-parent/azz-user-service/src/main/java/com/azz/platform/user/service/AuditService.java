/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月20日 下午2:54:40
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.exception.BaseException;
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
        
        
        // 审核通过该企业信息注册到商户表中,拒绝的商户信息将不注册到商户表中依旧保留在申请表中状态为拒绝
        if(object.getStatus().equals(AuditConstants.AuditStatus.PASSED.getValue())) {
            Merchant record = new Merchant();
           /* record.setMerchantCode(object.getMerchantCode());
            record.setMerchantName(merchantName);
            record.setLegalPersonName(legalPersonName);
            record.setLegalPersonIdCard(legalPersonIdCard);
            record.setCreditCode(creditCode);
            record.setCompanyTel(companyTel);
            record.setAddress(address);
            record.settr*/
            
            
            merchantMapper.insertSelective(record);
        }
        
        
        return JsonResult.successJsonResult();
    }

}

