package com.azz.platform.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformGoodsParams;
import com.azz.platform.merchant.pojo.bo.SearchParams;
import com.azz.platform.merchant.pojo.vo.Params;

@Mapper
public interface PlatformGoodsParamsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformGoodsParams record);

    int insertSelective(PlatformGoodsParams record);

    PlatformGoodsParams selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformGoodsParams record);

    int updateByPrimaryKey(PlatformGoodsParams record);
    
    
    /**
     * <p>参数列表</p>
     * @param param
     * @return
     * @author 刘建麟  2018年10月31日 上午11:28:49
     */
    List<Params> searchParamsList(SearchParams param);
}