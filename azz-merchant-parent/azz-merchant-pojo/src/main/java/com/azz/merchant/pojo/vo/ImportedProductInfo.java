/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月13日 下午2:46:34
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年12月13日 下午2:46:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportedProductInfo {
	
	private GoodsModuleInfo moduleInfo;
	
	private List<ModuleProduct> moduleProducts;

}

