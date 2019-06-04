package com.system.facade.vo;

import com.system.common.valid.CustomLenthCheck;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RoleVO {

    private String roleId;

    @NotNull(message = "角色名称不能为空")
    @CustomLenthCheck(max=30,message = "角色名称过长")
    private String roleName;

    private Short status;

    @NotNull(message = "角色说明不能为空")
    @CustomLenthCheck(max = 200, message = "角色说明过长")
    private String roleRemark;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

}