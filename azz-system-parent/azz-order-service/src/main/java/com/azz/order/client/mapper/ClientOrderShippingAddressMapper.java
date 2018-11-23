package com.azz.order.client.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.client.pojo.ClientOrderShippingAddress;
import com.azz.order.client.pojo.vo.ShippingAddress;
@Mapper
public interface ClientOrderShippingAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientOrderShippingAddress record);

    int insertSelective(ClientOrderShippingAddress record);

    ClientOrderShippingAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientOrderShippingAddress record);

    int updateByPrimaryKey(ClientOrderShippingAddress record);
    
    /**
     * 
     * <p>根据客户编码查询默认收货地址</p>
     * @param clientUserCode
     * @return
     * @author 黄智聪  2018年11月13日 下午3:28:37
     */
    ShippingAddress getDefaultShippingAddressByClientUserCode(String clientUserCode);
    
    /**
     * 
     * <p>根据客户编码查询收货地址列表</p>
     * @param clientUserCode
     * @return
     * @author 黄智聪  2018年11月13日 下午3:28:37
     */
    List<ShippingAddress> getShippingAddressByClientUserCode(String clientUserCode);
    
    /**
     * 
     * <p>查询当前客户的收货地址数量</p>
     * @param clientUserCode
     * @return
     * @author 黄智聪  2018年11月13日 下午5:13:18
     */
    int countShippingAddressByClientUserCode(String clientUserCode);
    
    /**
     * 
     * <p>将客户的其他收货地址设置成非默认</p>
     * @param clientUserCode
     * @return
     * @author 黄智聪  2018年11月13日 下午6:03:11
     */
    int setOtherShippingAddressNotDefault(Long clientUserId);
    
    /**
     * 
     * <p>根据主键查询收货地址</p>
     * @param clientUserCode
     * @return
     * @author 黄智聪  2018年11月13日 下午3:28:37
     */
    ShippingAddress getShippingAddressByShippingId(Long shippingId);
}