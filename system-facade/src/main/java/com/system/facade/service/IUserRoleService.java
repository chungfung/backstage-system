package com.system.facade.service;

import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.facade.vo.UserRoleVO;

import java.util.List;

/**
 * @Author: cw
 * @Date: 2018/10/15 18:08
 * @Description:
 */
public interface IUserRoleService {

    PageBean<UserRoleVO> queryByUserId(String userId, PageParam pageParam);

    String setRole(String userId, String roleIds);

    List<UserRoleVO> queryByUserId(String userId);

    int delete(String userId, String roleId);

}
