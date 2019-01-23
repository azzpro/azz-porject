/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 下午5:28:47
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import com.azz.core.common.QueryPage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月4日 下午5:28:47
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SearchStartClassRecordParam extends QueryPage{

	private static final long serialVersionUID = 754959189102406643L;
	
	@NotBlank(message = "请选择课程")
	private String courseCode;
	
	private Byte status;
	
	private String searchInput;
	
	/**
	 * 排序类型
	 * 		1综合降序(开课信息创建时间降序)
	 * 		2综合升序(开课信息创建时间升序)  
	 * 		3开课时间降序 
	 *  	4开课时间升序  
	 *  	5开课价格降序  
	 *  	6开课价格升序
	 */
	private Integer sortType = 1; 
	
}

