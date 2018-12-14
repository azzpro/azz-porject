package com.azz.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.merchant.pojo.MerchantGoodsProduct;
import com.azz.merchant.pojo.bo.MerchantProductParam;
import com.azz.merchant.pojo.vo.MerchantProductList;

@Mapper
public interface MerchantGoodsProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantGoodsProduct record);

    int insertSelective(MerchantGoodsProduct record);

    MerchantGoodsProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantGoodsProduct record);

    int updateByPrimaryKey(MerchantGoodsProduct record);
    
    int updateProductById(@Param("status") Byte status,@Param("id") Long id);
    
    /**
     * <p>根据系统CODE 查询产品</p>
     * @param code
     * @return
     * @author 刘建麟  2018年11月2日 下午7:51:13
     */
    MerchantGoodsProduct selectProductBySystemCode(String code);
    
    /**
     * <p>根据分类ID 查询产品</p>
     * @param id
     * @return
     * @author 刘建麟  2018年11月1日 下午2:03:56
     */
    int selectProductByAssortmentId(Long id);
    
    /**
     * <p>产品列表</p>
     * @param param
     * @return
     * @author 刘建麟  2018年11月2日 下午2:31:40
     */
    List<MerchantProductList> selectProductList(MerchantProductParam param);
    
    /**
     * <p>商品编码唯一校验</p>
     * @param code
     * @return
     * @author 彭斌  2018年12月14日 下午1:52:18
     */
    MerchantGoodsProduct selectProductByProductCode(String code);
    
}