package com.system.facade.service;

import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.facade.vo.MenuVO;
import com.system.facade.vo.RoleMenuVO;

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
