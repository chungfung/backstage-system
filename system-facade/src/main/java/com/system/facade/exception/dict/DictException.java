package com.system.facade.exception.dict;

import com.system.common.exception.base.BaseException;
import com.system.facade.exception.code.ErrorCode;

/**
 * @Description
 * @Author 丰涌
 * @Date 2019/6/10 14:08
 * @Version 1.0
 */
public class DictException extends BaseException{

    public static DictException DICT_EXISTS_EXCEPTION = new DictException(ErrorCode.CODE_03001,"字典值已经存在！");
    public static DictException DICT_USED_EXCEPTION = new DictException(ErrorCode.CODE_03002,"字典值已分配,不能删除!");

    public DictException(String code) {
        super(code);
    }

    public DictException(String code, String msg) {
        super(code, msg);
    }
}
