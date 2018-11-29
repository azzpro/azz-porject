/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月27日 下午7:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月27日 下午7:05:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditImage {
    @NotNull(message = "参数异常")
    private Long imageId;
    private Long columnId;
    @NotBlank(message = "跳转链接不能为空")
    private String jumpLink;
    private String userCode;
    // 0未 1修改过
    private Integer editStatus;
    private MainPicture mainPicture;
}

