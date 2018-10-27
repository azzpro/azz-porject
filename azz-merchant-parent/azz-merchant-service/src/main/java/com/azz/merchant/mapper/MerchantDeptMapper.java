package com.azz.merchant.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.merchant.pojo.MerchantDept;

@Mapper
public interface MerchantDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantDept record);

    int insertSelective(MerchantDept record);

    MerchantDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantDept record);

    int updateByPrimaryKey(MerchantDept record);
    
    MerchantDept selectByDeptCode(@Param("merchantCode")String merchantCode, @Param("deptCode")String deptCode);
    
    
}