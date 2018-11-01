package com.azz.platform.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    
    
    int updateAssormentIdById(@Param("aid") Long aid,@Param("id") Long id);
    
    /**
     * <p>根据编码删除</p>
     * @param code
     * @return
     * @author 刘建麟  2018年11月1日 下午2:56:25
     */
    int deleteByCode(String code);
    
    /**
     * <p>参数列表</p>
     * @param param
     * @return
     * @author 刘建麟  2018年10月31日 上午11:28:49
     */
    List<Params> searchParamsList(SearchParams param);
    
    /**
     * <p>根据CODE 查询参数</p>
     * @param code
     * @return
     * @author 刘建麟  2018年10月31日 下午7:57:10
     */
    PlatformGoodsParams selectParamsByCode(String code);
    
    /**
     * <p>校验分类参数是否存在</p>
     * @param paramsCode
     * @return
     * @author 彭斌  2018年11月1日 下午9:11:43
     */
    int selectCountByParams(String paramsCode);
}