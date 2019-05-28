package com.system.service.common.domain;

import lombok.Data;

import java.util.Date;

@Data
public class RoleMenuVO {
    private String id;

    private String roleId;

    private String menuId;

    private String systemType;

    private Date createTime;

    private String createUser;

    private String updateUser;

    private Date updateTime;
}

