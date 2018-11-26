/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午3:43:55
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.sequence.sequence.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.azz.core.sequence.core.ThreadLocalRandom;
import com.azz.sequence.config.SequenceConfig;
import com.azz.sequence.sequence.service.SevenRandomSequenceService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月26日 下午3:43:55
 */
@Service
public class SevenRandomSequenceServiceImpl implements SevenRandomSequenceService{

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
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long minValue = getMinValue(length);
        long maxValue = getMaxValue(length);
        long segmentValue = random.nextLong(minValue, maxValue);
        String segmentString = String.valueOf(segmentValue);
        if (segmentString.length() != length) {
            throw new RuntimeException("the length of " + segmentString + " is illegal!");
        }
        return segmentString;
    }

    @Override
    public String getType() {
        return "OrderRandomSequence";
    }
    
    

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    /**
     * 获取指定位数的数据最小值
     *
     * @param length
     * @return
     */
    private long getMinValue(int length) {
        long minValue = 1;
        for (int i = 1; i < length; i++) {
            minValue *= 10;
        }
        return minValue;
    }

    /**
     * 获取指定位数的数据最大值
     *
     * @param length
     * @return
     */
    private long getMaxValue(int length) {
        long maxValue = 0;
        long sumValue = 1;
        for (int i = 0; i < length; i++) {
            sumValue *= 10;
            maxValue = sumValue - 1;
        }
        return maxValue;
    }

}

