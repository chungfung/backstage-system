package com.system.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.system.framework.controller.BaseController;
import com.system.facade.vo.MenuVO;
import com.system.facade.service.IMenuService;
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
 * @Author: 陈葳
 * @Date: 2018/10/13 11:11
 * @Description:
 */
@Controller
@RequestMapping("menu")
public class MenuController extends BaseController {

    public static final String view = "menu/list";
    public static final String menuView = "menu/listMenu";
    public static final String menuDetail = "menu/detail";
    public static final String menuLoad = "menu/menuTree";

    @Autowired
    private IMenuService menuService;

    @RequiresPermissions("menu:list")
    @RequestMapping(value = "list")
    public String menuView() {
        return view;
    }

    @RequiresPermissions("menu:list")
    @RequestMapping(value = "listMenu")
    public String menuViewPartion(MenuVO menuVO, HttpServletRequest req) {
        List<MenuVO> list = menuService.queryMenuList(menuVO);
        req.setAttribute("list", list);
        return menuView;
    }

    @RequiresPermissions("role:setMenu")
    @RequestMapping(value = "load")
    public String loadMenu(String roleId, HttpServletRequest req) {
        List<Map<String,Object>> list = menuService.queryRoleMenu(roleId);
        req.setAttribute("menus", JSONArray.parseArray(JSON.toJSONString(list)));
        return menuLoad;
    }

    @RequiresPermissions(value = {"menu:add", "menu:edit"}, logical = Logical.OR)
    @RequestMapping(value = "detail")
    public String menuDetail(String menuId,String systemType,String parentId,HttpServletRequest req) {
        if(menuId != null) {
            MenuVO menuVO = menuService.queryMenuById(menuId);
            req.setAttribute("detail",menuVO);
        }
        req.setAttribute("parentId",parentId);
        req.setAttribute("systemType",systemType);
        return menuDetail;
    }

    @RequiresPermissions(value = {"menu:add", "menu:edit"}, logical = Logical.OR)
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ModelAndView save(@Valid MenuVO menuVO, HttpServletRequest req, BindingResult br) {
        if(br.hasErrors()){
            return jsonView(br.getAllErrors().get(0).getDefaultMessage());
        }
        return jsonView(menuService.editMenu(menuVO),null,"listMenu",true);
    }

    @RequiresPermissions("menu:delete")
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam String menuId, HttpServletRequest req) {
       return jsonView(menuService.delete(menuId),null,"listMenu",false);
    }
}
