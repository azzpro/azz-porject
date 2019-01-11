package com.azz.platform.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.merchant.pojo.PlatformGoodsParamsTerm;

@Mapper
public interface PlatformGoodsParamsTermMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformGoodsParamsTerm record);

    int insertSelective(PlatformGoodsParamsTerm record);

    PlatformGoodsParamsTerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformGoodsParamsTerm record);

    int updateByPrimaryKey(PlatformGoodsParamsTerm record);
    
    int updateParamTremHidden(@Param("id") Long id,@Param("status") Integer status);
    
    /**
     * <p>删除参数项</p>
     * @param id
     * @return
     * @author 刘建麟  2018年11月1日 下午2:54:31
     */
    int deleteByParamsId(Long id);
    
    /**
     * <p>根据父参数ID 查询参数项</p>
     * @param id
     * @return
     * @author 刘建麟  2018年10月31日 下午7:58:29
     */
    List<PlatformGoodsParamsTerm> selectParamsTermByCode(Long id);
    
    /**
     * <p>更新参数</p>
     * @param p
     * @return
     * @author 刘建麟  2018年10月31日 下午8:56:33
     */
    int updateBycode(PlatformGoodsParamsTerm p);
    
    /**
     * <p>根据CODE查询ID</p>
     * @param code
     * @return
     * @author 刘建麟  2018年10月31日 下午9:09:42
     */
    PlatformGoodsParamsTerm selectIdTermByCode(String code);
}