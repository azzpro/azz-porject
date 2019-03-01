package com.azz.order.client.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * @author THINK
 * 子商户入网参数【个人】
 */
@Data
public class Personreginfo {
	@NotBlank(message="入网请求号不能为空")
	private String requestNo;//入网请求号
	@NotBlank(message="代理商编号不能为空")
	private String parentMerchantNo;//代理商编号
	@NotBlank(message="法人姓名不能为空")
	private String legalName;//法人姓名
	@NotBlank(message="法人身份证号不能为空")
	private String legalIdCard;//法人身份证号
	@NotBlank(message="商户法人手机不能为空")
	private String merLegalPhone;//商户法人手机
	private String merLegalEmail;//法人邮箱 【非必传】
	private String merLevel1No;//商户一级分类 【非必传】
	private String merLevel2No;//商户二级分类 【非必传】
	@NotBlank(message="商户所在省不能为空")
	private String merProvince;//商户所在省
	@NotBlank(message="商户所在市不能为空")
	private String merCity;//商户所在市
	@NotBlank(message="商户所在区不能为空")
	private String merDistrict;//商户所在区
	@NotBlank(message="商户所在详细地址不能为空")
	private String merAddress;//商户所在详细地址
	private String merScope;//商户经营范围 【非必传】
	@NotBlank(message="结算银行卡号不能为空")
	private String cardNo;//结算银行卡号
	@NotBlank(message="开户总行编码不能为空")
	private String headBankCode;//开户总行编码
	private String bankCity;//开户市【非必传】
	private String bankCode;//开户支行编码【非必传】
	private String bankProvince;//开户省【非必传】
	private String productInfo;//开通产品信息【非必传】
	private String fileInfo;//资质影印件信息
	private String businessFunction;//业务功能【非必传】
	private String merAuthorizeType;//授权类型【非必传】
	private String merShortName;//商户简称【非必传】
}
