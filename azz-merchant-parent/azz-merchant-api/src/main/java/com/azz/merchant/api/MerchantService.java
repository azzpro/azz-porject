/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:30:18
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.api;

import java.io.IOException;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.merchant.pojo.bo.AddMerchantUserParam;
import com.azz.merchant.pojo.bo.CheckVerificationCodeParam;
import com.azz.merchant.pojo.bo.CompleteMerchantInfoParam;
import com.azz.merchant.pojo.bo.EditMerchantUserParam;
import com.azz.merchant.pojo.bo.EditPersonalInfoParam;
import com.azz.merchant.pojo.bo.EnableOrDisableOrDelMerchantUserParam;
import com.azz.merchant.pojo.bo.ImportMerchantUserParam;
import com.azz.merchant.pojo.bo.LoginParam;
import com.azz.merchant.pojo.bo.MerchantRegistParam;
import com.azz.merchant.pojo.bo.SearchMerchantUserParam;
import com.azz.merchant.pojo.vo.LoginMerchantUserInfo;
import com.azz.merchant.pojo.vo.MerchantInfo;
import com.azz.merchant.pojo.vo.MerchantUserInfo;

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
    JsonResult<LoginMerchantUserInfo> getLoginMerchantUserInfoByPhoneNumber(@RequestParam("phoneNumber")String phoneNumber);
    
    /**
     * 
     * <p>根据手机号发送验证码，并返回短信id</p>
     * @param phoneNumber
     * @return
     * @author 黄智聪  2018年10月22日 下午3:25:21
     */
    @GetMapping("/azz/api/merchant/sendVerificationCode")
    JsonResult<String> sendVerificationCode(@RequestParam("phoneNumber")String phoneNumber);
    
    /**
     * 
     * <p>商户注册</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月22日 下午3:01:34
     */
    @PostMapping("/azz/api/merchant/merchantRegist")
    JsonResult<String> merchantRegist(@RequestBody MerchantRegistParam param);
    
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
     * <p>新增商户成员</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月24日 下午7:30:27
     */
    @RequestMapping("/azz/api/merchant/addMerchantUser")
    public JsonResult<String> addMerchantUser(@RequestBody AddMerchantUserParam param);
    
    /**
     * 
     * <p>编辑商户成员</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月24日 下午7:30:45
     */
    @RequestMapping("/azz/api/merchant/editMerchantUser")
    public JsonResult<String> editMerchantUser(@RequestBody EditMerchantUserParam param);
    
    
    /**
     * 
     * <p>查询商户资质状态</p>
     * @param merchantCode
     * @return
     * @author 黄智聪  2018年10月26日 下午8:03:59
     */
    @RequestMapping("/azz/api/merchant/getMerchantQualificationApplyStatus")
    public JsonResult<Integer> getMerchantQualificationApplyStatus(@RequestParam("merchantCode") String merchantCode);
    
    /**
     * 
     * <p>查询商户成员列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月24日 下午7:30:57
     */
    @RequestMapping("/azz/api/merchant/getMerchantUserList")
    public JsonResult<Pagination<MerchantUserInfo>> getMerchantUserList(@RequestBody SearchMerchantUserParam param);
    
    /**
     * 
     * <p>启用、禁用或删除商户成员</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月24日 下午7:31:09
     */
    @RequestMapping("/azz/api/merchant/enableOrDisableOrDelMerchantUser")
    public JsonResult<String> enableOrDisableOrDelMerchantUser(@RequestBody EnableOrDisableOrDelMerchantUserParam param);
    
    /**
     * 
     * <p>查询商户成员信息</p>
     * @param merchantUserCode
     * @return
     * @author 黄智聪  2018年10月24日 下午7:31:29
     */
    @RequestMapping("/azz/api/merchant/getMerchantUserInfo")
    public JsonResult<MerchantUserInfo> getMerchantUserInfo(@RequestParam("merchantUserCode") String merchantUserCode);
    
    /**
     * 
     * <p>查询商户资料</p>
     * @param merchantCode
     * @return
     * @author 黄智聪  2018年10月29日 下午1:40:26
     */
    @RequestMapping("/azz/api/merchant/getMerchantInfo")
    public JsonResult<MerchantInfo> getMerchantInfo(@RequestParam("merchantCode") String merchantCode);
    
    /**
     * 
     * <p>导入商户成员</p>
     * @param param
     * @return
     * @throws IOException
     * @author 黄智聪  2018年12月11日 下午4:22:31
     */
    @RequestMapping("/azz/api/merchant/importMerchantUser")
    JsonResult<String> importMerchantUser(@RequestBody ImportMerchantUserParam param) throws IOException;
    
    /**
     * 
     * <p>修改个人资料</p>
     * @param param
     * @return
     * @author 黄智聪  2018年12月12日 下午5:43:40
     */
    @RequestMapping("/azz/api/merchant/editPersonalInfo")
    JsonResult<String> editPersonalInfo(@RequestBody EditPersonalInfoParam param);
    
    /**
     * 
     * <p>发送修改个人信息的验证码 </p>
     * @param phoneNumber
     * @return
     * @author 黄智聪  2018年12月12日 下午5:45:42
     */
    @RequestMapping("/azz/api/merchant/sendEditVerificationCode")
    JsonResult<String> sendEditVerificationCode(@RequestParam("phoneNumber")String phoneNumber);
    
    /**
     * 
     * <p>校验验证码</p>
     * @param param
     * @return
     * @author 黄智聪  2018年12月12日 下午5:45:46
     */
    @RequestMapping("/azz/api/merchant/checkEditVerificationCode")
    JsonResult<String> checkEditVerificationCode(@RequestBody CheckVerificationCodeParam param);
}

