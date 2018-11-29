/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月27日 下午7:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月27日 下午7:05:46
 */
@Data
public class AddColumnWebParam {
    @NotBlank(message = "栏目名称不能为空")
    private String columnName;
    @NotBlank(message = "栏目代码不能为空")
    private String columnCode;
    @NotNull(message = "栏目类型不能为空")
    private Integer columnType;
    @NotNull(message = "请上传主图")
    private MultipartFile mainPicture;
}

