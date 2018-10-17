/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月16日 上午11:33:13
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月16日 上午11:33:13
 */
@RestController
public class ShiroController {
    
    @RequestMapping(value = "/admin/getMessage")
    public String getAdminMessage() {
        return "这是管理员权限";
    }
    
    @RequestMapping(value = "/user/getMessage")
    public String getNomalUserMessage() {
        return "这是普通用户权限";
    }
    
}

