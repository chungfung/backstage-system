package com.system.admin.controller;

import com.system.common.utils.ServletUtils;
import com.system.common.utils.StringUtils;
import com.system.common.utils.spring.SpringUtils;
import com.system.framework.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Controller
public class LoginController extends BaseController
{
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"301\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ModelAndView ajaxLogin(String loginName, String password, Boolean rememberMe,HttpServletRequest request,
                                  HttpServletResponse response)
    {
        ModelAndView view = new ModelAndView("/login");
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password, StringUtils.isNull(rememberMe)?false:rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            WebUtils.redirectToSavedRequest(request, response, SpringUtils.getBean(ShiroFilterFactoryBean.class).getSuccessUrl());
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            view.addObject("msg",msg);
            view.addObject("loginName", loginName);
            return view;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "403";
    }
}
