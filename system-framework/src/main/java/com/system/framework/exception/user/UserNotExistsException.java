package com.system.framework.exception.user;

import com.system.common.exception.base.BaseException;

/**
 * 用户不存在异常类
 * @author system
 */
public class UserNotExistsException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserNotExistsException() {
        super("user.not.exists", null);
    }
}
