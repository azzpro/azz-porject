package com.azz.platform.client.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.client.pojo.ClientUserCompany;
import com.azz.platform.client.pojo.bo.SearchClientMerchantManagerParam;
import com.azz.platform.client.pojo.vo.ClientMerchantInfo;

@Mapper
public interface ClientUserCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientUserCompany record);

    int insertSelective(ClientUserCompany record);

    ClientUserCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientUserCompany record);

    int updateByPrimaryKey(ClientUserCompany record);
    
    /**
     * <p>客户企业管理</p>
     * @return
     * @author 刘建麟  2018年10月25日 下午5:35:29
     */
    List<ClientMerchantInfo> selectClientMerchantList(SearchClientMerchantManagerParam param);
}