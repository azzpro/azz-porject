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
import com.azz.core.common.page.Pagination;
import com.azz.platform.user.pojo.bo.AddUserParam;
import com.azz.platform.user.pojo.bo.EditPasswordParam;
import com.azz.platform.user.pojo.bo.EditUserParam;
import com.azz.platform.user.pojo.bo.EnableOrDisableOrDelUserParam;
import com.azz.platform.user.pojo.bo.LoginParam;
import com.azz.platform.user.pojo.bo.SearchUserParam;
import com.azz.platform.user.pojo.vo.LoginUserInfo;
import com.azz.platform.user.pojo.vo.UserInfo;

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
    @PostMapping("editPassword")
    JsonResult<String> editPassword(@RequestBody EditPasswordParam param);
    
    /**
     * 
     * <p>添加用户</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月19日 下午5:39:40
     */
    @PostMapping("addUser")
    JsonResult<String> addUser(@RequestBody AddUserParam param);
    
    /**
     * 
     * <p>修改用户</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月19日 下午6:02:11
     */
    @PostMapping("editUser")
    JsonResult<String> editUser(@RequestBody EditUserParam param);
    
    /**
     * 
     * <p>根据条件查询用户列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月20日 上午10:23:34
     */
    @PostMapping("getUserList")
    JsonResult<Pagination<UserInfo>> getUserList(@RequestBody SearchUserParam param);
    
    /**
     * 
     * <p>启用、禁用或删除用户</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月20日 上午10:31:52
     */
    @PostMapping("enableOrDisableUser")
    JsonResult<String> enableOrDisableOrDelUser(@RequestBody EnableOrDisableOrDelUserParam param);
    
    /**
     * 
     * <p>查询用户详情</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月20日 上午10:31:52
     */
    @GetMapping("getUserInfo")
    JsonResult<UserInfo> getUserInfo(@RequestParam("userCode") String userCode);
    
    
}
