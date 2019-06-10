package com.system.common.exception;

import com.system.common.exception.base.BaseException;
import com.system.common.exception.code.ErrorCode;

/**
 * @Description
 * @Author ChungFung
 * @Date 2019/6/10 11:03
 * @Version 1.0
 */
public class SystemException extends BaseException{

    /** 系统异常 */
    public static SystemException SYSTEM_EXCEPTION = new SystemException(ErrorCode.CODE_00001);

    public SystemException(String code) {
        super(code);
    }
}
