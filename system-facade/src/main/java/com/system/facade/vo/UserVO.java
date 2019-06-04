package com.system.facade.vo;

import com.system.common.valid.CustomLenthCheck;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserVO {

    private String userId;

    @NotNull(message = "登录名不能为空")
    @CustomLenthCheck(max=30,message = "登录名长度过长")
    private String loginName;

    @NotNull(message = "用户名称不能为空")
    @CustomLenthCheck(max = 30, message = "用户名称过长")
    private String fullName;

    private String password;

    private String authTicket;

    private String roadNo;
    private String roadName;

    private String stationNo;
    private String stationName;

    @Length(max = 11, message = "手机号码过长")
    private String mobile;

    private Integer status;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private String isAdmin;
}