package com.azz.client.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.client.pojo.bo.SearchSpecialPerformanceOfIndexParam;
import com.azz.client.pojo.vo.ModuleInfo;
import com.azz.client.pojo.vo.SpecialPerformanceOfIndex;

@Mapper
public interface IndexMapper {

	SpecialPerformanceOfIndex getSpecialPerformanceOfIndex(String specialPerformanceCode);

	List<ModuleInfo> getSpecialPerformanceModulesOfIndex(SearchSpecialPerformanceOfIndexParam param);

}