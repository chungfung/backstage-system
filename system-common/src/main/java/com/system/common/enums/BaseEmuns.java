package com.system.common.enums;


/**
 * 财务管理系统基础枚举接口
 * @author 丰涌
 * @date 2018-10-24
 */
public interface BaseEmuns<K,V> {

    K getCode();

    V getDesc();
}
