package com.azz.order.client.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.client.pojo.ClientInvoice;
import com.azz.order.client.pojo.bo.SearchAddInvoiceApplyParam;
import com.azz.order.client.pojo.bo.SearchClientInvoiceParam;
import com.azz.order.client.pojo.vo.ClientInvoiceApplyDetail;
import com.azz.order.client.pojo.vo.ClientInvoiceDeliveryDetail;
import com.azz.order.client.pojo.vo.ClientInvoiceList;
import com.azz.order.platform.bo.SearchInvoiceListParam;
import com.azz.order.platform.vo.ClientOrderRelevanceInvoice;
import com.azz.order.platform.vo.PlatformClientInvoiceApplyDetail;
import com.azz.order.platform.vo.PlatformClientInvoiceList;

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

    /**
     * <p>【平台】获取客户发票列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月22日 下午2:47:35
     */
    List<PlatformClientInvoiceList> getPlatformClientInvoiceList(SearchInvoiceListParam param);

    /**
     * <p>【平台】获取申请详情基本信息</p>
     * @param clientOrderCode
     * @return
     * @author 彭斌  2018年11月22日 下午2:46:50
     */
    PlatformClientInvoiceApplyDetail getPlatformClientInvoiceOrderDetail(String clientOrderCode);
    
    /**
     * <p>【平台】获取关联商户的发票信息</p>
     * @param clientOrderCode
     * @return
     * @author 彭斌  2018年11月22日 下午2:46:27
     */
    List<ClientOrderRelevanceInvoice> getOrderRelevanceInvoiceList(String clientOrderCode);
    
    /**
     * <p>【平台】获取客户发票申请明细</p>
     * @param clientApplyCode
     * @return
     * @author 彭斌  2018年11月22日 下午4:15:30
     */
    ClientInvoice getClientInvoiceByApplyCode(String clientApplyCode);
}