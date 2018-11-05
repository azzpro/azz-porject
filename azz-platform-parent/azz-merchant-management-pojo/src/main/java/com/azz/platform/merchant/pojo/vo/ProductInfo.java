/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月5日 下午3:38:00
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.vo;

import java.util.Date;
import java.util.List;

import com.azz.platform.merchant.pojo.MerchantGoodsProductParams;
import com.azz.platform.merchant.pojo.MerchantGoodsProductPrice;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月5日 下午3:38:00
 */
@Data
public class ProductInfo {
	private String productCode;//产品编码
	private String merchantName;//所属商户
	private String moduleName;//所属模组
	private Integer currentStatus;//当前状态
	private Date createTime;//创建时间
	private List<MerchantGoodsProductPrice> prices;// 交期价格
	private List<MerchantGoodsProductParams> params;//产品参数
	
}

