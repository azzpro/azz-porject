/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午4:20:14
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.sequence.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月24日 下午4:20:14
 */
@FeignClient("azz-sequence-service")
public interface DbSequenceService {
	
	
	/**
	 * <p>微信品牌</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="/azz/api/sequence/getWxBrandNumber",method=RequestMethod.GET)
	public String getWxBrandNumber() ;
	
	/**
	 * <p>微信分类</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="/azz/api/sequence/getWxClassificationNumber",method=RequestMethod.GET)
	public String getWxClassificationNumber() ;
	
	/**
	 * <p>微信开课</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="/azz/api/sequence/getWxClassBeginNumber",method=RequestMethod.GET)
	public String getWxClassBeginNumber() ;
	
	
	/**
	 * <p>微信参数项</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="/azz/api/sequence/getWxParamTremNumber",method=RequestMethod.GET)
	public String getWxParamTremNumber();
	
	/**
	 * <p>微信参数值</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="/azz/api/sequence/getWxParamValueNumber",method=RequestMethod.GET)
	public String getWxParamValueNumber() ;
	
	/**
	 * <p>微信课程</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="/azz/api/sequence/getWxCourseNumber",method=RequestMethod.GET)
	public String getWxCourseNumber() ;
	
	/**
	 * <p>个人订单编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="/azz/api/sequence/getPersonlOrderNumber",method=RequestMethod.GET)
	public String getPersonlOrderNumber() ;
	
	/**
	 * <p>企业订单编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="/azz/api/sequence/getCompanyOrderNumber",method=RequestMethod.GET)
	public String getCompanyOrderNumber() ;
	
	/**
	 * <p>商户订单编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="/azz/api/sequence/getMerchantOrderNumber",method=RequestMethod.GET)
	public String getMerchantOrderNumber() ;
	
	/**
	 * <p>客户端 客户编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="/azz/api/sequence/getClientCustomerNumber",method=RequestMethod.GET)
	public String getClientCustomerNumber() ;
	
	/**
	 * <p>客户端 企业编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="/azz/api/sequence/getClientCompanyNumber",method=RequestMethod.GET)
	public String getClientCompanyNumber() ;
	
	/**
	 * <p>客户端 员工编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="/azz/api/sequence/getClientEmployeeNumber",method=RequestMethod.GET)
	public String getClientEmployeeNumber() ;
	
	/**
	 * <p>客户端 部门编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="/azz/api/sequence/getClientDepartmentNumber",method=RequestMethod.GET)
	public String getClientDepartmentNumber() ;
	
	/**
	 * <p>客户端 权限编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="/azz/api/sequence/getClientPowerNumber",method=RequestMethod.GET)
	public String getClientPowerNumber() ;
	
	/**
	 * <p>平台端  员工编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="/azz/api/sequence/getPlatEmployeeNumber",method=RequestMethod.GET)
	public String getPlatEmployeeNumber() ;
	
	/**
	 * <p>平台端  部门编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="/azz/api/sequence/getPlatDepartmentNumber",method=RequestMethod.GET)
	public String getPlatDepartmentNumber() ;
	
	/**
	 * <p>平台端  部门编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="/azz/api/sequence/getPlatPowerNumber",method=RequestMethod.GET)
	public String getPlatPowerNumber() ;
	
	
	/**
	 * <p>商户端  商户编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="/azz/api/sequence/getMerchantTenantNumber",method=RequestMethod.GET)
	public String getMerchantTenantNumber() ;
	
	/**
	 * <p>商户端  员工编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="/azz/api/sequence/getMerchantEmployeeNumber",method=RequestMethod.GET)
	public String getMerchantEmployeeNumber() ;
	
	/**
	 * <p>商户端  部门编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="/azz/api/sequence/getMerchantDepartmentNumber",method=RequestMethod.GET)
	public String getMerchantDepartmentNumber() ;
	
	/**
	 * <p>商户端  权限编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="/azz/api/sequence/getMerchantPowerNumber",method=RequestMethod.GET)
	public String getMerchantPowerNumber() ;
	
	/**
	 * <p>分类编码</p>
	 * @return
	 * @author 彭斌  2018年10月31日 上午11:26:01
	 */
	@RequestMapping(value="/azz/api/sequence/getClassificationNumber",method=RequestMethod.GET)
    public String getClassificationNumber() ;
	
