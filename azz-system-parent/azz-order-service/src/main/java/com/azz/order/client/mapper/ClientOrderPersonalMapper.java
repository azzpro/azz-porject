package com.azz.order.client.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.client.pojo.ClientOrderPersonal;
import com.azz.order.client.pojo.bo.SearchClientOrderParam;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.order.client.pojo.vo.DeliveryInfo;
import com.azz.order.client.pojo.vo.SignInfo;
@Mapper
public interface ClientOrderPersonalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientOrderPersonal record);

    int insertSelective(ClientOrderPersonal record);

    ClientOrderPersonal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientOrderPersonal record);

    int updateByPrimaryKey(ClientOrderPersonal record);
    
    /**
     * 
     * <p>查询客户订单列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月12日 下午4:47:45
     */
    List<ClientOrderInfo> getClientOrderInfoList(SearchClientOrderParam param);
    
    /**
     * 
     * <p>查询订单详情</p>
     * @param clientOrderCode
     * @return
     * @author 黄智聪  2018年11月13日 上午11:06:57
     */
    ClientOrderInfo getClientOrderDetailByClientOrderCode(String clientOrderCode);
    
    /**
     * 
     * <p>查询订单详情</p>
     * @param clientOrderCode
     * @return
     * @author 黄智聪  2018年11月13日 上午11:06:57
     */
    List<DeliveryInfo> getDeliveryInfoByClientOrderCode(String clientOrderCode);
    
    /**
     * 
     * <p>查询签收信息</p>
     * @param clientOrderCode
     * @return
     * @author 黄智聪  2018年11月13日 下午2:04:36
     */
    SignInfo getSignInfoByClientOrderCode(String clientOrderCode);
}