package com.azz.platform.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.azz.platform.merchant.pojo.MerchantGoodsModule;
import com.azz.platform.merchant.pojo.bo.SearchGoodsModuleParam;
import com.azz.platform.merchant.pojo.vo.GoodModuleInfo;

public interface MerchantGoodsModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantGoodsModule record);

    int insertSelective(MerchantGoodsModule record);

    MerchantGoodsModule selectByPrimaryKey(Long id);
    
    MerchantGoodsModule selectByModuleCode(String moduleCode);

    int updateByPrimaryKeySelective(MerchantGoodsModule record);

    int updateByPrimaryKeyWithBLOBs(MerchantGoodsModule record);

    int updateByPrimaryKey(MerchantGoodsModule record);
    
    int updateByModuleCode(MerchantGoodsModule record);
    
    int countGoodsModule(@Param("merchantId")Long merchantId, @Param("moduleName")String moduleName, @Param("moduleCode")String moduleCode);
    
    /**
     * 
     * <p>查询模组列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月1日 下午3:26:00
     */
    List<GoodModuleInfo> getGoodsModuleInfoList(SearchGoodsModuleParam param);
    
    /**
     * 
     * <p>查询模组详情</p>
     * @param moduleCode
     * @return
     * @author 黄智聪  2018年11月1日 下午8:44:16
     */
    GoodModuleInfo getGoodsModuleInfo(String moduleCode);
    
    
}