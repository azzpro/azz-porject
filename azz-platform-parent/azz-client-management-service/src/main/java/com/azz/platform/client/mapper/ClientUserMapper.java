package com.azz.platform.client.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.client.pojo.ClientUser;
import com.azz.platform.client.pojo.bo.SearchClientManagerParam;
import com.azz.platform.client.pojo.vo.ClientAccountInfo;
import com.azz.platform.client.pojo.vo.ClientInfo;

@Mapper
public interface ClientUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientUser record);

    int insertSelective(ClientUser record);

    ClientUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientUser record);

    int updateByPrimaryKey(ClientUser record);
    
    /**
	 * <p>平台客户管理</p>
	 * @param scpm
	 * @return
	 * @author 刘建麟  2018年10月25日 下午3:08:55
	 */
	List<ClientUser> selectClientUserList(SearchClientManagerParam scpm);
	
	/**
	 * <p>平台客户管理 启用禁用</p>
	 * @param code
	 * @param status
	 * @return
	 * @author 刘建麟  2018年10月25日 下午4:01:38
	 */
	Integer updateClientUserStatus(@Param("code") String code,@Param("status") Integer status);
   
	/**
	 * <p>平台客户管理  用户详情</p>
	 * @param code
	 * @return
	 * @author 刘建麟  2018年10月26日 上午10:27:28
	 */
	ClientAccountInfo selectClientUserInfo(@Param("code") String code);
	
	ClientInfo selectDetailsByCompanyCode(String companyCode);
    
    int checkClientApplyInfo(String clientUserCode);
    
    ClientUser selectByClientUserCode(String clientUserCode);
}