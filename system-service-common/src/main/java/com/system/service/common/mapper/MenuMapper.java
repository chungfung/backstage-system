package com.system.service.common.mapper;

import com.system.service.common.domain.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MenuMapper {

    List<MenuVO> select(MenuVO menuVO);

    MenuVO selectByMenuId(@Param("menuId") String menuId);

    int insert(MenuVO menuVO);

    int update(MenuVO menuVO);

    int deleteByMenuId(@Param("menuId") String menuId);

    List<Map<String,Object>> selectRoleMenu(@Param("roleId") String roleId);

    MenuVO selectByMenuName(@Param("menuName") String menuName);

    int judgeMenuExistsByMenuName(@Param("menuName") String menuName);

    /**
     * 通过角色列表获取用户菜单
     *
     * @param userId
     * @return
     */
    List<MenuVO> selectMenusByUserId(@Param("userId") String userId);

}