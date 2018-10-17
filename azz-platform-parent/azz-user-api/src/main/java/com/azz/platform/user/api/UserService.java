/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午2:52:17
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.user.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.platform.user.pojo.bo.LoginParam;

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
     * 登录认证
     * </p>
     * 
     * @param param
     * @return
     * @author 黄智聪 2018年10月17日 下午3:06:35
     */
    @PostMapping("loginAuth")
    JsonResult<String> loginAuth(@RequestBody LoginParam param);
}
