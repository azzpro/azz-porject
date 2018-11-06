package com.azz.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.merchant.pojo.MerchantGoodsProductParams;

@Mapper
public interface MerchantGoodsProductParamsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantGoodsProductParams record);

    int insertSelective(MerchantGoodsProductParams record);

    MerchantGoodsProductParams selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantGoodsProductParams record);

    int updateByPrimaryKey(MerchantGoodsProductParams record);
    
    List<MerchantGoodsProductParams> selectParamsByProductId(Long id);
    
    int deleteByProductId(Long pid);
}