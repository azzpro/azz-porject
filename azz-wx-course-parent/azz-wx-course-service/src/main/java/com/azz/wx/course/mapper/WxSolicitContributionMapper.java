package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxSolicitContribution;
import com.azz.wx.course.pojo.bo.SearchSolicitContributionParam;
import com.azz.wx.course.pojo.vo.SolicitContributionInfo;

@Mapper
public interface WxSolicitContributionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxSolicitContribution record);

    int insertSelective(WxSolicitContribution record);

    WxSolicitContribution selectByPrimaryKey(Integer id);
    
    WxSolicitContribution selectByCode(String solicitContributionCode);

    int updateByPrimaryKeySelective(WxSolicitContribution record);

    int updateByPrimaryKeyWithBLOBs(WxSolicitContribution record);

    int updateByPrimaryKey(WxSolicitContribution record);
    
    /**
     * 
     * <p>查询征稿列表</p>
     * @param param
     * @return
     * @author 黄智聪  2019年5月13日 下午5:07:05
     */
    List<SolicitContributionInfo> getSolicitContributions(SearchSolicitContributionParam param);
    
    /**
     * 
     * <p>查询征稿详情</p>
     * @param solicitContributionCode
     * @return
     * @author 黄智聪  2019年5月13日 下午5:25:08
     */
    SolicitContributionInfo getSolicitContributionDetail(String solicitContributionCode);
}