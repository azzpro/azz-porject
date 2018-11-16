package com.azz.order.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.merchant.pojo.ExpressCompany;
import com.azz.order.merchant.pojo.vo.ExpressCompanyInfo;


@Mapper
public interface ExpressCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExpressCompany record);

    int insertSelective(ExpressCompany record);

    ExpressCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExpressCompany record);

    int updateByPrimaryKey(ExpressCompany record);
    
    List<ExpressCompanyInfo> selectAll();
}