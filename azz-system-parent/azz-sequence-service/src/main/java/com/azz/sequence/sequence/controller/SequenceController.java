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

import com.azz.sequence.sequence.service.ClientInvoiceApplySequenceService;
import com.azz.sequence.sequence.service.MerchantDeptSequenceService;
import com.azz.sequence.sequence.service.MerchantEmployeeSequenceService;
import com.azz.sequence.sequence.service.MerchantInvoiceApplySequenceService;
import com.azz.sequence.sequence.service.MerchantPowerSequenceService;
import com.azz.sequence.sequence.service.MerchantTenantSequenceService;
import com.azz.sequence.sequence.service.PayRandomSequenceService;
import com.azz.sequence.sequence.service.PlatDeptSequenceService;
import com.azz.sequence.sequence.service.PlatEmployeeSequenceService;
import com.azz.sequence.sequence.service.PlatPowerSequenceService;
import com.azz.sequence.sequence.service.RandomSequenceService;
import com.azz.sequence.sequence.service.TenRandomSequenceService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月24日 下午4:32:29
 */
@RestController
@RequestMapping("/azz/api/sequence")
public class SequenceController {

	
	@Autowired
	private RandomSequenceService randomSequenceService;
	
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
    private TenRandomSequenceService tenRandomSequenceService;
	
	@Autowired
    private PayRandomSequenceService payRandomSequenceService;
	
	@Autowired
    private ClientInvoiceApplySequenceService clientInvoiceApplySequenceService;
	
	@Autowired
    private MerchantInvoiceApplySequenceService merchantInvoiceApplySequenceService;
	
	/**
	 * <p>支付流水号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="getPayCodeNumber",method=RequestMethod.GET)
	public String getPayCodeNumber() {
		return "IORP1"+payRandomSequenceService.getSequence();
	}
	
	/**
	 * <p>客户端 客户编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:36:33
	 */
	@RequestMapping(value="getClientCustomerNumber",method=RequestMethod.GET)
	public String getClientCustomerNumber() {
		return "IF"+randomSequenceService.getSequence();
	}
	
	/**
	 * <p>客户端 企业编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getClientCompanyNumber",method=RequestMethod.GET)
	public String getClientCompanyNumber() {
		return "IG"+randomSequenceService.getSequence();
	}
	
	/**
	 * <p>客户端 员工编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getClientEmployeeNumber",method=RequestMethod.GET)
	public String getClientEmployeeNumber() {
		return "IJ"+randomSequenceService.getSequence();
	}
	
	/**
	 * <p>客户端 部门编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getClientDepartmentNumber",method=RequestMethod.GET)
	public String getClientDepartmentNumber() {
		return "IK"+randomSequenceService.getSequence();
	}
	
	/**
	 * <p>客户端 权限编号</p>
	 * @return
	 * @author 刘建麟  2018年10月24日 下午4:37:16
	 */
	@RequestMapping(value="getClientPowerNumber",method=RequestMethod.GET)
	public String getClientPowerNumber() {
		return "IM"+randomSequenceService.getSequence();
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
        return "IGA" + tenRandomSequenceService.getSequence();
    }
	
	/**
	 * <p>品牌编号</p>
	 * @return
	 * @author 彭斌  2018年10月31日 上午11:35:35
	 */
	@RequestMapping(value="getBrandCodeNumber",method=RequestMethod.GET)
    public String getBrandCodeNumber() {
        return "IGB" + tenRandomSequenceService.getSequence();
    }
	
	/**
	 * <p>参数项编号</p>
	 * @return
	 * @author 彭斌  2018年10月31日 上午11:35:56
	 */
	@RequestMapping(value="getParameterItemCodeNumber",method=RequestMethod.GET)
    public String getParameterItemCodeNumber() {
        return "IGC" + tenRandomSequenceService.getSequence();
    }
	
    /**
     * <p>产品参数编号</p>
     * @return
     * @author 彭斌  2018年10月31日 上午11:38:14
     */
    @RequestMapping(value="getProductParameterCodeNumber",method=RequestMethod.GET)
    public String getProductParameterCodeNumber() {
        return "IGD" + tenRandomSequenceService.getSequence();
    }
    
    /**
     * <p>产品编码</p>
     * @return
     * @author 彭斌  2018年10月31日 上午11:41:18
     */
    @RequestMapping(value="getProductCodeNumber",method=RequestMethod.GET)
    public String getProductCodeNumber() {
        return "IGF" + tenRandomSequenceService.getSequence();
    }
    
    /**
     * <p>模组编号</p>
     * @return
     * @author 彭斌  2018年10月31日 上午11:41:33
     */
    @RequestMapping(value="getModuleCodeNumber",method=RequestMethod.GET)
    public String getModuleCodeNumber() {
        return "IGG" + tenRandomSequenceService.getSequence();
    }
    
    /**
     * <p>方案编码</p>
     * @return
     * @author 彭斌  2018年11月7日 下午4:18:30
     */
    @RequestMapping(value="getCaseCodeNumber",method=RequestMethod.GET)
    public String getCaseCodeNumber() {
        return "IRA" + randomSequenceService.getSequence();
    }
    
    /**
     * <p>推荐组合编码</p>
     * @return
     * @author 彭斌  2018年11月7日 下午4:18:33
     */
    @RequestMapping(value="getCombinationCodeNumber",method=RequestMethod.GET)
    public String getCombinationCodeNumber() {
        return "IRB" + randomSequenceService.getSequence();
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

