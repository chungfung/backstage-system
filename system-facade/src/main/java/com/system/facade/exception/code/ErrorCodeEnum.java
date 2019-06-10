package com.system.facade.exception.code;
/**
 * 返回错误编码
 * @author 未知
 */
public enum ErrorCodeEnum {

    SYS_OPR_ERR(700, "系统操作异常"),
    FILE_OPR_ERR(701,"文件操作异常"),
    MD5_CREATE_ERR(702,"生成文件md5异常"),
    REQ_FILE_NOT_EXIST(703,"请求文件不存在"),
    UPLOAD_CONTENT_ERR(704,"上传内容出错"),
    UPLOAD_CONTENT_BLANK(705,"上传内容空"),
    GET_PROPERTIE_ERR(706,"获取配置信息出错"),
    DB_DATA_ERR(707,"数据表中数据出错"),
    BUSINESS_DEAL_ERR(708,"业务处理异常"),
    PARAM_NOT_ALL(709,"最新参数不全"),
    DB_INSERT_ERR(710, "数据插入异常"),
    DB_SQL_ERR(711, "SqlException异常"),
    FILE_MAX_NUM_EXCEED(712,"文件内容超过最大数100"),
    DB_BATCH_ERR(713,"批量插入异常"),
    PARAM_FILE_NOT_EXIST(714,"参数文件不存在"),
    DATA_DB_ERR(715,"数据库操作异常无明细"),
    PARAM_SET_ERR(716,"参数设置异常"),
    DB_OPR_ERR(740, "数据库操作异常"),
    DB_DUPLICATE_ERR(742, "重复"),
    SQUAD_DATE_NE_ERR(743, "工班日期不一致"),
    VIRTUAL_TABLE_NAME_ERR(744, "文件名中的虚拟表名错误"),

    NOT_SUPPORT_FILE(945,"不支持此类文件"),
    RES_FILE_NOT_EXIST(946,"响应文件不存在"),
    DATA_FORMAT_ERR(947,"请求数据格式错误"),
    FILE_FORMAT_ERR(948,"请求文件名格式不正确"),
    NOT_DEFINE_FILE(949,"协议中未定义此类文件名"),
    AUTH_ERR(950,"鉴权失败"),
    MD5_VALID_ERR(951,"文件md5校验失败"),
    FORMAT_VALID_ERR(952,"格式校验失败"),
    FILE_NULL( 953,"请求文件为空"),
    ERR_UNKOWN(999,"未知错误");

    /** 编码 */
    private final int code;

    /** 描述 */
    private final String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return this.code;
    }

    public String getMsg() {
        return msg;
    }
}