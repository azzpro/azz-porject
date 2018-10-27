package com.azz.platform.client.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.client.pojo.ClientUserCompany;
import com.azz.platform.client.pojo.bo.SearchClientMerchantManagerParam;
import com.azz.platform.client.pojo.vo.ClientCompanyInfo;
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
	 * <p>平台客户管理 企业管理 启用禁用</p>
	 * @param code
	 * @param status
	 * @return
	 * @author 刘建麟  2018年10月25日 下午4:01:38
	 */
	Integer updateClientCompnayStatus(@Param("code") String code,@Param("status") Integer status);
    
    /**
     * <p>客户企业管理</p>
     * @return
     * @author 刘建麟  2018年10月25日 下午5:35:29
     */
    List<ClientMerchantInfo> selectClientMerchantList(SearchClientMerchantManagerParam param);
    
    /**
     * <p>T企业详情</p>
     * @param code
     * @return
     * @author 刘建麟  2018年10月26日 上午11:06:16
     */
    ClientCompanyInfo selectClientCompanyDetail(String code);
    
    /**
     * <p>根据企业编码获取企业信息</p>
     * @param companyCode
     * @return
     * @author 彭斌  2018年10月26日 下午12:13:27
     */
    ClientUserCompany selectByCompanyCode(String companyCode);
}