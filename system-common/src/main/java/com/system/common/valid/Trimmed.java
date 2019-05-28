package com.system.common.valid;

import java.lang.annotation.*;

/**
 * @author 陈葳 2018/11/26 17:26
 * @Description
 */

@Target(value = {ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Trimmed {

    public static enum TrimmerType {
        SIMPLE, ALL_WHITESPACES, EXCEPT_LINE_BREAK;
    }

    TrimmerType value() default TrimmerType.SIMPLE;

}
