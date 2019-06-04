package com.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.facade.service.IUserRoleService;
import com.system.facade.vo.UserRoleVO;
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
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public PageBean<UserRoleVO> queryByUserId(String userId, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getNumPerPage());
        List<UserRoleVO> list = userRoleMapper.selectByUserId(userId);
        PageBean<UserRoleVO> pageBean = new PageBean<>(list);
        return pageBean;
    }

    @Transactional
    @Override
    public String setRole(String userId,String roleIds) {
        int result = 0;
        if(StringUtil.isNotEmpty(roleIds)){
            String[] roleArr = roleIds.split(",");
            List<String> roleList = new ArrayList<>();
            for (String rId : roleArr) {
                UserRoleVO ru = new UserRoleVO();
                ru.setUserId(userId);
                ru.setRoleId(rId);
                ru.setCreateUser(ShiroUtils.getUserName());
                ru.setUpdateUser(ShiroUtils.getUserName());
                roleList.add(rId);
                userRoleMapper.insert(ru);
            }
            result = userRoleMapper.deleteNotIn(userId, roleList);
        } else {
            result = this.delete(userId, null);
        }
        return null;
    }

    @Override
    public List<UserRoleVO> queryByUserId(String userId) {
        return userRoleMapper.selectByUserId(userId);
    }

    @Transactional
    @Override
    public int delete(String userId, String roleId) {
        return userRoleMapper.delete(userId,roleId);
    }
}
