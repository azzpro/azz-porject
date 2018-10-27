package com.azz.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.merchant.pojo.MerchantDept;
import com.azz.merchant.pojo.bo.SearchMerchantChildDeptParam;
import com.azz.merchant.pojo.bo.SearchMerchantDeptInfo;
import com.azz.merchant.pojo.bo.SearchMerchantDeptListParam;
import com.azz.merchant.pojo.vo.MerchantDeptInfo;
import com.azz.merchant.pojo.vo.MerchantDeptList;
@Mapper
public interface MerchantDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantDept record);

    int insertSelective(MerchantDept record);

    MerchantDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantDept record);

    int updateByPrimaryKey(MerchantDept record);
    
    MerchantDeptInfo selectByMerchantIdAndName(SearchMerchantDeptInfo param);
    
    int selectFirstLevelExist(SearchMerchantDeptInfo param);
    
    MerchantDept selectByDeptAllInfo(SearchMerchantDeptInfo param);

    List<MerchantDeptList> selectFirstDeptList(SearchMerchantDeptListParam param);
    
    List<MerchantDeptList> selectChildDeptList(SearchMerchantChildDeptParam param);
    
    MerchantDept selectByDeptCode(String deptCode);
    
    
}