package com.system.framework.exception.user;

import com.system.common.exception.base.BaseException;

/**
 * 用户错误记数异常类
 * @author system
 */
public class UserPasswordRetryLimitCountException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitCountException(int retryLimitCount) {
        super("user.password.retry.limit.count", null, new Object[]{retryLimitCount});
    }
}
