package com.system.common.valid;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @Description 自定义长度检查
 * @Author 丰涌
 * @Date 2018/11/22 15:17
 * @Version 1.0
 */
@Constraint(validatedBy = CustomLenthCheckDeal.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomLenthCheck {

    int max() default Integer.MAX_VALUE;

    int min() default 0;

    String message() default "长度错误！";


    Class<?>[] groups() default {};

    Class<? extends javax.validation.Payload>[] payload() default {};
}
