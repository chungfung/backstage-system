package com.system.service.common.mapper;

import com.system.service.common.domain.UserRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {

    List<UserRoleVO> selectByUserId(@Param("userId") String userId);

    List<UserRoleVO> selectByRoleId(@Param("roleId") String roleId);

    int delete(@Param("userId") String userId, @Param("roleId") String roleId);

    int deleteNotIn(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);

    int insert(UserRoleVO userRoleVO);
}