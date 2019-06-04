package com.system.common.annotation;

import com.system.common.enums.LogEnums;
import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 *
 * @author ruoyi
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    LogEnums.BusinessType businessType() default LogEnums.BusinessType.OTHER;

    /**
     * 操作人类别
     */
    LogEnums.OperatorType operatorType() default LogEnums.OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
