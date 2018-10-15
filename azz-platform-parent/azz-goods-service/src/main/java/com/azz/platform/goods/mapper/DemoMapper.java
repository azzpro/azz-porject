/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月14日 上午9:22:36
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.goods.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.goods.pojo.Demo;
import com.azz.platform.goods.pojo.vo.DemoInfo;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 刘建麟 2018年10月14日 上午9:22:36
 */
@Mapper
public interface DemoMapper {

    List<Demo> getName();
    
    List<DemoInfo> getDemoInfosByIds(List<String> ids);

}
