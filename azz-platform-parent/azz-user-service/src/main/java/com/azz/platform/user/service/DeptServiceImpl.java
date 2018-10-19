/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月17日 上午9:17:00 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.exception.BaseException;
import com.azz.platform.user.api.DeptService;
import com.azz.platform.user.mapper.PlatformDeptMapper;
import com.azz.platform.user.pojo.PlatformDept;
import com.azz.platform.user.pojo.bo.AddDeptParam;
import com.azz.platform.user.pojo.bo.EditDeptParam;
import com.azz.platform.user.pojo.bo.SearchDeptParam;
import com.azz.platform.user.pojo.vo.Dept;
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
@RestController
public class DeptServiceImpl implements DeptService {

    @Autowired
    private PlatformDeptMapper deptMapper;

    @Override
    public JsonResult<String> addDeptInfo(AddDeptParam param) {
        // 部门信息非空校验
        JSR303ValidateUtils.validate(param);
        String deptParentCode = param.getParentCode();
        
        
        // 部门名称/父级编码校验
        PlatformDept deptObj = deptMapper.selectByDeptName(param.getDeptName());
        
        if (ObjectUtils.isNotNull(deptObj)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_EXIST);
        }
        
        PlatformDept dept = new PlatformDept();
        
        if(ObjectUtils.isNotNull(deptParentCode)) {
            PlatformDept deptObjCode = deptMapper.selectByDeptCode(deptParentCode);
            if(ObjectUtils.isNull(deptObjCode)) {
                throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
            }
            dept.setParentCode(deptParentCode);
        } else {
            // 系统自动生成部门编码
            dept.setDeptCode("D"+System.currentTimeMillis()); // TODO 部门编码生成
            dept.setParentCode("0");
        }
        
        dept.setDeptName(param.getDeptName());
        dept.setStatus(param.getStatus());
        dept.setCreator(param.getCreator());
        dept.setCreateTime(new Date());

        deptMapper.insertSelective(dept);
        return JsonResult.successJsonResult();
    }

    @Override
    public JsonResult<String> editDeptInfo(EditDeptParam param) {
        JSR303ValidateUtils.validate(param);

        PlatformDept dept = deptMapper.selectByDeptCode(param.getDeptCode());
        if (ObjectUtils.isNull(dept)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }

        if (!dept.getDeptName().equals(param.getDeptName().trim())) {
            PlatformDept deptObj = deptMapper.selectByDeptName(param.getDeptName());
            if(ObjectUtils.isNotNull(deptObj)) {
                throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_EXIST);
            }
        }
        dept.setParentCode(param.getParentCode());
        dept.setDeptName(param.getDeptName());
        dept.setLastModifyTime(new Date());
        dept.setModifier(param.getModifier());
        dept.setStatus(param.getStatus());

        deptMapper.updateByPrimaryKeySelective(dept);
        return JsonResult.successJsonResult();
    }

    @Override
    public JsonResult<List<Dept>> getDeptList(SearchDeptParam param) {
        List<Dept> infos = deptMapper.selectDeptList(param);
        return JsonResult.successJsonResult(infos);
    }

    @Override
    public JsonResult<String> delDeptInfo(String deptCode, String modifier) {
        if (ObjectUtils.isNull(deptCode) ) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }
        PlatformDept dept = deptMapper.selectByDeptCode(deptCode);
        if (ObjectUtils.isNull(dept)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }

        dept.setStatus(0);
        dept.setModifier(modifier);
        dept.setLastModifyTime(new Date());
        
        deptMapper.updateByPrimaryKeySelective(dept);
        return JsonResult.successJsonResult();
    }

    @Override
    public JsonResult<List<Dept>> getDeptInfo(String deptCode) {
        if (ObjectUtils.isNull(deptCode)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }
        List<Dept> dept = deptMapper.selectDeptParentList(deptCode);
        return JsonResult.successJsonResult(dept);
    }

}

