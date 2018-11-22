package com.azz.order.client.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.client.pojo.ClientInvoice;
import com.azz.order.client.pojo.bo.SearchAddInvoiceApplyParam;
import com.azz.order.client.pojo.bo.SearchClientInvoiceParam;
import com.azz.order.client.pojo.vo.ClientInvoiceApplyDetail;
import com.azz.order.client.pojo.vo.ClientInvoiceDeliveryDetail;
import com.azz.order.client.pojo.vo.ClientInvoiceList;

@Mapper
public interface ClientInvoiceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientInvoice record);

    int insertSelective(ClientInvoice record);

    ClientInvoice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientInvoice record);

    int updateByPrimaryKey(ClientInvoice record);
    
    List<ClientInvoiceList> getClientInvoiceList(SearchClientInvoiceParam param);
    
    int getExistClientInvoice(String clientOrderCode);
    
    ClientInvoiceApplyDetail getClientInvoiceOrderApplyDetail(SearchAddInvoiceApplyParam param);

    List<ClientInvoiceDeliveryDetail> getInvoiceDeliveryDetail(String clientOrderCode);
}