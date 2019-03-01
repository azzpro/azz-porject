package com.azz.order.client.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.order.client.pojo.Personreginfoadd;

@Mapper
public interface ClientPersonRegInfoMapper {

	/**
	 * 插入子商户入网个人信息
	 * @param eif
	 * @return
	 */
	int insertSelective(Personreginfoadd eif);
	
	/**
	 * 根据商户全称和开卡号 查询状态
	 * @param cn
	 * @param mfn
	 * @return
	 */
	String selectStatusByCardNoAndParentMerchantNo(@Param("cn") String cn,@Param("mfn") String mfn);
}
