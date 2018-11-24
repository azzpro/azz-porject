package com.azz.order.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.merchant.pojo.MerchantInvoice;
import com.azz.order.merchant.pojo.bo.SearchInvoiceListParam;
import com.azz.order.merchant.pojo.vo.MerchantInvoiceDetail;
import com.azz.order.merchant.pojo.vo.MerchantInvoiceList;
@Mapper
public interface MerchantInvoiceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantInvoice record);

    int insertSelective(MerchantInvoice record);

    MerchantInvoice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantInvoice record);

    int updateByPrimaryKey(MerchantInvoice record);

    List<MerchantInvoiceList> getMerchantInvoiceList(SearchInvoiceListParam param);

    MerchantInvoiceDetail getMerchantInvoiceDetailInfo(String merchantOrderCode);
    
    MerchantInvoice selectMerchantInvoiceByOrderId(Long merchantOrderId);
}