/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午1:24:28
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.client.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.client.mapper.ClientDeptMapper;
import com.azz.client.pojo.ClientDept;
import com.azz.client.pojo.bo.AddClientDeptParam;
import com.azz.client.pojo.bo.DelDeptParam;
import com.azz.client.pojo.bo.EditClientDeptParam;
import com.azz.client.pojo.bo.EditDeptIsEnableParam;
import com.azz.client.pojo.bo.SearchClientChildDeptParam;
import com.azz.client.pojo.bo.SearchClientDeptInfoByCodeParam;
import com.azz.client.pojo.bo.SearchClientDeptInfoParam;
import com.azz.client.pojo.bo.SearchClientDeptIsExistParam;
import com.azz.client.pojo.bo.SearchClientDeptParam;
import com.azz.client.pojo.vo.ClientDeptInfo;
import com.azz.client.pojo.vo.ClientDeptList;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.ClientErrorCode;
import com.azz.core.constants.ClientConstants;
import com.azz.core.exception.BaseException;
import com.azz.system.sequence.api.RandomSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;


/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月29日 上午11:29:39
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ClientDeptService {

    
    @Autowired
    private ClientDeptMapper clientDeptMapper;
    
    @Autowired
    private RandomSequenceService randomSequenceService;
    
    public JsonResult<List<ClientDeptList>> searchClientDeptList(@RequestBody SearchClientDeptParam param){
        JSR303ValidateUtils.validate(param);
        List<ClientDeptList> clientDeptList = clientDeptMapper.selectFirstLevelList(param);
        return JsonResult.successJsonResult(clientDeptList);
    }
    
    public JsonResult<List<ClientDeptList>> searchChildClientDeptList(@RequestBody SearchClientChildDeptParam param){
        JSR303ValidateUtils.validate(param);
        List<ClientDeptList> clientDeptList = clientDeptMapper.selectChildlList(param);
        return JsonResult.successJsonResult(clientDeptList);
    }
    
    public JsonResult<String> editDept(@RequestBody EditClientDeptParam param){
        JSR303ValidateUtils.validate(param);
        
        ClientDept cdObj = clientDeptMapper.selectByDeptCode(param.getDeptCode());
        if(ObjectUtils.isNull(cdObj)) {
            throw new BaseException(ClientErrorCode.CLIENT_DEPT_ERROR_NO_EXIST);
        }
        
        if(!param.getDeptName().trim().equals(cdObj.getDeptName())) {
            SearchClientDeptInfoParam scdObj = new SearchClientDeptInfoParam();
            scdObj.setCompanyCode(param.getCompanyCode());
            scdObj.setDeptName(param.getDeptName().trim());
            ClientDept clientDept = clientDeptMapper.selectClientDeptInfoByName(scdObj);
            if(ObjectUtils.isNotNull(clientDept)) {
                throw new BaseException(ClientErrorCode.CLIENT_DEPT_ERROR_EXIST);
            }
        }
        
        ClientDept cdParentObj = clientDeptMapper.selectByDeptCode(param.getParentCode());
        
        if(ObjectUtils.isNull(cdParentObj)) {
            throw new BaseException(ClientErrorCode.CLIENT_DEPT_PARENT_CODE_ERROR_NO_EXIST);
        }
        
        cdObj.setDeptName(param.getDeptName().trim());
        cdObj.setLastModifyTime(new Date());
        cdObj.setParentCode(param.getParentCode());
        cdObj.setStatus(param.getStatus());
        cdObj.setModifier(param.getModifier());
        clientDeptMapper.updateByPrimaryKeySelective(cdObj);
        return JsonResult.successJsonResult();
    }
    
    public JsonResult<String> isEnableDept(@RequestBody EditDeptIsEnableParam param){
        JSR303ValidateUtils.validate(param);
        
        ClientDept cdObj = clientDeptMapper.selectByDeptCode(param.getDeptCode());
        if(ObjectUtils.isNull(cdObj)) {
            throw new BaseException(ClientErrorCode.CLIENT_DEPT_ERROR_NO_EXIST);
         }
        if(param.getStatus().equals(ClientConstants.DeptStatus.DISABLE.getValue())) {
            // 禁用
            cdObj.setStatus(ClientConstants.DeptStatus.DISABLE.getValue());
        } else if(param.getStatus().equals(ClientConstants.DeptStatus.ENABLE.getValue())) {
            // 启用
            cdObj.setStatus(ClientConstants.DeptStatus.ENABLE.getValue());
        } else {
            // 异常信息 状态不存在
            throw new BaseException(ClientErrorCode.CLIENT_DEPT_STATUS_ERROR_NO_EXIST);
        }
        clientDeptMapper.updateByPrimaryKeySelective(cdObj);
        return JsonResult.successJsonResult();
    }
    
    public JsonResult<String> delDept(@RequestBody DelDeptParam param){
        JSR303ValidateUtils.validate(param);
        ClientDept cdObj = clientDeptMapper.selectByDeptCode(param.getDeptCode());
        if(ObjectUtils.isNull(cdObj)) {
            throw new BaseException(ClientErrorCode.CLIENT_DEPT_ERROR_NO_EXIST);
         }
        // 校验该部门是否还存在用户信息
        int isExist = clientDeptMapper.selectClientUserIsExistDept(param.getDeptCode());
        if(isExist > 0) {
            throw new BaseException(ClientErrorCode.CLIENT_DEPT_CLIENT_ERROR_EXIST);
        }
        cdObj.setStatus(ClientConstants.DeptStatus.INVALID.getValue());
        cdObj.setLastModifyTime(new Date());
        cdObj.setModifier(param.getModifier());
        clientDeptMapper.updateByPrimaryKeySelective(cdObj);
        return JsonResult.successJsonResult();
    }
    
    public JsonResult<String> addFirstLevelDept(@RequestBody AddClientDeptParam param){
        // 部门信息非空校验
        JSR303ValidateUtils.validate(param);
        SearchClientDeptIsExistParam record = new SearchClientDeptIsExistParam();
        record.setCompanyCode(param.getCompanyCode());
        record.setDeptName(param.getDeptName());
        int isExist = clientDeptMapper.selectFirstLevelExist(record);
        if(isExist>0) {
            throw new BaseException(ClientErrorCode.CLIENT_DEPT_ERROR_EXIST);
        }
        ClientDept clientDept = new ClientDept();
        clientDept.setDeptCode(randomSequenceService.getDepartmentNumber());
        clientDept.setCompanyCode(param.getCompanyCode());
        clientDept.setCreateTime(new Date());
        clientDept.setDeptName(param.getDeptName());
        clientDept.setParentCode("0");
        clientDept.setStatus(param.getStatus());
        clientDeptMapper.insertSelective(clientDept);
        
        return JsonResult.successJsonResult();
    }
    
    public JsonResult<String> addChildDept(@RequestBody AddClientDeptParam param){
        // 部门信息非空校验
        JSR303ValidateUtils.validate(param);
        SearchClientDeptInfoParam record = new SearchClientDeptInfoParam();
        record.setCompanyCode(param.getCompanyCode());
        record.setDeptName(param.getDeptName());
        ClientDept cdObj = clientDeptMapper.selectClientDeptInfoByName(record);
        if(ObjectUtils.isNotNull(cdObj)) {
            throw new BaseException(ClientErrorCode.CLIENT_DEPT_ERROR_EXIST);
        }
        
        SearchClientDeptInfoByCodeParam deptByCodeObj = new SearchClientDeptInfoByCodeParam();
        deptByCodeObj.setCompanyCode(param.getCompanyCode());
        deptByCodeObj.setDeptCode(param.getParentCode());
        ClientDept dept = clientDeptMapper.selectClientDeptInfoByCode(deptByCodeObj);
        
        if(ObjectUtils.isNotNull(dept)) {
            throw new BaseException(ClientErrorCode.CLIENT_DEPT_ERROR_EXIST);
        }
        
        ClientDept clientDept = new ClientDept();
        clientDept.setDeptCode(randomSequenceService.getDepartmentNumber());
        clientDept.setCompanyCode(param.getCompanyCode());
        clientDept.setCreateTime(new Date());
        clientDept.setDeptName(param.getDeptName());
        clientDept.setParentCode(param.getParentCode());
        clientDept.setStatus(param.getStatus());
        clientDeptMapper.insertSelective(clientDept);
        return JsonResult.successJsonResult();
    }
    
    public JsonResult<ClientDeptInfo> getDeptInfo(@RequestBody SearchClientDeptInfoByCodeParam param){
        ClientDeptInfo cdObj = clientDeptMapper.selectClientByCode(param);
        return JsonResult.successJsonResult(cdObj);
    }
}

