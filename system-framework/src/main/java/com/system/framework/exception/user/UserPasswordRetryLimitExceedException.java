package com.system.framework.exception.user;

import com.system.common.exception.base.BaseException;

/**
 * 用户错误最大次数异常类
 * @author system
 */
public class UserPasswordRetryLimitExceedException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitExceedException(int retryLimitCount) {
        super("user.password.retry.limit.exceed",null, new Object[]{retryLimitCount});
    }
}
