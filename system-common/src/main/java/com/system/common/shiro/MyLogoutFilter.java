package com.system.common.shiro;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Description
 * @Author 陈葳
 * @Date 2019/4/15 14:43
 * @Version 1.0
 */
public class MyLogoutFilter extends LogoutFilter {
    private static final Logger log = LoggerFactory.getLogger(MyLogoutFilter.class);
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = this.getSubject(request, response);
        String redirectUrl = this.getRedirectUrl(request, response, subject);
        String sessionId = subject.getSession().getId().toString();
        try {
            subject.logout();
        } catch (SessionException var6) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", var6);
        }
        ShiroUtils.removeSubject(sessionId);
        this.issueRedirect(request, response, redirectUrl);
        return false;
    }
}