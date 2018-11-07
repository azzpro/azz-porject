package com.azz.platform.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformCase;
import com.azz.platform.merchant.pojo.bo.SearchCaseInfoParam;
import com.azz.platform.merchant.pojo.bo.SearchCaseListParam;
import com.azz.platform.merchant.pojo.bo.SearchCaseParamList;
import com.azz.platform.merchant.pojo.vo.CaseInfo;
import com.azz.platform.merchant.pojo.vo.CaseList;
import com.azz.platform.merchant.pojo.vo.CaseParams;
import com.azz.platform.merchant.pojo.vo.CaseParamsList;
@Mapper
public interface PlatformCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformCase record);

    int insertSelective(PlatformCase record);

    PlatformCase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformCase record);

    int updateByPrimaryKey(PlatformCase record);
    
    PlatformCase selectByCaseCode(String caseCode);
    
    PlatformCase selectByCaseName(String caseName);
    
    PlatformCase selectByClassificationId(Long classificationId);
    
    List<CaseParams> selectParamsByAssortmentId(SearchCaseParamList param);
    
    List<CaseParamsList> selectParamsByCaseCode(String caseCode);
    
    List<CaseInfo> getCaseInfoList(SearchCaseInfoParam param);
    
    List<CaseList> selectCaseListByParam(SearchCaseListParam param);
}