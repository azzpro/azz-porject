/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午2:52:17
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
import com.azz.platform.user.pojo.bo.EditPasswordParam;
import com.azz.platform.user.pojo.bo.LoginParam;
import com.azz.platform.user.pojo.vo.LoginUserInfo;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 刘建麟 2018年10月15日 下午2:52:17
 */
@FeignClient("azz-user-service")
public interface UserService {
    
    /**
     * 
     * <p>
     * shiro的登录认证
     * </p>
     * 
     * @param param 登录参数
     * @return
     * @author 黄智聪 2018年10月17日 下午3:06:35
     */
    @PostMapping("loginAuth")
    JsonResult<String> loginAuth(@RequestBody LoginParam param);
    
    /**
     * 
     * <p>TODO</p>
     * @param phoneNumber
     * @return
     * @author 黄智聪  2018年10月18日 下午1:51:00
     */
    @GetMapping("getLoginUserInfoByPhoneNumber")
    JsonResult<LoginUserInfo> getLoginUserInfoByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber);
    
    /**
     * <p>修改用户密码</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月18日 下午2:30:58
     */
    @GetMapping("editPassword")
    JsonResult<String> editPassword(@RequestBody EditPasswordParam param);
    
}
