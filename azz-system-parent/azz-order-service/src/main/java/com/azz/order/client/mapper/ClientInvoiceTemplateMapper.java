package com.azz.order.client.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.order.client.pojo.ClientInvoiceTemplate;
import com.azz.order.client.pojo.bo.SearchCountTemplateParam;
import com.azz.order.client.pojo.bo.SearchInvoiceTemplateParam;
import com.azz.order.client.pojo.vo.ClientInvoiceTemplateList;
@Mapper
public interface ClientInvoiceTemplateMapper {
    int deleteByPrimaryKey(@Param("id") Long id,@Param("clientUserId")Long clientUserId);

    int insert(ClientInvoiceTemplate record);

    int insertSelective(ClientInvoiceTemplate record);

    ClientInvoiceTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientInvoiceTemplate record);

    int updateByPrimaryKey(ClientInvoiceTemplate record);
    
    /**
     * <p>根据发票类型和客户id获取所有发票模板信息</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月19日 下午3:11:52
     */
    List<ClientInvoiceTemplateList> getInvoiceTemplate(SearchInvoiceTemplateParam param);

    /**
     * <p>多参数获取模板校验</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月20日 下午2:41:48
     */
    int getCountTemplate(SearchCountTemplateParam param);
}