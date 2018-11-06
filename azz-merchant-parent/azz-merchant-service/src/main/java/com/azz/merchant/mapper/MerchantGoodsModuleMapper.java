package com.azz.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.merchant.pojo.MerchantGoodsModule;
import com.azz.merchant.pojo.bo.ModulePrams;
import com.azz.merchant.pojo.bo.SearchGoodsModuleParam;
import com.azz.merchant.pojo.vo.GoodsModuleInfo;
import com.azz.merchant.pojo.vo.Module;

@Mapper
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
    
    List<Module> selectModuleByAssorId(ModulePrams mp);
    
    /**
     * 
     * <p>查询模组列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月1日 下午3:26:00
     */
    List<GoodsModuleInfo> getGoodsModuleInfoList(SearchGoodsModuleParam param);
    
    /**
     * 
     * <p>查询模组详情</p>
     * @param moduleCode
     * @return
     * @author 黄智聪  2018年11月1日 下午8:44:16
     */
    GoodsModuleInfo getGoodsModuleInfo(String moduleCode);
    
    /**
     * <p>根据CODE查询模组</p>
     * @param code
     * @return
     * @author 刘建麟  2018年11月2日 下午3:45:15
     */
    MerchantGoodsModule selectModuleById(Long id);
    
    
}