	/**
	 * <p>品牌编号</p>
	 * @return
	 * @author 彭斌  2018年10月31日 上午11:35:35
	 */
	@RequestMapping(value="/azz/api/sequence/getBrandCodeNumber",method=RequestMethod.GET)
    public String getBrandCodeNumber() ;
	
	/**
	 * <p>参数项编号</p>
	 * @return
	 * @author 彭斌  2018年10月31日 上午11:35:56
	 */
	@RequestMapping(value="/azz/api/sequence/getParameterItemCodeNumber",method=RequestMethod.GET)
    public String getParameterItemCodeNumber() ;
	
	/**
	 * <p>参数编号</p>
	 * @return
	 * @author 彭斌  2018年10月31日 上午11:35:56
	 */
	@RequestMapping(value="/azz/api/sequence/getParameterCodeNumber",method=RequestMethod.GET)
    public String getParameterCodeNumber() ;
	
    /**
     * <p>产品编码</p>
     * @return
     * @author 彭斌  2018年10月31日 上午11:41:18
     */
    @RequestMapping(value="/azz/api/sequence/getProductCodeNumber",method=RequestMethod.GET)
    public String getProductCodeNumber() ;
    
    /**
     * <p>模组编号</p>
     * @return
     * @author 彭斌  2018年10月31日 上午11:41:33
     */
    @RequestMapping(value="/azz/api/sequence/getModuleCodeNumber",method=RequestMethod.GET)
    public String getModuleCodeNumber() ;
    
    /**
     * <p>方案编码</p>
     * @return
     * @author 彭斌  2018年11月7日 下午4:18:30
     */
    @RequestMapping(value="/azz/api/sequence/getCaseCodeNumber",method=RequestMethod.GET)
    public String getCaseCodeNumber() ;
    
    /**
     * <p>推荐组合编码</p>
     * @return
     * @author 彭斌  2018年11月7日 下午4:18:33
     */
    @RequestMapping(value="/azz/api/sequence/getCombinationCodeNumber",method=RequestMethod.GET)
    public String getCombinationCodeNumber() ;
    
    /**
     * <p>获取客户端发票申请编码</p>
     * @return
     * @author 彭斌  2018年11月20日 上午10:01:31
     */
    @RequestMapping(value="/azz/api/sequence/getClientInvoiceApplyNumber",method=RequestMethod.GET)
    public String getClientInvoiceApplyNumber() ;
    
    /**
     * <p>获取商户端发票申请编码</p>
     * @return
     * @author 彭斌  2018年11月20日 上午10:01:34
     */
    @RequestMapping(value="/azz/api/sequence/getMerchantInvoiceApplyNumber",method=RequestMethod.GET)
    public String getMerchantInvoiceApplyNumber() ;
	
    /**
     * <p>获取微信课程订单编码序列</p>
     * @return
     * @author 彭斌  2019年2月18日 下午3:59:53
     */
    @RequestMapping(value="/azz/api/sequence/getWxCourseOrderSequenceNumber",method=RequestMethod.GET)
    public String getWxCourseOrderSequenceNumber();
    
    /**
     * <p>获取微信课程订单售后申请序列</p>
     * @return
     * @author 彭斌  2019年2月18日 下午4:02:32
     */
    @RequestMapping(value="/azz/api/sequence/getWxCourseOrderAfterSaleApplySequenceNumber",method=RequestMethod.GET)
    public String getWxCourseOrderAfterSaleApplySequenceNumber();
    
    /**
     * <p>获取微信课程订单退款申请编码</p>
     * @return
     * @author 彭斌  2019年2月18日 下午4:03:37
     */
    @RequestMapping(value="/azz/api/sequence/getWxCourseOrderRefundSequenceNumber",method=RequestMethod.GET)
    public String getWxCourseOrderRefundSequenceNumber();

    /**
     * <p>>微信课程订单提现申请编码</p>
     * @return
     * @author 彭斌  2019年2月18日 下午4:04:06
     */
    @RequestMapping(value="/azz/api/sequence/getWxCourseOrderWithdrawSequenceNumber",method=RequestMethod.GET)
    public String getWxCourseOrderWithdrawSequence();
    
}

