package com.azz.selection.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.order.selection.bo.ModuleInitParamsParam;
import com.azz.order.selection.bo.SearchCombinationInitParamsParam;
import com.azz.order.selection.bo.SearchInitParamsParam;
import com.azz.order.selection.bo.SearchSelectionModuleParam;
import com.azz.order.selection.vo.ClassificationInfo;
import com.azz.order.selection.vo.CombinationDetail;
import com.azz.order.selection.vo.CombinationInfo;
import com.azz.order.selection.vo.CombinationInitParams;
import com.azz.order.selection.vo.InitParams;
import com.azz.order.selection.vo.ModuleInfo;
import com.azz.order.selection.vo.ModuleProductInfo;
import com.azz.order.selection.vo.Params;
import com.azz.order.selection.vo.ProductInfo;
import com.azz.order.selection.vo.ProductInfomation;
import com.azz.order.selection.vo.ProductPrice;
import com.azz.order.selection.vo.SelectionCaseInfo;
import com.azz.order.selection.vo.SelectionModuleInfo;

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
	 * <p>查询选型的一级分类列表</p>
	 * @return
	 * @author 黄智聪  2018年12月18日 下午3:35:30
	 */
	List<ClassificationInfo> getClassificationInfos();
	
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
	 * <p>根据参数完善页中选中的参数，获取符合这些参数的产品的公共参数</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月22日 下午4:32:20
	 */
	List<CombinationInitParams> getCombinationInitParams(SearchInitParamsParam param);
	
	/**
	 * 
	 * <p>根据参数完善页中选中的参数，获取符合这些参数的产品的公共参数</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月22日 下午4:32:20
	 */
	List<CombinationInitParams> getCombinationParams(SearchInitParamsParam param);
	
	/**
	 * 
	 * <p>根据参数完善页中选中的参数，获取包含了符合这些参数的产品的推荐组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月22日 下午4:32:20
	 */
	List<CombinationInfo> getCombinationInfos(SearchInitParamsParam param);
	
	/**
	 * 
	 * <p>查询推荐组合详情</p>
	 * @param combinationCode
	 * @return
	 * @author 黄智聪  2018年11月22日 下午3:10:11
	 */
	CombinationDetail getCombinationDetail(String combinationCode);
	
	/**
	 * 
	 * <p>查询初始化参数</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月21日 下午7:07:28
	 */
	List<InitParams> getCombinationDetailInitParams(SearchCombinationInitParamsParam param);
	
	/**
	 * 
	 * <p>根据推荐组合详情页面所选参数查询剩余的公共参数</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月23日 上午10:28:45
	 */
	List<InitParams> getCombinationDetailParams(SearchCombinationInitParamsParam param);
	
	/**
	 * 
	 * <p>查询方案下所有产品的公共参数</p>
	 * @param caseId
	 * @return
	 * @author 黄智聪  2018年11月22日 下午6:53:21
	 */
	List<Params> getParamsNamesByCaseId(Long caseId);
	
	/**
	 * 
	 * <p>查询产品信息</p>
	 * @return
	 * @author 黄智聪  2018年11月22日 下午7:55:06
	 */
	List<ProductInfo> getProductInfos(SearchCombinationInitParamsParam param);
	
	/**
	 * 
	 * <p>根据产品编码查询该产品的价格信息</p>
	 * @param productCode
	 * @return
	 * @author 黄智聪  2018年11月23日 上午11:09:56
	 */
	ProductPrice getProductPrice(String productCode);

	/**
	 * 
	 * <p>根据产品编码、产品价格id查询该产品信息</p>
	 * @param productCode
	 * @param productPriceId
	 * @return
	 * @author 黄智聪  2018年11月23日 下午5:09:12
	 */
	ProductInfomation getProductInfoByProductCode(@Param("productCode")String productCode, @Param("productPriceId")Long productPriceId);
	
	/**
	 * 
	 * <p>查询确认订单页面的商品信息</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月24日 下午3:01:19
	 */
	List<ProductInfomation> getConfirmOrderProductInfos(String clientUserCode);
	
	/**
	 * 
	 * <p>根据分类编码查询模组列表</p>
	 * @return
	 * @author 黄智聪  2018年12月18日 下午5:51:13
	 */
	List<SelectionModuleInfo> getSelectionModuleInfos(SearchSelectionModuleParam param);
	
	/**
	 * 
	 * <p>查询选择三级分类后的初始化参数</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月19日 上午10:46:42
	 */
	List<InitParams> getSelectionModuleParams(SearchSelectionModuleParam param);
	
	/**
	 * 	
	 * <p>查询模组详情</p>
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年12月19日 上午11:20:48
	 */
	ModuleInfo getModuleInfo(String moduleCode);
	
	/**
	 * 
	 * <p>查询模组详情的初始化参数</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月19日 下午2:08:29
	 */
	List<InitParams> getModuleInitParams(ModuleInitParamsParam param);
	
	/**
	 * 
	 * <p>查询模组下产品所有公共的参数名称</p>
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年12月19日 下午2:31:27
	 */
	List<Params> getParamsNamesByModuleCode(String moduleCode);
	
	/**
	 * 
	 * <p>查询模组产品信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月19日 下午3:48:29
	 */
	List<ModuleProductInfo> getModuleProductInfos(ModuleInitParamsParam param);
	
}