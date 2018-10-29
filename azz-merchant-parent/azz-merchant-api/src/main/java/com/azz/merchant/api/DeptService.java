/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:30:18
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.merchant.pojo.MerchantDept;
import com.azz.merchant.pojo.bo.AddMerchantDeptParam;
import com.azz.merchant.pojo.bo.DelDeptParam;
import com.azz.merchant.pojo.bo.EditDeptIsEnableParam;
import com.azz.merchant.pojo.bo.EditMerchantDeptParam;
import com.azz.merchant.pojo.bo.SearchMerchantChildDeptParam;
import com.azz.merchant.pojo.bo.SearchMerchantDeptInfoParam;
import com.azz.merchant.pojo.bo.SearchMerchantDeptListParam;
import com.azz.merchant.pojo.vo.MerchantDeptList;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月26日 下午3:44:55
 */
@FeignClient("azz-merchant-service")
public interface DeptService {
    
    /**
     * <p>新增一级部门</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月26日 下午5:54:55
     */
    @PostMapping("/azz/api/merchant/addFirstLevelDept")
    JsonResult<String> addFirstLevelDept(@RequestBody AddMerchantDeptParam param);
    
    /**
     * <p>新增子级部门</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月26日 下午6:39:10
     */
    @PostMapping("/azz/api/merchant/addChildDept")
    JsonResult<String> addChildDept(@RequestBody AddMerchantDeptParam param);
    
    /**
     * <p>根据商户编码和部门编码获取部门详情</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月26日 下午7:00:50
     */
    @PostMapping("/azz/api/merchant/selectDeptInfo")
    JsonResult<MerchantDept> getDeptInfo(SearchMerchantDeptInfoParam param);
    
    /**
     * <p>修改部门信息</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月26日 下午7:09:37
     */
    @PostMapping("/azz/api/merchant/editDept")
    JsonResult<String> editDept(@RequestBody EditMerchantDeptParam param);
    
    /**
     * <p>获取一级部门集合</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月26日 下午8:28:21
     */
    @PostMapping("/azz/api/merchant/searchDeptList")
    JsonResult<List<MerchantDeptList>> searchDeptList(@RequestBody SearchMerchantDeptListParam param);
    
    /**
     * <p>获取子级部门集合</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月27日 下午2:20:42
     */
    @PostMapping("/azz/api/merchant/searchChildDeptList")
    JsonResult<List<MerchantDeptList>> searchChildDeptList(@RequestBody SearchMerchantChildDeptParam param);
    
    /**
     * <p>启用、禁用部门</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月27日 下午2:32:47
     */
    @PostMapping("/azz/api/merchant/isEnableDept")
    JsonResult<String> isEnableDept(@RequestBody EditDeptIsEnableParam param);
    
    /**
     * <p>删除部门</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月27日 下午2:33:11
     */
    @PostMapping("/azz/api/merchant/delDept")
    JsonResult<String> delDept(@RequestBody DelDeptParam param);
}

