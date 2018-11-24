package com.azz.selection.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.selection.ClientShoppingCart;
import com.azz.order.selection.vo.ShoppingCartProductInfo;

@Mapper
public interface ClientShoppingCartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientShoppingCart record);

    int insertSelective(ClientShoppingCart record);

    ClientShoppingCart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientShoppingCart record);

    int updateByPrimaryKey(ClientShoppingCart record);
    
    /**
     * 
     * <p>查询客户购物车产品信息</p>
     * @param clientUserCode
     * @return
     * @author 黄智聪  2018年11月23日 下午6:23:47
     */
    List<ShoppingCartProductInfo> getShoppingCartProductInfos(String clientUserCode);
    
    /**
     * 
     * <p>查询客户购物车中的产品存在的下架商品数量</p>
     * @param clientUserCode
     * @return
     * @author 黄智聪  2018年11月23日 下午7:05:05
     */
    int countPutOffProducts(String clientUserCode);
}