package com.azz.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.merchant.pojo.MerchantGoodsProductPrice;

@Mapper
public interface MerchantGoodsProductPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantGoodsProductPrice record);

    int insertSelective(MerchantGoodsProductPrice record);

    MerchantGoodsProductPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantGoodsProductPrice record);

    int updateByPrimaryKey(MerchantGoodsProductPrice record);
    
    /**
     * <p>批量插入产品价格</p>
     * @param prices
     * @return
     * @author 刘建麟  2018年11月2日 下午4:31:03
     */
    int insertBatchPrice(List<MerchantGoodsProductPrice> prices);
}