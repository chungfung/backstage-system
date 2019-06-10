package com.system.framework.exception.user;

import com.system.common.exception.base.BaseException;

/**
 * 用户锁定异常类
 * @author system
 */
public class UserBlockedException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserBlockedException() {
        super("user.blocked", null);
    }
}
