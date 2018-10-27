/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:27:34
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.constants.MerchantConstants;
import com.azz.core.exception.BaseException;
import com.azz.merchant.mapper.MerchantDeptMapper;
import com.azz.merchant.pojo.MerchantDept;
import com.azz.merchant.pojo.bo.AddMerchantDeptParam;
import com.azz.merchant.pojo.bo.DelDeptParam;
import com.azz.merchant.pojo.bo.EditDeptIsEnableParam;
import com.azz.merchant.pojo.bo.EditMerchantDeptParam;
import com.azz.merchant.pojo.bo.SearchMerchantChildDeptParam;
import com.azz.merchant.pojo.bo.SearchMerchantDeptInfo;
import com.azz.merchant.pojo.bo.SearchMerchantDeptListParam;
import com.azz.merchant.pojo.vo.MerchantDeptInfo;
import com.azz.merchant.pojo.vo.MerchantDeptList;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * <P>部门服务类</P>
 * @version 1.0
 * @author 彭斌  2018年10月26日 下午3:42:58
 */
@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class DeptService {

    @Autowired
    private MerchantDeptMapper merchantDeptMapper;
    
    @Autowired
     private DbSequenceService dbSequenceService;
    
    JsonResult<String> addFirstLevelDept(@RequestBody AddMerchantDeptParam param){
        // 部门信息非空校验
        JSR303ValidateUtils.validate(param);
        SearchMerchantDeptInfo smd = new SearchMerchantDeptInfo();
        smd.setMerchantId(param.getMerchantId());
        smd.setDeptName(param.getDeptName());
        int mdiObj = merchantDeptMapper.selectFirstLevelExist(smd);
        
        if(mdiObj>0) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_EXIST);
        }
        
        MerchantDept md = new MerchantDept();
        md.setDeptName(param.getDeptName());
        md.setCreateTime(new Date());
        md.setMerchantId(param.getMerchantId());
        md.setParentCode("0");
        md.setStatus(param.getStatus());
        md.setDeptCode(dbSequenceService.getMerchantDepartmentNumber());
        merchantDeptMapper.insertSelective(md);
        
        return JsonResult.successJsonResult();
    }

    
    JsonResult<String> addChildDept(@RequestBody AddMerchantDeptParam param){
        // 部门信息非空校验
        JSR303ValidateUtils.validate(param);
        
        SearchMerchantDeptInfo smd = new SearchMerchantDeptInfo();
        smd.setMerchantId(param.getMerchantId());
        smd.setDeptName(param.getDeptName());
        MerchantDeptInfo mdi = merchantDeptMapper.selectByMerchantIdAndName(smd);
        if(ObjectUtils.isNotNull(mdi)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_EXIST);
        }
        
        MerchantDept md = new MerchantDept();
        md.setDeptName(param.getDeptName());
        md.setCreateTime(new Date());
        md.setMerchantId(param.getMerchantId());
        md.setParentCode(param.getParentCode());
        md.setStatus(param.getStatus());
        md.setDeptCode(dbSequenceService.getMerchantDepartmentNumber()); 
        merchantDeptMapper.insertSelective(md);
        return JsonResult.successJsonResult();
    }
    
    JsonResult<MerchantDeptInfo> getDeptInfo(@RequestBody SearchMerchantDeptInfo param){
        return JsonResult.successJsonResult(merchantDeptMapper.selectByMerchantIdAndName(param));
    }
    
    JsonResult<String> editDept(@RequestBody EditMerchantDeptParam param){
        JSR303ValidateUtils.validate(param);
        
        SearchMerchantDeptInfo smd = new SearchMerchantDeptInfo();
        smd.setMerchantId(param.getMerchantId());
        smd.setDeptName(param.getDeptName());
        MerchantDeptInfo mdi = merchantDeptMapper.selectByMerchantIdAndName(smd);
        if(ObjectUtils.isNull(mdi)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_EXIST);
        }
        
        MerchantDept md = merchantDeptMapper.selectByDeptAllInfo(smd);
        md.setDeptName(param.getDeptName());
        md.setLastModifyTime(new Date());
        md.setModifier(param.getModifier());
        md.setParentCode(param.getParentCode());
        md.setStatus(param.getStatus());
        merchantDeptMapper.updateByPrimaryKeySelective(md);
        return JsonResult.successJsonResult();
    }
    
    JsonResult<List<MerchantDeptList>> searchDeptList(@RequestBody SearchMerchantDeptListParam param){
        JSR303ValidateUtils.validate(param);
        List<MerchantDeptList> merchantDept = merchantDeptMapper.selectFirstDeptList(param);
        return JsonResult.successJsonResult(merchantDept);
    }
    
    JsonResult<List<MerchantDeptList>> searchChildDeptList(@RequestBody SearchMerchantChildDeptParam param){
        JSR303ValidateUtils.validate(param);
        List<MerchantDeptList> merchantChildDept = merchantDeptMapper.selectChildDeptList(param);
        return JsonResult.successJsonResult(merchantChildDept);
    }
    
    JsonResult<String> isEnableDept(@RequestBody EditDeptIsEnableParam param){
        JSR303ValidateUtils.validate(param);
        
        MerchantDept record = merchantDeptMapper.selectByDeptCode(param.getDeptCode());
        if(ObjectUtils.isNull(record)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }
        if(param.getStatus().equals(MerchantConstants.DeptStatus.DISABLE.getValue())) {
            // 禁用
            record.setStatus(MerchantConstants.DeptStatus.DISABLE.getValue());
        } else if(param.getStatus().equals(MerchantConstants.DeptStatus.ENABLE.getValue())) {
            // 启用
            record.setStatus(MerchantConstants.DeptStatus.ENABLE.getValue());
        } else {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_STATUS_ERROR_NO_EXIST);
        }
        record.setModifier(param.getModifier());
        record.setLastModifyTime(new Date());
        merchantDeptMapper.updateByPrimaryKeySelective(record);
        return JsonResult.successJsonResult();
    }
    
    JsonResult<String> delDept(@RequestBody DelDeptParam param){
        JSR303ValidateUtils.validate(param);
        MerchantDept record = merchantDeptMapper.selectByDeptCode(param.getDeptCode());
        record.setStatus(MerchantConstants.DeptStatus.INVALID.getValue());
        merchantDeptMapper.updateByPrimaryKeySelective(record);
        return JsonResult.successJsonResult();
    }
}
