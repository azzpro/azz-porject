/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午5:07:16
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.vo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月31日 下午5:07:16
 */
@Data
public class Classification {
    /**
     * 分类编码
     */
    private String assortmentCode;
    
    /**
     * 上级分类编号，可为空
     *
     * @mbg.generated
     */
    private String assortmentParentCode;

    /**
     * 分类名称
     *
     * @mbg.generated
     */
    private String assortmentName;
    
    /**
     * 分类排序(默认0)
     *
     * @mbg.generated
     */
    private Integer assortmentSort;
    
    /**
     * 分类图片url
     *
     * @mbg.generated
     */
    private String assortmentPicUrl;

    /**
     * 分类图片名称
     *
     * @mbg.generated
     */
    private String assortmentPicName;
}

