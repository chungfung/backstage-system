package com.system.framework.exception.user;

import com.system.common.exception.base.BaseException;

/**
 * 用户账号已被删除
 * @author system
 */
public class UserDeleteException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserDeleteException() {
        super("user.password.delete", null);
    }
}
