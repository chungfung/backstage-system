package com.system.service.common.service;

import com.system.framework.page.PageBean;
import com.system.framework.page.PageParam;
import com.system.service.common.domain.UserRoleVO;

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
