package com.system.service.common.domain;

import com.system.common.valid.CustomLenthCheck;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class MenuVO {

    private String menuId;

    @NotNull(message = "菜单名称不能为空")
    @CustomLenthCheck(max = 50, message = "菜单名称过长")
    private String name;

    private Integer parentId;

    @CustomLenthCheck(max = 100, message = "url地址过长")
    private String url;

    @NotNull(message = "权限不能为空")
    @CustomLenthCheck(max = 500, message = "权限过长")
    private String perms;

    @NotNull(message = "类型不能为空")
    @Digits(integer = 1, fraction = 0, message = "类型格式错误")
    private Integer type;

    @NotNull(message = "排序不能为空")
    @Digits(integer = 4, fraction = 0, message = "排序过大")
    private Integer orderNum;

    @NotNull(message = "所属系统不能为空")
    @Digits(integer = 1,fraction = 0,message = "所属系统格式错误")
    private Integer systemType;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}