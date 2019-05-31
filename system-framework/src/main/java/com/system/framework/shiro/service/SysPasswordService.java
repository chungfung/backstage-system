package com.system.framework.shiro.service;

import com.system.common.exception.user.UserPasswordNotMatchException;
import com.system.common.exception.user.UserPasswordRetryLimitExceedException;
import com.system.service.common.domain.UserVO;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 登录密码方法
 * 
 * @author ruoyi
 */
@Component
public class SysPasswordService
{
    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @PostConstruct
    public void init()
    {
        loginRecordCache = cacheManager.getCache("loginRecordCache");
    }

    public void validate(UserVO user, String password)
    {
        String loginName = user.getLoginName();

        AtomicInteger retryCount = loginRecordCache.get(loginName);

        if (retryCount == null)
        {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue())
        {
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }

        if (!matches(user, password))
        {
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        }
        else
        {
            clearLoginRecordCache(loginName);
        }
    }

    public boolean matches(UserVO user, String newPassword)
    {
        String p = encryptPassword(user.getLoginName(), newPassword);
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword));
    }

    public void clearLoginRecordCache(String username)
    {
        loginRecordCache.remove(username);
    }

    public String encryptPassword(String username, String password)
    {
        return new Md5Hash(new SimpleByteSource(password), username,2).toHex().toString();
    }

}
