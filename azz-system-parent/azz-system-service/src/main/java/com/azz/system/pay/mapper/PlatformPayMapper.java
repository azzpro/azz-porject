/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午2:18:55
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.pay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.system.bo.PayList;
import com.azz.system.pojo.PlatformPay;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午2:18:55
 */
@Mapper
public interface PlatformPayMapper {
	/**
	 * <p>插入支付</p>
	 * @param pp
	 * @return
	 * @author 刘建麟  2018年11月26日 下午2:30:31
	 */
	int insertPay(PlatformPay pp);
	
	/**
	 * <p>支付列表</p>
	 * @param pl
	 * @return
	 * @author 刘建麟  2018年12月3日 下午2:58:46
	 */
	List<PlatformPay> selectPayList(PayList pl);
}

