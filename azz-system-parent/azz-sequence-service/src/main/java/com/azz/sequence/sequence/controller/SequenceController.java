/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午4:32:29
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.sequence.sequence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azz.sequence.sequence.service.ClientCompanySequence;
import com.azz.sequence.sequence.service.ClientDeptSequenceService;
import com.azz.sequence.sequence.service.ClientEmployeeSequence;
import com.azz.sequence.sequence.service.ClientGuestSequence;
import com.azz.sequence.sequence.service.ClientInvoiceApplySequenceService;
import com.azz.sequence.sequence.service.ClientPowerSequence;
import com.azz.sequence.sequence.service.MerchantDeptSequenceService;
import com.azz.sequence.sequence.service.MerchantEmployeeSequenceService;
import com.azz.sequence.sequence.service.MerchantInvoiceApplySequenceService;
import com.azz.sequence.sequence.service.MerchantPowerSequenceService;
import com.azz.sequence.sequence.service.MerchantTenantSequenceService;
import com.azz.sequence.sequence.service.OptionGroupSequence;
import com.azz.sequence.sequence.service.OptionPlanSequence;
import com.azz.sequence.sequence.service.OrderCompanySequence;
import com.azz.sequence.sequence.service.OrderMerchantSequence;
import com.azz.sequence.sequence.service.OrderPersonlSequence;
import com.azz.sequence.sequence.service.PlatDeptSequenceService;
import com.azz.sequence.sequence.service.PlatEmployeeSequenceService;
import com.azz.sequence.sequence.service.PlatPowerSequenceService;
import com.azz.sequence.sequence.service.ProductBrandSequence;
import com.azz.sequence.sequence.service.ProductClassSequence;
import com.azz.sequence.sequence.service.ProductMoudleSequence;
import com.azz.sequence.sequence.service.ProductParamSequence;
import com.azz.sequence.sequence.service.ProductParamTermSequence;
import com.azz.sequence.sequence.service.ProductSequence;
import com.azz.sequence.sequence.service.WxBrandSequence;
import com.azz.sequence.sequence.service.WxClassBeginSequence;
import com.azz.sequence.sequence.service.WxClassificationSequence;
import com.azz.sequence.sequence.service.WxCourseSequence;
import com.azz.sequence.sequence.service.WxParamTremSequence;
import com.azz.sequence.sequence.service.WxParamValueSequence;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月24日 下午4:32:29
 */
@RestController
@RequestMapping("/azz/api/sequence")
public class SequenceController {

	
	@Autowired
	private OrderCompanySequence orderCompanySequence;
	
	@Autowired
	private OrderPersonlSequence orderPersonlSequence;
	
	@Autowired
	private OrderMerchantSequence orderMerchantSequence;
	
	@Autowired
	private PlatDeptSequenceService platDeptSequenceService;
	
	@Autowired
	private PlatPowerSequenceService platPowerSequenceService;
	
	@Autowired
	private PlatEmployeeSequenceService platEmployeeSequenceService;
	
	@Autowired
	private MerchantDeptSequenceService merchantDeptSequenceService;
	
	@Autowired
	private MerchantPowerSequenceService merchantPowerSequenceService;
	
	@Autowired
	private MerchantTenantSequenceService merchantTenantSequenceService;
	
	@Autowired
	private MerchantEmployeeSequenceService merchantEmployeeSequenceService;
	
	@Autowired
    private ClientInvoiceApplySequenceService clientInvoiceApplySequenceService;
	
	@Autowired
    private MerchantInvoiceApplySequenceService merchantInvoiceApplySequenceService;
	
	@Autowired
	private ClientCompanySequence clientCompanySequence;
	
	@Autowired
	private ClientDeptSequenceService clientDeptSequenceService;
	
	@Autowired
	private ClientEmployeeSequence clientEmployeeSequence;
	
	@Autowired
	private ClientGuestSequence clientGuestSequence;
	
	@Autowired
	private ClientPowerSequence clientPowerSequence;
	
	@Autowired
	private OptionGroupSequence optionGroupSequence;
	
	@Autowired
	private OptionPlanSequence optionPlanSequence;
	
	@Autowired
	private ProductBrandSequence productBrandSequence;
	
