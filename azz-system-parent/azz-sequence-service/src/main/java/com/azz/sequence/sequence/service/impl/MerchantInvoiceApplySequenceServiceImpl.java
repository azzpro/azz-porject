/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 上午11:50:22
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.sequence.sequence.service.impl;

import java.util.Calendar;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.azz.sequence.config.SequenceConfig;
import com.azz.sequence.sequence.service.MerchantInvoiceApplySequenceService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月20日 上午9:54:11
 */
@Service
public class MerchantInvoiceApplySequenceServiceImpl implements MerchantInvoiceApplySequenceService{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
     * 序列哈片段长度
     */
    private static int length = 10;
    @Override
	public void init(SequenceConfig sequenceConfig) throws Exception {
		Map<String, Object> args = sequenceConfig.getArgs();
        if (args.containsKey("length")) {
            this.length = Integer.parseInt((String) args.get("length"));
        } else {
            logger.info("length is not configured, use default value " + this.length);
        }
	}

	@Override
	public String getSequence() {
		StringBuilder sb = new StringBuilder();
		sb.append("MB")
		.append(getYear()).append(getMonth()).append(getDay());
        if (sb.toString().length() != length) {
            throw new RuntimeException("the length of " + sb.toString() + " is illegal!");
        }
        return sb.toString();
	}

	@Override
	public String getType() {
		return "ClientInvoiceApplyCodeSequence";
	}
	
	

    public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	private String getYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String years = year+"";
		return years.substring(years.length()-1,years.length());
	}
	
	private String getMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		return month+"";
	}
	
	private String getDay() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		return day+"";
	}
}

