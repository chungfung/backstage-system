package com.system.admin.controller;

import com.system.framework.page.PageBean;
import com.system.framework.page.PageParam;
import com.system.framework.controller.BaseController;
import com.system.service.common.domain.RoleVO;
import com.system.service.common.service.IRoleMenuService;
import com.system.service.common.service.IRoleService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author: cw
 * @Date: 2018/10/13 11:11
 * @Description:
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    public static final String roleView = "role/list";
    public static final String roleList= "role/listRole";
    public static final String roleDetail = "role/detail";

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRoleMenuService roleMenuService;

    @RequiresPermissions("role:list")
    @RequestMapping(value = "list")
    public String roleView(RoleVO roleVO, PageParam pageParam, HttpServletRequest req) {
        PageBean<RoleVO> pageBean = roleService.queryRoleList(roleVO,pageParam);
        req.setAttribute("pageBean",pageBean);
        return roleView;
    }

    @RequiresPermissions("user:setRole")
    @RequestMapping(value = "listRole")
    public String listRole(@RequestParam String userId, RoleVO roleVO, HttpServletRequest req) {
        List<Map<String,Object>> list = roleService.queryUserRole(userId,roleVO);
        req.setAttribute("list",list);
        return roleList;
    }

    @RequiresPermissions(value = {"role:add", "role:edit"}, logical = Logical.OR)
    @RequestMapping(value = "detail")
    public String roleDetail(String roleId, HttpServletRequest req) {
        if(roleId != null) {
            RoleVO roleVO = roleService.queryRoleDetailByRoleId(roleId);
            req.setAttribute("detail",roleVO);
        }
        return roleDetail;
    }

    @RequiresPermissions(value = {"role:add", "role:edit"}, logical = Logical.OR)
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ModelAndView save(@Valid RoleVO roleVO, HttpServletRequest req, BindingResult br) {
        if(br.hasErrors()){
            return jsonView(br.getAllErrors().get(0).getDefaultMessage());
        }
        return jsonView(roleService.editRole(roleVO),true);
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "delete")
    public ModelAndView delete(@RequestParam String roleId, HttpServletRequest req) {
       return jsonView(roleService.delete(roleId));
    }

    @RequiresPermissions("role:setMenu")
    @RequestMapping(value = "setMenu",method = RequestMethod.POST)
    public ModelAndView setMenu(String menuIds, @RequestParam String roleId, HttpServletRequest req) {
        return jsonView(roleMenuService.setMenu(roleId,menuIds),true);
    }
}
