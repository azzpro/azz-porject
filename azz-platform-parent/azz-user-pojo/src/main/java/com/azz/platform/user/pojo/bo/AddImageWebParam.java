/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月27日 下午7:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.pojo.bo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月27日 下午7:05:46
 */
@Data
public class AddImageWebParam {
    
    private String jumpLink;
    // 关联栏目表id
    private Long indexColumnId;
    private MultipartFile mainPicture;
}

