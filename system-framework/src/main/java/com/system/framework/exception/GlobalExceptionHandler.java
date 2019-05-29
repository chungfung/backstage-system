package com.system.framework.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 * @author system
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(Exception ex,HttpServletRequest req) {
        if(req.getHeader("X-Requested-With")!=null
                && "XMLHttpRequest".equals(req.getHeader("X-Requested-With").toString())) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code", 500);
            map.put("msg", ex.getMessage());
            return map;
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("error/index");
            return mav;
        }
    }
}
