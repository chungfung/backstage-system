package com.system.framework.exception.user;

import com.system.common.exception.base.BaseException;

/**
 * 角色锁定异常类
 * @author system
 */
public class RoleBlockedException extends BaseException {

    private static final long serialVersionUID = 1L;

    public RoleBlockedException() {
        super("role.blocked", null);
    }
}
