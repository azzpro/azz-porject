/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月20日 下午2:54:40
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <P>审核企业信息</P>
 * @version 1.0
 * @author 彭斌  2018年10月20日 下午2:54:40
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AuditService{
    
   /* @Autowired
    MerchantApplyMapper merchantApplyMapper;
    
    @Autowired
    MerchantMapper merchantMapper;
    
    @Autowired
    private SystemSmsSendService systemSmsSendService;
    
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
        
        // 发送审批短信
        SmsParams sms = new SmsParams();
        if(param.getStatus().equals(AuditConstants.AuditStatus.PASSED.getValue())) {
            sms.setMsgType(SmsType.MERCHANT_ENTER_EXAMINE_SUCCESS);
        }else if(param.getStatus().equals(AuditConstants.AuditStatus.REFUSED.getValue())){
            sms.setMsgType(SmsType.MERCHANT_ENTER_EXAMINE_FAIL);
        }
        // try-catch起来，防止事务回滚
        try {
            sms.setPhone(record.getContactPhone());
            systemSmsSendService.sendSmsCode(sms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return JsonResult.successJsonResult();
    }*/

}

