/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月16日 下午7:33:31
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.user.pojo.PlatformDept;
import com.azz.platform.user.pojo.bo.AddDeptParam;
import com.azz.platform.user.pojo.bo.DeptSearchParam;
import com.azz.platform.user.pojo.bo.EditDeptParam;
import com.azz.platform.user.pojo.vo.Dept;

/**
 * <P>部门服务管理</P>
 * @version 1.0
 * @author 彭斌  2018年10月16日 下午7:33:31
 */
@FeignClient("azz-user-service")
public interface DeptService {
    
    /**
     * <p>新增部门信息</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月17日 下午2:50:20
     */
    @PostMapping("addDeptInfo")
    JsonResult<String> addDeptInfo(@RequestBody AddDeptParam param);
    
    /**
     * <p>修改部门信息</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月17日 下午2:50:24
     */
    @PostMapping("editDeptInfo")
    JsonResult<String> editDeptInfo(@RequestBody EditDeptParam param);
    
    /**
     * <p>获取部门信息分页列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月17日 下午2:50:28
     */
    @PostMapping("getDeptList")
    JsonResult<Pagination<Dept>> getDeptList(@RequestBody DeptSearchParam param);
    
    /**
     * <p>逻辑删除部门信息</p>
     * @param id
     * @return
     * @author 彭斌  2018年10月17日 下午2:50:31
     */
    @GetMapping("delDeptInfo")
    JsonResult<String> delDeptInfo(@RequestParam("id") Long id);
    
    /**
     * <p>获取部门详情信息</p>
     * @param id
     * @return
     * @author 彭斌  2018年10月17日 下午2:50:34
     */
    @GetMapping("getDeptInfo")
    JsonResult<PlatformDept> getDeptInfo(@RequestParam("id") Long id);
}

