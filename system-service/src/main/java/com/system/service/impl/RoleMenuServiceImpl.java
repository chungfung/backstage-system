package com.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.facade.service.IRoleMenuService;
import com.system.facade.vo.MenuVO;
import com.system.facade.vo.RoleMenuVO;
import com.system.service.mapper.RoleMenuMapper;
import com.system.service.mapper.UserRoleMapper;
import com.system.service.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈葳 on 2018/10/19.
 */
@Service
public class RoleMenuServiceImpl implements IRoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public PageBean<RoleMenuVO> queryByRoleId(String roleId, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getNumPerPage());
        List<RoleMenuVO> list = roleMenuMapper.selectByRoleId(roleId);
        PageBean<RoleMenuVO> pageBean = new PageBean<>(list);
        return pageBean;
    }

    @Transactional
    @Override
    public String setMenu(String roleId,String menuIds) {
        int result = 0;
        if(StringUtil.isNotEmpty(menuIds)){
            String[] menuArr = menuIds.split(",");
            List<String> menuList = new ArrayList<>();
            for (String mId : menuArr) {
                RoleMenuVO rm = new RoleMenuVO();
                rm.setRoleId(roleId);
                rm.setMenuId(mId);
                rm.setCreateUser(ShiroUtils.getUserName());
                rm.setUpdateUser(ShiroUtils.getUserName());
                menuList.add(mId);
                roleMenuMapper.insert(rm);
            }
            result = roleMenuMapper.deleteNotIn(roleId,menuList);
        }else{
            result =  roleMenuMapper.deleteByRoleId(roleId);
        }
        return null;
    }

    @Override
    public List<MenuVO> selectByUserId(String userId) {
        return roleMenuMapper.selectByUserId(userId);
    }
}
