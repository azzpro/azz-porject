/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月20日 下午2:50:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import com.azz.core.common.QueryPage;

import lombok.Data;

/**
 * <P>审批管理列表参数</P>
 * @version 1.0
 * @author 彭斌  2018年10月20日 下午2:50:46
 */
@Data
public class SearchMerchantParam extends QueryPage{
    /**
     * 商户列表参数
     */
    private String param;
    
    /**
     * 审核结果信息
     */
    private Integer status;
    
}

