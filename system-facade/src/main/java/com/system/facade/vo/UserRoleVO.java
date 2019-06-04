package com.system.facade.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserRoleVO {
    private String id;

    private String userId;

    private String roleId;

    private String roleName;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;



}