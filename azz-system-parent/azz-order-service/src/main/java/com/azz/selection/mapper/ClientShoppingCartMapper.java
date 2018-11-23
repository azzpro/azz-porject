package com.azz.selection.mapper;

import com.azz.order.selection.ClientShoppingCart;

public interface ClientShoppingCartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientShoppingCart record);

    int insertSelective(ClientShoppingCart record);

    ClientShoppingCart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientShoppingCart record);

    int updateByPrimaryKey(ClientShoppingCart record);
}