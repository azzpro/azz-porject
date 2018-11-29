/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月27日 下午7:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.pojo.bo;

import javax.validation.constraints.NotNull;

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
public class AddImage {
    
    @NotNull(message = "栏目类型不能为空")
    private String jumpLink;
    private String userCode;
    // 关联栏目表id
    private Long indexColumnId;
    @NotNull(message = "请上传主图")
    private MainPicture mainPicture;
}

