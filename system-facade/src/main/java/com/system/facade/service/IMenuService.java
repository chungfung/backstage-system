package com.system.facade.service;

import com.system.facade.vo.MenuVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: cw
 * @Date: 2018/10/15 18:08
 * @Description:
 */
public interface IMenuService {

    List<MenuVO> queryMenuList(MenuVO menuVO);

    MenuVO queryMenuById(String menuId);

    String editMenu(MenuVO menuVO);

    String delete(String menuId);

    List<Map<String,Object>> queryRoleMenu(String roleId);

    MenuVO queryByName(String name);

    Set<String> queryPermsByUserId(String userId);
}
