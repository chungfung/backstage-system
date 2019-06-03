package com.system.admin.controller;

import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.framework.controller.BaseController;
import com.system.service.common.domain.SysDictTypeVO;
import com.system.service.common.service.ISysDictTypeService;
import com.system.service.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据字典信息
 * @author system
 */
@Controller
@RequestMapping("/dict")
public class DictTypeController extends BaseController {

    public static final String TYPE_LIST_VIEW = "dict/listType";
    public static final String TYPE_ADD_VIEW = "dict/typeAdd";
    public static final String TYPE_EDIT_VIEW = "dict/typeEdit";

    @Autowired
    private ISysDictTypeService dictTypeService;

    @RequestMapping(value = "list_type")
    public String userView(SysDictTypeVO sysDictTypeVO, PageParam pageParam, ModelMap modelMap) {
        PageBean<SysDictTypeVO> pageBean = dictTypeService.selectDictTypeList(sysDictTypeVO,pageParam);
        modelMap.put("pageBean",pageBean);
        return TYPE_LIST_VIEW;
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add")
    public String add() {
        return TYPE_ADD_VIEW;
    }

    /**
     * 新增保存字典类型
     */
    @PostMapping("/add")
    public ModelAndView addSave(SysDictTypeVO dict) {
        dict.setCreateUser(ShiroUtils.getUserName());
        try {
            return jsonView(dictTypeService.insertDictType(dict)>0?null:"新增失败",true);
        } catch (Exception e) {
            return jsonView(e.getMessage(), true);
        }
    }

    /**
     * 修改字典类型
     */
    @GetMapping("/edit")
    public String edit(Long dictId, ModelMap mmap) {
        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        return TYPE_EDIT_VIEW;
    }

    /**
     * 修改保存字典类型
     */
    @PostMapping("/edit")
    @ResponseBody
    public ModelAndView editSave(SysDictTypeVO dict) {
        dict.setUpdateUser(ShiroUtils.getUserName());
        return jsonView(dictTypeService.updateDictType(dict)>0?null:"修改失败", true);
    }

    @PostMapping("/remove")
    @ResponseBody
    public ModelAndView remove(String dictId) {
        try {
            return jsonView(dictTypeService.deleteDictTypeByIds(dictId)>0?null:"删除失败");
        } catch (Exception e) {
            return jsonView(e.getMessage());
        }
    }


}
