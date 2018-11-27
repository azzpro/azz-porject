/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月16日 下午7:33:31
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.platform.user.pojo.PlatformIndexColumn;
import com.azz.platform.user.pojo.bo.AddColumn;
import com.azz.platform.user.pojo.bo.EditColumn;
import com.azz.platform.user.pojo.vo.ColumnInfo;

@FeignClient("azz-user-service")
public interface IndexService {
    
    /**
     * <p>新增栏目</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月27日 下午9:38:56
     */
    @PostMapping("/azz/api/index/addColumn")
    JsonResult<String> addColumn(@RequestBody AddColumn param);
    
    /**
     * <p>获取所有栏目集合倒序</p>
     * @return
     * @author 彭斌  2018年11月27日 下午9:38:59
     */
    @PostMapping("/azz/api/index/getColumnLsit")
    JsonResult<List<ColumnInfo>> getColumnLsit();
    
    /**
     * <p>编辑栏目</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月27日 下午9:39:02
     */
    @PostMapping("/azz/api/index/editColumn")
    JsonResult<String> editColumn(@RequestBody EditColumn param);
    
    /**
     * <p>删除栏目</p>
     * @param columnId
     * @return
     * @author 彭斌  2018年11月27日 下午9:39:04
     */
    @GetMapping("/azz/api/index/delColumn")
    JsonResult<String> delColumn(@RequestParam("columnId") Long columnId);
    
    /**
     * <p>获取栏目详情</p>
     * @param columnId
     * @return
     * @author 彭斌  2018年11月27日 下午9:39:07
     */
    @GetMapping("/azz/api/index/getColumnInfo")
    JsonResult<PlatformIndexColumn> getColumnInfo(@RequestParam("columnId") Long columnId);
}

