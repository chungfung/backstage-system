package com.system.service.common.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SysOperLogVO {

    private BigDecimal id;

    private String title;

    private Integer businessType;

    private String method;

    private Integer operatorType;

    private String operName;

    private String operUrl;

    private String operIp;

    private String operLocation;

    private String operParam;

    private Integer status;

    private String errorMsg;

    private String operTime;

}