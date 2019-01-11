package com.azz.platform.merchant.pojo;


import java.io.Serializable;

public class MerchantGoodsProductParams implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 参数项名称
     *
     * @mbg.generated
     */
    private String paramsName;

    /**
     * 参数项ID
     *
     * @mbg.generated
     */
    private Long paramsId;

    /**
     * 参数值
     *
     * @mbg.generated
     */
    private String paramsValue;

    /**
     * 产品ID
     *
     * @mbg.generated
     */
    private Long productId;
    
    private Byte paramsType;
    
    private Byte paramsChoice;
    
    private Byte paramsHidden;
    
    private Long paramsTermId;
    

    public Long getParamsTermId() {
		return paramsTermId;
	}

	public void setParamsTermId(Long paramsTermId) {
		this.paramsTermId = paramsTermId;
	}

	public Byte getParamsHidden() {
		return paramsHidden;
	}

	public void setParamsHidden(Byte paramsHidden) {
		this.paramsHidden = paramsHidden;
	}

	private static final long serialVersionUID = 1L;

    /**
     * 主键<br/>
     * 返回值对应的表列名 merchant_goods_product_params.id
     *
     * @return 返回值对应 merchant_goods_product_params.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键<br/>
     * merchant_goods_product_params.id
     *
     * @param id 值对应 merchant_goods_product_params.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 参数项名称<br/>
     * 返回值对应的表列名 merchant_goods_product_params.params_name
     *
     * @return 返回值对应 merchant_goods_product_params.params_name
     *
     * @mbg.generated
     */
    public String getParamsName() {
        return paramsName;
    }

    /**
     * 参数项名称<br/>
     * merchant_goods_product_params.params_name
     *
     * @param paramsName 值对应 merchant_goods_product_params.params_name
     *
     * @mbg.generated
     */
    public void setParamsName(String paramsName) {
        this.paramsName = paramsName == null ? null : paramsName.trim();
    }

    /**
     * 参数项ID<br/>
     * 返回值对应的表列名 merchant_goods_product_params.params_id
     *
     * @return 返回值对应 merchant_goods_product_params.params_id
     *
     * @mbg.generated
     */
    public Long getParamsId() {
        return paramsId;
    }

    /**
     * 参数项ID<br/>
     * merchant_goods_product_params.params_id
     *
     * @param paramsId 值对应 merchant_goods_product_params.params_id
     *
     * @mbg.generated
     */
    public void setParamsId(Long paramsId) {
        this.paramsId = paramsId;
    }

    /**
     * 参数值<br/>
     * 返回值对应的表列名 merchant_goods_product_params.params_value
     *
     * @return 返回值对应 merchant_goods_product_params.params_value
     *
     * @mbg.generated
     */
    public String getParamsValue() {
        return paramsValue;
    }

    /**
     * 参数值<br/>
     * merchant_goods_product_params.params_value
     *
     * @param paramsValue 值对应 merchant_goods_product_params.params_value
     *
     * @mbg.generated
     */
    public void setParamsValue(String paramsValue) {
        this.paramsValue = paramsValue == null ? null : paramsValue.trim();
    }

    /**
     * 产品ID<br/>
     * 返回值对应的表列名 merchant_goods_product_params.product_id
     *
     * @return 返回值对应 merchant_goods_product_params.product_id
     *
     * @mbg.generated
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 产品ID<br/>
     * merchant_goods_product_params.product_id
     *
     * @param productId 值对应 merchant_goods_product_params.product_id
     *
     * @mbg.generated
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

	public Byte getParamsType() {
		return paramsType;
	}

	public void setParamsType(Byte paramsType) {
		this.paramsType = paramsType;
	}

	public Byte getParamsChoice() {
		return paramsChoice;
	}

	public void setParamsChoice(Byte paramsChoice) {
		this.paramsChoice = paramsChoice;
	}

	
   
}