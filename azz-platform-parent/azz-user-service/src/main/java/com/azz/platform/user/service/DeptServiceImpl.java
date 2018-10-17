/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月17日 上午9:17:00 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.user.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.exception.BaseException;
import com.azz.platform.user.api.DeptService;
import com.azz.platform.user.mapper.PlatformDeptMapper;
import com.azz.platform.user.pojo.PlatformDept;
import com.azz.platform.user.pojo.bo.AddDeptParam;
import com.azz.platform.user.pojo.bo.DeptSearchParam;
import com.azz.platform.user.pojo.bo.EditDeptParam;
import com.azz.platform.user.pojo.vo.Dept;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>
 * 部门服务接口
 * </P>
 * 
 * @version 1.0
 * @author 彭斌 2018年10月17日 上午9:17:00
 */
public class DeptServiceImpl implements DeptService {

    @Autowired
    private PlatformDeptMapper deptMapper;

    @Override
    public JsonResult<String> addDeptInfo(AddDeptParam param) {
        // 部门信息非空校验
        JSR303ValidateUtils.validate(param);

        // 部门名称校验
        PlatformDept deptObj = deptMapper.selectByDeptName(param.getDeptName());
        if (ObjectUtils.isNotNull(deptObj)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_EXIST);
        }

        // TODO 部门编码生成

        // 添加新部门
        PlatformDept dept = new PlatformDept();
        dept.setDeptCode("");
        dept.setDeptName(param.getDeptName());
        dept.setStatus(1);
        dept.setCreator("");
        dept.setCreateTime(new Date());

        if (ObjectUtils.isNotNull(param.getDescription())) {
            dept.setDescription(param.getDescription());
        }

        deptMapper.insertSelective(dept);
        return JsonResult.successJsonResult();
    }

    @Override
    public JsonResult<String> editDeptInfo(EditDeptParam param) {
        JSR303ValidateUtils.validate(param);

        PlatformDept dept = deptMapper.selectByPrimaryKey(param.getId());
        if (ObjectUtils.isNull(dept)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }

        PlatformDept deptByName = deptMapper.selectByDeptName(param.getDeptName());
        if (ObjectUtils.isNotNull(deptByName)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_EXIST);
        }
        dept.setDeptName(param.getDeptName());
        if (ObjectUtils.isNotNull(param.getDescription())) {
            dept.setDescription(param.getDescription());
        }
        dept.setLastModifyTime(new Date());
        // TODO 操作人
        dept.setModifier("");
        dept.setStatus(param.getStatus());

        deptMapper.updateByPrimaryKeySelective(dept);
        return JsonResult.successJsonResult();
    }

    @Override
    public JsonResult<Pagination<Dept>> getDeptList(DeptSearchParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<Dept> infos = deptMapper.selectDeptList(param);
        return JsonResult.successJsonResult(new Pagination<>(infos));
    }

    @Override
    public JsonResult<String> delDeptInfo(String id) {
        if (ObjectUtils.isNull(id) || NumberUtils.isNumber(id)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }
        Long deptId = Long.parseLong(id);
        PlatformDept dept = deptMapper.selectByPrimaryKey(deptId);
        if (ObjectUtils.isNull(dept)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }

        dept.setStatus(0);
        // TODO 操作人
        dept.setModifier("");
        dept.setLastModifyTime(new Date());
        
        deptMapper.updateByPrimaryKeySelective(dept);
        return JsonResult.successJsonResult();
    }

    @Override
    public JsonResult<PlatformDept> getDeptInfo(String id) {
        if (ObjectUtils.isNull(id) || NumberUtils.isNumber(id)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }
        PlatformDept dept = deptMapper.selectByPrimaryKey(Long.parseLong(id));
        return JsonResult.successJsonResult(dept);
    }

}

