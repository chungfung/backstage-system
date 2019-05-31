package com.system.service.common.domain;

import lombok.Data;

@Data
public class SysDictDataVO {

    private Long id;

    private Short dictSort;

    private String dictLabel;

    private String dictValue;

    private String dictType;

    private String isDefault;

    private String status;

    private String createTime;

    private String createUser;

    private String updateTime;

    private String updateUser;

    private String remark;

}