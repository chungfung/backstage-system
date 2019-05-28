package com.system.service.common.mapper;

import com.system.service.common.domain.MenuVO;
import com.system.service.common.domain.RoleMenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuMapper {
    List<RoleMenuVO> selectByRoleId(@Param("roleId") String roleId);

    int deleteByRoleId(@Param("roleId") String roleId);

    int deleteNotIn(@Param("roleId") String roleId, @Param("menuIds") List<String> menuId);

    int insert(RoleMenuVO roleMenuVO);

    List<MenuVO> selectByUserId(@Param("userId") String userId);
}