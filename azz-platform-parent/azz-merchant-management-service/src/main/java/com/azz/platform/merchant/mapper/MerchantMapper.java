package com.azz.platform.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.platform.merchant.pojo.Merchant;
import com.azz.platform.merchant.pojo.bo.SearchMerchantListParam;
import com.azz.platform.merchant.pojo.bo.SearchMerchantParam;
import com.azz.platform.merchant.pojo.vo.MerchantApproval;
import com.azz.platform.merchant.pojo.vo.MerchantInfoOpen;
import com.azz.platform.merchant.pojo.vo.MerchantListInfo;

@Mapper
public interface MerchantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    Merchant selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Merchant record);

    int updateByPrimaryKey(Merchant record);
    
    Merchant selectByCode(String merchantCode);
    
    List<MerchantApproval> selectMerchantList(SearchMerchantParam record);
    
    
    /**
     * <p>商户管理列表</p>
     * @param smlp
     * @return
     * @author 刘建麟  2018年10月24日 下午7:19:52
     */
    List<MerchantListInfo> selectMerchantInfoList(SearchMerchantListParam smlp);
    
    /**
     * <p>商户详情</p>
     * @param code
     * @return
     * @author 刘建麟  2018年10月24日 下午9:07:59
     */
    MerchantInfoOpen getMerchantInfo(@Param("code") String code);
    
    /**
     * <p>商户管理列表</p>
     * @param smlp
     * @return
     * @author 刘建麟  2018年10月24日 下午7:19:52
     */
    Integer merchantStatusChange(@Param("code") String code,@Param("status") Integer status);
}