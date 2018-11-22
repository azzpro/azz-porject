package com.azz.selection.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.selection.bo.SearchInitParamsParam;
import com.azz.order.selection.vo.CombinationInfo;
import com.azz.order.selection.vo.InitParams;
import com.azz.order.selection.vo.SelectionCaseInfo;
@Mapper
public interface SelectionMapper {
	
	/**
	 * 
	 * <p>查询选型的所有方案列表</p>
	 * @return
	 * @author 黄智聪  2018年11月19日 下午5:05:32
	 */
	List<SelectionCaseInfo> getSelectionCaseInfos();
	
	/**
	 * 
	 * <p>查询初始化参数</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月21日 下午7:07:28
	 */
	List<InitParams> getInitParams(SearchInitParamsParam param);
	
	/**
	 * 
	 * <p>查询推荐组合列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月22日 下午2:41:49
	 */
	List<CombinationInfo> getCombinationInfos(SearchInitParamsParam param);
	
}