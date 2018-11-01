package com.azz.platform.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.platform.merchant.pojo.PlatformGoodsParamsValue;

@Mapper
public interface PlatformGoodsParamsValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformGoodsParamsValue record);

    int insertSelective(PlatformGoodsParamsValue record);

    PlatformGoodsParamsValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformGoodsParamsValue record);

    int updateByPrimaryKey(PlatformGoodsParamsValue record);
    
    /**
     * <p>根据父ID 删除value</p>
     * @param pid
     * @return
     * @author 刘建麟  2018年11月1日 上午9:57:52
     */
    int deleteByParentId(Long pid);
    
    /**
     * <p>根据父ID 查询参数值</p>
     * @param id
     * @return
     * @author 刘建麟  2018年10月31日 下午8:05:21
     */
    List<PlatformGoodsParamsValue> selectValueById(Long id);
    
    /**
     * <p>根据父ID 更新值</p>
     * @param value
     * @param pid
     * @return
     * @author 刘建麟  2018年10月31日 下午9:11:40
     */
    int updateByCode(@RequestParam("value") Long value,@RequestParam("pid") Long pid);
}