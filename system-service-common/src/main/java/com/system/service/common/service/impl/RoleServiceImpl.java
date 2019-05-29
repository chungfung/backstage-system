package com.system.service.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.system.framework.page.PageBean;
import com.system.framework.page.PageParam;
import com.system.service.common.domain.RoleVO;
import com.system.service.common.mapper.RoleMapper;
import com.system.service.common.service.IRoleService;
import com.system.service.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: 丰涌
 * @Date: 2018/10/15 18:08
 * @Description:
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageBean<RoleVO> queryRoleList(RoleVO roleVO, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getNumPerPage());
        List<RoleVO> list = roleMapper.select(roleVO);
        PageBean<RoleVO> pageBean = new PageBean<RoleVO>(list);

        return pageBean;
    }

    @Transactional
    @Override
    public String editRole(RoleVO roleVO) {
        if (StringUtils.isEmpty(roleVO.getRoleId())) {
            if(roleMapper.judgeRoleExistsByRoleName(roleVO.getRoleName())>0){
                return "角色名已存在";
            }
            roleVO.setCreateUser(ShiroUtils.getUserName());
            roleVO.setCreateTime(new Date());
            roleVO.setUpdateUser("");
            roleVO.setUpdateTime(null);
            return roleMapper.insert(roleVO)>0?null:"保存失败";
        } else {
            roleVO.setUpdateUser(ShiroUtils.getUserName());
            roleVO.setUpdateTime(new Date());
            return roleMapper.update(roleVO)>0?null:"更新失败";
        }
    }

    @Transactional
    @Override
    public String delete(String roleId) {
        return roleMapper.deleteByRoleId(roleId)>0?null:"删除失败";
    }

    @Override
    public List<Map<String, Object>> queryUserRole(String userId,RoleVO roleVO) {
        List<Map<String,Object>> list = roleMapper.selectUserRole(userId,roleVO);
        return list;
    }

    @Override
    public RoleVO queryRoleDetailByRoleId(String roleId){
        return roleMapper.selectByRoleId(roleId);
    }

    @Override
    public RoleVO queryByRoleName(String roleName) {
       return roleMapper.selectByRoleName(roleName);
    }
}
