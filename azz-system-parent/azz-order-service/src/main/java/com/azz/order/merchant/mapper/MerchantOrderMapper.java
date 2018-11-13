package com.azz.order.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.merchant.pojo.MerchantOrder;
import com.azz.order.merchant.pojo.bo.SearchOrderDetailParam;
import com.azz.order.merchant.pojo.bo.SearchOrderListParam;
import com.azz.order.merchant.pojo.vo.OrderDetail;
import com.azz.order.merchant.pojo.vo.OrderList;
@Mapper
public interface MerchantOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantOrder record);

    int insertSelective(MerchantOrder record);

    MerchantOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantOrder record);

    int updateByPrimaryKey(MerchantOrder record);
    
    List<OrderList> selectOrderList(SearchOrderListParam param);
    
    OrderDetail selectOrderInfo(SearchOrderDetailParam param);
}