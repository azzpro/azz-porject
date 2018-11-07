package com.azz.platform.merchant.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.bo.PlatformProduct;
import com.azz.platform.merchant.pojo.bo.SearchProductInfoParam;
import com.azz.platform.merchant.pojo.vo.PlatfomrProductList;
import com.azz.platform.merchant.pojo.vo.ProdInfo;

@Mapper
public interface MerchantGoodsProductMapper {
	
	 List<PlatfomrProductList> selectPlatformProductList(PlatformProduct ppc);
    
	 int downProduct(Long id);
	 
	 Map<String,Object> selectProductById(Long id);
	 
	 List<ProdInfo> getProductInfoList(SearchProductInfoParam param);
}