/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月2日 下午2:38:14
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.bo;

import java.util.List;

import com.azz.merchant.pojo.MerchantGoodsProductParams;
import com.azz.merchant.pojo.MerchantGoodsProductPrice;
import com.azz.merchant.pojo.vo.GoodsBrandInfo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月2日 下午2:38:14
 */
@Data
public class Products {

	private List<MerchantGoodsProductPrice> prices;
	private String creator;
	private List<MerchantGoodsProductParams> params;
	private String assortmentCode;
	private String productCode;
	private String brandCode;
	private Byte status;
	private String moduleCode;
	private String paramsCode;
	private String assormentName;
	private List<GoodsBrandInfo> brands;
	private Long productId;
}

