/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月14日 下午1:53:44
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.pojo.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月14日 下午1:53:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentFile {
    
    private String fileName;
    
    private long fileSize;
    
    private String fileBase64Str;

}

