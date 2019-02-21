/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月20日 下午1:51:00
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.pojo.bo;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.azz.crawler.pojo.Bdsh5Title;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年2月20日 下午1:51:00
 */
@Data
public class SearchInfoParam {
	
	/**
	 * 标题集合
	 */
	@Size(min = 1)
	@NotEmpty(message = "请选择需要爬取数据的标题")
	private List<Bdsh5Title> titlesToSearch;
	
}

