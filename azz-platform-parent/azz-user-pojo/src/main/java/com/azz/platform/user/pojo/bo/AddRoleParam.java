/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月18日 下午3:12:50
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.pojo.bo;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月18日 下午3:12:50
 */
@Data
public class AddRoleParam implements Serializable{
    
    private static final long serialVersionUID = 9015982174488427040L;

    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    
    @NotEmpty(message = "请选择权限")
    private List<String> permissionCodes;

    private String remark;
    
    private String creator;
    
}

