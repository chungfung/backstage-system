package com.system.service.common.service.impl;

import com.system.common.utils.StringUtils;
import com.system.service.common.domain.MenuVO;
import com.system.service.common.mapper.MenuMapper;
import com.system.service.common.service.IMenuService;
import com.system.service.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author: 陈葳
 * @Date: 2018/10/15 18:08
 * @Description:
 */
@Service
public class MenuServiceImpl implements IMenuService{

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVO> queryMenuList(MenuVO menuVO) {
        List<MenuVO> list = menuMapper.select(menuVO);
        return list;
    }

    @Override
    public MenuVO queryMenuById(String menuId) {
        return menuMapper.selectByMenuId(menuId);
    }

    @Transactional
    @Override
    public String editMenu(MenuVO menuVO) {
        if (StringUtils.isEmpty(menuVO.getMenuId())) {
            if(menuVO.getParentId()==null){
                menuVO.setParentId(0);
            }
            menuVO.setCreateUser(ShiroUtils.getUserName());
            menuVO.setUpdateUser(ShiroUtils.getUserName());
            return menuMapper.insert(menuVO)>0?null:"保存成功";
        } else {
            menuVO.setUpdateUser(ShiroUtils.getUserName());
            menuVO.setUpdateTime(new Date());
            return menuMapper.update(menuVO)>0?null:"更新失败";
        }
    }

    @Override
    public String delete(String menuId) {
        return menuMapper.deleteByMenuId(menuId)>0?null:"删除失败";
    }

    @Override
    public List<Map<String,Object>> queryRoleMenu(String roleId) {
        List<Map<String,Object>> list = menuMapper.selectRoleMenu(roleId);
        return list;
    }

    @Override
    public MenuVO queryByName(String name) {
       return menuMapper.selectByMenuName(name);
    }

    @Override
    public Set<String> queryPermsByUserId(String userId) {
        List<MenuVO> perms = menuMapper.selectMenusByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (MenuVO perm : perms) {
            if (StringUtils.isNotEmpty(perm.getPerms())) {
                permsSet.addAll(Arrays.asList(perm.getPerms().trim().split(",")));
            }
        }
        return permsSet;
    }
}
