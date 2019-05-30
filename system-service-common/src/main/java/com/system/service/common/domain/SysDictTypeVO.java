package com.system.service.common.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SysDictTypeVO {

    private Long id;

    private String dictName;

    private String dictType;

    private String status;

    private String createTime;

    private String createUser;

    private String updateTime;

    private String updateUser;

    private String remark;

}