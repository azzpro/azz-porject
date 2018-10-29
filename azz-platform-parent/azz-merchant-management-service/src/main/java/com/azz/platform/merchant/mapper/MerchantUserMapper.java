package com.azz.platform.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.merchant.pojo.MerchantUser;
import com.azz.platform.merchant.pojo.bo.SearchMerchantUserParam;
import com.azz.platform.merchant.pojo.vo.MerchantUserInfo;

@Mapper
public interface MerchantUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantUser record);

    int insertSelective(MerchantUser record);

    MerchantUser selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(MerchantUser record);

    int updateByPrimaryKey(MerchantUser record);
    
    int updateByMerchantUserCode(MerchantUser record);
    
    MerchantUser getMerchantUserByPhoneNumber(String phoneNumber);
    
    /**
     * <p>根据用户编码查询</p>
     * @param merchantUserCode
     * @return
     * @author 黄智聪  2018年10月18日 下午5:14:23
     */
    MerchantUser getMerchantUserByMerchantUserCode(String merchantUserCode);

    
    /**
     * 
     * <p>根据手机号查询用户实体</p>
     * @param phoneNumber 手机号
     * @return
     * @author 黄智聪  2018年10月17日 下午7:14:23
     */
    MerchantUser getMerchantUserByPhoneNumberAndMerchantUserCode(@Param("phoneNumber") String phoneNumber, @Param("merchantUserCode") String merchantUserCode);
    
    
    MerchantUserInfo getMerchantUserInfoByPhoneNumber(String phoneNumber);

    /**
     * 
     * <p>根据邮箱查询用户</p>
     * @param email
     * @param merchantUserCode
     * @return
     * @author 黄智聪  2018年10月20日 下午3:05:55
     */
    MerchantUser getMerchantUserByEmail(@Param("email") String email, @Param("merchantUserCode") String merchantUserCode);

    
    /**
     * 
     * <p>根据查询条件查询用户信息</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月20日 上午10:10:19
     */
    List<MerchantUserInfo> getMerchantUserInfoBySearchParam(SearchMerchantUserParam param);
    
    /**
     * 
     * <p>根据用户编码查询用户信息</p>
     * @param userCode
     * @return
     * @author 黄智聪  2018年10月20日 上午10:51:33
     */
    MerchantUserInfo getMerchantUserInfoByMerchantUserCode(String merchantUserCode);
    
    /**
     * 
     * <p>根据商户编码查询商户注册人信息</p>
     * @param merchantCode
     * @return
     * @author 彭斌  2018年10月30日 上午1:26:09
     */
    MerchantUser getRegistMerchantUserByMerchantCode(String merchantCode);
}