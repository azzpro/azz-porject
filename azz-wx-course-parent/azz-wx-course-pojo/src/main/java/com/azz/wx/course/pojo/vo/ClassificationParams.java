/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午5:19:40
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.vo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月31日 下午5:19:40
 */
@Data
public class ClassificationParams {
    
    private Long id;
    
    
    private String assortmentParentCode;
    
    /**
     * 分类编码
     */
    private String assortmentCode;
    
    /**
     * 分类名称
     *
     * @mbg.generated
     */
    private String assortmentName;
    
    /**
     * 图片url
     */
    private String assortmentPicUrl;
    
}

