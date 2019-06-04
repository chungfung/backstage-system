package com.system.service.common.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SysOperLogVO {

    private Long id;

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

    // 拓展字段
    private String businessTypeLabel;

    private String operatorTypeLabel;

    private String statusLabel;

    private String startDate;

    private String endDate;
}