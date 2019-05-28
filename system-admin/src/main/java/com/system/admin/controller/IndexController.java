package com.system.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author 丰涌
 * @Date 2019/5/28 11:35
 * @Version 1.0
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/")
    public String home(){
        return "index";
    }

    @RequestMapping(value = "/403")
    public String unAuthorization(){
        return "403";
    }

    @RequestMapping(value = "/404")
    public String notFound(){
        return "404";
    }
}
