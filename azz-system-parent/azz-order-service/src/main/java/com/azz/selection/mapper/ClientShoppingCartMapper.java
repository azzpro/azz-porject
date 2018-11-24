package com.azz.selection.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.order.selection.ClientShoppingCart;
import com.azz.order.selection.bo.OrderItem;
import com.azz.order.selection.vo.ShoppingCartItemInfo;
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
    
    /**
     * 
     * <p>查询客户购物车的商品信息</p>
     * @param clientUserCode
     * @param orderItems
     * @return
     * @author 黄智聪  2018年11月24日 下午1:24:54
     */
    List<ShoppingCartItemInfo> getShoppingCartOrderItems(@Param("clientUserCode") String clientUserCode, @Param("orderItems") List<OrderItem> orderItems);

    /**
     * 
     * <p>删除客户的购物车的记录</p>
     * @param clientUserId
     * @return
     * @author 黄智聪  2018年11月24日 下午2:24:22
     */
    int deleteShoppingCartByClientUserId(Long clientUserId);
}