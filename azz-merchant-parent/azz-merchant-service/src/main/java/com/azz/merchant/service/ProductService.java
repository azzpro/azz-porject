/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:04:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azz.merchant.mapper.MerchantGoodsProductMapper;
import com.azz.util.JSR303ValidateUtils;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月1日 下午2:04:46
 */
@Service
public class ProductService {
	
	@Autowired
	private MerchantGoodsProductMapper goodsProductMapper;
	
	
	/**
	 * <p>根据分类ID 查询产品</p>
	 * @param id
	 * @return
	 * @author 刘建麟  2018年11月1日 下午2:05:44
	 */
	public String selectProductByAssortmentId(Long id) {
		JSR303ValidateUtils.validate(id);
		int count = goodsProductMapper.selectProductByAssortmentId(id);
		if(count > 0) {
			return "NO";
		}else {
			return "OK";
		}
	} 
}

