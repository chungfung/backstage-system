package com.system.facade.service;

import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.facade.vo.RoleVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    Set<String> queryRoleKeys(String userId);
}
