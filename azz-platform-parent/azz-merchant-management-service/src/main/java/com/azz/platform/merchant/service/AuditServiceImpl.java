package com.azz.platform.merchant.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.exception.BaseException;
import com.azz.platform.merchant.api.AuditService;
import com.azz.platform.merchant.common.constants.AuditConstants;
import com.azz.platform.merchant.mapper.MerchantApplyMapper;
import com.azz.platform.merchant.mapper.MerchantMapper;
import com.azz.platform.merchant.pojo.Merchant;
import com.azz.platform.merchant.pojo.MerchantApply;
import com.azz.platform.merchant.pojo.bo.AuditParam;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;

/**
 * <P>
 * 审核企业信息
 * </P>
 * 
 * @version 1.0
 * @author 彭斌 2018年10月20日 下午2:54:40
 */
@RestController
public class AuditServiceImpl implements AuditService {

    @Autowired
    MerchantApplyMapper merchantApplyMapper;

    @Autowired
    MerchantMapper merchantMapper;

    @Override
    public JsonResult<String> auditEnterprise(@RequestBody AuditParam param) {
        JSR303ValidateUtils.validate(param);

        // 校验商户编码
        MerchantApply maObj = merchantApplyMapper.selectByCodeAndStatus(param.getMerchantCode());

        if (ObjectUtils.isNull(maObj)) {
            // 商户不存在无法审核
            throw new BaseException(PlatformUserErrorCode.PLATFORM_MERCHANT_ERROR_NO_EXIST);
        }

        if (!AuditConstants.AuditStatus.checkStatusExist(param.getStatus())) {
            // 审核状态不存在
            throw new BaseException(
                    PlatformUserErrorCode.PLATFORM_MERCHANT_AUDIT_STATUS_ERROR_NO_EXIST);
        }

        maObj.setStatus(param.getStatus());
        maObj.setAuditor(param.getAuditor());
        maObj.setAuditorTime(new Date());
        // 处理申请资料审批结果
        merchantApplyMapper.updateByPrimaryKeySelective(maObj);

        Merchant merchant = merchantMapper.selectByCode(maObj.getMerchantCode());

        if (param.getStatus().equals(AuditConstants.AuditStatus.PASSED.getValue())) {
            // 审核通过
            merchant.setQualificationApplyStatus(AuditConstants.AuditStatus.PASSED.getValue());
        } else if (param.getStatus().equals(AuditConstants.AuditStatus.REFUSED.getValue())) {
            // 审核拒绝
            merchant.setQualificationApplyStatus(AuditConstants.AuditStatus.REFUSED.getValue());
        }
        merchant.setAuditor(param.getAuditor());
        merchant.setMerchantName(maObj.getMerchantName());
        merchant.setLegalPersonName(maObj.getLegalPersonName());
        merchant.setLegalPersonIdCard(maObj.getLegalPersonName());
        merchant.setCompanyName(maObj.getCompanyName());
        merchant.setCreditCode(maObj.getCreditCode());
        merchant.setCompanyTel(maObj.getCompanyTel());
        merchant.setAddress(maObj.getAddress());
        merchant.setTradingCertificateFirstFileName(maObj.getTradingCertificateFirstFileName());
        merchant.setTradingCertificateFirstFileUrl(maObj.getTradingCertificateFirstFileUrl());
        merchant.setTradingCertificateSecondFileName(maObj.getTradingCertificateSecondFileName());
        merchant.setTradingCertificateSecondFileUrl(maObj.getTradingCertificateSecondFileUrl());
        merchant.setTradingCertificateThirdFileName(maObj.getTradingCertificateThirdFileName());
        merchant.setTradingCertificateThirdFileUrl(maObj.getTradingCertificateThirdFileUrl());
        merchant.setLegalPersonIdCardFileName(maObj.getBusinessLicenseFileName());
        merchant.setLegalPersonIdCardFileUrl(maObj.getBusinessLicenseFileUrl());
        // 审批流程走完需要覆盖商户表数据作为最新的商户信息
        merchantMapper.updateByPrimaryKeySelective(merchant);

        return JsonResult.successJsonResult();
    }


}

