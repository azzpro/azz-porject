/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午2:42:56
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.goods.pojo.bo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月15日 下午2:42:56
 */
@Data
public class DemoSearchParam implements Serializable{
    
    private static final long serialVersionUID = -4757815162304652090L;
    
    private List<String> ids;

}

