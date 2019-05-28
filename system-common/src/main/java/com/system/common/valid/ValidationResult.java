package com.system.common.valid;

/**
 * @Author: 丰涌
 * @Date: 2018/10/26 16:27
 * @Description:
 */
public class ValidationResult {

    //校验结果是否有错
    private boolean hasErrors;

    //校验错误信息
    private String errorMsg;

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg="
                + errorMsg + "]";
    }
}
