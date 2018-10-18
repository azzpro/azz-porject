package com.azz.platform.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.user.pojo.PlatformUser;
import com.azz.platform.user.pojo.vo.UserInfo;

@Mapper
public interface PlatformUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformUser record);

    int insertSelective(PlatformUser record);

    PlatformUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformUser record);

    int updateByPrimaryKey(PlatformUser record);
    
    /**
     * 
     * <p>根据手机号查询用户实体</p>
     * @param phoneNumber 手机号
     * @return
     * @author 黄智聪  2018年10月17日 下午7:14:23
     */
    PlatformUser getUserByPhoneNumber(String phoneNumber);
    
    /**
     * 
     * <p>根据用户id查询用户信息</p>
     * @param userId
     * @return
     * @author 黄智聪  2018年10月17日 下午7:14:20
     */
    UserInfo getUserInfoByPhoneNumber(String phoneNumber);
}