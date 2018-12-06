/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月17日 上午9:17:00 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.exception.BaseException;
import com.azz.platform.user.mapper.PlatformDeptMapper;
import com.azz.platform.user.pojo.PlatformDept;
import com.azz.platform.user.pojo.bo.AddDeptParam;
import com.azz.platform.user.pojo.bo.EditDeptParam;
import com.azz.platform.user.pojo.bo.SearchDeptParam;
import com.azz.platform.user.pojo.vo.Dept;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;

/**
 * <P>
 * 部门服务接口
 * </P>
 * 
 * @version 1.0
 * @author 彭斌 2018年10月17日 上午9:17:00
 */
@Service
public class DeptService{

    @Autowired
    private PlatformDeptMapper deptMapper;

    @Autowired
    private DbSequenceService dbSequenceService;
    
    public JsonResult<String> addDeptInfo(@RequestBody AddDeptParam param) {
        // 部门信息非空校验
        JSR303ValidateUtils.validate(param);
        String deptParentCode = param.getParentCode();
        
        PlatformDept dept = new PlatformDept();
        
        if(!"".equals(deptParentCode) && !"0".equals(deptParentCode)) {
            PlatformDept deptObjCode = deptMapper.selectByDeptCode(deptParentCode);
            if(ObjectUtils.isNull(deptObjCode)) {
                throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
            }
            dept.setParentCode(deptParentCode);
            
            if(deptObjCode.getDescription().equals("0")) {
                dept.setDescription("1");
            } else if(deptObjCode.getDescription().equals("1")) {
                dept.setDescription("2");
            } else if(deptObjCode.getDescription().equals("2")) {
                throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_LEVEL_ERROR);
            }
        } else {
            // 系统自动生成部门编码
            dept.setParentCode("0");
            deptParentCode = "0";
            // 部门等级 0 一级 1 二级 2 三级
            dept.setDescription("0");
        }
        
        int count = deptMapper.selectCountByParam(deptParentCode, param.getDeptName());
        if(count>0) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_EXIST);
        }
        
        
        dept.setDeptCode(dbSequenceService.getPlatDepartmentNumber());
        dept.setDeptName(param.getDeptName());
        dept.setStatus(param.getStatus());
        dept.setCreator(param.getCreator());
        dept.setCreateTime(new Date());

        deptMapper.insertSelective(dept);
        return JsonResult.successJsonResult();
    }

    public JsonResult<String> editDeptInfo(@RequestBody EditDeptParam param) {
        JSR303ValidateUtils.validate(param);

        PlatformDept dept = deptMapper.selectByDeptCode(param.getDeptCode());
        if (ObjectUtils.isNull(dept)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }
        String parentCode = param.getParentCode();
        
        if(!parentCode.equals("0")) {
            PlatformDept deptObjCode = deptMapper.selectByDeptCode(parentCode);
            if(ObjectUtils.isNull(deptObjCode)) {
                throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
            }
        }
        
        if (!dept.getDeptName().equals(param.getDeptName().trim())) {
            int count = deptMapper.selectCountByParam(parentCode, param.getDeptName().trim());
            if(count>0) {
                throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_EXIST);
            }
        }
        
        List<PlatformDept> pd = deptMapper.selectByParentDeptCode(parentCode);
        if(pd.size()>0) {
            if(pd.get(0).getDescription().equals("2")) {
                throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_LEVEL_ERROR);
            }
        }
        
        dept.setParentCode(parentCode);
        dept.setDeptName(param.getDeptName());
        dept.setLastModifyTime(new Date());
        dept.setModifier(param.getModifier());
        dept.setStatus(param.getStatus());

        deptMapper.updateByPrimaryKeySelective(dept);
        return JsonResult.successJsonResult();
    }

    public JsonResult<List<Dept>> getDeptList(@RequestBody SearchDeptParam param) {
        List<Dept> infos = deptMapper.selectDeptList(param);
        return JsonResult.successJsonResult(infos);
    }

    public JsonResult<String> delDeptInfo(String deptCode, String modifier) {
        if (ObjectUtils.isNull(deptCode) ) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }
        PlatformDept dept = deptMapper.selectByDeptCode(deptCode);
        if (ObjectUtils.isNull(dept)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }

        // 校验是否绑定成员信息
        int count = deptMapper.selectCountDeptUser(deptCode);
        if(count>0) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_USER_EXIST);
        }
        
        dept.setStatus(0);
        dept.setModifier(modifier);
        dept.setLastModifyTime(new Date());
        
        deptMapper.updateByPrimaryKeySelective(dept);
        return JsonResult.successJsonResult();
    }

    public JsonResult<List<Dept>> getDeptParentInfo(String deptCode) {
        if (ObjectUtils.isNull(deptCode)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }
        List<Dept> dept = deptMapper.selectDeptParentList(deptCode);
        return JsonResult.successJsonResult(dept);
    }
    
    public JsonResult<String> disableDeptInfo(String deptCode, String modifier) {
        if (ObjectUtils.isNull(deptCode) ) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }
        PlatformDept dept = deptMapper.selectByDeptCode(deptCode);
        if (ObjectUtils.isNull(dept)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }

        dept.setStatus(2);
        dept.setModifier(modifier);
        dept.setLastModifyTime(new Date());
        
        deptMapper.updateByPrimaryKeySelective(dept);
        return JsonResult.successJsonResult();
    }

    public JsonResult<String> enableDeptInfo(String deptCode, String modifier) {
        if (ObjectUtils.isNull(deptCode) ) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }
        PlatformDept dept = deptMapper.selectByDeptCode(deptCode);
        if (ObjectUtils.isNull(dept)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }

        dept.setStatus(1);
        dept.setModifier(modifier);
        dept.setLastModifyTime(new Date());
        
        deptMapper.updateByPrimaryKeySelective(dept);
        return JsonResult.successJsonResult();
    }

   /* public boolean parentCodeLevel(String parentCode) {
        PlatformDept platformDept = deptMapper.selectByParentDeptCode(parentCode);
        if(ObjectUtils.isNotNull(platformDept)) {
            PlatformDept  platformDeptObj = deptMapper.selectByParentDeptCode(platformDept.getDeptCode());
            if(ObjectUtils.isNotNull(platformDeptObj)) {
                
            }
        } else {
            PlatformDept pdByParentCode = deptMapper.selectByDeptCode(parentCode);
            if(ObjectUtils.isNotNull(pdByParentCode) && !pdByParentCode.getParentCode().equals("0")) {
                // 该部门编码为三级部门
                return false;
            }
        }
        return true;
    }*/
}

