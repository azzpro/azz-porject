package com.azz.order.client.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.client.pojo.ClientOrderOperationRecord;
import com.azz.order.platform.vo.OrderOperationRecord;

@Mapper
public interface ClientOrderOperationRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientOrderOperationRecord record);

    int insertSelective(ClientOrderOperationRecord record);

    ClientOrderOperationRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientOrderOperationRecord record);

    int updateByPrimaryKey(ClientOrderOperationRecord record);
    
    /**
     * 
     * <p>查询客户订单的操作记录</p>
     * @param clientOrderCode
     * @return
     * @author 黄智聪  2018年11月16日 下午2:53:37
     */
    List<OrderOperationRecord> getClientOrderOperationRecordByClientOrderCode(String clientOrderCode);
}