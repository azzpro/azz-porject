/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 上午9:18:34
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.azz.core.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.azz.core.common.errorcode.BaseErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.exception.BaseException;

import lombok.extern.slf4j.Slf4j;


/**
 * <P>异常捕获处理器</P>
 * @version 1.0
 * @author 黄智聪（13510946256）  2018年8月31日 下午1:53:50
 */
@Slf4j
public class BaseExceptionHandler {

    /**
     * 
     * <p>处理异常</p>
     * @param e
     * @return
     * @author 黄智聪（13510946256）  2018年8月31日 下午1:56:18
     */
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception e) {
        Map<String, Object> errorResult = new HashMap<>();
        int code = 0;
        String detailMessage = null;
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            BaseErrorCode errorCode = baseException.getErrorCode();
            code = errorCode.getCode();
            detailMessage = errorCode.getErrorType() + ":" + e.getMessage();
            log.error(detailMessage, e);
        } else {// 非BaseException的异常均视为系统未知错误
            BaseErrorCode errorCode = SystemErrorCode.SYS_ERROR_UNKNOWN;
            code = errorCode.getCode();
            detailMessage = errorCode.getErrorType() + ":" + errorCode.toString();
            log.error(detailMessage, e);
        }
        errorResult.put("code", code);
        errorResult.put("msg", detailMessage);
        return errorResult;
    }
    
}

