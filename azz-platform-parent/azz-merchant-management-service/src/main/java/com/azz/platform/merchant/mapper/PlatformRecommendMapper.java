package com.azz.platform.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformRecommend;
import com.azz.platform.merchant.pojo.bo.SearchRecommendInfoParam;
import com.azz.platform.merchant.pojo.bo.SearchRecommendProductInfoParam;
import com.azz.platform.merchant.pojo.bo.SearchRelatedModuleInfoParam;
import com.azz.platform.merchant.pojo.bo.SearchSpecialPerformanceRelatedModuleInfoParam;
import com.azz.platform.merchant.pojo.vo.ModuleInfo;
import com.azz.platform.merchant.pojo.vo.RecommendInfo;
import com.azz.platform.merchant.pojo.vo.RecommentProductInfo;
import com.azz.platform.merchant.pojo.vo.RelatedModuleInfo;

@Mapper
public interface PlatformRecommendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformRecommend record);

    int insertSelective(PlatformRecommend record);

    PlatformRecommend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformRecommend record);

    int updateByPrimaryKey(PlatformRecommend record);
    
    PlatformRecommend selectByRecommendCode(String recommendCode);
    
    /**
     * 
     * <p>查询某专场的推荐列表</p>
     * @param param
     * @return
     * @author 黄智聪  2019年1月7日 上午11:21:02
     */
    List<RecommendInfo> getRecommendInfos(SearchRecommendInfoParam param);
    
    /**
     * 
     * <p>查询关联模组列表</p>
     * @param param
     * @return
     * @author 黄智聪  2019年1月7日 下午2:37:29
     */
    List<ModuleInfo> getRelatedModuleInfos(SearchRelatedModuleInfoParam param);
    
    /**
     * 
     * <p>查询专场活动下的所有关联的模组列表</p>
     * @param param
     * @return
     * @author 黄智聪  2019年1月7日 下午4:27:23
     */
    List<RelatedModuleInfo> getSpecialPerformanceRelatedModuleInfos(SearchSpecialPerformanceRelatedModuleInfoParam param);
    
    /**
     * 
     * <p>查询活动中某个模组所关联的产品列表</p>
     * @param param
     * @return
     * @author 黄智聪  2019年1月7日 下午6:39:44
     */
    List<RecommentProductInfo> getRecommentProductInfos(SearchRecommendProductInfoParam param);
    
}