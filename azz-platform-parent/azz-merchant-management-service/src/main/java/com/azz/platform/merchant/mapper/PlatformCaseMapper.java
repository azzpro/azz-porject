package com.azz.platform.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformCase;
import com.azz.platform.merchant.pojo.vo.CaseParams;
import com.azz.platform.merchant.pojo.vo.CaseParamsList;
import com.azz.platform.merchant.pojo.bo.SearchCaseInfoParam;
import com.azz.platform.merchant.pojo.vo.CaseInfo;
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
    
    List<CaseParams> selectParamsByAssortmentId(Long classificationId);
    
    List<CaseParamsList> selectParamsByCaseCode(String caseCode);
    
    List<CaseInfo> getCaseInfoList(SearchCaseInfoParam param);
}