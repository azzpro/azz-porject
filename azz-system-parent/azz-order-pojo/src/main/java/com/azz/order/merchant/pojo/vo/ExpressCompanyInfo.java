package com.azz.order.merchant.pojo.vo;


import java.io.Serializable;

public class ExpressCompanyInfo implements Serializable {
    private Integer id;

    /**
     * 快递公司名称
     *
     * @mbg.generated
     */
    private String companyName;


    private static final long serialVersionUID = 1L;

    /**
     * <br/>
     * 返回值对应的表列名 express_company.id
     *
     * @return 返回值对应 express_company.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * <br/>
     * express_company.id
     *
     * @param id 值对应 express_company.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 快递公司名称<br/>
     * 返回值对应的表列名 express_company.company_name
     *
     * @return 返回值对应 express_company.company_name
     *
     * @mbg.generated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 快递公司名称<br/>
     * express_company.company_name
     *
     * @param companyName 值对应 express_company.company_name
     *
     * @mbg.generated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }
}