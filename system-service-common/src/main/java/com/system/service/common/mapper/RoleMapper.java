package com.system.service.common.mapper;

import com.system.service.common.domain.RoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper {

    List<RoleVO> select(RoleVO roleVO);

    RoleVO selectByRoleId(@Param("roleId") String roleId);

    int insert(RoleVO roleVO);

    int update(RoleVO roleVO);

    int deleteByRoleId(@Param("roleId") String roleId);

    List<Map<String,Object>> selectUserRole(@Param("userId") String userId, @Param("roleVO") RoleVO roleVO);

    RoleVO selectByRoleName(@Param("roleName") String roleName);

    int judgeRoleExistsByRoleName(@Param("roleName") String roleName);

    /**
     * 通过用户名获取角色列表
     *
     * @param userId
     * @return
     */
    List<RoleVO> selectRolesByUserId(@Param("userId") String userId);

}