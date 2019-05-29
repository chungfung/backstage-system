package com.system.service.common.service;

import com.system.framework.page.PageBean;
import com.system.framework.page.PageParam;
import com.system.service.common.domain.RoleVO;

import java.util.List;
import java.util.Map;

/**
 * @Author: 丰涌
 * @Date: 2018/10/15 18:08
 * @Description:
 */
public interface IRoleService {

    PageBean<RoleVO> queryRoleList(RoleVO roleVO, PageParam pageParam);

    RoleVO queryRoleDetailByRoleId(String roleId);

    RoleVO queryByRoleName(String roleName);

    String editRole(RoleVO roleVO);

    String delete(String roleId);

    List<Map<String,Object>> queryUserRole(String userId, RoleVO roleVO);
}
