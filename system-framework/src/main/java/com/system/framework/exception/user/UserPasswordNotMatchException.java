package com.system.framework.exception.user;

import com.system.common.exception.base.BaseException;

/**
 * 用户密码不正确或不符合规范异常类
 * @author system
 */
public class UserPasswordNotMatchException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
