package com.system.service.common.service;

import com.system.framework.page.PageBean;
import com.system.framework.page.PageParam;
import com.system.service.common.domain.UserVO;

/**
 * @Author: 丰涌
 * @Date: 2018/10/15 18:08
 * @Description:
 */
public interface IUserService {

    PageBean<UserVO> queryUserList(UserVO userVO, PageParam pageParam);

    UserVO queryUserByUserId(String userId);

    UserVO queryUserByName(String uesrName);

    String updateByUserId(UserVO userVO);

    String editUser(UserVO userVO);

    String delete(String userId);
}
