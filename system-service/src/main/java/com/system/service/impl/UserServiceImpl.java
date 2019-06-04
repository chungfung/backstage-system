package com.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.system.common.utils.EncryptionUtils;
import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.facade.service.IUserService;
import com.system.facade.vo.UserVO;
import com.system.service.mapper.UserMapper;
import com.system.service.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 陈葳 on 2018/10/17.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageBean<UserVO> queryUserList(UserVO userVo, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getNumPerPage());
        List<UserVO> list = userMapper.select(userVo);
        PageBean<UserVO> pageBean = new PageBean<>(list);
        return pageBean;

    }

    @Override
    public UserVO queryUserByUserId(String userId) {
        return userMapper.selectByUserId(userId);
    }

    @Override
    public UserVO queryUserByName(String userName) {
       return userMapper.selectByLoginName(userName);
    }

    @Transactional
    @Override
    public String updateByUserId(UserVO userVO) {
        return userMapper.updateByUserId(userVO)>0?null:"更新失败";
    }

    @Transactional
    @Override
    public String editUser(UserVO userVO) {
        if (StringUtils.isEmpty(userVO.getUserId())) {
            if(userMapper.judgeUserExistsByLoginName(userVO.getLoginName())>0){
                return "登录名已经存在";
            }
            userVO.setPassword(EncryptionUtils.encrypt(userVO.getLoginName(), ""));
            userVO.setCreateUser(ShiroUtils.getUser().getLoginName());
            userVO.setUpdateUser(ShiroUtils.getUser().getLoginName());
            userVO.setAuthTicket(EncryptionUtils.encrypt(userVO.getUserId(), userVO.getLoginName()));
            return userMapper.insert(userVO)>0?null:"保存失败";
        } else {
            userVO.setUpdateUser(ShiroUtils.getUser().getLoginName());
            return userMapper.updateByUserId(userVO)>0?null:"更新失败";
        }
    }

    @Transactional
    @Override
    public String delete(String userId) {
        return userMapper.deleteByUserId(userId)>0?null:"删除失败";
    }
}
