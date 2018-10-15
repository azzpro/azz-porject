/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 上午9:18:34
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.azz.util;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import lombok.experimental.UtilityClass;

/**
 * <P>
 * 随机字符串工具类,基于Apache commons-text <a href=
 * "https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/RandomStringGenerator.html">
 * RandomStringGenerator</a>
 * </P>
 * 
 * @version 1.0
 * @author 王好（18675539583） 2017年11月6日 下午4:24:39
 */
@UtilityClass
public class RandomStringUtils {

    static final RandomStringGenerator NUMERIC =
            new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(CharacterPredicates.DIGITS).build();

    static final RandomStringGenerator LETTERS =
            new RandomStringGenerator.Builder().withinRange('A', 'z').filteredBy(CharacterPredicates.LETTERS).build();

    /**
     * 
     * <p>
     * 生成指定长度的随机数字
     * </p>
     * 
     * @param count
     * @return
     * @author 王好（18675539583） 2017年11月6日 下午4:27:09
     */
    public static String randomNumeric(final int count) {

        return NUMERIC.generate(count);
    }

    /**
     * <P>
     * 生成指定长度的字母
     * </P>
     * 
     * @version 1.0
     * @author 王好（18675539583） 2017/12/27 15:11
     * @param count
     * @return
     */
    public static String randomLetters(final int count) {
        return LETTERS.generate(count);
    }

}

