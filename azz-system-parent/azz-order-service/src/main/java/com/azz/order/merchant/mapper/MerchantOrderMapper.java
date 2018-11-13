package com.azz.order.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.merchant.pojo.MerchantOrder;
import com.azz.order.merchant.pojo.bo.SearchOrderDetailParam;
import com.azz.order.merchant.pojo.bo.SearchOrderListParam;
import com.azz.order.merchant.pojo.vo.OrderDetail;
import com.azz.order.merchant.pojo.vo.OrderList;
import com.azz.order.merchant.pojo.vo.ReceiverAddress;
import com.azz.order.merchant.pojo.vo.ShipInfo;
import com.azz.order.merchant.pojo.vo.SignForInfo;
@Mapper
public interface MerchantOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantOrder record);

    int insertSelective(MerchantOrder record);

    MerchantOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantOrder record);

    int updateByPrimaryKey(MerchantOrder record);
    
    // 获取商户订单列表
    List<OrderList> selectOrderList(SearchOrderListParam param);
    
    // 获取商户订单基本详情
    OrderDetail selectOrderInfo(SearchOrderDetailParam param);

    // 收货地址
    ReceiverAddress selectReceiverAddressInfo(String orderCode);

    // 发货信息
    ShipInfo selectShipInfo(String orderCode);
    
    // 签收信息
    SignForInfo selectSignFor(String orderCode);
}