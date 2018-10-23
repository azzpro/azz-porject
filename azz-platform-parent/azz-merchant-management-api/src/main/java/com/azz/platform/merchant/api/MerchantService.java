/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午8:27:42
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.pojo.bo.SearchMerchantParam;
import com.azz.platform.merchant.pojo.vo.MerchantApproval;
import com.azz.platform.merchant.pojo.vo.MerchantInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月22日 下午8:27:42
 */
@FeignClient("azz-merchant-management-service")
public interface MerchantService {
    
    /**
     * <p>平台端的商户信息列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月22日 下午8:30:00
     */
    @PostMapping("searchMerchantList")
    JsonResult<Pagination<MerchantApproval>> searchMerchantList(@RequestBody SearchMerchantParam param);

    /**
     * <p>平台端的商户详情信息</p>
     * @param merchantCode
     * @return
     * @author 彭斌  2018年10月23日 上午10:10:06
     */
    @GetMapping("searchMerchantInfo")
    JsonResult<MerchantInfo> searchMerchantInfo(@RequestParam("merchantCode") String merchantCode);
}

