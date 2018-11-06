/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午8:27:42
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.platform.merchant.pojo.bo.AddCaseParam;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月5日 下午6:43:08
 */
@FeignClient("azz-merchant-management-service")
public interface CaseService {
    
  
    @PostMapping("/azz/api/merchant/case/addCase")
    JsonResult<String> addCase(@RequestBody AddCaseParam param);
    
   
}

