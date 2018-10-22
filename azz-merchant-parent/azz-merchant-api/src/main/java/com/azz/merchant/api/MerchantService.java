/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:30:18
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.merchant.pojo.bo.MerchantRegistParam;
import com.azz.merchant.pojo.bo.SearchMerchantParam;
import com.azz.merchant.pojo.vo.MerchantInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月22日 上午10:30:18
 */
@FeignClient("azz-merchant-service")
public interface MerchantService {
    
    
    /**
     * 
     * <p>根据手机号发送验证码，并返回短信id</p>
     * @param phoneNumber
     * @return
     * @author 黄智聪  2018年10月22日 下午3:25:21
     */
    @GetMapping("sendVerificationCode")
    JsonResult<Long> sendVerificationCode(String phoneNumber);
    
    /**
     * 
     * <p>商户注册</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月22日 下午3:01:34
     */
    @PostMapping("merchantRegist")
    JsonResult<String> merchantRegist(MerchantRegistParam param);
    
    

    /**
     * 
     * <p>查询商户列表--此接口查询的都是审批通过的</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月22日 下午2:40:47
     */
    @PostMapping("getMerchantList")
    JsonResult<Pagination<MerchantInfo>> getMerchantList(@RequestBody SearchMerchantParam param);
    
}

