package com.system.framework.exception.user;

import com.system.common.exception.base.BaseException;

/**
 * 验证码错误异常类
 * @author system
 */
public class CaptchaException extends BaseException {

    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }
}
