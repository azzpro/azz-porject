/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午2:17:16
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月31日 下午2:17:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationPic {
    private String fileName;
    
    private long fileSize;
    
    private String fileBase64Str;
}

