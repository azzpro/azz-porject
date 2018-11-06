package com.azz.platform.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.merchant.pojo.PlatformCombination;
import com.azz.platform.merchant.pojo.bo.SearchCombinationParam;
import com.azz.platform.merchant.pojo.vo.CombinationInfo;
import com.azz.platform.merchant.pojo.vo.GoodsModuleInfo;

@Mapper
public interface PlatformCombinationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformCombination record);

    int insertSelective(PlatformCombination record);

    PlatformCombination selectByPrimaryKey(Long id);
    
    PlatformCombination selectByCombinationCode(String combinationCode);

    int updateByPrimaryKeySelective(PlatformCombination record);

    int updateByCombinationCode(PlatformCombination record);

    int updateByPrimaryKey(PlatformCombination record);
    
    /**
     * 
     * <p>根据组合编码查询绑定的模组个数</p>
     * @param combinationCode
     * @return
     * @author 黄智聪  2018年11月6日 下午2:53:20
     */
    int countBindingModuleByCombinationCode(String combinationCode);
    
    /**
     * 
     * <p>根据组合编码、名称查询推荐组合是否存在</p>
     * @param combinationCode
     * @param combinationName
     * @return
     * @author 黄智聪  2018年11月5日 下午8:06:07
     */
    int countCombinationByCombinationNameAndCaseId(@Param("combinationCode")String combinationCode, @Param("combinationName")String combinationName, @Param("caseId")Long caseId);
    
    /**
     * 
     * <p>根据方案id和模组id查询组合、模组关系表记录的数量</p>
     * @param combinationCode
     * @param moduleIds
     * @param caseId
     * @return
     * @author 黄智聪  2018年11月5日 下午8:43:30
     */
    int countCombinationModuleByCaseIdAndModuleIds(@Param("combinationCode")String combinationCode, @Param("moduleIds")List<Long> moduleIds, @Param("caseId")Long caseId);
    
    /**
     * 
     * <p>查询moduleIds</p>
     * @param moduleCodes
     * @return
     * @author 黄智聪  2018年11月5日 下午8:55:53
     */
    List<Long> getModuleIdsByModuleCodes(List<String> moduleCodes);
    
    /**
     * 
     * <p>查询组合列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月5日 下午7:05:44
     */
    List<CombinationInfo> getCombinationInfoList(SearchCombinationParam param);
    
    /**
     * 
     * <p>查询组合详情</p>
     * @param combinationCode
     * @return
     * @author 黄智聪  2018年11月5日 下午7:18:33
     */
    CombinationInfo getCombinationInfo(String combinationCode);
    
    /**
     * 
     * <p>根据组合编码查询模组列表</p>
     * @param combinationCode
     * @return
     * @author 黄智聪  2018年11月6日 下午2:45:08
     */
    List<GoodsModuleInfo> getModuleInfoByCombinationCode(String combinationCode);
}