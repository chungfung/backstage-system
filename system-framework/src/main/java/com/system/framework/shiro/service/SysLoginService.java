package com.system.framework.shiro.service;

import com.system.common.constant.ShiroConstants;
import com.system.common.constant.UserConstants;
import com.system.common.enums.UserStatus;
import com.system.common.exception.user.CaptchaException;
import com.system.common.exception.user.UserBlockedException;
import com.system.common.exception.user.UserNotExistsException;
import com.system.common.exception.user.UserPasswordNotMatchException;
import com.system.common.utils.ServletUtils;
import com.system.service.common.domain.UserVO;
import com.system.service.common.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 登录校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysLoginService
{
    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private IUserService userService;

    /**
     * 登录
     */
    public UserVO login(String username, String password)
    {
        // 验证码校验
        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        UserVO user = userService.queryUserByName(username);


        if (user == null)
        {
            throw new UserNotExistsException();
        }
        
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            throw new UserBlockedException();
        }

        passwordService.validate(user, password);
        return user;
    }
}
