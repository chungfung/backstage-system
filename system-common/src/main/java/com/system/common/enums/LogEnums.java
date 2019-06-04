package com.system.common.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @Author 丰涌
 * @Date 2019/6/4 09:52
 * @Version 1.0
 */
public interface LogEnums {
    /**
     * 业务操作类型
     * @author ruoyi
     */
    enum BusinessType implements BaseEmuns<Integer, String> {
        /**
         * 其它
         */
        OTHER(1, "其他"),

        /**
         * 新增
         */
        INSERT(2, "新增"),

        /**
         * 修改
         */
        UPDATE(3, "修改"),

        /**
         * 删除
         */
        DELETE(4, "删除");

        /**
         * 类型值
         */
        @Getter
        @Setter
        private Integer code;

        /**
         * 类型值描述
         */
        @Getter
        @Setter
        private String desc;

        BusinessType(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static BusinessType getBusinessType(Integer code) {
            for (BusinessType type : values()) {
                if (type.code.equals(code)) {
                    return type;
                }
            }
            return null;
        }

        public static String getBusinessTypeDesc(Integer code) {
            BusinessType businessType = getBusinessType(code);
            return businessType == null ? null:businessType.getDesc();
        }
    }

    /**
     * 操作人类别
     */
    enum OperatorType implements BaseEmuns<Integer, String> {

        /**
         * 其它
         */
        OTHER(1, "其他"),

        /**
         * 后台用户
         */
        MANAGE(2, "后台用户"),

        /**
         * 手机端用户
         */
        MOBILE(3, "手机端用户");

        /**
         * 类型值
         */
        @Getter
        @Setter
        private Integer code;

        /**
         * 类型值描述
         */
        @Getter
        @Setter
        private String desc;

        OperatorType(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static OperatorType getOperatorType(Integer code) {
            for (OperatorType type : values()) {
                if (type.code.equals(code)) {
                    return type;
                }
            }
            return null;
        }

        public static String getOperatorTypeDesc(Integer code) {
            OperatorType operatorType = getOperatorType(code);
            return operatorType == null ? null:operatorType.getDesc();
        }
    }

    /**
     * 操作状态
     */
    enum Status implements BaseEmuns<Integer, String> {

        /**
         * 异常
         */
        EXCEPTION(1, "异常"),

        /**
         * 正常
         */
        NORMAL(2, "正常");

        /**
         * 类型值
         */
        @Getter
        @Setter
        private Integer code;

        /**
         * 类型值描述
         */
        @Getter
        @Setter
        private String desc;

        Status(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static Status getStatus(Integer code) {
            for (Status type : values()) {
                if (type.code.equals(code)) {
                    return type;
                }
            }
            return null;
        }

        public static String getStatusDesc(Integer code) {
            Status status = getStatus(code);
            return status == null ? null:status.getDesc();
        }
    }
}
