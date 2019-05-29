package com.system.common.valid;

import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: 丰涌
 * @Date: 2018/10/26 16:28
 * @Description:
 */
public class ValidationUtils {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> ValidationResult validateEntity(T obj) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setHasErrors(true);
            List<String> errorMsg = new ArrayList<String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.add(cv.getMessage());
            }
            result.setErrorMsg(String.join(",", errorMsg));
        }
        return result;
    }

    public static <T> ValidationResult validateProperty(T obj, String propertyName) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setHasErrors(true);
            List<String> errorMsg = new ArrayList<String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.add(cv.getMessage());
            }
            result.setErrorMsg(String.join(",", errorMsg));
        }
        return result;
    }
}
