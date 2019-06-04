package com.system.admin.controller;

import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.common.utils.EncryptionUtils;
import com.system.framework.controller.BaseController;
import com.system.facade.vo.CodeVO;
import com.system.facade.vo.UserVO;
import com.system.facade.service.ICodeService;
import com.system.facade.service.IUserRoleService;
import com.system.facade.service.IUserService;
import com.system.service.utils.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 丰涌
 * @Date: 2018/10/13 11:11
 * @Description:
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    public static final String userView = "user/list";
    public static final String userDetail = "user/detail";
    private final static String userInfoView = "user/info";

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private ICodeService codeService;

    @RequiresPermissions("user:list")
    @RequestMapping(value = "list")
    public String userView(UserVO userVO, PageParam pageParam, HttpServletRequest req) {
        PageBean<UserVO> pageBean = userService.queryUserList(userVO,pageParam);
        req.setAttribute("pageBean",pageBean);
        return userView;
    }

    @RequiresPermissions(value = {"user:add", "user:edit"}, logical = Logical.OR)
    @RequestMapping(value = "detail")
    public String userDetail(String userId, HttpServletRequest req) {
        if(userId != null) {
            UserVO userVO = userService.queryUserByUserId(userId);
            req.setAttribute("detail",userVO);
            req.setAttribute("stationList",userVO.getRoadNo()==null?null: codeService.queryStationByType(userVO.getRoadNo(), 1));
        }
        List<CodeVO> roadList = codeService.queryRoad(null);
        req.setAttribute("roadList", roadList);
        return userDetail;
    }

    @RequiresPermissions(value = {"user:add", "user:edit"}, logical = Logical.OR)
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ModelAndView save(@Valid UserVO userVO, BindingResult br, HttpServletRequest req) {
       if(br.hasErrors()){
           return jsonView(br.getAllErrors().get(0).getDefaultMessage());
       }
       return jsonView(userService.editUser(userVO),true);
    }

    /**
     * 删除用户
     * @return
     */
    @RequiresPermissions("user:delete")
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam String userId, HttpServletRequest req) {
       return jsonView(userService.delete(userId));
    }

    /**
     * 分配角色
     * @return
     */
    @RequiresPermissions("user:setRole")
    @RequestMapping(value = "setRole",method = RequestMethod.POST)
    public ModelAndView setRole(String roleIds, @RequestParam String userId, HttpServletRequest req) {
        return jsonView(userRoleService.setRole(userId, roleIds),true);
    }

    /**
     * 修改当前用户密码
     * @return
     */
    @RequiresPermissions("user:editPwd")
    @RequestMapping(value = "/editPwd", method = RequestMethod.GET)
    public ModelAndView editPwd() {
        ModelAndView view = new ModelAndView("user/editPwd");
        return view;
    }

    /**
     * 修改当前用户密码
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return
     */
    @RequiresPermissions("user:editPwd")
    @RequestMapping(value = "/savePwd", method = RequestMethod.POST)
    public ModelAndView savePwd(@RequestParam String oldPwd, @RequestParam String newPwd) {
        UserVO user = ShiroUtils.getUser();
        if (!user.getPassword().equals(EncryptionUtils.encrypt(user.getLoginName(), oldPwd))) {
            return super.jsonView("操作失败，旧密码错误！");
        }
        if (oldPwd.equals(newPwd)) {
            return super.jsonView("操作失败，新密码不能和旧密码相同！");
        }
        //更新用户密码
        UserVO userVO = new UserVO();
        userVO.setUserId(String.valueOf(user.getUserId()));
        userVO.setPassword(EncryptionUtils.encrypt(user.getLoginName(), newPwd));
        return super.jsonView(userService.updateByUserId(userVO), true);
    }

    /**
     * 重置密码用户密码
     *
     * @param userId 用户ID
     * @return
     */
    @RequiresPermissions("user:resetPwd")
    @RequestMapping(value = "/resetPwd")
    public ModelAndView restPwd(@RequestParam String userId) {
        UserVO userVO = userService.queryUserByUserId(userId);
        //重置用户密码
        userVO.setPassword(EncryptionUtils.encrypt(userVO.getLoginName(), ""));
        return super.jsonView(userService.updateByUserId(userVO));
    }

    /**
     * 查看用户的基本信息
     *
     * @return
     */
    @RequiresPermissions("user:info")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info(HttpServletRequest req) {
        UserVO user = ShiroUtils.getUser();
        //获取用户最新信息
        if (user != null) {
            UserVO userVO = userService.queryUserByUserId(String.valueOf(user.getUserId()));
            req.setAttribute("user", user);
        }
        return userInfoView;
    }

    @RequestMapping(value = "queryInUseRoads")
    @ResponseBody
    public List queryInUseRoadsByAreaNo() {
        List all = new ArrayList();
        //添加返回默认选项
        all.add(Arrays.asList("", "--请选择--"));
        //查询使用中的路段信息
        List<CodeVO> roadList = codeService.queryRoad(null);
        //dwz要求将每个option封装成对应的list
        for (CodeVO roadVO : roadList) {
            all.add(Arrays.asList(roadVO.getNo(), roadVO.getName()));
        }
        return all;
    }

    @RequestMapping(value = "queryInUseStationByRoadNo")
    @ResponseBody
    public List queryInUseStationByRoadNo(@RequestParam("roadNo") String roadNo) {
        List all = new ArrayList();
        //添加返回默认选项
        all.add(Arrays.asList("", "--请选择--"));
        if (!StringUtils.isEmpty(roadNo)) {
            //查询使用中的收费站信息
            List<CodeVO> stationList = codeService.queryStationByType(roadNo,1);
            //dwz要求将每个option封装成对应的list
            for (CodeVO stationVO : stationList) {
                all.add(Arrays.asList(stationVO.getNo(), stationVO.getName()));
            }
        }
        return all;
    }

}
