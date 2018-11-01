package com.azz.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.merchant.pojo.MerchantGoodsProduct;

@Mapper
public interface MerchantGoodsProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantGoodsProduct record);

    int insertSelective(MerchantGoodsProduct record);

    MerchantGoodsProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantGoodsProduct record);

    int updateByPrimaryKey(MerchantGoodsProduct record);
    
    /**
     * <p>根据分类ID 查询产品</p>
     * @param id
     * @return
     * @author 刘建麟  2018年11月1日 下午2:03:56
     */
    int selectProductByAssortmentId(Long id);
}