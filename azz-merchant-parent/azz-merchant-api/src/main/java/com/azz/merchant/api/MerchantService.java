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
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.merchant.pojo.bo.CompleteMerchantInfoParam;
import com.azz.merchant.pojo.bo.LoginParam;
import com.azz.merchant.pojo.bo.MerchantRegistParam;
import com.azz.merchant.pojo.bo.UploadTradingCertificateParam;
import com.azz.merchant.pojo.vo.LoginMerchantUserInfo;
import com.azz.merchant.pojo.vo.UploadFileInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月22日 上午10:30:18
 */
@FeignClient("azz-merchant-service")
public interface MerchantService {
    
    /**
     * 
     * <p>商户登录认证</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月23日 下午3:49:33
     */
    @PostMapping("/azz/api/merchant/loginAuth")
    JsonResult<String> loginAuth(@RequestBody LoginParam param);
    
    /**
     * 
     * <p>获取登录商户信息</p>
     * @param phoneNumber
     * @return
     * @author 黄智聪  2018年10月23日 下午4:22:14
     */
    @GetMapping("/azz/api/merchant/getLoginMerchantUserInfoByPhoneNumber")
    JsonResult<LoginMerchantUserInfo> getLoginMerchantUserInfoByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber);
    
    /**
     * 
     * <p>根据手机号发送验证码，并返回短信id</p>
     * @param phoneNumber
     * @return
     * @author 黄智聪  2018年10月22日 下午3:25:21
     */
    @GetMapping("/azz/api/merchant/sendVerificationCode")
    JsonResult<Long> sendVerificationCode(String phoneNumber);
    
    /**
     * 
     * <p>商户注册</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月22日 下午3:01:34
     */
    @PostMapping("/azz/api/merchant/merchantRegist")
    JsonResult<String> merchantRegist(MerchantRegistParam param);
    
    /**
     * 
     * <p>完善商户信息</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月23日 下午8:04:02
     */
    @PostMapping("/azz/api/merchant/completeMerchantInfo")
    JsonResult<String> completeMerchantInfo(@RequestBody CompleteMerchantInfoParam param);
    
    /**
     * 
     * <p>上传营业执照</p>
     * @param uploadTradingCertificateParam
     * @return
     * @author 黄智聪  2018年10月24日 上午10:25:48
     */
    @PostMapping("/azz/api/merchant/uploadTradingCertificateFile")
    JsonResult<UploadFileInfo> uploadTradingCertificateFile(@RequestBody UploadTradingCertificateParam param);
    
}

