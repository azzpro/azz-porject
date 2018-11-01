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
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.pojo.bo.AddClassificationParam;
import com.azz.platform.merchant.pojo.bo.DelClassificationParam;
import com.azz.platform.merchant.pojo.bo.EditClassificationParam;
import com.azz.platform.merchant.pojo.bo.SearchClassificationListParam;
import com.azz.platform.merchant.pojo.vo.Classification;
import com.azz.platform.merchant.pojo.vo.ClassificationList;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月22日 下午8:27:42
 */
@FeignClient("azz-merchant-management-service")
public interface ClassificationService {
    
    @PostMapping("/azz/api/merchant/product/getClassificationList")
    JsonResult<Pagination<ClassificationList>> getClassificationList(@RequestBody SearchClassificationListParam param);
    
    @PostMapping("/azz/api/merchant/product/addClassification")
    JsonResult<String> addClassification(@RequestBody AddClassificationParam param);
    
    @PostMapping("/azz/api/merchant/product/editClassification")
    JsonResult<String> editClassification(@RequestBody EditClassificationParam param);

    @PostMapping("/azz/api/merchant/product/delClassification")
    JsonResult<String> delClassification(@RequestBody DelClassificationParam param);
    
    @PostMapping("/azz/api/merchant/product/getClassificationInfo")
    JsonResult<Classification> getClassificationInfo(@RequestParam("assortmentCode") String assortmentCode);
    
    
}

