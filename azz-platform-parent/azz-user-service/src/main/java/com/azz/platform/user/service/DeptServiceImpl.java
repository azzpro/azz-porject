/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月17日 上午9:17:00
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.azz.core.common.JsonResult;
import com.azz.core.exception.BaseException;
import com.azz.platform.user.api.DeptService;
import com.azz.platform.user.mapper.PlatformDeptMapper;
import com.azz.platform.user.pojo.PlatformDept;
import com.azz.platform.user.pojo.bo.AddDeptParam;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;

/**
 * <P>部门服务接口</P>
 * @version 1.0
 * @author 彭斌  2018年10月17日 上午9:17:00
 */
public class DeptServiceImpl implements DeptService{
    
    @Autowired
    private PlatformDeptMapper deptMapper;
    
    @Override
    public JsonResult<String> addDeptInfo(AddDeptParam param) {
        // 部门信息非空校验
        JSR303ValidateUtils.validate(param);
        
        // 部门名称校验
        PlatformDept deptObj = deptMapper.selectByDeptName(param.getDeptName());
        
        if(ObjectUtils.isNotNull(deptObj)) {
            //throw new BaseException();
        }
        return null;
    }

}

