/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:04:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azz.core.common.errorcode.PlatformGoodsErrorCode;
import com.azz.core.exception.BaseException;
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
	public String selectProductByAssortmentId(String id) {
		JSR303ValidateUtils.validate(id);
		
		String[] split = id.split(",");
		
		if(null != split && split.length > 0) {
			for (String string : split) {
				int count = goodsProductMapper.selectProductByAssortmentId(Long.parseLong(string));
				if(count > 0)
					return "NO";
			}
			return "OK";
		}else {
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
		}
	} 
}