	@Autowired
	private ProductClassSequence productClassSequence;
	
	@Autowired
	private ProductMoudleSequence productMoudleSequence;
	
	@Autowired
	private ProductParamTermSequence productParamTermSequence;
	
	@Autowired
	private ProductParamSequence productParamSequence;
	
	@Autowired
	private ProductSequence productSequence;
	
	@Autowired
	private WxBrandSequence wxBrandSequence;
	
	@Autowired
	private WxClassBeginSequence wxClassBeginSequence;
	
	@Autowired
	private WxClassificationSequence wxClassificationSequence;
	
	@Autowired
	private WxCourseSequence wxCourseSequence;
	
	@Autowired
	private WxParamTremSequence wxParamTremSequence;
	
	@Autowired
	private WxParamValueSequence wxParamValueSequence;
	
	
	/**
	 * <p>微信品牌</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="getWxBrandNumber",method=RequestMethod.GET)
	public String getWxBrandNumber() {
		return wxBrandSequence.getSequence();
	}
	
	/**
	 * <p>微信分类</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="getWxClassificationNumber",method=RequestMethod.GET)
	public String getWxClassificationNumber() {
		return wxClassificationSequence.getSequence();
	}
	
	/**
	 * <p>微信开课</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="getWxClassBeginNumber",method=RequestMethod.GET)
	public String getWxClassBeginNumber() {
		return wxClassBeginSequence.getSequence();
	}
	
	
	/**
	 * <p>微信参数项</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="getWxParamTremNumber",method=RequestMethod.GET)
	public String getWxParamTremNumber() {
		return wxParamTremSequence.getSequence();
	}
	
	/**
	 * <p>微信参数值</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="getWxParamValueNumber",method=RequestMethod.GET)
	public String getWxParamValueNumber() {
		return wxParamValueSequence.getSequence();
	}
	
	/**
	 * <p>微信课程</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="getWxCourseNumber",method=RequestMethod.GET)
	public String getWxCourseNumber() {
		return wxCourseSequence.getSequence();
	}
	
	
	/**
	 * <p>个人订单编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="getPersonlOrderNumber",method=RequestMethod.GET)
	public String getPersonlOrderNumber() {
		return orderPersonlSequence.getSequence();
	}
	
	/**
	 * <p>企业订单编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="getCompanyOrderNumber",method=RequestMethod.GET)
	public String getCompanyOrderNumber() {
		return orderCompanySequence.getSequence();
	}
	
	/**
	 * <p>商户订单编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="getMerchantOrderNumber",method=RequestMethod.GET)
	public String getMerchantOrderNumber() {
		return orderMerchantSequence.getSequence();
	}
	
	/**
	 * <p>客户端 客户编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="getClientCustomerNumber",method=RequestMethod.GET)
	public String getClientCustomerNumber() {
		return clientGuestSequence.getSequence();
	}
	
	/**
	 * <p>客户端 企业编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getClientCompanyNumber",method=RequestMethod.GET)
	public String getClientCompanyNumber() {
		return clientCompanySequence.getSequence();
	}
	
	/**
	 * <p>客户端 员工编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getClientEmployeeNumber",method=RequestMethod.GET)
	public String getClientEmployeeNumber() {
		return clientEmployeeSequence.getSequence();
	}
	
	/**
	 * <p>客户端 部门编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getClientDepartmentNumber",method=RequestMethod.GET)
	public String getClientDepartmentNumber() {
		return clientDeptSequenceService.getSequence();
	}
	
	/**
	 * <p>客户端 权限编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getClientPowerNumber",method=RequestMethod.GET)
	public String getClientPowerNumber() {
		return clientPowerSequence.getSequence();
	}
	
	/**
	 * <p>平台端  员工编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getPlatEmployeeNumber",method=RequestMethod.GET)
	public String getPlatEmployeeNumber() {
		return platEmployeeSequenceService.getSequence();
	}
	
	/**
	 * <p>平台端  部门编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getPlatDepartmentNumber",method=RequestMethod.GET)
	public String getPlatDepartmentNumber() {
		return platDeptSequenceService.getSequence();
	}
	
	/**
	 * <p>平台端  部门编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getPlatPowerNumber",method=RequestMethod.GET)
	public String getPlatPowerNumber() {
		return platPowerSequenceService.getSequence();
	}
	
	
	/**
	 * <p>商户端  员工编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getMerchantTenantNumber",method=RequestMethod.GET)
	public String getMerchantTenantNumber() {
		return merchantTenantSequenceService.getSequence();
	}
	
	/**
	 * <p>商户端  员工编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getMerchantEmployeeNumber",method=RequestMethod.GET)
	public String getMerchantEmployeeNumber() {
		return merchantEmployeeSequenceService.getSequence();
	}
	
	/**
	 * <p>商户端  部门编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getMerchantDepartmentNumber",method=RequestMethod.GET)
	public String getMerchantDepartmentNumber() {
		return merchantDeptSequenceService.getSequence();
	}
	
	/**
	 * <p>商户端  权限编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getMerchantPowerNumber",method=RequestMethod.GET)
	public String getMerchantPowerNumber() {
		return merchantPowerSequenceService.getSequence();
	}
	
	/**
	 * <p>分类编码</p>
	 * @return
	 * @author 彭斌  2018年10月31日 上午11:26:01
	 */
	@RequestMapping(value="getClassificationNumber",method=RequestMethod.GET)
    public String getClassificationNumber() {
        return productClassSequence.getSequence();
    }
	
