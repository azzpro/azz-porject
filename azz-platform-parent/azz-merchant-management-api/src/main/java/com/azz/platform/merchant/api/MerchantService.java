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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.pojo.bo.SearchMerchantListParam;
import com.azz.platform.merchant.pojo.bo.SearchMerchantParam;
import com.azz.platform.merchant.pojo.bo.SearchMerchantUserParam;
import com.azz.platform.merchant.pojo.vo.MerchantApproval;
import com.azz.platform.merchant.pojo.vo.MerchantInfo;
import com.azz.platform.merchant.pojo.vo.MerchantInfoOpen;
import com.azz.platform.merchant.pojo.vo.MerchantListInfo;
import com.azz.platform.merchant.pojo.vo.MerchantUserInfo;

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
    @PostMapping("/azz/api/merchant/searchMerchantList")
    JsonResult<Pagination<MerchantApproval>> searchMerchantList(@RequestBody SearchMerchantParam param);

    /**
     * <p>平台端的商户详情信息</p>
     * @param merchantCode
     * @return
     * @author 彭斌  2018年10月23日 上午10:10:06
     */
    @GetMapping("/azz/api/merchant/searchMerchantInfo")
    JsonResult<MerchantInfo> searchMerchantInfo(@RequestParam("merchantCode") String merchantCode);
    
    /**
     * <p>平台端的商户列表</p>
     * @param merchantCode
     * @return
     * @author 彭斌  2018年10月23日 上午10:10:06
     */
    @PostMapping("/azz/api/merchant/searchMerchantListInfo")
    JsonResult<Pagination<MerchantListInfo>> searchMerchantListInfo(@RequestBody SearchMerchantListParam param);
    
    /**
     * <p>平台端的商户 启用 禁用</p>
     * @param merchantCode
     * @return
     * @author 彭斌  2018年10月23日 上午10:10:06
     */
    @PostMapping("/azz/api/merchant/merchantStatusChange")
    JsonResult<String> merchantStatusChange(@RequestParam("code") String code,@RequestParam("status") Integer status);
    
    /**
     * <p>平台端的商户 详情</p>
     * @param merchantCode
     * @return
     * @author 彭斌  2018年10月23日 上午10:10:06
     */
    @PostMapping("/azz/api/merchant/getMerchantInfo")
    JsonResult<MerchantInfoOpen> getMerchantInfo(@RequestParam("code") String code);
    
    /**
     * <p>平台端的商户  成员详情</p>
     * @param code
     * @return
     * @author 刘建麟  2018年10月24日 下午10:40:49
     */
    @RequestMapping(value="/azz/api/merchant/getMerchantUserInfo",method=RequestMethod.POST)
	 public JsonResult<Pagination<MerchantUserInfo>> getMerchantUserInfo(@RequestBody SearchMerchantUserParam param);
}

