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
import com.azz.order.platform.Merchant;
import com.azz.order.platform.bo.SearchMerchantOrderParam;
import com.azz.order.platform.vo.MerchantOrderList;
@Mapper
public interface MerchantOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantOrder record);

    int insertSelective(MerchantOrder record);

    MerchantOrder selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(MerchantOrder record);

    int updateByPrimaryKey(MerchantOrder record);
    
    Merchant selectMerchantByMerchantCode(String merchantCode);
    
    /**
     * <p>【商户端】获取商户订单列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月14日 下午5:11:09
     */
    List<OrderList> selectOrderList(SearchOrderListParam param);
    
    /**
     * <p>【商户端】获取商户订单基本详情</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月14日 下午5:10:59
     */
    OrderDetail selectOrderInfo(SearchOrderDetailParam param);

    /**
     * <p>【商户端】收货地址</p>
     * @param orderCode
     * @return
     * @author 彭斌  2018年11月14日 下午5:10:47
     */
    ReceiverAddress selectReceiverAddressInfo(String orderCode);

    /**
     * <p>【商户端】发货信息</p>
     * @param orderCode
     * @return
     * @author 彭斌  2018年11月14日 下午5:10:38
     */
    ShipInfo selectShipInfo(String orderCode);
    
    /**
     * <p>【商户端】签收信息</p>
     * @param orderCode
     * @return
     * @author 彭斌  2018年11月14日 下午5:10:26
     */
    SignForInfo selectSignFor(String orderCode);
    
    /**
     * <p>【商户端】获取订单头信息</p>
     * @param orderCode
     * @return
     * @author 彭斌  2018年11月14日 下午5:09:57
     */
    MerchantOrder selectMerchantOrderInfo(String orderCode);
    
    /**
     * 
     * <p>查询待发货的商户订单数量</p>
     * @return
     * @author 黄智聪  2018年11月14日 下午1:59:58
     */
    int countSendOutMerchantOrderByClientOrderId(Long clientOrderId); 
    
    /**
     * 
     * <p>根据客户订单id查询所关联的商户订单id</p>
     * @param clientOrderId
     * @return
     * @author 黄智聪  2018年11月14日 下午7:09:55
     */
    Long getMerchantOrderIdByClientOrderId(Long clientOrderId);

    /**
     * <p>【平台端】商户订单列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月14日 下午5:09:43
     */
    List<MerchantOrderList> selectPlatformMerchantOrder(SearchMerchantOrderParam param);
    
    /**
     * <p>【平台端】根据客户订单id获取所有商户订单信息</p>
     * @param clientOrderId
     * @return
     * @author 彭斌  2018年11月22日 下午4:37:56
     */
    List<MerchantOrder> selectMerchantOrderByClientOrderId(Long clientOrderId);
}