	/**
	 * <p>品牌编号</p>
	 * @return
	 * @author 彭斌  2018年10月31日 上午11:35:35
	 */
	@RequestMapping(value="getBrandCodeNumber",method=RequestMethod.GET)
    public String getBrandCodeNumber() {
        return productBrandSequence.getSequence();
    }
	
	/**
	 * <p>参数项编号</p>
	 * @return
	 * @author 彭斌  2018年10月31日 上午11:35:56
	 */
	@RequestMapping(value="getParameterItemCodeNumber",method=RequestMethod.GET)
    public String getParameterItemCodeNumber() {
        return productParamTermSequence.getSequence();
    }
	
	/**
	 * <p>参数编号</p>
	 * @return
	 * @author 彭斌  2018年10月31日 上午11:35:56
	 */
	@RequestMapping(value="getParameterCodeNumber",method=RequestMethod.GET)
    public String getParameterCodeNumber() {
        return productParamSequence.getSequence();
    }
	
    /**
     * <p>产品编码</p>
     * @return
     * @author 彭斌  2018年10月31日 上午11:41:18
     */
    @RequestMapping(value="getProductCodeNumber",method=RequestMethod.GET)
    public String getProductCodeNumber() {
        return productSequence.getSequence();
    }
    
    /**
     * <p>模组编号</p>
     * @return
     * @author 彭斌  2018年10月31日 上午11:41:33
     */
    @RequestMapping(value="getModuleCodeNumber",method=RequestMethod.GET)
    public String getModuleCodeNumber() {
        return productMoudleSequence.getSequence();
    }
    
    /**
     * <p>方案编码</p>
     * @return
     * @author 彭斌  2018年11月7日 下午4:18:30
     */
    @RequestMapping(value="getCaseCodeNumber",method=RequestMethod.GET)
    public String getCaseCodeNumber() {
        return optionPlanSequence.getSequence();
    }
    
    /**
     * <p>推荐组合编码</p>
     * @return
     * @author 彭斌  2018年11月7日 下午4:18:33
     */
    @RequestMapping(value="getCombinationCodeNumber",method=RequestMethod.GET)
    public String getCombinationCodeNumber() {
        return optionGroupSequence.getSequence();
    }
    
    /**
     * <p>获取客户端发票申请编码</p>
     * @return
     * @author 彭斌  2018年11月20日 上午10:01:31
     */
    @RequestMapping(value="getClientInvoiceApplyNumber",method=RequestMethod.GET)
    public String getClientInvoiceApplyNumber() {
        return clientInvoiceApplySequenceService.getSequence();
    }
    
    /**
     * <p>获取商户端发票申请编码</p>
     * @return
     * @author 彭斌  2018年11月20日 上午10:01:34
     */
    @RequestMapping(value="getMerchantInvoiceApplyNumber",method=RequestMethod.GET)
    public String getMerchantInvoiceApplyNumber() {
        return merchantInvoiceApplySequenceService.getSequence();
    }
    
}

