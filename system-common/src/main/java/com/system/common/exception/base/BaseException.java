package com.system.common.exception.base;

import com.system.common.utils.MessageUtils;
import com.system.common.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName BaseExceptionErrorCode
 * @Description 服务层异常基类
 * @Author ChungFung
 * @Date 2018\11\13 0008 10:10
 * @Version 1.0
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String UNKONWN = "未知错误！";

    @Getter
    @Setter
    private String code;

    @Setter
    private String msg;

    @Getter
    @Setter
    private Object[] args;

    public BaseException(String code, String msg, Object... args) {
        this.code = code;
        this.msg = msg;
        this.args = args;
    }

    public BaseException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String code) {
        this.code = code;
        this.msg = null;
    }

    public BaseException() {
        this.code = null;
        this.msg = null;
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(code)) {
            message = MessageUtils.message(code, args);
        }
        if (message == null) {
            message = msg;
        }
        if(message == null) {
            message = UNKONWN;
        }
        return message;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
