package com.azz.order.selection.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ShoppingCartItemInfo{

    private String productCode;

    private String paramsValue;

    private String assortmentName;

    private String brandName;

    private Integer deliveryDate;

    private String moduleName;

    private String modulePicUrl;

    private BigDecimal productPrice;

}