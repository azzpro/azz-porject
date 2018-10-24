package com.azz.sequence.base.service;

import com.azz.core.sequence.core.SystemServiceFactory;
import com.azz.sequence.config.SequenceConfig;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月24日 上午11:38:17
 */
public class GenerateSequenceService {
    public static SystemSequenceService getSequence(SequenceConfig SequenceConfig) {
    	SystemSequenceService sequence = SystemServiceFactory.getService(SequenceConfig.getType(), SystemSequenceService.class);
        return sequence;
    }

    public static <T> T getSequence(SequenceConfig SequenceConfig, Class<T> classType) {
        T sequence = SystemServiceFactory.getService(SequenceConfig.getType(), classType);
        return sequence;
    }
}
