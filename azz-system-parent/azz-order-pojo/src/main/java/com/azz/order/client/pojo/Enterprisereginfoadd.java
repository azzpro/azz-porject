package com.azz.order.client.pojo;

import java.util.Date;

import lombok.Data;

/**
 * @author THINK
 * 子商户入网参数【企业】
 */
@Data
public class Enterprisereginfoadd {
	
	private Long id;
	private String merchantNo;//子商户编码
	private String merFullName;//商户全称
	private String merShortName;//商户简称
	private String merCertNo;//证件号
	private String merCertType;//证件类型
	private String legalName;//法人姓名
	private String legalIdCard;//法人身份证号
	private String merContactName;//商户联系人姓名
	private String merContactPhone;//商户联系人手机号
	private String merContactEmail;//商户联系人邮箱 【非必传】
	private String merLevelfNo;//商户一级分类 【非必传】
	private String merLevelsNo;//商户二级分类 【非必传】
	private String merProvince;//商户所在省
	private String merCity;//商户所在市
	private String merDistrict;//商户所在区
	private String merAddress;//商户所在详细地址
	private String taxRegistCert;//税务登记证编号 【非必传】
	private String accountLicense;//开户许可证编号
	private String orgCode;//组织机构代码证 【非必传】
	private String orgCodeExpiry;//组织机构代理证有效期 【非必传】
	private String isOrgCodeLong;//组织机构代码证是否长期有效【非必传】
	private String cardNo;//结算银行账户
	private String headBankCode;//开户银行编码
	private String bankCity;//开户市【非必传】
	private String bankCode;//开户支行编码
	private String bankProvince;//开户省【非必传】
	private String productInfo;//开通产品信息【非必传】
	private String requestNo;//入网请求号
	private String parentMerchantNo;//代理商编号
	private String businessFunction;//业务功能【非必传】
	private String merAuthorizeType;//授权类型【非必传】
	private String status;//0 未注册 1已注册
	private String legalFrontPic;//法人正面身份证照
	private String legalBackPic;//法人反面身份证照
	private String openAccountPic;//开户许可照片
	private String businessPic;//4证合一
	private String icpAuthPic;//ICP 授权许可照片
	private String creator;
	private Date createTime;
}
