package com.azz.order.client.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.client.pojo.ClientOrderPersonal;
import com.azz.order.client.pojo.bo.SearchClientOrderParam;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
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
}