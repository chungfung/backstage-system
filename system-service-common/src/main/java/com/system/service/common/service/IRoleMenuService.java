package com.system.service.common.service;

import com.system.framework.page.PageBean;
import com.system.framework.page.PageParam;
import com.system.service.common.domain.MenuVO;
import com.system.service.common.domain.RoleMenuVO;

import java.util.List;

/**
 * @Author: cw
 * @Date: 2018/10/15 18:08
 * @Description:
 */
public interface IRoleMenuService {

    PageBean<RoleMenuVO> queryByRoleId(String roleId, PageParam pageParam);

    String setMenu(String roleId, String menuIds);

    List<MenuVO> selectByUserId(String userId);

}
